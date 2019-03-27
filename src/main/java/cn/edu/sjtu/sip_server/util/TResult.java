package cn.edu.sjtu.sip_server.util;

import io.swagger.annotations.Api;

@Api
public class TResult<T> {

    private Integer code;
    private String message;
    private T data;

    public TResult() {
        setResultCode(TResultCode.SUCCESS);
    }

    public TResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public TResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public void setSuccess(T data) {
        setResultCode(TResultCode.SUCCESS);
        setData(data);
    }


    public void setSuccess(String message, T data) {
        setSuccess(data);
        setMessage(message);
    }


    public void setFailure(String message) {
        setCode(TResultCode.FAILURE.getCode());
        setMessage(message);
    }

    public void setFailure(TResultCode resultCode) {
        setResultCode(resultCode);
    }

    private void setResultCode(TResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}

