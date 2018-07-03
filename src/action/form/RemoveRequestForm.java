package action.form;

import org.apache.struts.action.ActionForm;

public class RemoveRequestForm extends ActionForm {

	String requestID;

	public RemoveRequestForm() {
		requestID = "";
	}

	public String getRequestID() {
		return requestID;
	}

	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}



}
