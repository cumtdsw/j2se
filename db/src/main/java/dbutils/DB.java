package dbutils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

/**
 * 用于获得数据源DataSource对象和Connection对象
 * 需要导入apache dbcp和pool两个包
 * 
 * @author pugwoo
 * @date 2011年11月13日
 */
public class DB {

	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/dbutils";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";

	private static DataSource ds;

	public static DataSource getDataSource() {
		if (ds == null) {
			BasicDataSource bds = new BasicDataSource();
			bds.setDriverClassName(DRIVER);
			bds.setUrl(DBURL);
			bds.setUsername(USERNAME);
			bds.setPassword(PASSWORD);
			// 最大活动连接数
			bds.setMaxActive(20);
			// 最大空闲连接数
			bds.setMaxIdle(8);

			ds = bds;
		}
		return ds;
	}

	public static Connection getConnection() throws SQLException {
		return getDataSource().getConnection();
	}

	public static void close(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws SQLException {
		System.out.println(getDataSource());
		System.out.println(getConnection());
	}

}
