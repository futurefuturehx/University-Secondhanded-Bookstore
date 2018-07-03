package beans;

public class SellRequest {

	private int user_id;
	private int post_id;
	private String item_name;
	private String email;
	private String message;
	private String inpdate;


	public SellRequest() {
		user_id = 0;
		post_id = 0;
		item_name = "";
		email = "";
		message = "";
		inpdate = "";
	}


	public int getUser_id() {
		return user_id;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}


	public int getPost_id() {
		return post_id;
	}


	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getInpdate() {
		return inpdate;
	}


	public void setInpdate(String inpdate) {
		this.inpdate = inpdate;
	}


	public String getItem_name() {
		return item_name;
	}


	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}



}
