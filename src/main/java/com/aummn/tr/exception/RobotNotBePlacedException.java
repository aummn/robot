package com.aummn.tr.exception;

/**
 * This class defines a customized exception for unplaced robot.
 *
 * @author James Jin
 * 
 */
public class RobotNotBePlacedException extends RuntimeException {

	private static final long serialVersionUID = -8424577600355101231L;
	private int status;
	private String message;
	
	public RobotNotBePlacedException() {
		super();
	}
	
	public RobotNotBePlacedException(String message) {
		this.message = message;
	}
	
	public RobotNotBePlacedException(int status, String message) {
		this.status = status;
		this.message = message;
	}
	
	public RobotNotBePlacedException(ErrorStatus errorStatus) {
		this.status = errorStatus.getStatus();
		this.message = errorStatus.getMessage();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Robot place error: ");
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
