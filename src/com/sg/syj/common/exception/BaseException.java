package com.sg.syj.common.exception;

/**
 * 
 * TODO 异常处理基类�?
 * 
 * @ClassName: BaseException
 * @author zhangdie
 */
public class BaseException extends RuntimeException
{
	protected Throwable cause; // 传入的异�?

	protected String message; // 错误描述信息

	protected int errorCode; // 系统错误编码

	/**
	 * 
	* 构�?�异常基�?
	 */
	public BaseException()
	{
		super();
	}

	/**
	 * .
	* 构�?�异常基�?
	* @param message
	 */
	public BaseException(String message)
	{
		super(message);
		this.message = message;
	}

	/**
	 * .
	* 构�?�异常基�?
	* @param cause
	 */
	public BaseException(Throwable cause)
	{
		super(cause);
		this.cause = cause;
	}

	/**
	 * .
	* 构�?�异常基�?
	* @param message
	* @param cause
	 */
	public BaseException(String message, Throwable cause)
	{
		super(message, cause);
		this.cause = cause;
	}

	/**
	 * get cause
	 * 
	 * @return cause
	 */
	public Throwable getCause()
	{
		return cause;
	}

	/**
	 * set cause
	 * 
	 * @param cause
	 *            the cause to set
	 */
	public void setCause(Throwable cause)
	{
		this.cause = cause;
	}

	/**
	 * get message
	 * 
	 * @return message
	 */
	public String getMessage()
	{
		String msg = this.message;
		if (null == msg || "".equals(msg)){
			msg = super.getMessage();
		}
		return msg;
	}

	/**
	 * set message
	 * 
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message)
	{
		this.message = message;
	}

	/**
	 * get errorCode
	 * 
	 * @return errorCode
	 */
	public int getErrorCode()
	{
		return errorCode;
	}

	/**
	 * set errorCode
	 * 
	 * @param errorCode
	 *            the errorCode to set
	 */
	public void setErrorCode(int errorCode)
	{
		this.errorCode = errorCode;
	}

}
