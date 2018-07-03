package action.buyAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.PostInfo;
import database.DbPostOp;

public class RequestedExpiredPostDetailAction extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String postID = request.getParameter("postID");

		PostInfo pb = null;

		DataSource source = getDataSource(request);

		DbPostOp postOp = null;

		try {
			// 渡されたpostIDでポストを取得
			postOp = new DbPostOp(source.getConnection());
			pb = postOp.getPostBook(Integer.parseInt(postID));

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

		request.setAttribute("currentPost", pb);

		return mapping.findForward("detail");
	}

}
