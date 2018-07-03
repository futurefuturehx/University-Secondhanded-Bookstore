package action.memberAction;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import action.form.LoginForm;
import database.DbMemberOp;


public class LoginAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		LoginForm frm = (LoginForm) form;
		String email = frm.getEmail();
		String password = frm.getPassword();
		int UserID = 0;

		DataSource source = getDataSource(request);
		DbMemberOp op = null;


		try {
			op = new DbMemberOp(source.getConnection());

			if (!op.hasEmailandPasswordSet(email, password)) {
				ActionMessages errors = new ActionMessages();
				errors.add("invalid", new ActionMessage(
						"error.login.invalid"));
				saveErrors(request, errors);
				return mapping.findForward("index");
			}

			UserID = op.getUserID(email);



		} catch (SQLException ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (op != null) {
				op.close();
			}
		}

		HttpSession session = request.getSession();
		session.setAttribute("email", email);
		session.setAttribute("userID", UserID);


		return mapping.findForward("login");
	}
}
