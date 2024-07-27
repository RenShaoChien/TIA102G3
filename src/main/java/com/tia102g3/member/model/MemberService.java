package com.tia102g3.member.model;

import java.sql.Date;
import java.util.List;

public class MemberService {

	private MemberDAO_interface dao;

	public MemberService() {
		dao = new MemberDAO();
	}

	public MemberVO addMember(byte[] personalPhotos, String name, String account, String password, String email,
			String gender, String phone, String address, Date bD, Date regDate, Double coachMemberID, String receiver,
			String receiverAddress, String receiverPhone, String cardName, String cardValidTime, String cardLast3No,
			String cardPhone) {

		MemberVO memberVO = new MemberVO();

		memberVO.setPersonalPhotos(personalPhotos);
		memberVO.setName(name);
		memberVO.setAccount(account);
		memberVO.setPassword(password);
		memberVO.setEmail(email);
		memberVO.setGender(gender);
		memberVO.setPhone(phone);
		memberVO.setAddress(address);
		memberVO.setbD(bD);
		memberVO.setRegDate(regDate);
		memberVO.setCoachMemberID(coachMemberID);
		memberVO.setReceiver(receiver);
		memberVO.setReceiverAddress(receiverAddress);
		memberVO.setReceiverPhone(receiverPhone);
		memberVO.setCardName(cardName);
		memberVO.setCardValidTime(cardValidTime);
		memberVO.setCardLast3No(cardLast3No);
		memberVO.setCardPhone(cardPhone);

		dao.insert(memberVO);

		return memberVO;
	}

	public MemberVO updateMember(Integer memberID , byte[] personalPhotos, String name, String account, String password, String email,
			String gender, String phone, String address, Date bD, Date regDate, Double coachMemberID, String receiver,
			String receiverAddress, String receiverPhone, String cardName, String cardValidTime, String cardLast3No,
			String cardPhone) {

		MemberVO memberVO = new MemberVO();

		memberVO.setMemberID(memberID);
		memberVO.setPersonalPhotos(personalPhotos);
		memberVO.setName(name);
		memberVO.setAccount(account);
		memberVO.setPassword(password);
		memberVO.setEmail(email);
		memberVO.setGender(gender);
		memberVO.setPhone(phone);
		memberVO.setAddress(address);
		memberVO.setbD(bD);
		memberVO.setRegDate(regDate);
		memberVO.setCoachMemberID(coachMemberID);
		memberVO.setReceiver(receiver);
		memberVO.setReceiverAddress(receiverAddress);
		memberVO.setReceiverPhone(receiverPhone);
		memberVO.setCardName(cardName);
		memberVO.setCardValidTime(cardValidTime);
		memberVO.setCardLast3No(cardLast3No);
		memberVO.setCardPhone(cardPhone);

		dao.update(memberVO);

		return memberVO;
	}

	public void deleteMember(Integer memberID) {
		dao.delete(memberID);
	}

	public MemberVO getOneMember(Integer memberID) {
		return dao.getOne(memberID);
	}

	public List<MemberVO> getAll() {
		return dao.getAll();
	}
}
