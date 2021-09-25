package com.aummn.tr.exception;

/**
 * This interface defining the method for error handling. 
 *
 * @author James Jin
 * 
 */
public interface ExceptionHandler {
	
	/**
	 * handle the exceptions
	 * 
	 * @param ex the thrown exception to be handled
	 */
	void handleExceptions(Exception ex) ;
	
}
