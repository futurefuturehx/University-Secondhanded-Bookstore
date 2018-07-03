package action.buyAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import util.EmailSender;
import action.form.RequstSubmitForm;
import database.DbMemberOp;
import database.DbPostOp;
import database.DbRequestOp;

public class SubmitRequestAction extends Action {

	//メール機能 ON/OFF
	private static final boolean SEND_MAIL = common.Contants.SEND_MAIL;

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		RequstSubmitForm frm = (RequstSubmitForm)form;

		Integer userID = (Integer) session.getAttribute("userID");
		int postID = Integer.parseInt(request.getParameter("postID"));
		String message = frm.getMessage();


		DataSource source = getDataSource(request);
		DbRequestOp requestOp = null;
		DbMemberOp memberOp = null;
		DbPostOp postOp = null;

		boolean alreadyRequested = false;

		try {
			//すでにリクエストしたかを確認
			requestOp = new DbRequestOp(source.getConnection());
			alreadyRequested = requestOp.alreadyRequested(postID, userID);

			System.out.println("requested: " + alreadyRequested);

			if (alreadyRequested) {
				System.out.println("Entered error loop");
				ActionMessages errors = new ActionMessages();
				errors.add("alreadyRequested", new ActionMessage(
						"error.request.already"));
				saveErrors(request, errors);
				return new ActionForward(mapping.findForward("detail").getPath()+"?postID="+Integer.toString(postID), false);
			}

			//リクェストを登録
			requestOp.insertRequest(userID, postID, message);
			requestOp.commit();

			//買い手にメールを送る
			if(SEND_MAIL) {
				String senderMailAddress = null;
				String recepientMailAddress = null;
				String bookName = null;

				memberOp = new DbMemberOp(source.getConnection());
				senderMailAddress = memberOp.getCurentUserEmail(userID);
				recepientMailAddress = memberOp.getSellerMail(postID);
				System.out.println(senderMailAddress);
				System.out.println(recepientMailAddress);

				postOp = new DbPostOp(source.getConnection());
				bookName = postOp.getBookName(postID);

				if(senderMailAddress!=null&&recepientMailAddress!=null) {
					if(isValidEmail(senderMailAddress)&&isValidEmail(recepientMailAddress)){

						String title = "BookStore: Puchasing Request for「" + bookName + "」";
						String text = "Your received a puchasing request for「" + bookName + "」\n "
								+ "Message: "+message + "\n\n"
								+ "Please reply to " + recepientMailAddress +" \n\n"
								+ "bookstore.com";

						EmailSender.sendMail(recepientMailAddress, title, text);
					}
				}
			}


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

		System.out.println(frm.getMessage());

		return mapping.findForward("submitRequest");

	}
    /**
     * Eメールフォーマットチェック
     * @param email
     * @return true->email false->not email
     */
	private boolean isValidEmail (String address) {
		return address.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$");
	}

}
