
public class User implements Comparable<User>{
	private String username;
	private String password;
	private String tag;
	private String id;
	
	User(String username, String password, String tag, String id){
		this.username = username;
		this.password = password;
		this.tag = tag;
		this.id = id;
	}

	@Override
	public int compareTo(User o) {
		if (this.username.compareTo(o.username) == 0 && this.password.compareTo(o.password) == 0 && this.tag.compareTo(o.tag) == 0) {
			//return 0 if pass and name and tag matches.
			return 0;
		}
		else if(this.username.compareTo(o.username) == 0 && this.password.compareTo(o.password) == 0) {
			//tag does not match.
			return 2;
		}
		return 1;
	}

	//Returns true if this user has the same id part with the given.
	protected boolean isSamePartialID(String idPart) {
		if(this.getPartialID().equals(idPart)) {
			return true;
		}
		return false;
	}
	

	//Returns true if this user has the same id with the given.
	protected boolean isSameID(String id) {
		if(id.equals(id)) {
			return true;
		}
		return false;
	}
	
	//Returns this user's id part
	protected String getPartialID() {
		String[] idParts = this.id.split("-");
		String tempIDPart = idParts[0];
		
		return tempIDPart;
	}
	
 	protected String getTag() {
		return this.tag;
	}
	
	protected String getName() {
		return this.username;
	}
	
	protected String getID() {
		return this.id;
	}
	
}
