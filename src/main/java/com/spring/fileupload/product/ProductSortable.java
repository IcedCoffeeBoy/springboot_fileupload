package com.spring.fileupload.product;

public enum ProductSortable {
    INVOICE_NO(Product_.INVOICE_NO),
    STOCK_CODE(Product_.STOCK_CODE),
    DESCRIPTION(Product_.DESCRIPTION),
    QUANTITY(Product_.QUANTITY),
    INVOICE_DATE(Product_.INVOICE_NO),
    UNIT_PRICE(Product_.UNIT_PRICE),
    CUSTOMER_ID(Product_.CUSTOMER_ID),
    COUNTRY(Product_.COUNTRY);

    public final String label;

    ProductSortable(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
