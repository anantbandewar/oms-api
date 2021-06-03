package com.assignment.oms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails {

    @JsonProperty("userId")
    private Long userId;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("mobile")
    private String mobile;

    @JsonProperty("email")
    private String email;
}
