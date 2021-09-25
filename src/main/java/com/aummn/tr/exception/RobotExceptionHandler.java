package com.aummn.tr.exception;

public enum RobotExceptionHandler implements ExceptionHandler {
	INSTANCE;
	
	public void handleExceptions(Exception ex) {
		if(ex instanceof CommandParsingException) {
			this.doHandleCommandParsingException((CommandParsingException)ex);
		} else if(ex instanceof UnsupportedCommandException) {
			this.doHandleUnspportedCommandException((UnsupportedCommandException)ex);
		} else if(ex instanceof OffTableException) {
			this.doHandleOffTableException((OffTableException)ex);
		} else if(ex instanceof RobotNotBePlacedException) {
			this.doHandleRobotNotBePlacedException((RobotNotBePlacedException)ex);
		} else if(ex instanceof EmptyCommandException) {
			this.doHandleEmptyCommandException((EmptyCommandException)ex);
		}
	}
	
	private void doHandleCommandParsingException(CommandParsingException cpe) {
		System.out.println(cpe.toString());
	}
	
	private void doHandleUnspportedCommandException(UnsupportedCommandException usce) {
		System.out.println(usce.toString());
	}

	private void doHandleOffTableException(OffTableException ote) {
		System.out.println(ote.toString());
	}
	
	private void doHandleRobotNotBePlacedException(RobotNotBePlacedException rnbpe) {
		System.out.println(rnbpe.toString());
	}
	
	private void doHandleEmptyCommandException(EmptyCommandException ece) {
		System.out.println(ece.toString());
	}	
}
