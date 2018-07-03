package action.form;

import org.apache.struts.action.ActionForm;


public class SearchForm extends ActionForm {

	private String itemName;
	private String writer;
	private String isbn;
	private String courseNo;
	private String courseName;
	private String professor;
	private int priceRange;

	public SearchForm() {
		itemName = "";
		writer = "";
		isbn = "";
		courseNo = "";
		courseName = "";
		professor = "";
		priceRange = 0;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
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

	public int getPriceRange() {
		return priceRange;
	}

	public void setPriceRange(int priceRange) {
		this.priceRange = priceRange;
	}



}
