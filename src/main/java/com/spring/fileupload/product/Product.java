package com.spring.fileupload.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-char")
    private UUID id;

    @Column
    private String invoiceNo;

    @Column
    private String stockCode;

    @Column
    private String description;

    @Column
    private Long quantity;

    @Column
    private LocalDateTime invoiceDate;

    @Column
    private Double unitPrice;

    @Column
    private String customerID;

    @Column
    private String country;

    @Column
    private Long fileRecordId;

}
