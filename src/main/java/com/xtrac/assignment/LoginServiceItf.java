package com.xtrac.assignment;

import javax.ws.rs.core.Response;

public interface LoginServiceItf {

	public Response newUserSignup(String emailId, String firstName, String lastName, String telephoneNumber);
	public Response userSearch(String emailId);
	public Response userUpdate(String emailId, String firstName, String lastName, String telephoneNumber);
	public Response userDelete(String emailId);
	public Response userAppRegister(String emailId, String appName, String appDesc);
	public Response getUserAppList(String emailId);
}
