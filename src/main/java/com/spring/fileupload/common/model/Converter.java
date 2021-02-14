package com.spring.fileupload.common.model;

public interface Converter<T, S> {
    S convert(T t);
}
