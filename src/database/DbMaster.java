package database;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Databaseに連携するマスタクラス
 */
public class DbMaster {

	protected Connection con = null;

	public DbMaster(Connection con) throws SQLException {
		this.con = con;
		con.setAutoCommit(false);
	}

	public void commit( ) throws SQLException {
		con.commit();
	}

	public void rollback() throws SQLException {
		con.rollback();
	}

	public void close() throws SQLException {
		con.close();
	}

}
