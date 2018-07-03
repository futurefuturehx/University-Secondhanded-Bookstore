package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbMemberOp extends DbMaster {

	public DbMemberOp(Connection con) throws SQLException {
		super(con);
	}

    /**
     * Eメールの存在チェック
     * @param email
     * @return true->登録されている　false->登録されていない
     */
	public boolean hasEmail(String email) throws SQLException {

		boolean hasNext = false;
		String query = "SELECT * FROM user WHERE email = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = (PreparedStatement) con.prepareStatement(query);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			hasNext = rs.next();
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
		}

		return hasNext;
	}

    /**
     * ユーザ名の存在チェック
     * @param username
     * @return true->登録されている　false->登録されていない
     */
	public boolean hasUsername(String username) throws SQLException {

		boolean hasNext = false;
		String query = "SELECT * FROM user WHERE username = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = (PreparedStatement) con.prepareStatement(query);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			hasNext = rs.next();
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
		}

		return hasNext;
	}

    /**
     * メールでユーザ名を取得
     * @param email
     * @return username
     */
	public String getUsername(String email) throws SQLException {

		String query = "SELECT username FROM user WHERE email = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String username = null;

		try {
			pstmt = (PreparedStatement) con.prepareStatement(query);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			rs.next();
			username = rs.getString(1);
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
		}

		return username;
	}

    /**
     * メールでユーザIDを取得
     * @param email
     * @return UserID
     */
	public int getUserID(String email) throws SQLException {

		String query = "SELECT user_id FROM user WHERE email = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int userID = -1;

		try {
			pstmt = (PreparedStatement) con.prepareStatement(query);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			rs.next();
			userID = rs.getInt(1);
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
		}

		return userID;
	}

    /**
     * UserIDでメールを取得
     * @param userID
     * @return email
     */
	public String getCurentUserEmail(int userID) throws SQLException {

		String query = "SELECT email FROM user WHERE user_id = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String mail = null;

		try {
			pstmt = (PreparedStatement) con.prepareStatement(query);
			pstmt.setInt(1, userID);
			rs = pstmt.executeQuery();
			rs.next();
			mail = rs.getString(1);
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
		}

		return mail;
	}

    /**
     * メールとパスワードセットを確認
     * @param email, password
     * @return IDとpasswordが一致 return true --> OK false --> passwordが待ちがっちる
     */
	public boolean hasEmailandPasswordSet(String email, String password)
			throws SQLException {

		boolean hasNext = false;
		String query = "SELECT * FROM user WHERE email = ? AND password = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = (PreparedStatement) con.prepareStatement(query);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			hasNext = rs.next();
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
		}

		return hasNext;
	}

	/**
	 * 会員情報を登録
	 * @param email,username,password
	 */
	public void registerMember(String email, String username, String password)
			throws SQLException {

		String query = "INSERT INTO user (`email`, `username`, `password`, `inpdate`, `upddate`)"
				+ " VALUES (?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)";
		PreparedStatement pstmt = null;

		try {
			pstmt = (PreparedStatement) con.prepareStatement(query);
			pstmt.setString(1, email);
			pstmt.setString(2, username);
			pstmt.setString(3, password);
			pstmt.executeUpdate();
		} finally {
			if (pstmt != null)
				pstmt.close();
		}
	}

	/**
	 * パスワード変更
	 * @param email,password
	 */
	public void updatePassword(String email, String password)
			throws SQLException {

		String query = "UPDATE user SET password = ?, upddate = CURRENT_TIMESTAMP WHERE email = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = (PreparedStatement) con.prepareStatement(query);
			pstmt.setString(1, password);
			pstmt.setString(2, email);
			pstmt.executeUpdate();
		} finally {
			if (pstmt != null)
				pstmt.close();
		}
	}

    /**
     * postIDでEメールアドレスを取得
     * @param postID
     * @return email
     */
	public String getSellerMail (int postID) throws SQLException {

		String query = "SELECT user.email from user INNER JOIN post ON post.user_id = user.user_id "
				+ "WHERE post.post_id = ? ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String mailAddress = null;

		try {
			pstmt = (PreparedStatement) con.prepareStatement(query);
			pstmt.setInt(1, postID);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				mailAddress = rs.getString(1);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
		}
		return mailAddress;
	}

}
