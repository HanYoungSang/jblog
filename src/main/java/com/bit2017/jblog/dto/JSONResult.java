package com.bit2017.jblog.dto;

public class JSONResult {
	private String result ; // success -> 통신 성공
							// failed -> 통신 실패
	private String message;
	private Object data;

	private JSONResult() {}
	private JSONResult(String result, String message, Object data) {
		this.result = result;
		this.message = message;
		this.data = data;
	}

	public static JSONResult success( Object object ){
		return new JSONResult("success", null, object);
	}
	
	public static JSONResult fail( String message ){
		return new JSONResult("fail", message, null);
	}
	
	public String getResult() {
		return result;
	}
	
	public String getMessage() {
		return message;
	}
	
	public Object getData() {
		return data;
	}
	
	@Override
	public String toString() {
		return "JSONResult [result=" + result + ", message=" + message
				+ ", data=" + data + "]";
	}

	
	
}
