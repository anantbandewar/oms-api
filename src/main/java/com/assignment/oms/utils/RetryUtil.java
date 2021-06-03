package com.assignment.oms.utils;

import com.assignment.oms.model.DelayConfig;
import com.assignment.oms.model.RetryConfig;
import lombok.Builder;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

@Component
@Scope(value = "prototype")
@Data
@Builder
public class RetryUtil {

    private final long DEFAULT_MAX_ATTEMPTS = 1;

    private RetryConfig init(RetryConfig retryConfig) {
        if (retryConfig == null)
            retryConfig = RetryConfig
                    .builder()
                    .maxAttempts(DEFAULT_MAX_ATTEMPTS)
                    .retryOn(new HashSet<>(Arrays.asList(Exception.class)))
                    .build();

        if (retryConfig.getMaxAttempts() <= 0)
            retryConfig.setMaxAttempts(DEFAULT_MAX_ATTEMPTS);

        if (retryConfig.getRetryOn().isEmpty())
            retryConfig.setRetryOn(new HashSet<>(Arrays.asList(Exception.class)));

        return retryConfig;
    }

    public <T> T executeWithRetry(Callable<T> action, RetryConfig retryConfig) throws Exception {
        retryConfig = init(retryConfig);
        long retryCount = retryConfig.getMaxAttempts();
        DelayConfig delayConfig = retryConfig.getDelayConfig();
        long delay = 0L;
        boolean delayRetries = false;
        if (delayConfig != null && delayConfig.getTimeUnit() != null && delayConfig.getDelay() > 0) {
            delayRetries = true;
            delay = delayConfig.getDelay();
        }
        boolean isRetry = false;
        Exception exception = null;
        do {
            try {
                retryCount--;
                var result = action.call();
                if (result == null && retryConfig.isRetryIfNull()) {
                    isRetry = retryConfig.isRetryIfNull();
                }
                else return result;
            } catch (HttpServerErrorException e) {
                exception = e;
                if (e.getStatusCode().equals(HttpStatus.GATEWAY_TIMEOUT) || isRetryContinueAfterException(e, retryConfig))
                    isRetry = true;
                else
                    throw exception;
            } catch (Exception retriesException) {
                exception = retriesException;
                if (isRetryContinueAfterException(retriesException, retryConfig)) {
                    isRetry = true;
                } else
                    throw exception;
            }
            finally {
                if (delayRetries && isRetry) {
                    if (retryConfig.isIncreaseDelay() && retryConfig.getDelayExponent() > 0 && (retryConfig.getMaxAttempts() != (retryCount + 1L))) {
                        delay = delay * retryConfig.getDelayExponent();
                    }
                    delayConfig.getTimeUnit().sleep(delay);
                }
            }
        } while (isRetry && retryCount > 0);
        throw exception;
    }

    private boolean isRetryContinueAfterException(Exception retriesException, RetryConfig retryConfig) {
        return !retryConfig.getRetryOn()
                .stream()
                .filter(letClass -> letClass == retriesException.getClass())
                .collect(Collectors.toSet())
                .isEmpty();
    }
}