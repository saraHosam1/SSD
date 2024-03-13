package packageName;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
@XmlRootElement (name="Post")
public class Post {
	private long id;
	private String post;
	private Date created;
	private String author;
	private Map <Long,Comment> comments= new HashMap<>();
	@XmlTransient
	public Map<Long, Comment> getComments() {
		return comments;
	}
	public void setComments(Map<Long, Comment> comments) {
		this.comments = comments;
	}
	@XmlElement
	public long getId() {
		return id;
	}
	
	public Post(long id, String post, String author) {
		Comment c1 = new Comment(1,"hello its sara comment","sara");
		Comment c2 = new Comment(2,"hello its basma comment","basma");
		this.comments.put(1l, c1);
		this.comments.put(2l, c2);
		this.id = id;
		this.post = post;
		this.author = author;
		this.created=new Date();
	}

	public Post() {
		
	}

	public void setId(long id) {
		this.id = id;
	}
	@XmlElement
	public String getPost() {
		return post;
	}
	public void setPost(String message) {
		this.post = message;
	}
	@XmlElement
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	@XmlElement
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	



}
