package com.schibsted.recipe.api;

import com.schibsted.recipe.bean.Bean;

import java.util.List;

import retrofit.client.Header;

public class ApiResponse<T extends Bean> {
    private int mCode;
    private T mBean;
    private List<Header> mHeaders;

    public ApiResponse(int code) {
        this(code, null);
    }

    public ApiResponse(int code, List<Header> headers) {
        this(code, headers, null);
    }

    public ApiResponse(int code,List<Header> headers, T bean) {
        mCode = code;
        mBean = bean;
        mHeaders = headers;
    }

    public int getCode() {
        return mCode;
    }

    public T getBean() {
        return mBean;
    }

    public List<Header> getHeaders() {
        return mHeaders;
    }
}