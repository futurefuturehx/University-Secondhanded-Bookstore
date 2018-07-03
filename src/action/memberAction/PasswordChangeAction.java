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

import action.form.PasswordResetForm;
import database.DbMemberOp;

public class PasswordChangeAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PasswordResetForm frm = (PasswordResetForm) form;

		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");

		DataSource source = getDataSource(request);
		DbMemberOp op = null;

		try {

			op = new DbMemberOp(source.getConnection());

			if (!op.hasEmailandPasswordSet(email, frm.getOld_password())) {
				ActionMessages errors = new ActionMessages();
				errors.add("exisitingEmail", new ActionMessage(
						"error.setting.password.invalid"));
				saveErrors(request, errors);
				return mapping.findForward("setting");
			}

			op.updatePassword(email, frm.getNew_password1());
			op.commit();

		} catch (SQLException ex) {
			if (op != null)
				op.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			if (op != null) {
				op.close();
			}
		}

		return mapping.findForward("change");
	}

}
