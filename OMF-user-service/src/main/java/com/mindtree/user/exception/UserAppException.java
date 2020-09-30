/**
 * 
 */
package com.mindtree.user.exception;

/**
 * @author M1056182
 *
 */
public class UserAppException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserAppException() {
		super();
	}

	public UserAppException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UserAppException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserAppException(String message) {
		super(message);
	}

	public UserAppException(Throwable cause) {
		super(cause);
	}
	
	

}
