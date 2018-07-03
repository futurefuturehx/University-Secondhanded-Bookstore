package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import action.form.SearchForm;
import beans.PostInfo;

import com.mysql.jdbc.Statement;

public class DbPostOp extends DbMaster {

	public DbPostOp(Connection con) throws SQLException {
		super(con);
	}

    /**
     * 売り手画面(使用モードは売り手)
     * bookテーブルにポストを登録する
     * @param ポストの詳細
     * @return 登録されたのitem_id
     */
	public int insertBook(int userID, String itemName, String ver, String writer, String isbn, String courseNo,
			String courseName, String prof, String price, String condition, String picPass) throws SQLException {

		String query = "INSERT INTO book(user_id, item_name, version, writer, ISBN,"
				+ "course_no, course_name, professor, price, condtn, picture_pass, inpdate, upddate) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int generatedKey = -1;

		try {
			pstmt = (PreparedStatement) con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, userID);
			pstmt.setString(2, itemName);
			pstmt.setString(3, ver);
			pstmt.setString(4, writer);
			pstmt.setString(5, isbn);
			pstmt.setString(6, courseNo);
			pstmt.setString(7, courseName);
			pstmt.setString(8, prof);
			pstmt.setInt(9, Integer.parseInt(price));
			pstmt.setString(10, condition);
			pstmt.setString(11, picPass);
			pstmt.executeUpdate();

			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				generatedKey = rs.getInt(1);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
		}

		return generatedKey;
	}

    /**
     * 売り手画面(使用モードは売り手)
     * postテーブルにポストを登録する
     * @param ポストの詳細
     * @return 登録されたのpost_id
     */
	public int insertPost(int userID, int bookID, String validDayNo) throws SQLException {

		String query = "INSERT INTO `post`( `user_id`,`item_id`, `valid_til`, `status`, `inpdate`, `upddate`) VALUES (?,?,DATE_ADD(NOW(), INTERVAL ?  DAY) ,1,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int generatedKey = -1;

		try {
			pstmt = (PreparedStatement) con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, userID);
			pstmt.setInt(2, bookID);
			pstmt.setInt(3, Integer.parseInt(validDayNo));

			pstmt.executeUpdate();

			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				generatedKey = rs.getInt(1);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
		}

		return generatedKey;
	}

    /**
     * postIDで特定されたポスト詳細を取得
     * @param postID
     * @return PostInfo(ポスト詳細)
     */
	public PostInfo getPostBook(int postID) throws SQLException {

		PostInfo info = new PostInfo();

		String query = "SELECT post.item_id,book.item_name,book.version,book.writer,book.ISBN,"
				+ "book.course_no,book.course_name,book.professor,book.price,book.condtn,"
				+ "post.valid_til,post.status,post.inpdate,book.picture_pass,user.username "
				+ "FROM post "
				+ "INNER JOIN book ON post.item_id = book.item_id "
				+ "INNER JOIN user ON post.user_id = user.user_id "
				+ "WHERE post.post_id = ?";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = (PreparedStatement) con.prepareStatement(query);
			pstmt.setInt(1, postID);

			rs = pstmt.executeQuery();

			rs.next();

			info.setBookID(rs.getInt(1));
			info.setPostID(postID);
			info.setItemName(rs.getString(2));
			info.setVersion(rs.getString(3));
			info.setWriter(rs.getString(4));
			info.setIsbn(rs.getString(5));
			info.setCourseNo(rs.getString(6));
			info.setCourseName(rs.getString(7));
			info.setProfessor(rs.getString(8));
			info.setPrice(rs.getInt(9));
			info.setCondition(rs.getString(10));
			info.setValidTil(rs.getString(11));
			info.setStatus(rs.getInt(12));
			info.setPostInpdate(rs.getString(13));
			info.setPicturePass(rs.getString(14));
			info.setUsername(rs.getString(15));

		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
		}

		return info;
	}

    /**
     * 売り手画面(使用モードは売り手)
     * 売り手の登録したポストを取得
     * @param userID 売り手のuserID
     * 		 active true-->有効ポスト false-->無効ポスト
     * @return 取得したポストのリスト
     */
	public ArrayList<PostInfo> getMyPostBookList(int userID, boolean active) throws SQLException {

		ArrayList<PostInfo> postBookList = new ArrayList<PostInfo>();

		String validCon;
		if (active) {
			validCon = ">= NOW()";
		} else {
			validCon = "< NOW()";
		}

		String query = "SELECT post.post_id,post.item_id,post.valid_til,post.status,post.inpdate,"
				+ "book.item_name,book.version,book.writer,book.ISBN,book.course_no,book.course_name,"
				+ "book.professor,book.price,book.condtn,book.picture_pass "
				+ "FROM post INNER JOIN book ON post.item_id = book.item_id "
				+ "WHERE post.user_id = ? AND post.valid_til " + validCon + " ORDER BY inpdate DESC";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = (PreparedStatement) con.prepareStatement(query);
			pstmt.setInt(1, userID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				PostInfo pb = new PostInfo();
				pb.setPostID(rs.getInt(1));
				pb.setBookID(rs.getInt(2));
				pb.setValidTil(rs.getString(3));
				pb.setStatus(rs.getInt(4));
				pb.setPostInpdate(rs.getString(5));
				pb.setItemName(rs.getString(6));
				pb.setVersion(rs.getString(7));
				pb.setWriter(rs.getString(8));
				pb.setIsbn(rs.getString(9));
				pb.setCourseNo(rs.getString(10));
				pb.setCourseName(rs.getString(11));
				pb.setProfessor(rs.getString(12));
				pb.setPrice(rs.getInt(13));
				pb.setCondition(rs.getString(14));
				pb.setPicturePass(rs.getString(15));
				postBookList.add(pb);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
		}

		return postBookList;
	}

    /**
     * 買い手画面(使用モードは買い手)
     * 売り手の登録したポストを取得(現在ログインしているユーザーのポストを除く)
     * @param userID 買い手のuserID
     * 		 active true-->有効ポスト false-->無効ポスト
     * @return 取得したポストのリスト
     */
	public ArrayList<PostInfo> getPublicPostBookList(int myUserID, boolean active) throws SQLException {

		ArrayList<PostInfo> postBookList = new ArrayList<PostInfo>();

		String validCon;
		if (active) {
			validCon = ">= NOW()";
		} else {
			validCon = "< NOW()";
		}

		String query = "SELECT post.post_id,post.item_id,post.valid_til,post.status,post.inpdate,"
				+ "book.item_name,book.version,book.writer,book.ISBN,book.course_no,book.course_name,"
				+ "book.professor,book.price,book.condtn,book.picture_pass,user.username "
				+ "FROM post "
				+ "INNER JOIN book ON post.item_id = book.item_id "
				+ "INNER JOIN user ON post.user_id = user.user_id "
				+ "WHERE post.user_id != ? AND post.valid_til " + validCon + " ORDER BY inpdate DESC";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = (PreparedStatement) con.prepareStatement(query);
			pstmt.setInt(1, myUserID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				PostInfo pb = new PostInfo();
				pb.setPostID(rs.getInt(1));
				pb.setBookID(rs.getInt(2));
				pb.setValidTil(rs.getString(3));
				pb.setStatus(rs.getInt(4));
				pb.setPostInpdate(rs.getString(5));
				pb.setItemName(rs.getString(6));
				pb.setVersion(rs.getString(7));
				pb.setWriter(rs.getString(8));
				pb.setIsbn(rs.getString(9));
				pb.setCourseNo(rs.getString(10));
				pb.setCourseName(rs.getString(11));
				pb.setProfessor(rs.getString(12));
				pb.setPrice(rs.getInt(13));
				pb.setCondition(rs.getString(14));
				pb.setPicturePass(rs.getString(15));
				pb.setUsername(rs.getString(16));
				postBookList.add(pb);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
		}

		return postBookList;
	}

    /**
     * 売り手画面(使用モードは売り手)
     * ポストのステータスを変更
     * @param postID　
     * 		　status　目標のステータス
     */
	public void updateStatus(int postID, int status) throws SQLException {

		String query = "Update post SET status = ? ,upddate = CURRENT_TIMESTAMP WHERE post_id = ?";
		PreparedStatement pstmt = null;

		try {
			pstmt = (PreparedStatement) con.prepareStatement(query);
			pstmt.setInt(1, status);
			pstmt.setInt(2, postID);
			pstmt.executeUpdate();

		} finally {
			if (pstmt != null)
				pstmt.close();
		}
	}

	/**
     * 買い手画面(使用モードは買い手)
     * 入力された値でポストを検索する
     * @param itemName,writer,isbn,courseNo,courseName,professor,priceRange --> 検索項目
     * @return 検索の結果 PostInfoのリスト
     */

	public ArrayList<PostInfo> searchPublicPostBookList(int userID, String itemName, String writer, String isbn, String courseNo,
			String courseName, String professor, int priceRange) throws SQLException {

		ArrayList<PostInfo> postBookList = new ArrayList<PostInfo>();

		String query = "SELECT post.item_id,post.post_id,book.item_name,book.version,book.writer,book.ISBN,"
				+ "book.course_no,book.course_name,book.professor,book.price,book.condtn,"
				+ "post.valid_til,post.inpdate,book.picture_pass,user.username "
				+ "FROM post "
				+ "INNER JOIN book ON post.item_id = book.item_id "
				+ "INNER JOIN user ON post.user_id = user.user_id "
				+ "WHERE post.status = 1 AND "
				+ "book.item_name LIKE ? AND "
				+ "book.writer LIKE ? AND "
				+ "book.ISBN LIKE ? AND "
				+ "book.course_no LIKE ? AND "
				+ "book.course_name LIKE ? AND "
				+ "book.professor LIKE ? AND "
				+ "book.price BETWEEN ? AND ? AND "
				+ "user.user_id != ? AND "
				+ "post.valid_til >= NOW() "
				+ "ORDER BY post.inpdate DESC";

		String itemNameCon;
		String writerCon;
		String isbnCon;
		String courseNoCon;
		String courseNameCon;
		String professorCon;
		int priceRangeFromCon;
		int priceRangeToCon;

		if (itemName.equals("")) {
			itemNameCon = "%";
		} else {
			itemNameCon = "%" + itemName + "%";
		}

		if (writer.equals("")) {
			writerCon = "%";
		} else {
			writerCon = "%" + writer + "%";
		}

		if (isbn.equals("")) {
			isbnCon = "%";
		} else {
			isbnCon = isbn;
		}

		if (courseNo.equals("")) {
			courseNoCon = "%";
		} else {
			courseNoCon = courseNo;
		}

		if (courseName.equals("")) {
			courseNameCon = "%";
		} else {
			courseNameCon = "%" + courseName + "%";
		}

		if (professor.equals("")) {
			professorCon = "%";
		} else {
			professorCon = "%" + professor + "%";
		}

		switch (priceRange) {
		case 0:
			priceRangeFromCon = 0;
			priceRangeToCon = 2147483647;
			break;
		case 1:
			priceRangeFromCon = 0;
			priceRangeToCon = 1000;
			break;
		case 2:
			priceRangeFromCon = 1001;
			priceRangeToCon = 2000;
			break;
		case 3:
			priceRangeFromCon = 2001;
			priceRangeToCon = 3000;
			break;
		case 4:
			priceRangeFromCon = 3001;
			priceRangeToCon = 2147483647;
			break;
		default:
			priceRangeFromCon = 0;
			priceRangeToCon = 2147483647;
			break;
		}

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = (PreparedStatement) con.prepareStatement(query);
			pstmt.setString(1, itemNameCon);
			pstmt.setString(2, writerCon);
			pstmt.setString(3, isbnCon);
			pstmt.setString(4, courseNoCon);
			pstmt.setString(5, courseNameCon);
			pstmt.setString(6, professorCon);
			pstmt.setInt(7, priceRangeFromCon);
			pstmt.setInt(8, priceRangeToCon);
			pstmt.setInt(9, userID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				PostInfo pb = new PostInfo();
				pb.setBookID(rs.getInt(1));
				pb.setPostID(rs.getInt(2));
				pb.setItemName(rs.getString(3));
				pb.setVersion(rs.getString(4));
				pb.setWriter(rs.getString(5));
				pb.setIsbn(rs.getString(6));
				pb.setCourseNo(rs.getString(7));
				pb.setCourseName(rs.getString(8));
				pb.setProfessor(rs.getString(9));
				pb.setPrice(rs.getInt(10));
				pb.setCondition(rs.getString(11));
				pb.setValidTil(rs.getString(12));
				pb.setPostInpdate(rs.getString(13));
				pb.setPicturePass(rs.getString(14));
				pb.setUsername(rs.getString(15));
				pb.setStatus(1);

				postBookList.add(pb);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
		}

		return postBookList;
	}

	/**
     * 買い手画面(使用モードは買い手)
     * 入力された値でポストを検索する
     * @param SearchFormのフィールド --> 検索項目
     * @return 検索の結果　PostInfoのリスト
     */
	public ArrayList<PostInfo> searchPublicPostBookList(int userID, SearchForm form) throws SQLException {

		return searchPublicPostBookList(userID,form.getItemName(), form.getWriter(), form.getIsbn(), form.getCourseNo(),
				form.getCourseName(), form.getProfessor(), form.getPriceRange());
	}

	/**
     * postIDで書名を取得
     * @param postID
     * @return 書名
     */
	public String getBookName (int postID) throws SQLException {

		String query = "SELECT book.item_name from book INNER JOIN post ON post.item_id = book.item_id "
				+ "WHERE post.post_id = ? ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String bookName = null;

		try {
			pstmt = (PreparedStatement) con.prepareStatement(query);
			pstmt.setInt(1, postID);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				bookName = rs.getString(1);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
		}
		return bookName;
	}

}
