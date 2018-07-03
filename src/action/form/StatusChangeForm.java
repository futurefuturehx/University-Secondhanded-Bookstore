package action.form;

import org.apache.struts.action.ActionForm;

public class StatusChangeForm extends ActionForm {

	private String status;
	private int postID;

	public StatusChangeForm() {
		status = "1";
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getPostID() {
		return postID;
	}

	public void setPostID(int postID) {
		this.postID = postID;
	}
	
	


}
