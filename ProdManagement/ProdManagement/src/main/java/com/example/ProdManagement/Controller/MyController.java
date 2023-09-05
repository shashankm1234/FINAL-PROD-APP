package com.example.ProdManagement.Controller;

import com.example.ProdManagement.Model.Request.ProductRequest;
import com.example.ProdManagement.Model.Response.ProductResponse;
import com.example.ProdManagement.Model.Response.ResponseClass;
import com.example.ProdManagement.Service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.util.List;

@RestController
public class MyController {

    @Autowired
    private ProductService productService;

    @PostMapping("/api/products")
    public ResponseEntity<ProductResponse> creatingProduct(@RequestBody ProductRequest productRequest){
        return productService.addingProduct(productRequest);
    }

    @GetMapping("/api/products")
    public ResponseEntity<List<ResponseClass>> gettingAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/api/products/{id}")
    public ResponseEntity<ResponseClass> gettingProductById(@PathVariable("id") Integer productCode){
        return productService.getProductById(productCode);
    }

//    @GetMapping("/api/products/csvexport")
//    public void exportCSV(ProductResponse productResponse) throws Exception {
//        //set file name and content type
//        String filename = "Employee-data.csv";
//        productResponse.setContentType("text/csv");
//        productResponse.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"");
//        //create a csv writer
//        StatefulBeanToCsv<ProductData> writer = new StatefulBeanToCsvBuilder<ProductData>(productResponse.getWriter())
//                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).withSeparator(CSVWriter.DEFAULT_SEPARATOR).withOrderedResults(false)
//                .build();

//        //write all employees data to csv file
//        writer.write(productService.findAll());
//
//    }

    @GetMapping("/api/products/csvexport")
    public ResponseEntity<InputStreamResource> getFile() {
        String filename = "products.csv";
        InputStreamResource file = new InputStreamResource(productService.load());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }

    @PutMapping("/api/products/{id}")
    public ResponseEntity<String> updatingProduct(@PathVariable("id") Integer productCode, @RequestBody ProductRequest productRequest){
        return productService.updateProductData(productRequest,productCode);
    }

    @DeleteMapping("/api/products/{id}")
    public ResponseEntity<ProductResponse> deletingProduct(@PathVariable("id") Integer productCode){
        return productService.deleteProduct(productCode);
    }
}