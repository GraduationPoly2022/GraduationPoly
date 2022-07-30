package com.shop.enumEntity;

public enum PaymentEnum {
    PAID("Đã thanh toán"),
    UNPAID("Chưa thanh toán");

    private String pay;
    private PaymentEnum(String pay) {
        this.pay = pay;
    }

    public String getPay() {
        return pay;
    }
    @Override
    public String toString() {
        return this.getPay();
    }
}
