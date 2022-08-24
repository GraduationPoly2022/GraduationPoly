package com.shop.enumEntity;

public enum Reason {
    ARRIVED_TOO_LATE, DAMAGED_IN_TRANSIT, NO_REASON, ITEM_WAS_DEFECTIVE, NOT_QUALITY, DESCRIPTION_INCORRECTLY,
    POOR_SERVICE_COVERAGE, MISSING_ACCESSORIES, DIFFERENT_ORDERS, ANOTHER_REASON;

    public String getReason() {
        String value;
        switch (this) {
            case NO_REASON -> {
                value = "Không có lý do gì";
            }
            case ARRIVED_TOO_LATE -> {
                value = "Đến quá muộn";
            }
            case ITEM_WAS_DEFECTIVE -> {
                value = "Mặt hàng đã bị lỗi";
            }
            case NOT_QUALITY -> {
                value = "Không hài lòng với chất lượng";
            }
            case DESCRIPTION_INCORRECTLY -> {
                value = "Mô tả mặt hàng không chính xác";
            }
            case POOR_SERVICE_COVERAGE -> {
                value = "Phạm vi dịch vụ kém";
            }
            case MISSING_ACCESSORIES -> {
                value = "Các bộ phận hoặc phụ kiện bị thiếu";
            }
            case DIFFERENT_ORDERS -> {
                value = "Khác với những gì đã đặt hàng";
            }
            case DAMAGED_IN_TRANSIT -> {
                value = "Hư hại trong quá trình vận chuyển";
            }
            default -> {
                value = "Lý do khác";
            }
        }
        return value;
    }

    @Override
    public String toString() {
        return this.getReason();
    }
}
