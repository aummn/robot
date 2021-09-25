package com.aummn.tr.exception;

/**
 * This class defines a customized exception for command parsing errors.
 *
 * @author James Jin
 * 
 */
public class CommandParsingException extends RuntimeException {

	private static final long serialVersionUID = -6719765628660884806L;
	private int status;
	private String message;
	
	public CommandParsingException() {
		super();
	}
	
	public CommandParsingException(String message) {
		this.message = message;
	}
	
	public CommandParsingException(int status, String message) {
		this.status = status;
		this.message = message;
	}
	
	public CommandParsingException(ErrorStatus errorStatus) {
		this.status = errorStatus.getStatus();
		this.message = errorStatus.getMessage();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("command parse error: ");
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
