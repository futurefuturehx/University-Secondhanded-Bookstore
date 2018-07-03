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
import beans.SellRequest;
import database.DbPostOp;
import database.DbRequestOp;

public class PostExpiredDetailAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String postID = request.getParameter("postID");

		PostInfo pb = null;
		ArrayList<SellRequest> requestList = null;

		DataSource source = getDataSource(request);
		DbRequestOp requestOp = null;
		DbPostOp postOp = null;

		try {
			// 渡されたpostIDでポストを取得
			postOp = new DbPostOp(source.getConnection());
			pb = postOp.getPostBook(Integer.parseInt(postID));

			//そのポストに対するリクェストを取得
			requestOp = new DbRequestOp(source.getConnection());
			requestList = requestOp.getPostRequest(Integer.parseInt(postID));

		} catch (Exception ex) {
			if (postOp != null)
				postOp.rollback();
			if (requestOp != null)
				requestOp.rollback();
			ex.printStackTrace();
			throw ex;
		} finally {
			if (postOp != null) {
				postOp.close();
			}
			if (requestOp != null) {
				requestOp.close();
			}
		}

		request.setAttribute("currentPost", pb);
		request.setAttribute("requestList", requestList);

		return mapping.findForward("detail");
	}
}
