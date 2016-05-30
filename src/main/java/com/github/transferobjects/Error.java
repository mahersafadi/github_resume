package com.github.transferobjects;
/**
 * 
 * @author maher
 * This class is used if an exception is raised
 * I go throw send always correct result (not 400, 404, ....)
 */
public class Error implements TransferObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9148632065596125144L;
	private String errMsg;

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
}
