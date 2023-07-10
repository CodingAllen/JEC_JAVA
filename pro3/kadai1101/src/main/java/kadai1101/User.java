package kadai1101;

public class User {
	private String userId;
	private String password;
	private String userName;
	private String photo;

	public User(String userId, String password, String userName, String photo) {
		super();
		this.userId = userId;
		this.password = password;
		this.userName = userName;
		this.photo = photo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

}
