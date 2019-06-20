package com.enums;

public enum OrderStatus {
    PLASED (0),
    PAYED (1),
    CONFIRMED (2),
    CLOSED (3);


    private int code;

    //public OrderStatus() {}

    OrderStatus(int code) {
        this.code = code;
    }

    public static OrderStatus findByCode(int code) {
        for (OrderStatus status : values()) {
            if (status.code== code) {
                return status;
            }
        }
        return null;
    }

    public  int getCode (){
        return this.code;
    }
}
