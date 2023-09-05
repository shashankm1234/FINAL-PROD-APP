package com.example.ProdManagement.Service;

import com.example.ProdManagement.Data.Entity.ProductData;
import com.example.ProdManagement.Model.Request.ProductRequest;
import com.example.ProdManagement.Model.Response.ProductResponse;
import com.example.ProdManagement.Model.Response.ResponseClass;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
public interface ProductService {

    ResponseEntity<ProductResponse> addingProduct(ProductRequest productRequest);

    ResponseEntity<List<ResponseClass>> getAllProducts();
    ResponseEntity<ResponseClass> getProductById(Integer productCode);

    ResponseEntity<ProductResponse> deleteProduct(Integer productCode);
    ResponseEntity<String> updateProductData(ProductRequest productRequest, Integer productCode);

    public ByteArrayInputStream load();
}