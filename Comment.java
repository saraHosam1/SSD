package packageName;

import java.util.Date;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
@XmlRootElement (name="Comment")

public class Comment {
	private long commentID;
	private String comment;
	private String author;
	private Date created;
	public Comment() {
		
	}
	
	public Comment(long commentID, String comment, String author) {
	
		this.commentID = commentID;
		this.comment = comment;
		this.author = author;
		this.created = new Date();
	}
	@XmlElement
	public long getCommentID() {
		return commentID;
	}
	public void setCommentID(long commentID) {
		this.commentID = commentID;
	}
	@XmlElement
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@XmlElement
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	@XmlElement
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	

}
