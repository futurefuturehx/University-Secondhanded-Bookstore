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

import database.DbMemberOp;

public class SettingAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();

		if(session.getAttribute("username")==null){

			String email = (String) session.getAttribute("email");
			DataSource source = getDataSource(request);
			DbMemberOp op = null;
			String username = null;

			try {
				op = new DbMemberOp(source.getConnection());

				username = op.getUsername(email);

			} catch (SQLException ex) {
				ex.printStackTrace();
				throw ex;
			} finally {
				if (op != null) {
					op.close();
				}
			}
			session.setAttribute("username", username);
		}
		return mapping.findForward("setting");
	}

}
