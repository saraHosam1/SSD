package packageName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class profileService {
	private Map<String,profile> profiles=DatabaseClass.getProfiles();
	public profileService(){
		profiles.put("Sara",new profile(1L,"Sara","sara","Hosam") );
		profiles.put("basma",new profile(2L,"basma","basma","Hosam") );
		
	}
	public List<profile> getProfiles(){
		return new ArrayList<profile>(profiles.values());
	}
	public profile getProfile(String profileNAme) {
		return profiles.get(profileNAme);
	}

	public profile addProfile(profile p) {
		p.setId(profiles.size()+1);
		 profiles.put(p.getProfileName(), p);
		 return p;
	}
	public profile updteProfile(profile p) {
		if(p.getProfileName().isEmpty()) {
			return null;
		}
		 profiles.put(p.getProfileName(), p);
		 return p;
	}
	public profile removeProfile(String profileName) {
		return profiles.remove(profileName);
	}


}
