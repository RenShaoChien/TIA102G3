package com.tia102g3.orderdetails.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tia102g3.order.model.OrderDAOInterface;
import com.tia102g3.order.model.OrderJDBCDAO;
import com.tia102g3.order.model.OrderVO;
import com.tia102g3.product.model.ProductVO;


public class OrderDetailsJDBCDAO implements OrderDetailsDAOInterface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/tia102g3?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO order_details (productID, quantity, orderID ) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM order_details";
	private static final String GET_ONE_STMT = "SELECT * FROM order_details  WHERE ordDtlID  = ?";
	private static final String DELETE = "DELETE FROM order_details WHERE ordDtlID = ?";
	private static final String UPDATE = "UPDATE order_details SET productID = ?, quantity = ?, orderID = ? WHERE ordDtlID = ?";

	@Override
	public void insert(OrderDetailsVO orderDetailsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, orderDetailsVO.getProductVO().getProductID());
			pstmt.setInt(2, orderDetailsVO.getQuantity());
			pstmt.setInt(3, orderDetailsVO.getOrderVO().getOrderID());
			

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(OrderDetailsVO orderDetailsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, orderDetailsVO.getOrdDtlID());
			pstmt.setInt(2, orderDetailsVO.getProductVO().getProductID());
			pstmt.setInt(3, orderDetailsVO.getQuantity());
			pstmt.setInt(4, orderDetailsVO.getOrderVO().getOrderID());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(Integer OrdDtlID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, OrdDtlID);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public OrderDetailsVO findByPrimaryKey(Integer OrdDtlID) {

		OrderDetailsVO orderDetailsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, OrdDtlID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				orderDetailsVO = new OrderDetailsVO();
				orderDetailsVO.setOrdDtlID(rs.getInt("ordDtlID"));
				orderDetailsVO.setProductVO(new ProductVO(rs.getInt("productID")));
				orderDetailsVO.setQuantity(rs.getInt("quantity"));
				orderDetailsVO.setOrderVO(new OrderVO(rs.getInt("orderID")));
				
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return orderDetailsVO;
	}
	
	

	public List<OrderDetailsVO> getAll() {
		List<OrderDetailsVO> list = new ArrayList<OrderDetailsVO>();
		OrderDetailsVO orderDetailsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				orderDetailsVO = new OrderDetailsVO();
				orderDetailsVO.setOrdDtlID(rs.getInt("ordDtlID"));
				orderDetailsVO.setProductVO(new ProductVO(rs.getInt("productID")));
				orderDetailsVO.setQuantity(rs.getInt("quantity"));
				orderDetailsVO.setOrderVO(new OrderVO(rs.getInt("orderID")));
				list.add(orderDetailsVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
}

//	public static void main(String[] args) {
//
//		OrderDetailsJDBCDAO dao = new OrderDetailsJDBCDAO();
//
//		// 新增
//		OrderDetailsVO orderDetailsVO1 = new OrderDetailsVO();
//		orderDetailsVO1.setOrdDtlID(1);
//		orderDetailsVO1.setProductID(1);
//		orderDetailsVO1.setQuantity(10);
//		orderDetailsVO1.setOrderID(1);
//		dao.insert(orderDetailsVO1);
//
//		// 修改
//		OrderDetailsVO orderDetailsVO2 = new OrderDetailsVO();
//		orderDetailsVO2.setOrdDtlID(2);
//		orderDetailsVO2.setProductID(2);
//		orderDetailsVO2.setQuantity(8);
//		orderDetailsVO2.setOrderID(2);
//		dao.insert(orderDetailsVO2);
//
//		// 刪除
//		dao.delete(3);
//
////		// 查詢
//		OrderDetailsVO orderDetailsVO3 = dao.findByPrimaryKey(1004);
//		System.out.print(orderDetailsVO3.getOrdDtlID() + ",");
//		System.out.print(orderDetailsVO3.getProductID() + ",");
//		System.out.print(orderDetailsVO3.getQuantity() + ",");
//		System.out.print(orderDetailsVO3.getOrderID() + ",");
//		System.out.println("---------------------");
//
//		// 查詢
//		List<OrderDetailsVO> list = dao.getAll();
//		for (OrderDetailsVO aOrder : list) {
//			System.out.print(aOrder.getOrdDtlID() + ",");
//			System.out.print(aOrder.getProductID() + ",");
//			System.out.print(aOrder.getQuantity() + ",");
//			System.out.print(aOrder.getOrderID() + ",");
//			System.out.println();
//		}
//	}
//
//}
