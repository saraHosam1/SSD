package packageName;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.core.UriInfo;



@Path("post")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)



public class PostResource {
	
	static Logger logger = LogManager.getLogger(PostResource.class);
PostServic s1=new PostServic();
CommentService cs= new CommentService();
	
	
	@GET
	
	public List<Post> getPosteOfYear(@QueryParam("year") int year,@QueryParam("start") int start,@QueryParam("size") int size){
		if(year>0) {
	    	return s1.getALlPostForaYear(year);
		}
		if(start>=0&&size>=0) {
			return s1.getAllPostsPaginated(start, size);
		}
		return s1.getAllPosts();
	
	
		
	}
	@POST
	public Response addPost(Post m,@Context UriInfo uri) throws URISyntaxException {
		Post newp= s1.addPost(m);
	String URI=	uri.getAbsolutePath().toString();
		return Response.created(new URI(URI+"/"+newp.getId())).entity(newp).build();
		
	}
	
	
	
	@GET
	@Path("/{postID}")
	public Response GetPost(@PathParam("postID") long id) {
		Post p= s1.getPost(id);
		try {
			
		if(p==null) {
			 throw new DataNotFoundException("The post with id: "+id+" Not found");
		}
		}
		catch(DataNotFoundException ex) {
			ErrorMessage em = new ErrorMessage(ex.getMessage(),404);
			return Response.status(Status.NOT_FOUND).entity(em).build();
			
		}
		
		return Response.status(Status.FOUND).entity(p).build();
	}
	
	@PUT
	@Path("/{postID}")
	public Post updatPost(@PathParam("postID") long id,Post m1) {
		m1.setId(id);
		return s1.updatePost(m1);
		
	}
	
	@DELETE
	@Path("/{postID}")
	public Response deletePost(@PathParam("postID") long id) {
		if(id<0|| id>s1.getAllPosts().size()) {
			logger.info("Post is not available"+Status.NOT_FOUND.getStatusCode());
			return Response.status(Status.NOT_FOUND.getStatusCode()).entity((String)"post not available").build();
		}
		else {
			s1.deletePost(id);
			logger.info("Post is available"+Status.FOUND.getStatusCode());
			return Response.status(Status.FOUND.getStatusCode()).entity("post is deleted").build();
		}
		
		
	}
	@Path("/{postID}/comments")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	
	public List<Comment>getAllComments(@PathParam("postID") long pID){
		return cs.getAllComments(pID);
	}
	
	@POST
	@Path("/{postID}/comments")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	public Comment addComment(@PathParam("postID") long pID,Comment c) {
		return cs.addComment(pID,c);
		
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{postID}/comments/{commentId}")
	public Comment getComment(@PathParam("postID") long pId,@PathParam("commentId") long cId) {
		return cs.getComment(pId, cId);
		
	}
	@PUT
	@Path("/{postID}/comments/{commentId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Comment updateComment(@PathParam("postID") long pID,@PathParam("commentId") long cID,Comment c) {
		c.setCommentID(cID);
		return cs.updateComment(pID, c);
		
	}
	@DELETE
	@Path("/{postID}/comments/{commentId}")
	@Produces(MediaType.TEXT_PLAIN)
	
	public Response removeComment(@PathParam("postID")long pID, @PathParam("commentId") long cID) {
		if(cID<=0||cs.getAllComments(pID).size()<cID) {
			logger.info("error "+Status.NOT_FOUND.getStatusCode());

			return Response.status(Status.NOT_FOUND.getStatusCode()).entity((String)"Comment not available").build();
		}
		else {
			
			cs.removeComment(pID, cID);
			logger.info("Correct comment id "+Status.OK.getStatusCode());
			return Response.status(Status.OK.getStatusCode()).entity("comment removed successfully").build();
			
		}
		
	}
	
	
	

}
