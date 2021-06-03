package com.assignment.oms.exception;

import com.assignment.oms.model.Error;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class HttpHeaderException extends RuntimeException {

    private List<Error> errors;

    @Builder
    public HttpHeaderException(String message, List<Error> errors){
        super(message);
        this.errors = errors;
    }
}
