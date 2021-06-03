package com.assignment.oms.interceptors;

import com.assignment.oms.constants.UserRole;
import com.assignment.oms.exception.HttpHeaderException;
import com.assignment.oms.model.CoreAttributes;
import com.assignment.oms.model.Error;
import com.assignment.oms.model.ErrorCodesProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.assignment.oms.constants.Properties.*;
import static java.util.function.Function.identity;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Component
@RequiredArgsConstructor
public class RequestInterceptor implements HandlerInterceptor {

    private final ErrorCodesProperties errorCodesProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        boolean areValidHeaders;

        try {
            List<String> methodList = new ArrayList<>();
            methodList.add(POST.toString());
            methodList.add(PATCH.toString());
            areValidHeaders = areValidHeaders(request, methodList);
            if (areValidHeaders) {
                String url = (request.getQueryString() != null) ? String.join("", request.getServletPath(), "?", request.getQueryString()) : request.getServletPath();
                Map<String, String> headersPresents = Collections.list(request.getHeaderNames())
                        .stream()
                        .collect(Collectors.toMap(identity(), request::getHeader));
                CoreAttributes coreAttributes = getRequestAttributesFromHeader(headersPresents);
                coreAttributes.setApi(url);
                coreAttributes.setStartTime(System.currentTimeMillis());
                request.setAttribute("coreAttributes", coreAttributes);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return areValidHeaders;
    }

    private boolean areValidHeaders(HttpServletRequest request, List<String> methodList) throws IOException {
        Set<String> requiredHeaders = new HashSet<>();
        requiredHeaders.add(CORRELATION_ID);
        requiredHeaders.add(USER_ID);
        requiredHeaders.add(USER_ROLE);

        Set<String> headersPresent = Collections.list(request.getHeaderNames())
                .stream()
                .filter(requiredHeaders::contains)
                .collect(Collectors.toSet());

        List<Error> errors = new ArrayList<>();

        if (headersPresent.size() != requiredHeaders.size()) {
            requiredHeaders.removeAll(headersPresent);
            Error error = new Error();
            error.setCode(errorCodesProperties.getMissingHeaderErrorCode());
            error.setMessage(String.join(" ", String.join(",", new ArrayList<>(requiredHeaders)), errorCodesProperties.getMissingHeaderErrorMessage()));
            errors.add(error);
        }

        Map<String, String> invalidHeaderValues = headersPresent.stream()
                .filter(header -> !(StringUtils.hasText(request.getHeader(header)) && isValidHeader(header, request.getHeader(header))))
                .collect(Collectors.toMap(identity(), request::getHeader));

        if (!invalidHeaderValues.isEmpty()) {
            Error error = new Error();
            error.setCode(errorCodesProperties.getInvalidHeaderErrorCode());
            error.setMessage(MessageFormat.format(errorCodesProperties.getInvalidHeaderErrorMessage(), String.join(",", getInvalidHeaderMessage(invalidHeaderValues))));
            errors.add(error);
        }

        if (!errors.isEmpty())
            throw new HttpHeaderException(errors);

        return true;
    }

    private List getInvalidHeaderMessage(Map<String, String> invalidHeaderValues) {
        List<String> headerKey = new ArrayList();
        for (String key : invalidHeaderValues.keySet()) {
            headerKey.add(key);
        }
        return headerKey;
    }

    private boolean isValidHeader(String header, String headerValue) {
        if (CORRELATION_ID.equals(header))
            return isValidUUID(headerValue);
        else if (USER_ROLE.equals(header))
            return UserRole.of(headerValue) != null;
        else
            return true;
    }

    private boolean isValidUUID(String value) {
        if (value.length() == 36)
            try {
                UUID.fromString(value);
                return true;
            } catch (IllegalArgumentException e) {
                return false;
            }
        else return false;
    }

    private CoreAttributes getRequestAttributesFromHeader(Map<String, String> httpHeaders) {
        CoreAttributes coreAttributes = new CoreAttributes();
        coreAttributes.setCorrelationId(httpHeaders.get(CORRELATION_ID));
        coreAttributes.setUserId(Long.parseLong(httpHeaders.get(USER_ID)));
        coreAttributes.setRole(httpHeaders.get(USER_ROLE));
        return coreAttributes;
    }
}
