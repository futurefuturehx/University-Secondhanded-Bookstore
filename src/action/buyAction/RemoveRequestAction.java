package action.buyAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import action.form.RemoveRequestForm;
import database.DbRequestOp;

public class RemoveRequestAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RemoveRequestForm frm = (RemoveRequestForm)form;
		
		int requestID = Integer.parseInt(frm.getRequestID());
		
		DataSource source = getDataSource(request);
		DbRequestOp requestOp = null;
		
		try {
			requestOp = new DbRequestOp(source.getConnection());
			requestOp.removeRequest(requestID);
			requestOp.commit();

		} catch (Exception ex) {
			if (requestOp != null)
				requestOp.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			if (requestOp != null) {
				requestOp.close();
			}
		}
		return mapping.findForward("remove");
	}

}
