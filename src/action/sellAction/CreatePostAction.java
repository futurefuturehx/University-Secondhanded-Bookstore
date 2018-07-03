package action.sellAction;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;

import action.form.PostForm;

public class CreatePostAction extends Action {

	private final static String FILE_PATH = common.Contants.LOCAL_PIC_DIRECTORY_PATH;

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PostForm frm = (PostForm) form;
		frm = trimPostForm(frm);

		FormFile file = frm.getFile();
		String fileName = file.getFileName();

		//ファイルの拡張子が間違った場合エラーを返す
		if(!fileName.isEmpty()&&!isValidFileType(fileName)) {
			ActionMessages errors = new ActionMessages();
			errors.add("invalidFile", new ActionMessage(
					"error.createPost.invalid.extension"));
			saveErrors(request, errors);
			return mapping.findForward("createPost");
		}

		Integer userID = (Integer) request.getSession().getAttribute("userID");
		String filePath = FILE_PATH  + userID;

		File newFile = new File(filePath, fileName);

		//ファイル名が既に登録されている場合エラーを返す
		if (!fileName.isEmpty()&&newFile.exists()) {
			ActionMessages errors = new ActionMessages();
			errors.add("exisitingFile", new ActionMessage(
					"error.createPost.file.exist"));
			saveErrors(request, errors);
			return mapping.findForward("createPost");
		}


		return mapping.findForward("regist");
	}

	private boolean isValidFileType (String filename) {

		String validExtension[] = {"jpg","png","jpeg","gif","bmp","tif","tiff"};

		String currentExtension = filename.substring(filename.lastIndexOf(".") + 1);

		for(String str: validExtension) {
			if(str.equals(currentExtension)) {
				return true;
			}
		}

		return false;

	}


	private PostForm trimPostForm (PostForm form) {

		form.setItemName(form.getItemName().trim());
		form.setVersion(form.getVersion().trim());
		form.setWriter(form.getWriter().trim());
		form.setIsbn(form.getIsbn().trim());
		form.setCourseNo(form.getCourseNo().trim());
		form.setCourseName(form.getCourseName().trim());
		form.setProfessor(form.getProfessor().trim());
		form.setCondition(form.getCondition().trim());

		return form;
	}

}
