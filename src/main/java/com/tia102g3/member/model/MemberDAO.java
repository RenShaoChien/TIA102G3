package com.tia102g3.member.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO implements MemberDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/tia102g3?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO member (personalPhotos, name, account, password, email, gender, phone, address, bD, regDate, coachMemberID, receiver, receiverAddress, receiverPhone, cardName, cardValidTime, cardLast3No, cardPhone) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT memberID, personalPhotos, name, account, password, email, gender, phone, address, bD, regDate, coachMemberID, receiver, receiverAddress, receiverPhone, cardName, cardValidTime, cardLast3No, cardPhone FROM member order by memberID";
	private static final String GET_ONE_STMT = 
		"SELECT memberID, personalPhotos, name, account, password, email, gender, phone, address, bD, regDate, coachMemberID, receiver, receiverAddress, receiverPhone, cardName, cardValidTime, cardLast3No, cardPhone FROM member WHERE memberID = ?";
	private static final String DELETE = 
		"DELETE FROM member WHERE memberID = ?";
	private static final String UPDATE = 
		"UPDATE member SET  name = ?, account = ?, password = ?, email = ?, gender = ?, phone = ?, address = ?, bD = ?, regDate = ?, coachMemberID = ?, receiver = ?, receiverAddress = ?, receiverPhone = ?, cardName = ?, cardValidTime = ?, cardLast3No = ?, cardPhone = ? WHERE memberID = ?";

	@Override
	public void insert(MemberVO memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setBytes(1, memberVO.getPersonalPhotos());
			pstmt.setString(2, memberVO.getName());
			pstmt.setString(3, memberVO.getAccount());
			pstmt.setString(4, memberVO.getPassword());
			pstmt.setString(5, memberVO.getEmail());
			pstmt.setString(6, memberVO.getGender());
			pstmt.setString(7, memberVO.getPhone());
			pstmt.setString(8, memberVO.getAddress());
			pstmt.setDate(9, memberVO.getbD());
			pstmt.setDate(10, memberVO.getRegDate());
			pstmt.setInt(11, memberVO.getCoachMemberID());
			pstmt.setString(12, memberVO.getReceiver());
			pstmt.setString(13, memberVO.getReceiverAddress());
			pstmt.setString(14, memberVO.getReceiverPhone());
			pstmt.setString(15, memberVO.getCardName());
			pstmt.setString(16, memberVO.getCardValidTime());
			pstmt.setString(17, memberVO.getCardLast3No());
			pstmt.setString(18, memberVO.getCardPhone());
			
			
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
	public void update(MemberVO memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

//			pstmt.setBytes(1, memberVO.getPersonalPhotos());
			pstmt.setString(1, memberVO.getName());
			pstmt.setString(2, memberVO.getAccount());
			pstmt.setString(3, memberVO.getPassword());
			pstmt.setString(4, memberVO.getEmail());
			pstmt.setString(5, memberVO.getGender());
			pstmt.setString(6, memberVO.getPhone());
			pstmt.setString(7, memberVO.getAddress());
			pstmt.setDate(8, memberVO.getbD());
			pstmt.setDate(9, memberVO.getRegDate());
			pstmt.setDouble(10, memberVO.getCoachMemberID());
			pstmt.setString(11, memberVO.getReceiver());
			pstmt.setString(12, memberVO.getReceiverAddress());
			pstmt.setString(13, memberVO.getReceiverPhone());
			pstmt.setString(14, memberVO.getCardName());
			pstmt.setString(15, memberVO.getCardValidTime());
			pstmt.setString(16, memberVO.getCardLast3No());
			pstmt.setString(17, memberVO.getCardPhone());
			pstmt.setInt(18, memberVO.getMemberID());

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
	public void delete(Integer memberID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, memberID);

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
	public MemberVO getOne(Integer memberID) {

		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, memberID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memberVO 也稱為 Domain objects
				memberVO = new MemberVO();
				memberVO.setMemberID(rs.getInt("memberID"));
				memberVO.setPersonalPhotos(rs.getBytes("personalPhotos"));
				memberVO.setName(rs.getString("name"));
				memberVO.setAccount(rs.getString("account"));
				memberVO.setPassword(rs.getString("password"));
				memberVO.setEmail(rs.getString("email"));
				memberVO.setGender(rs.getString("gender"));
				memberVO.setPhone(rs.getString("phone"));
				memberVO.setAddress(rs.getString("address"));
				memberVO.setbD(rs.getDate("bD"));
				memberVO.setRegDate(rs.getDate("regDate"));
				memberVO.setCoachMemberID(rs.getInt("coachMemberID"));
				memberVO.setReceiver(rs.getString("receiver"));
				memberVO.setReceiverAddress(rs.getString("receiverAddress"));
				memberVO.setReceiverPhone(rs.getString("receiverPhone"));
				memberVO.setCardName(rs.getString("cardName"));
				memberVO.setCardValidTime(rs.getString("cardValidTime"));
				memberVO.setCardLast3No(rs.getString("cardLast3No"));
				memberVO.setCardPhone(rs.getString("cardPhone"));
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
		return memberVO;
	}

	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memberVO 也稱為 Domain objects
				memberVO = new MemberVO();
				memberVO.setMemberID(rs.getInt("memberID"));
				memberVO.setPersonalPhotos(rs.getBytes("personalPhotos"));
				memberVO.setName(rs.getString("name"));
				memberVO.setAccount(rs.getString("account"));
				memberVO.setPassword(rs.getString("password"));
				memberVO.setEmail(rs.getString("email"));
				memberVO.setGender(rs.getString("gender"));
				memberVO.setPhone(rs.getString("phone"));
				memberVO.setAddress(rs.getString("address"));
				memberVO.setbD(rs.getDate("bD"));
				memberVO.setRegDate(rs.getDate("regDate"));
				memberVO.setCoachMemberID(rs.getInt("coachMemberID"));
				memberVO.setReceiver(rs.getString("receiver"));
				memberVO.setReceiverAddress(rs.getString("receiverAddress"));
				memberVO.setReceiverPhone(rs.getString("receiverPhone"));
				memberVO.setCardName(rs.getString("cardName"));
				memberVO.setCardValidTime(rs.getString("cardValidTime"));
				memberVO.setCardLast3No(rs.getString("cardLast3No"));
				memberVO.setCardPhone(rs.getString("cardPhone"));
				list.add(memberVO); // Store the row in the list
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

		MemberDAO dao = new MemberDAO();

		// 新增
		MemberVO memberVO1 = new MemberVO();
////		memberVO1.setPersonalPhotos(",");
//		memberVO1.setName("會員名稱");
//		memberVO1.setAccount("a123456");
//		memberVO1.setPassword("b123456");
//		memberVO1.setEmail("james123@gmail.com");
//		memberVO1.setGender("男");
//		memberVO1.setPhone("0987654321");
//		memberVO1.setAddress("地址");
//		memberVO1.setbD(Date.valueOf("2000-10-10"));
//		memberVO1.setRegDate(Date.valueOf("2010-10-10"));
//		memberVO1.setCoachMemberID(1);
//		memberVO1.setReceiver("收件人名稱");
//		memberVO1.setReceiverAddress("收件人地址");
//		memberVO1.setReceiverPhone("0987654321");
//		memberVO1.setCardName("1111111111111111");
//		memberVO1.setCardValidTime("2000-10-10");
//		memberVO1.setCardLast3No("123");
//		memberVO1.setCardPhone("0987654321");
		
//		dao.insert(memberVO1);

		// 修改
		MemberVO memberVO2 = new MemberVO();
		memberVO2.setMemberID(1);
//		memberVO2.setPersonalPhotos(",");
		memberVO2.setName("會員名稱");
		memberVO2.setAccount("c12345611111111111111");
		memberVO2.setPassword("d123456");
		memberVO2.setEmail("kelly123@gmail.com");
		memberVO2.setGender("男");
		memberVO2.setPhone("0912345678");
		memberVO2.setAddress("地址");
		memberVO2.setbD(Date.valueOf("2005-10-10"));
		memberVO2.setRegDate(Date.valueOf("2015-10-10"));
		memberVO2.setCoachMemberID(2);
		memberVO2.setReceiver("收件人名稱");
		memberVO2.setReceiverAddress("收件人地址");
		memberVO2.setReceiverPhone("0912345678");
		memberVO2.setCardName("2222222222222222");
		memberVO2.setCardValidTime("2015-10-10");
		memberVO2.setCardLast3No("456");
		memberVO2.setCardPhone("0912345678");

		dao.update(memberVO2);
		
		// 刪除
//		dao.delete(7014);

		// 查詢
		MemberVO memberVO3 = dao.getOne(1);
//		System.out.print(memberVO3.getPersonalPhotos() + ",");
//		System.out.print(memberVO3.getName() + ",");
//		System.out.print(memberVO3.getAccount() + ",");
//		System.out.print(memberVO3.getPassword() + ",");
//		System.out.print(memberVO3.getEmail() + ",");
//		System.out.print(memberVO3.getGender() + ",");
//		System.out.print(memberVO3.getPhone() + ",");
//		System.out.print(memberVO3.getAddress() + ",");
//		System.out.print(memberVO3.getbD() + ",");
//		System.out.print(memberVO3.getRegDate() + ",");
//		System.out.print(memberVO3.getCoachMemberID() + ",");
//		System.out.print(memberVO3.getReceiver() + ",");
//		System.out.print(memberVO3.getReceiverAddress() + ",");
//		System.out.print(memberVO3.getReceiverPhone() + ",");
//		System.out.print(memberVO3.getCardName() + ",");
//		System.out.print(memberVO3.getCardValidTime() + ",");
//		System.out.print(memberVO3.getCardLast3No() + ",");
//		System.out.print(memberVO3.getCardPhone() + ",");
//		System.out.println("---------------------");
		
		// 查詢
		List<MemberVO> list = dao.getAll();
		for (MemberVO aMember : list) {
//			System.out.print(memberVO3.getPersonalPhotos() + ",");
//			System.out.print(memberVO3.getName() + ",");
//			System.out.print(memberVO3.getAccount() + ",");
//			System.out.print(memberVO3.getPassword() + ",");
//			System.out.print(memberVO3.getEmail() + ",");
//			System.out.print(memberVO3.getGender() + ",");
//			System.out.print(memberVO3.getPhone() + ",");
//			System.out.print(memberVO3.getAddress() + ",");
//			System.out.print(memberVO3.getbD() + ",");
//			System.out.print(memberVO3.getRegDate() + ",");
//			System.out.print(memberVO3.getCoachMemberID() + ",");
//			System.out.print(memberVO3.getReceiver() + ",");
//			System.out.print(memberVO3.getReceiverAddress() + ",");
//			System.out.print(memberVO3.getReceiverPhone() + ",");
//			System.out.print(memberVO3.getCardName() + ",");
//			System.out.print(memberVO3.getCardValidTime() + ",");
//			System.out.print(memberVO3.getCardLast3No() + ",");
//			System.out.print(memberVO3.getCardPhone() + ",");
//			System.out.println();
		}
	}
}