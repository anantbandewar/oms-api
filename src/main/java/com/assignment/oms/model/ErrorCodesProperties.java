package com.assignment.oms.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
@EqualsAndHashCode
public class ErrorCodesProperties {

    @Value("${One_or_more_parameters_required_is_missing_in_the_header_error_code}")
    private String missingHeaderErrorCode;

    @Value("${One_or_more_parameters_required_is_missing_in_the_header_error_message}")
    private String missingHeaderErrorMessage;

    @Value("${One_or_more_parameters_is_invalid_in_the_header_error_code}")
    private String invalidHeaderErrorCode;

    @Value("${One_or_more_parameters_is_invalid_in_the_header_error_message}")
    private String invalidHeaderErrorMessage;
}
