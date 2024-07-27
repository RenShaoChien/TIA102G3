package com.tia102g3.course_order.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseOrderDAO implements CourseOrderDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/tia102g3?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO courseorder (memberID, coachCourseID, orderDate, price, status) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT courseOrderID, memberID, coachCourseID, orderDate, price, status FROM courseorder order by courseOrderID";
	private static final String GET_ONE_STMT = 
		"SELECT courseOrderID, memberID, coachCourseID, orderDate, price, status FROM courseorder WHERE courseOrderID = ?";
	private static final String DELETE = 
		"DELETE FROM courseorder WHERE courseOrderID = ?";
	private static final String UPDATE = 
		"UPDATE courseorder SET  memberID = ?, coachCourseID = ?, orderDate = ?, price = ?, status = ? WHERE courseOrderID = ?";

	@Override
	public void insert(CourseOrderVO courseOrderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, courseOrderVO.getMemberID());
			pstmt.setInt(2, courseOrderVO.getCoachCourseID());
			pstmt.setDate(3, courseOrderVO.getOrderDate());
			pstmt.setInt(4, courseOrderVO.getPrice());
			pstmt.setString(5, courseOrderVO.getStatus());
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(CourseOrderVO courseOrderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, courseOrderVO.getMemberID());
			pstmt.setInt(2, courseOrderVO.getCoachCourseID());
			pstmt.setDate(3, courseOrderVO.getOrderDate());
			pstmt.setInt(4, courseOrderVO.getPrice());
			pstmt.setString(5, courseOrderVO.getStatus());
			pstmt.setInt(6, courseOrderVO.getCourseOrderID());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(Integer courseOrderID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, courseOrderID);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public CourseOrderVO getOne(Integer courseOrderID) {

		CourseOrderVO courseOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, courseOrderID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// courseOrderVO 也稱為 Domain objects
				courseOrderVO = new CourseOrderVO();
				courseOrderVO.setCourseOrderID(rs.getInt("courseOrderID"));
				courseOrderVO.setMemberID(rs.getInt("memberID"));
				courseOrderVO.setCoachCourseID(rs.getInt("coachCourseID"));
				courseOrderVO.setOrderDate(rs.getDate("orderDate"));
				courseOrderVO.setPrice(rs.getInt("price"));
				courseOrderVO.setStatus(rs.getString("status"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return courseOrderVO;
	}

	@Override
	public List<CourseOrderVO> getAll() {
		List<CourseOrderVO> list = new ArrayList<CourseOrderVO>();
		CourseOrderVO courseOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// courseOrderVO 也稱為 Domain objects
				courseOrderVO = new CourseOrderVO();
				courseOrderVO.setCourseOrderID(rs.getInt("courseOrderID"));
				courseOrderVO.setMemberID(rs.getInt("memberID"));
				courseOrderVO.setCoachCourseID(rs.getInt("coachCourseID"));
				courseOrderVO.setOrderDate(rs.getDate("orderDate"));
				courseOrderVO.setPrice(rs.getInt("price"));
				courseOrderVO.setStatus(rs.getString("status"));
				list.add(courseOrderVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		CourseOrderDAO dao = new CourseOrderDAO();

		// 新增
//		CourseOrderVO courseOrderVO1 = new CourseOrderVO();
//		courseOrderVO1.setMemberID(2);
//		courseOrderVO1.setCoachCourseID(12);
//		courseOrderVO1.setOrderDate(Date.valueOf("2015-10-10"));
//		courseOrderVO1.setPrice(600);
//		courseOrderVO1.setStatus("已登錄");
//		
//		dao.insert(courseOrderVO1);

		// 修改
		CourseOrderVO courseOrderVO2 = new CourseOrderVO();
		courseOrderVO2.setCourseOrderID(1);
		courseOrderVO2.setMemberID(1);
		courseOrderVO2.setCoachCourseID(11);
		courseOrderVO2.setOrderDate(Date.valueOf("2005-10-10"));
		courseOrderVO2.setPrice(100);
		courseOrderVO2.setStatus("已登錄");

		dao.update(courseOrderVO2);
//		
		// 刪除
//		dao.delete(7014);

		// 查詢
//		CourseOrderVO courseOrderVO3 = dao.getOne(1);
//		System.out.print(courseOrderVO3.getMemberID() + ",");
//		System.out.print(courseOrderVO3.getCoachCourseID() + ",");
//		System.out.print(courseOrderVO3.getOrderDate() + ",");
//		System.out.print(courseOrderVO3.getPrice() + ",");
//		System.out.print(courseOrderVO3.getStatus() + ",");
//		System.out.println("---------------------");
		
		// 查詢
//		List<CourseOrderVO> list = dao.getAll();
//		for (CourseOrderVO aCourseOrder : list) {
//		System.out.print(courseOrderVO3.getMemberID() + ",");
//		System.out.print(courseOrderVO3.getCoachCourseID() + ",");
//		System.out.print(courseOrderVO3.getOrderDate() + ",");
//		System.out.print(courseOrderVO3.getPrice() + ",");
//		System.out.print(courseOrderVO3.getStatus() + ",");
//			System.out.println();
//		}
	}
}