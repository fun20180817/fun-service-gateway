package com.fun.result;

/**
 * ClassName: Result <br/>
 * Function: 返回体. <br/>
 * date: 2018年8月26日 上午12:10:38 <br/>
 * 
 * @author lishuai11
 * @version
 * @since JDK 1.8
 */
public class Result {

	private int code;

	private String message;

	private Object data;

	private Result(int code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	/**
	 * 
	 * success(成功，需要返回数据). <br/>
	 * 
	 * @author lishuai11
	 * @param code
	 * @param message
	 * @param data
	 * @return
	 * @since JDK 1.8
	 */
	public static Result success(String message, Object data) {
		return new Result(200, message, data);
	}

	/**
	 * 
	 * success:(成功，不需要返回数据). <br/>
	 * 
	 * @author lishuai11
	 * @param code
	 * @param message
	 * @return
	 * @since JDK 1.8
	 */
	public static Result success(String message) {
		return new Result(200, message, "");
	}

	/**
	 * 
	 * fail:(失败，不返回数据). <br/>
	 * 
	 * @author lishuai11
	 * @return
	 * @since JDK 1.8
	 */
	public static Result fail(int code, String message) {
		return new Result(code, message, "");
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
