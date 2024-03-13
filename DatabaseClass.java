package packageName;

import java.util.HashMap;
import java.util.Map;

public class DatabaseClass {
	private static Map<Long,Post> posts=new HashMap<>(); 
	private static Map<String,profile> profiles= new HashMap<>();// the key is the profile name and the profile instance is the value
	private static Map<String,Users> users=new HashMap<>();
	public static Map<Long,Post> getPosts(){
		return posts;
	}
	public static Map<String,profile> getProfiles(){
		return profiles;
	}
	public static Map<String,Users> getUsers(){
		return users;
	}


}
