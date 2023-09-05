package com.example.ProdManagement.Data;

import com.example.ProdManagement.Data.Entity.ProductData;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class CsvHelper {
    public static ByteArrayInputStream productsToCSV(List<ProductData> products) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
            for (ProductData productData : products) {
                List<String> data = Arrays.asList(
                        String.valueOf(productData.getProductCode()),
                        productData.getProductName(),
                        productData.getProductDescription(),
                        productData.getProductType(),
                        String.valueOf(productData.getIsActive())
//                        String.valueOf(productData.getCreatedAt()),
//                        String.valueOf(productData.getModifiedAt())
                );
                csvPrinter.printRecord(data);
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Fail to import data to CSV file");
        }
    }
}
