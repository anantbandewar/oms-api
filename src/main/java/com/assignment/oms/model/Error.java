package com.assignment.oms.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Data
@ToString
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Error implements Serializable {

    @JsonProperty("code")
    private String code;

    @JsonProperty("message")
    private String message;
}
