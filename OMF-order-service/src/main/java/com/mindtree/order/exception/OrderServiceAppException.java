/**
 * 
 */
package com.mindtree.order.exception;

/**
 * @author M1056182
 *
 */
public class OrderServiceAppException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	public OrderServiceAppException() {
		super();
	}

	public OrderServiceAppException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public OrderServiceAppException(String message, Throwable cause) {
		super(message, cause);
	}

	public OrderServiceAppException(String message) {
		super(message);
	}

	public OrderServiceAppException(Throwable cause) {
		super(cause);
	}

	
	
	
}
