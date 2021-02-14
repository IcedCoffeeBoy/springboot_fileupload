package com.spring.fileupload.product;

import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * List of specification for product
 */
public class ProductSpecifications {
    public static Specification<Product> startInvoiceDate(LocalDate date) {
        return (root, criteriaQuery, cb) -> {
            if (date == null) {
                return cb.conjunction();
            }
            return cb.greaterThanOrEqualTo(root.get(Product_.INVOICE_DATE), LocalDateTime.of(date, LocalTime.MIN));
        };
    }

    public static Specification<Product> endInvoiceDate(LocalDate date) {
        return (root, criteriaQuery, cb) -> {
            if (date == null) {
                return cb.conjunction();
            }
            return cb.lessThanOrEqualTo(root.get(Product_.INVOICE_DATE), LocalDateTime.of(date, LocalTime.MAX));
        };
    }

    public static Specification<Product> fromPrice(Double price) {
        return (root, criteriaQuery, cb) -> {
            if (price == null) {
                return cb.conjunction();
            }
            return cb.greaterThanOrEqualTo(root.get(Product_.UNIT_PRICE), price);
        };
    }

    public static Specification<Product> toPrice(Double price) {
        return (root, criteriaQuery, cb) -> {
            if (price == null) {
                return cb.conjunction();
            }
            return cb.lessThanOrEqualTo(root.get(Product_.UNIT_PRICE), price);
        };
    }

    public static Specification<Product> minQuantity(Long quantity) {
        return (root, criteriaQuery, cb) -> {
            if (quantity == null) {
                return cb.conjunction();
            }
            return cb.greaterThanOrEqualTo(root.get(Product_.QUANTITY), quantity);
        };
    }

    public static Specification<Product> maxQuantity(Long quantity) {
        return (root, criteriaQuery, cb) -> {
            if (quantity == null) {
                return cb.conjunction();
            }
            return cb.lessThanOrEqualTo(root.get(Product_.QUANTITY), quantity);
        };
    }

    public static Specification<Product> byInvoiceNo(String invoiceNo) {
        return (root, criteriaQuery, cb) -> {
            if (invoiceNo == null) {
                return cb.conjunction();
            }
            return cb.equal(root.get(Product_.INVOICE_NO), invoiceNo);
        };
    }

    public static Specification<Product> byStockCode(String stockCode) {
        return (root, criteriaQuery, cb) -> {
            if (stockCode == null) {
                return cb.conjunction();
            }
            return cb.equal(root.get(Product_.STOCK_CODE), stockCode);
        };
    }

    public static Specification<Product> byCustomerId(String customerId) {
        return (root, criteriaQuery, cb) -> {
            if (customerId == null) {
                return cb.conjunction();
            }
            return cb.equal(root.get(Product_.CUSTOMER_ID), customerId);
        };
    }

    public static Specification<Product> byCountry(String country) {
        return (root, criteriaQuery, cb) -> {
            if (country == null) {
                return cb.conjunction();
            }
            return cb.like(cb.lower(root.get(Product_.COUNTRY)), "%" + country.toLowerCase() + "%");
        };
    }


    public static Specification<Product> descriptionsContains(String keywords) {
        return (root, criteriaQuery, cb) -> {
            if (keywords == null) {
                return cb.conjunction();
            }
            return cb.like(cb.lower(root.get(Product_.DESCRIPTION)), "%" + keywords.toLowerCase() + "%");
        };
    }

}
