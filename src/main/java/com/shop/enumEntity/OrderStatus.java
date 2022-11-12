package com.shop.enumEntity;

public enum OrderStatus {
    CART, WAITING_FOR_CONFIRM, WAITING_FOR_PRODUCT, DELIVERING, DELIVERED, RETURNS_OF_PRODUCTS, CANCEL_ORDER;

    private String getStatus() {
        String status;
        switch (this) {
            case CART -> {
                status = "Giỏ Hàng";
            }
            case WAITING_FOR_CONFIRM -> {
                status = "Chờ xác nhận";
            }
            case WAITING_FOR_PRODUCT -> {
                status = "Đang chuẩn bị sản phẩm";
            }
            case DELIVERING -> {
                status = "Đang giao hàng";
            }
            case DELIVERED -> {
                status = "Đã nhận hàng";
            }
            case RETURNS_OF_PRODUCTS -> {
                status = "Trả hàng";
            }
            default -> {
                status = "Hủy đơn hàng";
            }
        }
        return status;
    }

    @Override
    public String toString() {
        return this.getStatus();
    }
}
