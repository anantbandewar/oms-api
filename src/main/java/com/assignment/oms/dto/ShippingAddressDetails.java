package com.assignment.oms.dto;

import com.assignment.oms.constants.AddressType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ShippingAddressDetails {

    @JsonProperty("addressId")
    private Long addressId;

    @JsonProperty("type")
    private AddressType type;

    @JsonProperty("addressLine1")
    private String addressLine1;

    @JsonProperty("addressLine2")
    private String addressLine2;

    @JsonProperty("city")
    private String city;

    @JsonProperty("state")
    private String state;

    @JsonProperty("country")
    private String country;

    @JsonProperty("postalCode")
    private String postalCode;
}
