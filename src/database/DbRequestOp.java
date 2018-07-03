package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.BuyRequest;
import beans.SellRequest;

public class DbRequestOp extends DbMaster {

	public DbRequestOp(Connection con) throws SQLException {
		super(con);
	}

	/**
	 * 売り手画面(使用モードは売り手) 特定されたポストに対するリクエスト詳細を取得
	 *
	 * @param postID
	 * @return リクエスト詳細のリスト
	 */
	public ArrayList<SellRequest> getPostRequest(int postID) throws SQLException {

		ArrayList<SellRequest> requestList = new ArrayList<SellRequest>();

		String query = "SELECT request.user_id,request.message,request.inpdate,user.email "
				+ "FROM request INNER JOIN user ON request.user_id = user.user_id "
				+ "WHERE request.post_id = ? ORDER BY inpdate DESC";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = (PreparedStatement) con.prepareStatement(query);
			pstmt.setInt(1, postID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				SellRequest req = new SellRequest();
				req.setUser_id(rs.getInt(1));
				req.setMessage(rs.getString(2));
				req.setInpdate(rs.getString(3));
				req.setEmail(rs.getString(4));
				requestList.add(req);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
		}

		return requestList;

	}

	/**
	 * 売り手画面(使用モードは売り手)
	 * 特定されたuserIDに対するリクエスト詳細を取得
	 * @param userID
	 *            active true -->有効 false -->無効
	 * @return リクエスト詳細のリスト
	 */

	public ArrayList<SellRequest> getMyRequest(int userID, boolean active) throws SQLException {

		ArrayList<SellRequest> requestList = new ArrayList<SellRequest>();

		String validStr = null;

		if (active) {
			validStr = " >= NOW() ";
		} else {
			validStr = " < NOW() ";
		}

		String query = "SELECT request.user_id, request.post_id, user.email, "
				+ "request.message, request.inpdate, book.item_name " + "FROM request "
				+ "INNER JOIN post ON request.post_id = post.post_id "
				+ "INNER JOIN user ON request.user_id = user.user_id "
				+ "INNER JOIN book ON post.item_id = book.item_id " + "WHERE post.user_id = ? " + "AND post.valid_til"
				+ validStr + "ORDER BY request.inpdate DESC";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = (PreparedStatement) con.prepareStatement(query);
			pstmt.setInt(1, userID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				SellRequest req = new SellRequest();
				req.setUser_id(rs.getInt(1));
				req.setPost_id(rs.getInt(2));
				req.setEmail(rs.getString(3));
				req.setMessage(rs.getString(4));
				req.setInpdate(rs.getString(5));
				req.setItem_name(rs.getString(6));
				requestList.add(req);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
		}

		return requestList;

	}

	/**
	 * 買い手画面(使用モードは買い手)
	 * 特定さてたポストに対してリクエストを登録する
	 * @param userID,postID,message
	 */
	public void insertRequest(int userID, int postID, String message) throws SQLException {
		String query = "INSERT INTO `request`( `user_id`, `post_id`, `message`, `inpdate`, `upddate`)"
				+ " VALUES (?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)";
		PreparedStatement pstmt = null;

		try {
			pstmt = (PreparedStatement) con.prepareStatement(query);
			pstmt.setInt(1, userID);
			pstmt.setInt(2, postID);
			pstmt.setString(3, message);
			pstmt.executeUpdate();

		} finally {
			if (pstmt != null)
				pstmt.close();
		}
	}

	/**
	 * 買い手画面(使用モードは買い手)
	 * 特定されたuserIDのPostIDを取得
	 * @param userID
	 * @return PostIDのリスト
	 */
	public ArrayList<Integer> getRequestedPostID(int userID) throws SQLException {

		ArrayList<Integer> postIDList = new ArrayList<Integer>();

		String query = "SELECT post_id FROM request WHERE user_id = ?";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = (PreparedStatement) con.prepareStatement(query);
			pstmt.setInt(1, userID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				postIDList.add(rs.getInt(1));
			}

		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
		}

		return postIDList;

	}

	/**
	 *  買い手画面(使用モードは買い手)
	 *  特定されたuserIDのリクエスト詳細を取得
	 * @param userID
	 *            active true -->有効 false -->無効
	 * @return リクエスト詳細のリスト
	 */
	public ArrayList<BuyRequest> getBuyRequestList(int userID, boolean active) throws SQLException {

		ArrayList<BuyRequest> buyRequestList = new ArrayList<BuyRequest>();

		String validStr = null;

		if (active) {
			validStr = " >= NOW() ";
		} else {
			validStr = " < NOW() ";
		}

		String query = "SELECT request.post_id, request.request_id, user.username, book.item_name, request.inpdate, post.status "
				+ "FROM request " + "INNER JOIN post ON request.post_id = post.post_id "
				+ "INNER JOIN book ON post.item_id = book.item_id " + "INNER JOIN user ON post.user_id = user.user_id "
				+ "WHERE request.user_id = ? AND post.valid_til" + validStr + "ORDER BY request.inpdate DESC";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = (PreparedStatement) con.prepareStatement(query);
			pstmt.setInt(1, userID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				BuyRequest br = new BuyRequest();
				br.setPostID(rs.getInt(1));
				br.setRequestID(rs.getInt(2));
				br.setPosterName(rs.getString(3));
				br.setItemName(rs.getString(4));
				br.setRequestTime(rs.getString(5));
				br.setStatus(rs.getInt(6));
				buyRequestList.add(br);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
		}

		return buyRequestList;

	}

	/**
	 *  買い手画面(使用モードは買い手)
	 *  リクエストを削除
	 * @param requestID
	 */
	public void removeRequest(int requestID) throws SQLException {
		String query = "DELETE FROM request WHERE request_id = ?";

		PreparedStatement pstmt = null;

		try {
			pstmt = (PreparedStatement) con.prepareStatement(query);
			pstmt.setInt(1, requestID);
			pstmt.executeUpdate();
		} finally {
			if (pstmt != null)
				pstmt.close();
		}
	}

	/**
	 *  現在のユーザはすでに特定にポストをリクエストしたかを確認
	 * @param userID
	 *        postID
	 * @return true--> リクエストした false--> リクエストしていない
	 */
	public boolean alreadyRequested(int postID,int userID) throws SQLException {
		String query = "SELECT * from request WHERE user_id = ? AND post_id = ? ";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		boolean requested = false;

		try {
			pstmt = (PreparedStatement) con.prepareStatement(query);
			pstmt.setInt(1, userID);
			pstmt.setInt(2, postID);

			rs = pstmt.executeQuery();
			requested = rs.next();

		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
		}

		return requested;



	}

}