package com.example.ProdManagement.Service.impl;

import com.example.ProdManagement.Data.CsvHelper;
import com.example.ProdManagement.Data.Entity.ProductData;
import com.example.ProdManagement.Data.Repository.ProductRepo;
import com.example.ProdManagement.Model.Request.ProductRequest;
import com.example.ProdManagement.Model.Response.ProductResponse;
import com.example.ProdManagement.Model.Response.ResponseClass;
import com.example.ProdManagement.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepo productRepo;

    @Override
    public ResponseEntity<ProductResponse> addingProduct(ProductRequest productRequest) {
        ProductResponse productResponse=new ProductResponse();
        ProductData productData=new ProductData();
            productData.setProductName(productRequest.getProductName());
            productData.setProductDescription(productRequest.getProductDesc());
            if (productRequest.getProductType().equalsIgnoreCase("Frozen") || productRequest.getProductType().equalsIgnoreCase("Not Frozen")){
                productData.setProductType(productRequest.getProductType());
            }
            else {
                throw new RuntimeException();
            }
            productData.setIsActive(true);
            productData.setCreatedAt(new Date());
            productData.setModifiedAt(new Date());

            productRepo.save(productData);

            productResponse.setMessage("Product Created Successfully");
            return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<ResponseClass>> getAllProducts() {
        List<ResponseClass> allResponse = new ArrayList<>();
        List<ProductData> productData = productRepo.findAll();
        for(ProductData productsData : productData){
            ResponseClass responseClass = new ResponseClass();
            responseClass.setProductCode(productsData.getProductCode());
            responseClass.setProductName(productsData.getProductName());
            responseClass.setProductDesc(productsData.getProductDescription());
            responseClass.setProductType(productsData.getProductType());
            if(productsData.getIsActive() == true){
                allResponse.add(responseClass);
            }
        }
        return new ResponseEntity<>(allResponse,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseClass> getProductById(Integer productCode) {
       ProductData productData = productRepo.getId(productCode);
           ResponseClass responseClass = new ResponseClass();
           if (productData.getIsActive() == true) {
           responseClass.setProductDesc(productData.getProductDescription());
           responseClass.setProductType(productData.getProductType());
           responseClass.setProductCode(productData.getProductCode());
           responseClass.setProductName(productData.getProductName());
               return new ResponseEntity<>(responseClass, HttpStatus.OK);
           }
           else {
               return new ResponseEntity<>(responseClass, HttpStatus.NOT_FOUND);
           }
    }

    @Override
    public ResponseEntity<ProductResponse> deleteProduct(Integer productCode) {
        try {
            ProductResponse productResponse =new ProductResponse();
            ProductData existingData = productRepo.getId(productCode);
            existingData.setIsActive(false);
            productRepo.save(existingData);
            productResponse.setMessage("Product Deleted Successfully!");
            return new ResponseEntity<>(productResponse, HttpStatus.OK);
        }
        catch (Exception e){
            throw new RuntimeException();
        }

    }

    @Override
    public ResponseEntity<String> updateProductData(ProductRequest productRequest, Integer productCode) {
                ProductData existingData = productRepo.getId(productCode);
                existingData.setProductName(productRequest.getProductName());
                existingData.setProductDescription(productRequest.getProductDesc());
                if (productRequest.getProductType().equalsIgnoreCase("Frozen") ||  productRequest.getProductType().equalsIgnoreCase("Not Frozen")){
                    existingData.setProductType(productRequest.getProductType());
                }
                else{
                    throw new RuntimeException();
                }
                productRepo.save(existingData);
                return new ResponseEntity<String>("Product Updated Successfully!",HttpStatus.OK);
    }

    public List<ProductData> listAll() {
        return productRepo.findAll(Sort.by("name").ascending());
    }

    public ByteArrayInputStream load() {
        List<ProductData> products = productRepo.findAll();
        ByteArrayInputStream data = CsvHelper.productsToCSV(products);
        return data;
    }
}