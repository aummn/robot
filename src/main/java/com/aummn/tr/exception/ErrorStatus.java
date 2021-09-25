package com.aummn.tr.exception;

/**
 * This enum representing the robot's error status. 
 * 
 * @author James Jin
 * 
 */
public enum ErrorStatus {
	PLACE_COMMAND_PARSING_ERROR(402,"Place command can not be parsed");
	
	private final int status;
	private final String message;
	
	ErrorStatus(int status, String message) {
		this.status = status;
		this.message = message;
	}
	
	public static ErrorStatus getErrorStatus(int status) {
		ErrorStatus errorStatus = null;
		for(ErrorStatus  es: ErrorStatus.values()) {
			if(es.getStatus() == status) {
				errorStatus = es;
			}
		}
		if (errorStatus == null) {
			throw new IllegalArgumentException("No matching ErrorStatus for [" + status + "]");
		}
		return errorStatus;
	}

	public int getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

}
