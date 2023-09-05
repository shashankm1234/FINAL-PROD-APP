package com.example.ProdManagement.Data.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Table(name="productdata")
@AllArgsConstructor
@NoArgsConstructor
public class ProductData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productCode")
    private Integer productCode;

    @Column(name = "name",nullable = false)
    private String productName;

    @Column(name = "description",nullable = false)
    private String productDescription;

    @Column(name = "type",nullable = false)
    private String productType;

    @Column(name = "isactive",nullable = false)
    private Boolean isActive;

//    @CreationTimestamp
    @Column(name = "createdAt")
    private Date createdAt;

//    @UpdateTimestamp
    @Column(name = "modifiedAt")
    private Date modifiedAt;
}