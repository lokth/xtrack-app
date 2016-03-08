package com.xtrac.assignment;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.xtrac.utils.DatabaseUtils;
import com.xtrac.utils.Helper;

@Path("/login")
public class LoginServiceImpl implements LoginServiceItf {

	// http://localhost:8080/xtrac-app/login/signup?emailid=test@gmail.com&firstName=Bob&lastName=Williams&telNumber=98563
	@GET
	@Path("/signup")
	@Override
	public Response newUserSignup(@QueryParam("emailid") String emailId,
			@QueryParam("firstName") String firstName,
			@QueryParam("lastName") String lastName,
			@QueryParam("telNumber") String telephoneNumber) {

		String flag = null;
		boolean emailflag = Helper.validate(emailId);

		if(!emailflag) {
			String emailStatus = "Invalid, enter correct email id";
			return Response.status(200).entity(emailStatus).build();
		}

		try {
			flag = DatabaseUtils.insertUser(emailId, firstName, lastName, telephoneNumber);
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return Response.status(200).entity(flag).build();
	}

	// http://localhost:8080/xtrac-app/login/search?emailId=test@gmail.com
	@GET
	@Path("/search")
	@Override
	public Response userSearch(@QueryParam("emailId") String emailId) {

		String flag = null;
		boolean emailflag = Helper.validate(emailId);

		if(!emailflag) {
			String emailStatus = "Invalid, enter correct email id";
			return Response.status(200).entity(emailStatus).build();
		}

		try {
			flag = DatabaseUtils.searchUser(emailId);
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return Response.status(200).entity(flag).build();
	}

	// http://localhost:8080/xtrac-app/login/update?emailId=test@gmail.com&firstName=testFirst&lastName=test2Last&telNumber=54321
	@GET
	@Path("/update")
	@Override
	public Response userUpdate(@QueryParam("emailId") String emailId,
			@QueryParam("firstName") String firstName,
			@QueryParam("lastName") String lastName,
			@QueryParam("telNumber") String telephoneNumber) {

		String flag = null;
		boolean emailflag = Helper.validate(emailId);

		if(!emailflag) {
			String emailStatus = "Invalid, enter correct email id";
			return Response.status(200).entity(emailStatus).build();
		}

		try {
			flag = DatabaseUtils.updateUser(emailId, firstName, lastName, telephoneNumber);
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return Response.status(200).entity(flag).build();
	}

	// http://localhost:8080/xtrac-app/login/delete?emailId=test@gmail.com
	@GET
	@Path("/delete")
	@Override
	public Response userDelete(@QueryParam("emailId") String emailId) {

		String flag = null;
		boolean emailflag = Helper.validate(emailId);

		if(!emailflag) {
			String emailStatus = "Invalid, enter correct email id";
			return Response.status(200).entity(emailStatus).build();
		}

		try {
			flag = DatabaseUtils.deleteUser(emailId);
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return Response.status(200).entity(flag).build();
	}

	// http://localhost:8080/xtrac-app/login/register?emailId=test@gmail.com&appName=testApp&appDesc=testAppDesc
	@GET
	@Path("/register")
	@Override
	public Response userAppRegister(@QueryParam("emailId") String emailId,
			@QueryParam("appName") String appName,
			@QueryParam("appDesc") String appDesc) {

		int flag = -1;
		boolean emailflag = Helper.validate(emailId);

		if(!emailflag) {
			String emailStatus = "Invalid, enter correct email id";
			return Response.status(200).entity(emailStatus).build();
		}

		try {
			flag = DatabaseUtils.registerApp(emailId, appName, appDesc);
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return Response.status(200).entity(String.valueOf("Registration Id: " + flag)).build();
	}

	// http://localhost:8080/xtrac-app/login/applist?emailId=test@gmail.com
	@GET
	@Path("/applist")
	@Override
	public Response getUserAppList(@QueryParam("emailId") String emailId) {

		String flag = null;
		boolean emailflag = Helper.validate(emailId);

		if(!emailflag) {
			String emailStatus = "Invalid, enter correct email id";
			return Response.status(200).entity(emailStatus).build();
		}

		try {
			flag = DatabaseUtils.getUserAppList(emailId);
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return Response.status(200).entity(flag).build();
	}
}