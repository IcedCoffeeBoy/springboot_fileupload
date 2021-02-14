package com.spring.fileupload.product;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;

import javax.annotation.Nullable;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Setter
@Getter
public class ProductFindRequest {
    @Min(value = 0)
    @Nullable
    private Integer pageNumber = 0;

    @Min(value = 1)
    @Nullable
    private Integer pageSize = 10;

    @Nullable
    private ProductSortable[] sortingProperty;

    @Nullable
    private Sort.Direction[] directions;

    @Nullable
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fromDate;

    @Nullable
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate toDate;

    @Nullable
    private Double fromPrice;

    @Nullable
    private Double toPrice;

    @Nullable
    private Long minQuantity;

    @Nullable
    private Long maxQuantity;

    @Nullable
    private String invoiceNo;

    @Nullable
    private String stockCode;

    @Nullable
    private String customerID;

    @Nullable
    private String country;

    @Nullable
    private String description;

}
