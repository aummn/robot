package com.aummn.tr.exception;

/**
 * This class defines a customized exception for off table errors.
 *
 * @author James Jin
 * 
 */
public class OffTableException extends RuntimeException {

	private static final long serialVersionUID = 3531350201822862725L;
	private int status;
	private String message;
	
	public OffTableException() {
		super();
	}
	
	public OffTableException(String message) {
		this.message = message;
	}
	
	public OffTableException(int status, String message) {
		this.status = status;
		this.message = message;
	}
	
	public OffTableException(ErrorStatus errorStatus) {
		this.status = errorStatus.getStatus();
		this.message = errorStatus.getMessage();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Off table error: ");
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
