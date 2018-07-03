package action.sellAction;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.SellRequest;
import database.DbRequestOp;

public class RequestNotificationAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Integer userID = (Integer) request.getSession().getAttribute("userID");

		ArrayList<SellRequest> activeRequestList = new ArrayList<SellRequest>();
		ArrayList<SellRequest> expiredRequestList = new ArrayList<SellRequest>();

		DataSource source = getDataSource(request);
		DbRequestOp requestOp = null;

		try {
			requestOp = new DbRequestOp(source.getConnection());
			activeRequestList = requestOp.getMyRequest(userID, true);
			expiredRequestList = requestOp.getMyRequest(userID, false);


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

		request.setAttribute("activeRequestList", activeRequestList);
		request.setAttribute("expiredRequestList", expiredRequestList);

		return mapping.findForward("requestNotification");
	}

}
