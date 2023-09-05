package com.example.ProdManagement.Data.Repository;
import com.example.ProdManagement.Data.Entity.ProductData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<ProductData,Integer> {

    @Query(value = "SELECT name FROM productdata WHERE name = productName",nativeQuery = true)
    String ProductData(String productName);

    @Query(value = "SELECT * FROM productdata WHERE productCode = :id",nativeQuery = true)
    ProductData getId(Integer id);
}