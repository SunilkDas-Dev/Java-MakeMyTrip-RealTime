

package com.pro.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "userregistration")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "mobile")
   // @Pattern(regexp = "^[0-9]{10}$", message = "Contact Number must be exactly 10 digits")
    private String mobileNumber;

    @CreationTimestamp
    @Column(name = "createdDate", updatable = false)
    private LocalDate insertedDate;

    @UpdateTimestamp
    @Column(name = "updatedDate")
    private LocalDate updatedDate;
}
