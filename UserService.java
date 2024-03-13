package packageName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class UserService {
private Map<String,Users> users= DatabaseClass.getUsers();
public UserService() {
	 
	Users user1= new Users("sara","sara@123",UUID.randomUUID().toString());
	Users user2= new Users("basma","basma@123",UUID.randomUUID().toString());
	users.put(user1.getUserName(), user1);
	users.put(user2.getUserName(), user2);
}
public List<Users> getAllUsers() {
	return new ArrayList<Users>(users.values());
}
public void addUser(Users user) {
	users.put(user.getUserName(), user);
	
}
public Users getUser(String userName) {
	return users.get(userName);
}
public Users updateUser(Users user) {
	if(user.getUserName()==null) {
		return null;
	}
	else {
		users.put(user.getUserName(), user);
		return user;
	}
}
public Users deleteUser(String userName) {
	return users.remove(userName);
}

}
