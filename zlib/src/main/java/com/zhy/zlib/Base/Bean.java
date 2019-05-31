package com.zhy.zlib.Base;

public class Bean<T> {
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private T data;
}
