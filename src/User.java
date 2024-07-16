
public class User implements Comparable<User>{
	private String username;
	private String password;
	private String tag;
	
	User(String username, String password, String tag){
		this.username = username;
		this.password = password;
		this.tag = tag;
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
	
	protected String getTag() {
		return this.tag;
	}
	
	protected String getName() {
		return this.username;
	}
	
	
}
