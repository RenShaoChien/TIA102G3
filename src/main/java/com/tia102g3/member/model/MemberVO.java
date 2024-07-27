package com.tia102g3.member.model;

import java.sql.Date;

public class MemberVO {
	private int memberID;
    private byte[] personalPhotos;
    private String name;
    private String account;
    private String password;
    private String email;
    private String gender;
    private String phone;
    private String address;
    private Date bD;
    private Date regDate;
    private int coachMemberID;
    private String receiver;
    private String receiverAddress;
    private String receiverPhone;
    private String cardName;
    private String cardValidTime;
    private String cardLast3No;
    private String cardPhone;

    // getters and setters
    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public byte[] getPersonalPhotos() {
        return personalPhotos;
    }

    public void setPersonalPhotos(byte[] personalPhotos) {
        this.personalPhotos = personalPhotos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getbD() {
        return bD;
    }

    public void setbD(Date bD) {
        this.bD = bD;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

	public Integer getCoachMemberID() {
		return coachMemberID;
	}

	public void setCoachMemberID(Integer coachMemberID) {
		this.coachMemberID = coachMemberID;
	}

	public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardValidTime() {
        return cardValidTime;
    }

    public void setCardValidTime(String cardValidTime) {
        this.cardValidTime = cardValidTime;
    }

    public String getCardLast3No() {
        return cardLast3No;
    }

    public void setCardLast3No(String cardLast3No) {
        this.cardLast3No = cardLast3No;
    }

    public String getCardPhone() {
        return cardPhone;
    }

    public void setCardPhone(String cardPhone) {
        this.cardPhone = cardPhone;
    }
}
