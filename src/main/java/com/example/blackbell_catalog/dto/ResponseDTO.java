package com.example.blackbell_catalog.dto;

import com.example.blackbell_catalog.constant.ResultType;
import com.example.blackbell_catalog.exception.BaseException;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude
public class ResponseDTO<T> implements Serializable {

    private ResultObject result;

    private T data;

    public ResponseDTO(ResultObject result) {
        this.result = result;
    }

    public ResponseDTO(T data) {
        this.data = data;
    }

    public static <T> ResponseDTO<T> ok() {
        return new ResponseDTO<>(ResultObject.getSuccess());
    }

    public static <T> ResponseDTO<T> ok(ResultType resultType, T data) {
        return new ResponseDTO<>(new ResultObject(resultType), data);
    }

    public ResponseDTO(BaseException ex) {
        this.result = new ResultObject(ex);
    }
}