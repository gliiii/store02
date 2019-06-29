package cn.tedu.store02.util;

import java.io.Serializable;
/**
 * 控制器层返回的json数据类型
 * @param <T> 泛型
 */
public class ResponseResult<T> implements Serializable {
	private static final long serialVersionUID = -939440019451842971L;
	private Integer state;
	private String message;
	private T data;
	
	public ResponseResult() {
		super();
	}
	public ResponseResult(Integer state) {
		super();
		this.state = state;
	}
	public ResponseResult(Integer state, T data) {
		super();
		this.state = state;
		this.data = data;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
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
	
}
