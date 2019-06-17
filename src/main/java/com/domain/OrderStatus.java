package com.domain;

public enum OrderStatus {
    PLASED (0),
    PAYED (1),
    CONFIRMED (2),
    CLOSED (2);


    private int code;

//    public OrderStatus() {}

    OrderStatus(int code) {
        this.code = code;
    }

    public OrderStatus findByCode(int code) {
        for (OrderStatus status : values()) {
            if (this.code== code) {
                return this;
            }
        }
        return null;
    }
}
