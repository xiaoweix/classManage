package com.classManage.tusdt.base.common;


import java.io.Serializable;

/**
 * description:
 *
 * @author wxcsdb88
 * @since 2017/11/7 10:03
 */
@SuppressWarnings("unchecked")
public class ResponseData<T> implements Serializable {
    private static final long serialVersionUID = -225863381103524707L;

    private Integer code = 200;
    private String msg = "success";
    private T result;

    public ResponseData(){};
    /**
     * 带参构造函数(默认错误)
     *
     * @param msg 信息
     * @param code    错误码
     */
    public ResponseData(final String msg, final Integer code) {
        this.msg = msg;
        this.code = code;
    }

    /**
     * 带参构造函数(默认正确)
     *
     * @param result 数据
     */
    public ResponseData(final T result) {
        this.result = result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    /*--------------------------  set normal start -----------------------*/
    public void set(int code, String success, ResponseData srcTables) {
        set(this.code, this.msg, this.result);
    }

    public void set(String msg) {
        set(this.code, msg, this.result);
    }

    public void set(Integer code, T result) {
        set(code, this.msg, result);
    }

    public void set(String msg, T result) {
        set(this.code, msg, result);
    }

    public void set(Integer code, String msg, T result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }
      /*--------------------------  set normal end -----------------------*/

    /*--------------------------  set Ok start -----------------------*/

    public void setOK() {
        setOK(this.code, this.msg, this.result);
    }

    public void setOK(String msg) {
        setOK(this.code, msg, this.result);
    }

    public void setOK(Integer code, T result) {
        setOK(code, this.msg, result);
    }

    public void setOK(String msg, T result) {
        setOK(this.code, msg, result);
    }

    public void setOK(Integer code, String msg, T result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }

    /*--------------------------  set Ok end -----------------------*/
/*--------------------------  set ERROR start -----------------------*/
    //todo test used
    public void setError(String msg) {
        setError(500, msg, this.result);
    }

    public void setError(Integer code) {
        setError(code, "error", this.result);
    }

    public void setError(Integer code, String msg) {
        setError(code, msg, this.result);
    }

    public void setError(Integer code, T result) {
        setError(code, "error", result);
    }

    public void setError(String msg, T result) {
        setError(500, msg, result);
    }

    public void setError(Integer code, String msg, T result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }
/*--------------------------  set ERROR end -----------------------*/

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ResponseData{");
        sb.append("code=").append(code);
        sb.append(", msg='").append(msg).append('\'');
        sb.append(", result=").append(result);
        sb.append('}');
        return sb.toString();
    }


    public static<T> ResponseData<T> successed(T result) {
        return new ResponseData<T>(result);
    }

    public static<T> ResponseData<T> failed(String msg,Integer code) {
        return new ResponseData<T>(msg,code);
    }
}
