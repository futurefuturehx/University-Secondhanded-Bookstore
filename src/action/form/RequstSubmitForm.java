package action.form;

import org.apache.struts.action.ActionForm;

public class RequstSubmitForm extends ActionForm {

	private String message;

	public RequstSubmitForm() {
		message = "";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}



}
