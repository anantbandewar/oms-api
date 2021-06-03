package com.assignment.oms.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "User")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "UserId", unique = true, nullable = false, updatable = false)
    private Long userId;

    @Column(name = "FirstName", updatable = false, nullable = false)
    private String firstName;

    @Column(name = "LastName", updatable = false, nullable = false)
    private String lastName;

    @Column(name = "Mobile", nullable = false)
    private String mobile;

    @Column(name = "Email", nullable = false)
    private String email;
}
