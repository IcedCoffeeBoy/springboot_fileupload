package com.spring.fileupload.filecontent.service;

import com.spring.fileupload.filecontent.service.converters.CSVToProductListConverter;
import com.spring.fileupload.filecontent.database.FileRecord;
import com.spring.fileupload.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Manages the content of the file
 */
@Service
@RequiredArgsConstructor
public class FileContentService {
    private final CSVToProductListConverter csvToProductListConverter = new CSVToProductListConverter();

    public List<Product> convertToProductList(MultipartFile file, FileRecord record) {
        List<Product> products = csvToProductListConverter.convert(file);
        products.forEach(product -> product.setFileRecordId(record.getId()));
        return products;
    }

}
