package packageName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommentService {
//	MessageServic m=new MessageServic();
	Map<Long,Post> posts=DatabaseClass.getPosts();
	public List<Comment> getAllComments(long postId) {
		return new ArrayList<Comment>(posts.get(postId).getComments().values());
		
	}
	public Comment getComment(long postID,long commentID) {
		Map<Long,Comment>comments=posts.get(postID).getComments();
		return comments.get(commentID);
	}
	public Comment addComment(long postId,Comment c) {
		Map<Long,Comment> comments=posts.get(postId).getComments();
		c.setCommentID(comments.size()+1);
		comments.put(c.getCommentID(), c);
		return c;
		
		
	}
	public Comment updateComment(long postId,Comment comment) {
		Map<Long,Comment> comments=posts.get(postId).getComments();
		if(comment.getCommentID()<=0) {
			return null;
		}
		comments.put(comment.getCommentID(), comment);
		return comment;
		
	}
	public Comment removeComment(long postId,long commentId) {
		Map<Long,Comment>coments=posts.get(postId).getComments();
		return coments.remove(commentId);
	}

}
