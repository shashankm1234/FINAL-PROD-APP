package com.example.ProdManagement.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseClass {
    private Integer productCode;
    private String productName;
    private String productDesc;
    private String productType;

}