package com.aummn.tr.exception;

/**
 * This class defines a customized exception for empty commands.
 *
 * @author James Jin
 * 
 */
public class EmptyCommandException extends RuntimeException {

	private static final long serialVersionUID = -2832387269571943782L;
	private int status;
	private String message;
	
	public EmptyCommandException() {
		super();
	}
	
	public EmptyCommandException(String message) {
		this.message = message;
	}
	
	public EmptyCommandException(int status, String message) {
		this.status = status;
		this.message = message;
	}
	
	public EmptyCommandException(ErrorStatus errorStatus) {
		this.status = errorStatus.getStatus();
		this.message = errorStatus.getMessage();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Empty command error: ");
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
