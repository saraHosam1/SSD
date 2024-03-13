package packageName;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;



public class PostServic {
	private Map<Long,Post> posts=DatabaseClass.getPosts();
	
	public PostServic () {
		posts.put(1l, new Post(1,"hello","sara"));
		posts.put(2l, new Post(2,"world","yousf"));
	}
	
	public List<Post> getAllPosts(){
		return new ArrayList<Post>(posts.values());
		
	}
	public Map<Long,Post> getAllPostshash(){
		return posts;
		
	}
	public List<Post> getALlPostForaYear(int year){
		List <Post>list=new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for(Post post:posts.values()) {
			cal.setTime(post.getCreated());
			if(cal.get(Calendar.YEAR)==year) {
				list.add(post);
			}
		}
		return list;
		
	}
	public List <Post> getAllPostsPaginated(int start,int size){
		List <Post> list = new ArrayList<>(posts.values());
		if(start+size>list.size()) return new ArrayList<Post>();
		return list.subList(start, start+size);
	}
	public Post getPost(long num) {
		return posts.get(num);
		
	}
	public Post deletePost (long id) {
	return	posts.remove(id);
	}
	public Post addPost(Post m) {
		m.setId(posts.size()+1);
		posts.put(m.getId(),m);
		return m;
		
	}
	public Post updatePost(Post m) {
		if(m.getId()<=0) {
			return null;
		}
		posts.put(m.getId(),m);
		return m;
		
	}
	
		
		

}
