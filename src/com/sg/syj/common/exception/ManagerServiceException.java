package com.sg.syj.common.exception;

/**
 * 
* 服务管理异常类，�?有服务管理的方法必须抛出此异常�??
* @ClassName: ManagerServiceException
* @author zhangdie
 */
public class ManagerServiceException extends BaseException
{
	public ManagerServiceException(Throwable ex){
		super(ex);
	}
	
	public ManagerServiceException(String message){
		super(message);
	}
	
	public ManagerServiceException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
