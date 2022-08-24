package com.shop.enumEntity;

public enum PaymentEnum {
    PAID, UNPAID;

    private String pay;

    private String getPay() {
        switch (this) {
            case PAID -> {
                this.pay = "Đã thanh toán";
            }
            case UNPAID -> {
                this.pay = "Chưa thanh toán";
            }
        }
        return pay;
    }

    @Override
    public String toString() {
        return this.getPay();
    }
}
