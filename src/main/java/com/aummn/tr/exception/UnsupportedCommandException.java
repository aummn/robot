package com.aummn.tr.exception;

/**
 * This class defines a customized exception for unsupported commands.
 *
 * @author James Jin
 * 
 */
public class UnsupportedCommandException extends RuntimeException {

	private static final long serialVersionUID = 4282077777501596123L;
	private int status;
	private String message;
	
	public UnsupportedCommandException() {
		super();
	}
	
	public UnsupportedCommandException(String message) {
		this.message = message;
	}
	
	public UnsupportedCommandException(int status, String message) {
		this.status = status;
		this.message = message;
	}
	
	public UnsupportedCommandException(ErrorStatus errorStatus) {
		this.status = errorStatus.getStatus();
		this.message = errorStatus.getMessage();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Command error: ");
		sb.append("status [").append(this.getStatus()).append("] ");
		sb.append("message [").append(this.getMessage()).append("]");
		return sb.toString();
	}

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
}
