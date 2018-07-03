package action.buyAction;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import action.form.SearchForm;
import beans.PostInfo;
import database.DbPostOp;
import database.DbRequestOp;

public class SearchAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Integer userID = (Integer) request.getSession().getAttribute("userID");

		SearchForm frm = trimSearchForm((SearchForm)form);

		DataSource source = getDataSource(request);
		DbPostOp postOp = null;
		DbRequestOp requestOp = null;

		ArrayList<PostInfo> temporaryList = null;
		ArrayList<PostInfo> searchList = new ArrayList<PostInfo>();
		ArrayList<Integer> postIDList = null;

		try {
			postOp = new DbPostOp(source.getConnection());
			temporaryList =  postOp.searchPublicPostBookList(userID, frm);

			requestOp = new DbRequestOp(source.getConnection());
			postIDList = requestOp.getRequestedPostID(userID);


			for (PostInfo pb:temporaryList) {
				if(!postIDList.contains(pb.getPostID())) {
					searchList.add(pb);
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

		request.setAttribute("searchList", searchList);

		return mapping.findForward("result");
	}

	private SearchForm trimSearchForm (SearchForm form) {

		form.setItemName(form.getItemName().trim());
		form.setWriter(form.getWriter().trim());
		form.setIsbn(form.getIsbn().trim());
		form.setCourseNo(form.getCourseNo().trim());
		form.setCourseName(form.getCourseName().trim());
		form.setProfessor(form.getProfessor().trim());

		return form;
	}

}
