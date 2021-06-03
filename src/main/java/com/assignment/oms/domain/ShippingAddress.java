package com.assignment.oms.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ShippingAddress")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ShippingAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "AddressId", unique = true, nullable = false, updatable = false)
    private Long addressId;

    @Column(name = "UserId", updatable = false, nullable = false)
    private Long userId;

    @Column(name = "Type", nullable = false)
    private String type;

    @Column(name = "AddressLine1", nullable = false)
    private String addressLine1;

    @Column(name = "AddressLine2")
    private String addressLine2;

    @Column(name = "City", nullable = false)
    private String city;

    @Column(name = "State", nullable = false)
    private String state;

    @Column(name = "Country", nullable = false)
    private String country;

    @Column(name = "PostalCode", nullable = false)
    private String postalCode;
}
