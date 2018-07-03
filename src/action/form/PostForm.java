package action.form;

import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorForm;


public class PostForm extends ValidatorForm {

	private String itemName;
	private String version;
	private String writer;
	private String isbn;
	private String courseNo;
	private String courseName;
	private String professor;
	private String price;
	private String condition;
	private String period;
	private FormFile file;


	public PostForm() {
		itemName = "";
		version = "";
		writer = "";
		isbn = "";
		courseNo = "";
		courseName = "";
		professor = "";
		price = "";
		condition = "";
		period = "";
		file = null;
	}


	public String getItemName() {
		return itemName;
	}


	public void setItemName(String itemName) {
		this.itemName = itemName;
	}


	public String getVersion() {
		return version;
	}


	public void setVersion(String version) {
		this.version = version;
	}


	public String getWriter() {
		return writer;
	}


	public void setWriter(String writer) {
		this.writer = writer;
	}


	public String getIsbn() {
		return isbn;
	}


	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}


	public String getCourseNo() {
		return courseNo;
	}


	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
	}


	public String getCourseName() {
		return courseName;
	}


	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}


	public String getProfessor() {
		return professor;
	}


	public void setProfessor(String professor) {
		this.professor = professor;
	}


	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}


	public String getCondition() {
		return condition;
	}


	public void setCondition(String condition) {
		this.condition = condition;
	}


	public FormFile getFile() {
		return file;
	}


	public void setFile(FormFile file) {
		this.file = file;
	}


	public String getPeriod() {
		return period;
	}


	public void setPeriod(String period) {
		this.period = period;
	}

}
