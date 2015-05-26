package com.sg.syj.entity.vo;

/***
 * 
 * 说明:返回结果的提示信息
 *
 */
public class ResultMsg {
	//结果
	private boolean rs;
	//提示信息
	private String msg;
    
	public ResultMsg(boolean rs, String msg) {
		this.rs = rs;
		this.msg = msg;
	}

	public ResultMsg() {
		
	}

	public boolean isRs() {
		return rs;
	}

	public void setRs(boolean rs) {
		this.rs = rs;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
