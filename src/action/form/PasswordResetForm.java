package action.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.validator.ValidatorForm;

public class PasswordResetForm extends ValidatorForm {
	private String old_password;
	private String new_password1;
	private String new_password2;

	public PasswordResetForm() {
		old_password = "";
		new_password1 = "";
		new_password2 = "";
	}

	public String getOld_password() {
		return old_password;
	}

	public void setOld_password(String old_password) {
		this.old_password = old_password;
	}

	public String getNew_password1() {
		return new_password1;
	}

	public void setNew_password1(String new_password1) {
		this.new_password1 = new_password1;
	}

	public String getNew_password2() {
		return new_password2;
	}

	public void setNew_password2(String new_password2) {
		this.new_password2 = new_password2;
	}

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {

		ActionErrors errors = super.validate(mapping, request);

		if (hasRequiredErr(old_password,new_password1,new_password2)) {
			errors.add("input_required_error", new ActionMessage(
					"error.setting.password.required"));
		} else if (!new_password1.equals(new_password2)) {
			errors.add("input_unmatch_error", new ActionMessage(
					"error.setting.password.unmatched"));
		}

		return errors;
	}

	private boolean hasRequiredErr(String pass1, String pass2, String pass3) {
		return (pass1.equals("") || pass2.equals("")||pass3.equals(""));
	}

}
