package com.spring.fileupload.product;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import static com.spring.fileupload.product.ProductSpecifications.byCountry;
import static com.spring.fileupload.product.ProductSpecifications.byCustomerId;
import static com.spring.fileupload.product.ProductSpecifications.byInvoiceNo;
import static com.spring.fileupload.product.ProductSpecifications.byStockCode;
import static com.spring.fileupload.product.ProductSpecifications.descriptionsContains;
import static com.spring.fileupload.product.ProductSpecifications.endInvoiceDate;
import static com.spring.fileupload.product.ProductSpecifications.fromPrice;
import static com.spring.fileupload.product.ProductSpecifications.maxQuantity;
import static com.spring.fileupload.product.ProductSpecifications.minQuantity;
import static com.spring.fileupload.product.ProductSpecifications.startInvoiceDate;
import static com.spring.fileupload.product.ProductSpecifications.toPrice;


public class ProductSpecificationsBuilder {

    public Specification<Product> toSpecification(ProductFindRequest request) {
        Specification<Product> spec = Specification.where(null);
        if (request.getFromDate() != null) {
            spec = spec.and(startInvoiceDate(request.getFromDate()));
        }
        if (request.getToDate() != null) {
            spec = spec.and(endInvoiceDate(request.getToDate()));
        }
        if (request.getFromPrice() != null) {
            spec = spec.and(fromPrice(request.getFromPrice()));
        }
        if (request.getToPrice() != null) {
            spec = spec.and(toPrice(request.getToPrice()));
        }
        if (request.getMinQuantity() != null) {
            spec = spec.and(minQuantity(request.getMinQuantity()));
        }
        if (request.getMaxQuantity() != null) {
            spec = spec.and(maxQuantity(request.getMaxQuantity()));
        }
        if (request.getInvoiceNo() != null) {
            spec = spec.and(byInvoiceNo(request.getInvoiceNo()));
        }
        if (request.getStockCode() != null) {
            spec = spec.and(byStockCode(request.getStockCode()));
        }
        if (request.getCustomerID() != null) {
            spec = spec.and(byCustomerId(request.getCustomerID()));
        }
        if (request.getCountry() != null) {
            spec = spec.and(byCountry(request.getCountry()));
        }
        if (request.getDescription() != null) {
            spec = spec.and(descriptionsContains(request.getDescription()));
        }
        return spec;
    }

    public Pageable toPageable(ProductFindRequest request) {
        if (request.getSortingProperty() == null) {
            return PageRequest.of(request.getPageNumber(), request.getPageSize(), Sort.Direction.DESC, Product_.INVOICE_DATE);
        }
        Sort.Order[] orders = new Sort.Order[request.getSortingProperty().length];
        for (int i = 0; i < request.getSortingProperty().length; i++) {
            if (request.getDirections() != null && i < request.getDirections().length) {
                orders[i] = new Sort.Order(request.getDirections()[i], request.getSortingProperty()[i].getLabel());
            } else {
                orders[i] = new Sort.Order(Sort.Direction.ASC, request.getSortingProperty()[i].getLabel());
            }
        }
        return PageRequest.of(request.getPageNumber(), request.getPageSize(), Sort.by(orders));
    }
}
