package com.lillanm.management.common;


import lombok.Data;

@Data
public class R<T> {
    private T data;

    private String msg;

    private Integer status;

    public static <T> R<T> success(T object) {
        R<T> r = new R<>();
        r.status = 1;
        r.data = object;
        return r;
    }

    public static <T> R<T> error(String msg) {
        R<T> r = new R<>();
        r.status = 0;
        r.msg = msg;
        return r;
    }

}
