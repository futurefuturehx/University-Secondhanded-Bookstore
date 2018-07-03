package action.form;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.validator.ValidatorForm;

public class SignupForm extends ValidatorForm {

	private final static String MAIL_DOMAIN = "gmail.com";

	String email;
	String username;
	String password;
	String password_conf;

	public SignupForm() {
		email = "";
		username = "";
		password = "";
		password_conf = "";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword_conf() {
		return password_conf;
	}

	public void setPassword_conf(String password_conf) {
		this.password_conf = password_conf;
	}

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {

		ActionErrors errors = super.validate(mapping, request);

		boolean hasValidatorEmailErr = false;

		Iterator itr = errors.properties();
		while(itr.hasNext()) {
			if(itr.next().equals("email")){
				hasValidatorEmailErr = true;
				break;
			}
		}

		if (!hasValidatorEmailErr&&!hasValidDomain(email)) {
			errors.add("email_domain_error", new ActionMessage(
					"error.email.domain"));
		}

		if (errors.isEmpty()) {
			if (!password.equals(password_conf)) {
				errors.add("password_confirm_error", new ActionMessage(
						"error.password.confirm.notmatch"));
			}
		}

		return errors;
	}

	private boolean hasValidDomain (String email) {
		String str[] = email.split("@");
		return str[1].equals(MAIL_DOMAIN);
	}



}
