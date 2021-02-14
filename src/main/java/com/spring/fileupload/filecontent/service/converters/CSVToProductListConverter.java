package com.spring.fileupload.filecontent.service.converters;


import com.spring.fileupload.common.exception.BusinessException;
import com.spring.fileupload.common.model.Converter;
import com.spring.fileupload.product.Product;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class CSVToProductListConverter implements Converter<MultipartFile, List<Product>> {
    private final CSVFormat csvFormat = CSVFormat.newFormat(',').withQuote('"').withFirstRecordAsHeader();
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M/d/yyyy H:mm");

    @Override
    public List<Product> convert(MultipartFile file) {
        try {
            InputStream inputStream = new BufferedInputStream(file.getInputStream());
            CSVParser csvParser = CSVParser.parse(inputStream, StandardCharsets.UTF_8, csvFormat);
            return csvParser.getRecords().stream().map(this::convertRecordToProduct).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Unable to read CSV format");
        }
        return null;
    }

    private Product convertRecordToProduct(CSVRecord record) {
        try {
            return Product.builder()
                    .invoiceNo(record.get("InvoiceNo"))
                    .stockCode(record.get("StockCode"))
                    .description(record.get("Description"))
                    .quantity(Long.parseLong(record.get("Quantity")))
                    .invoiceDate(LocalDateTime.from(dateTimeFormatter.parse(record.get("InvoiceDate"))))
                    .unitPrice(Double.parseDouble(record.get("UnitPrice")))
                    .customerID(record.get("CustomerID"))
                    .country(record.get("Country"))
                    .build();
        } catch (DateTimeParseException e) {
            log.error(e);
            throw new BusinessException("There is an incorrect date time format in line " + record.getRecordNumber());
        } catch (Exception e) {
            log.error(e);
            throw new BusinessException("There is an error in line " + record.getRecordNumber() + record.toString());
        }

    }
}

