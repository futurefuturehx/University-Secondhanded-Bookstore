package action.buyAction;

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
import database.DbRequestOp;

public class BuyHomeAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Integer userID = (Integer) request.getSession().getAttribute("userID");

		DataSource source = getDataSource(request);
		DbPostOp postOp = null;
		DbRequestOp requestOp = null;

		ArrayList<PostInfo> pbPublicActiveList = null;
		ArrayList<PostInfo> onsaleList = new ArrayList<PostInfo>();
		ArrayList<Integer> postIDList = null;

		try {
			postOp = new DbPostOp(source.getConnection());
			pbPublicActiveList =  postOp.getPublicPostBookList(userID, true);

			requestOp = new DbRequestOp(source.getConnection());
			postIDList = requestOp.getRequestedPostID(userID);


			//売り切れポストとリクェストしたポストをフィルターする

			for (PostInfo pb: pbPublicActiveList) {
				if(pb.getStatus()==1&&!postIDList.contains(pb.getPostID())){
				onsaleList.add(pb);
				}
			}

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

		request.setAttribute("onsaleList", onsaleList);

		return mapping.findForward("buyhome");


	}

}
