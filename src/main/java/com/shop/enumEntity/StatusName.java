package com.shop.enumEntity;

public enum StatusName {
    WAITING_CONFIRM("Chờ xác nhận"),
    PRODUCT_PREPARATION("Đang chuẩn bị sản phẩm"),
    SHIPPING ("Đang giao hàng"),
    WAIT_FOR_PAY ("Chờ thanh toán"),
    RECEIVED ("Đã nhận hàng"),
    RETURN_ORDER ("Huỷ đơn hàng");

    private String status;
    private StatusName(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return this.getStatus();
    }
}
