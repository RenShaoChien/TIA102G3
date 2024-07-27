package com.tia102g3.coach_member.model;

import java.util.List;

public class CoachMemberService {

	private CoachMemberDAO_interface dao;

	public CoachMemberService() {
		dao = new CoachMemberDAO();
	}

	public CoachMemberVO addCoachMember(byte[] personalPhotos, String status) {

		CoachMemberVO coachMemberVO = new CoachMemberVO();

		coachMemberVO.setPersonalPhotos(personalPhotos);
		coachMemberVO.setStatus(status);

		dao.insert(coachMemberVO);

		return coachMemberVO;
	}

	public CoachMemberVO updateCoachMember(int coachMemberID, byte[] personalPhotos, String status) {

		CoachMemberVO coachMemberVO = new CoachMemberVO();

		coachMemberVO.setCoachMemberID(coachMemberID);
		coachMemberVO.setPersonalPhotos(personalPhotos);
		coachMemberVO.setStatus(status);

		dao.update(coachMemberVO);

		return coachMemberVO;
	}

	public void deleteCoachMember(int coachMemberID) {
		dao.delete(coachMemberID);
	}

	public CoachMemberVO getOneCoachMember(int coachMemberID) {
		return dao.getOne(coachMemberID);
	}

	public List<CoachMemberVO> getAll() {
		return dao.getAll();
	}
}