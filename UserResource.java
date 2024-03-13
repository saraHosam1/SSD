package packageName;


import java.util.List;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Path("/user")
public class UserResource {
	static Logger logger = LogManager.getLogger(UserResource.class);
	UserService us = new UserService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	
	public List<Users> getAllUsers(){
		 return us.getAllUsers();
		
	}
	@GET
	@Path("{userName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Users getUser(@PathParam("userName") String userName) {
		return us.getUser(userName);
		
	}
	@PUT
	@Path("/{userName}")
	@Produces(MediaType.APPLICATION_JSON)
	
	public Response updateUserPassword(@PathParam("userName") String userName,@HeaderParam("password") String pass) {
		
		Users user =us.getUser(userName);
		try {
		if(user==null) {
			throw new DataNotFoundException("The User with username: "+userName+" Not found");
		}
		}
		catch(DataNotFoundException ex) {
			ErrorMessage em = new ErrorMessage(ex.getMessage(),404);
			return Response.status(Status.NOT_FOUND).entity(em).build();
		}
		user.setPassword(pass);
		return Response.status(Status.FOUND).entity(user).build();
		
		
	}
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response addUser(Users user,@HeaderParam("Content-Type") String expectedContentType,@HeaderParam("password") String pass) {
		if(expectedContentType.equals("application/json")){
		 UUID apikey = UUID.randomUUID();
			
		 if(user.getUserName()==null||user.getUserName().equalsIgnoreCase("true")|| user.getUserName().contains("@")
				 ||user.getUserName().length()<8) {
			return Response.status(Status.NOT_ACCEPTABLE.getStatusCode()).entity((String)"unaccepted username check that the username match the constraints ").build();
		 }
		 else {
			 if(us.getUser(user.getUserName())== null) {
				 if(pass!=null && pass.length()>8) {
					 user.setApikey(apikey.toString());
					 user.setPassword(pass);
					 us.addUser(user);
					 return Response.status(Status.CREATED.getStatusCode()).entity((String)"User created successfully your ipekey is: "+user.getApikey()).build();
				 }
				 else {
					 return Response.status(Status.NOT_ACCEPTABLE.getStatusCode()).entity((String)"unaccepted password check that the password match the constraints ").build();} }
			 return Response.status(Status.NOT_ACCEPTABLE.getStatusCode()).entity((String)"The username already exists. Enter another username ").build(); }
			
	}
		else {return Response.status(Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode()).entity("unexpected or missing content type headers"+expectedContentType).build();}
		
	}

		
	
	
	@POST
	@Path("login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response Login(Users user,@HeaderParam("apikey") String apikey) {
		if (us.getUser(user.getUserName())!=null) {
			if(us.getUser(user.getUserName()).getApikey().equals(apikey)) {
				logger.info("successful login by: "+user.getUserName()+" "+Status.OK.getStatusCode());
				return Response.status(Status.OK.getStatusCode()).entity((String)"User: "+user.getUserName()+" "+"logged in successfully ").build();
				
			}
			
			
		}
	
	logger.info("unauthorized attemp to login by : "+user.getUserName()+" "+Status.UNAUTHORIZED.getStatusCode());
    return Response.status(Status.UNAUTHORIZED.getStatusCode()).entity((String)"Wrong Username/password combination ").build();
		
		
		
	}

}
