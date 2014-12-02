package com.pugwoo.test.dbutils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import junit.framework.TestCase;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

/**
 * 2011年11月13日
 * 
 * @author pugwoo
 */
public class BasicUsage extends TestCase{

	/**
	 * 两种获得QueryRunner方法
	 * 
	 * @throws SQLException
	 */
	public static void useQueryRunner() throws SQLException {
		// 第一种，使用Connection，需要手工关闭connection
		QueryRunner qr1 = new QueryRunner();
		Connection conn = DB.getConnection();
		ArrayList<?> result1 = (ArrayList<?>) qr1.query(conn,
				"select * from person", new ArrayListHandler());
		DB.close(conn);
		System.out.println(result1.size());

		// 第二种，使用DataSource
		QueryRunner qr2 = new QueryRunner(DB.getDataSource());
		ArrayList<?> result2 = (ArrayList<?>) qr2.query("select * from person",
				new ArrayListHandler());
		System.out.println(result2.size());
	}

	/**
	 * 测试插入
	 */
	public static void testInsert() {
		String sql = "INSERT INTO person(name, age, address) values (?, ?, ?)";
		QueryRunner qr = new QueryRunner();
		Connection conn = null;
		try {
			conn = DB.getConnection();
			int row = qr.update(conn, sql, "karen", 23, "sz");
			// 如果需要获得插入后的自增ID，则SELECT LAST_INSERT_ID()
			System.out.println("affected rows: " + row);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(conn);
		}
	}

	/**
	 * 更新、删除和插入一样的，都用qr.update()函数
	 */

	/**
	 * 查询参见类：com.pugwoo.test.dbutils.Query
	 */

	/**
	 * 批量处理，掩饰批量插入，更新和删除同理
	 * 
	 * @throws SQLException
	 */
	public static void testBatchInsert() {
		String sql = "INSERT INTO person(name, age, address) values (?, ?, ?)";
		QueryRunner qr = new QueryRunner();
		Object[][] data = new Object[][] { { "aaa", 10, "addr1" },
				{ "bbb", 11, "addr2" }, { "ccc", 12, "addr3" } };

		Connection conn = null;
		try {
			conn = DB.getConnection();
			int[] row = qr.batch(conn, sql, data);
			for(int r : row) {
				System.out.println("affected row: " + r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(conn);
		}
	}

}
