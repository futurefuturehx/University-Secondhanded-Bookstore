package beans;

public class PostInfo {

	private int bookID;
	private int postID;
	private String username;
	private String itemName;
	private String version;
	private String writer;
	private String isbn;
	private String courseNo;
	private String courseName;
	private String professor;
	private int price;
	private String condition;
	private String validTil;
	private int status;
	private String postInpdate;
	private String picturePass;

	public PostInfo() {
		bookID = 0;
		bookID = 0;
		username = "";
		itemName = "";
		version = "";
		writer = "";
		isbn = "";
		courseNo = "";
		courseName = "";
		professor = "";
		price = 0;
		condition = "";
		validTil = "";
		postInpdate = "";
		status = 1;
		picturePass = "";
	}

	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getValidTil() {
		return validTil;
	}

	public void setValidTil(String validTil) {
		this.validTil = validTil;
	}

	public String getPostInpdate() {
		return postInpdate;
	}

	public void setPostInpdate(String postInpdate) {
		this.postInpdate = postInpdate;
	}


	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}



	public String getPicturePass() {
		return picturePass;
	}

	public void setPicturePass(String picturePass) {
		this.picturePass = picturePass;
	}


	public int getPostID() {
		return postID;
	}

	public void setPostID(int postID) {
		this.postID = postID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "PostInfo [bookID=" + bookID + ", postID=" + postID
				+ ", username=" + username + ", itemName=" + itemName
				+ ", version=" + version + ", writer=" + writer + ", isbn="
				+ isbn + ", courseNo=" + courseNo + ", courseName="
				+ courseName + ", professor=" + professor + ", price=" + price
				+ ", condition=" + condition + ", validTil=" + validTil
				+ ", status=" + status + ", postInpdate=" + postInpdate
				+ ", picturePass=" + picturePass + "]";
	}



}
