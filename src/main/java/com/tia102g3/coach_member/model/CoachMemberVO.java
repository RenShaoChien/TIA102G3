package com.tia102g3.coach_member.model;

import java.util.Arrays;

public class CoachMemberVO {

	    private int coachMemberID;
	    private byte[] personalPhotos;
	    private String status;

	    // Constructor
	    public CoachMemberVO() {}

	    public CoachMemberVO(int coachMemberID, byte[] personalPhotos, String status) {
	        this.coachMemberID = coachMemberID;
	        this.personalPhotos = personalPhotos;
	        this.status = status;
	    }

	    // Getter and Setter for coachMemberID
	    public int getCoachMemberID() {
	        return coachMemberID;
	    }

	    public void setCoachMemberID(int coachMemberID) {
	        this.coachMemberID = coachMemberID;
	    }

	    // Getter and Setter for personalPhotos
	    public byte[] getPersonalPhotos() {
	        return personalPhotos;
	    }

	    public void setPersonalPhotos(byte[] personalPhotos) {
	        this.personalPhotos = personalPhotos;
	    }

	    // Getter and Setter for status
	    public String getStatus() {
	        return status;
	    }

	    public void setStatus(String status) {
	        this.status = status;
	    }

	    @Override
	    public String toString() {
	        return "CoachMember{" +
	                "coachMemberID=" + coachMemberID +
	                ", personalPhotos=" + Arrays.toString(personalPhotos) +
	                ", status='" + status + '\'' +
	                '}';
	    }
	}