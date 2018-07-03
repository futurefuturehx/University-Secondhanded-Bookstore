package action.sellAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import action.form.StatusChangeForm;
import database.DbPostOp;

public class StatusChangeAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		StatusChangeForm frm = (StatusChangeForm)form;
		
		DataSource source = getDataSource(request);
		DbPostOp postOp = null;

		try {
			postOp = new DbPostOp(source.getConnection());
			postOp.updateStatus(frm.getPostID(), Integer.parseInt(frm.getStatus()));
			postOp.commit();

		} catch (Exception ex) {
			if (postOp != null)
				postOp.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			if (postOp != null) {
				postOp.close();
			}
		}
		request.getSession().removeAttribute("StatusChangeForm");
		
		return mapping.findForward("postmanager");
	}

}
