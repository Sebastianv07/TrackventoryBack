package com.ppi.trackventory.models.DTO;

public class StockByStoreDTO {
    private String reference;
    private String quantity;
    private String nameColor;
    private String nameProduct;
    private String code;
    private String colorVrt;

    public StockByStoreDTO() {
    }

    public StockByStoreDTO(String reference, String quantity, String nameColor, String nameProduct, String code, String colorVrt) {
        this.reference = reference;
        this.quantity = quantity;
        this.nameColor = nameColor;
        this.nameProduct = nameProduct;
        this.code = code;
        this.colorVrt = colorVrt;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getNameColor() {
        return nameColor;
    }

    public void setNameColor(String nameColor) {
        this.nameColor = nameColor;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getColorVrt() {
        return colorVrt;
    }

    public void setColorVrt(String colorVrt) {
        this.colorVrt = colorVrt;
    }
}
