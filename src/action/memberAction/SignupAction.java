package action.memberAction;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import action.form.SignupForm;
import database.DbMemberOp;

public class SignupAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		if (this.isCancelled(request)) {
			return mapping.findForward("back");
		}

		SignupForm frm = (SignupForm) form;

		String email = frm.getEmail();
		String username = frm.getUsername().trim();
		String password = frm.getPassword();

		DataSource source = getDataSource(request);
		DbMemberOp op = null;
		boolean hasErr = false;

		try {
			op = new DbMemberOp(source.getConnection());

			ActionMessages errors = new ActionMessages();

			if (op.hasEmail(email)) {
				errors.add("exisitingEmail", new ActionMessage(
						"error.email.existing"));
				hasErr = true;
			}

			if (op.hasUsername(username)) {
				errors.add("exisitingUsername", new ActionMessage(
						"error.username.existing"));
				hasErr = true;
			}

			saveErrors(request, errors);
			if (hasErr) {
				return mapping.findForward("signup");
			}

			op.registerMember(email, username, password);
			op.commit();


		} catch (SQLException ex) {
			if(op != null) op.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			if (op != null) {
				op.close();
			}
		}

		return mapping.findForward("confirm");
	}

}
