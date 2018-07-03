package action.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.validator.ValidatorForm;

public class LoginForm extends ValidatorForm {

	private String email;
	private String password;

	public LoginForm() {
		email = "";
		password = "";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 未入力エラー対応処理
	 *
	 * @return 処理されたエラーActionErrorsオブジェクト
	 */

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {

		ActionErrors errors = super.validate(mapping, request);

		if (hasLoginRequiredErr(email, password)) {
			errors.add("login_required_error", new ActionMessage(
					"error.login.required"));
		}

		return errors;
	}

	private boolean hasLoginRequiredErr(String email, String password) {
		return (email.equals("") || password.equals(""));
	}

}
