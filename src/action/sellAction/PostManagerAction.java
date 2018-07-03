package action.sellAction;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.PostInfo;
import database.DbPostOp;

public class PostManagerAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Integer userID = (Integer) request.getSession().getAttribute("userID");

		DataSource source = getDataSource(request);
		DbPostOp postOp = null;

		ArrayList<PostInfo> pbActiveList = null;
		ArrayList<PostInfo> pbExpiredList = null;

		try {
			postOp = new DbPostOp(source.getConnection());
			pbActiveList =  postOp.getMyPostBookList(userID, true);
			pbExpiredList =  postOp.getMyPostBookList(userID, false);
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

		request.setAttribute("pbActiveList", pbActiveList);
		request.setAttribute("pbExpiredList", pbExpiredList);

		return mapping.findForward("postmanager");
	}

}
