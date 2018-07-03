package action.buyAction;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.BuyRequest;
import database.DbRequestOp;

public class RequestManagerAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Integer userID = (Integer) request.getSession().getAttribute("userID");

		DataSource source = getDataSource(request);
		DbRequestOp requestOp = null;

		ArrayList<BuyRequest> activeBuyRequestList = null;
		ArrayList<BuyRequest> expiredBuyRequestList = null;

		try {

			requestOp = new DbRequestOp(source.getConnection());
			activeBuyRequestList = requestOp.getBuyRequestList(userID,true);
			expiredBuyRequestList = requestOp.getBuyRequestList(userID,false);

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

		request.setAttribute("activeBuyRequestList", activeBuyRequestList);
		request.setAttribute("expiredBuyRequestList", expiredBuyRequestList);

		return mapping.findForward("requestManager");
	}


}
