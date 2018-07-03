package action.sellAction;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import action.form.PostForm;
import database.DbPostOp;

public class ConfirmPostAction extends Action {

	private final static String LOCAL_FILE_PATH = common.Contants.LOCAL_PIC_DIRECTORY_PATH;
	private final static String SERVER_FILE_PATH = common.Contants.SERVER_PIC_DIRETORY_PATH;
	private final static String NO_IMAGE_FILE = common.Contants.NO_IMAGE_FILE_PATH;

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		if (this.isCancelled(request)) {
			return mapping.findForward("back");
		}

		HttpSession session = request.getSession();
		Integer userID = (Integer) session.getAttribute("userID");
		PostForm frm = (PostForm) form;
		FormFile file = frm.getFile();
		String userLocalFilePath = LOCAL_FILE_PATH + userID;
		String tomcatPath = request.getSession().getServletContext().getRealPath("/")+"pic/"+userID;
		String fileNameServerPath = SERVER_FILE_PATH + userID + "/" +file.getFileName();

		DataSource source = getDataSource(request);
		DbPostOp postOp = null;

		try {
			postOp = new DbPostOp(source.getConnection());

			//入力された情報をbookテーブルに登録する
			int bookID = postOp.insertBook(userID, frm.getItemName(),
					frm.getVersion(), frm.getWriter(), frm.getIsbn(),
					frm.getCourseNo(), frm.getCourseName(), frm.getProfessor(),
					frm.getPrice(), frm.getCondition(),(file.getFileName().equals(""))?NO_IMAGE_FILE:fileNameServerPath);

			//登録されたbookIDをポストテーブルに登録する
			postOp.insertPost(userID,bookID, frm.getPeriod());

			postOp.commit();

			//画像パスを登録する
			uploadFile(file, userLocalFilePath);
			uploadFile(file, tomcatPath);

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

		session.removeAttribute("PostForm");

		return mapping.findForward("confirm");
	}

	/**
     * サーバーに登録されたファイルをアップロードする
     * @param file ファイル自体
     * 		 filePath ファイルパス
     * @return true->アップロード成功 false->アップロード失敗
     */
	private boolean uploadFile(FormFile file, String filePath) throws Exception {

		boolean uploadSuccess = false;

		File folder = new File(filePath);
		if (!folder.exists()) {
			folder.mkdir();
		}

		String fileName = file.getFileName();

		if (!("").equals(fileName)) {

			File newFile = new File(filePath, fileName);

			if (!newFile.exists()) {
				FileOutputStream fos = new FileOutputStream(newFile);
				fos.write(file.getFileData());
				fos.flush();
				fos.close();
				uploadSuccess = true;
			}
		}

		return uploadSuccess;

	}
}
