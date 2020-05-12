package cn.edu.scujcc;

import java.io.Serializable;

public class Response implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2730410292261886891L;
	public final static int STATUS_OK=1;
	public final static int STATUS_ERROR=-1;
     public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public User getData() {
		return data;
	}
	public void setData(User data) {
		this.data = data;
	}
	private int status;
     private String message;
     private User data;
}
