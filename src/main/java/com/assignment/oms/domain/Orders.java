package com.assignment.oms.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "OrderId", unique = true, nullable = false, updatable = false)
    private Long orderId;

    @Column(name = "UserId", updatable = false, nullable = false)
    private Long userId;

    @Column(name = "AddressId", nullable = false)
    private Long addressId;

    @Column(name = "Status", nullable = false)
    private String status;

    @Column(name = "DueDate", nullable = false)
    private Timestamp dueDate;

    @Column(name = "Total", nullable = false)
    private Double total;

    @Column(name = "UserCreated", nullable = false)
    private Long userCreated;

    @Column(name = "DateCreated", nullable = false)
    private Timestamp dateCreated;

    @Column(name = "UserModified")
    private Long userModified;

    @Column(name = "DateModified")
    private Timestamp dateModified;
}
