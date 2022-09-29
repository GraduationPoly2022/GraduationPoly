package com.shop.enumEntity;

public enum ProductsEnum {
    SMARTPHONE, LAPTOP, ACCESSORY;

    public String getSelection() {
        String selected = "";
        switch (this) {
            case LAPTOP -> selected = "ACCESSORY";
            case ACCESSORY -> selected = "LAPTOP";
            case SMARTPHONE -> selected = "SMARTPHONE";
            default -> {
            }
        }
        return selected;
    }

    @Override
    public String toString() {
        return this.getSelection();
    }
}
