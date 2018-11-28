package com.mzk.bzj.dao.model;

import java.math.BigDecimal;
import java.util.Date;

public class BaUserInfo {
    private String userName;

    private String realName;

    private String nickName;

    private Short email;

    private String contact;

    private String papersType;

    private String papersNo;

    private String provinceId;

    private String cityId;

    private String districtId;

    private String townId;

    private String countryId;

    private String address;

    private BigDecimal freeBalance;

    private BigDecimal lockBalance;

    private String loginPwd;

    private String payPwd;

    private Short userStatus;

    private Date createTime;

    private String imgUrl;

    private Short bLongitude;

    private Short bLatitude;

    private Short dLongitude;

    private Short dLatitude;

    private BigDecimal scaleBalance;

    private Short userLevel;

    private String userBank;

    private BigDecimal userCredit;

    private String geoHash;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public Short getEmail() {
        return email;
    }

    public void setEmail(Short email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getPapersType() {
        return papersType;
    }

    public void setPapersType(String papersType) {
        this.papersType = papersType == null ? null : papersType.trim();
    }

    public String getPapersNo() {
        return papersNo;
    }

    public void setPapersNo(String papersNo) {
        this.papersNo = papersNo == null ? null : papersNo.trim();
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId == null ? null : provinceId.trim();
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId == null ? null : cityId.trim();
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId == null ? null : districtId.trim();
    }

    public String getTownId() {
        return townId;
    }

    public void setTownId(String townId) {
        this.townId = townId == null ? null : townId.trim();
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId == null ? null : countryId.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public BigDecimal getFreeBalance() {
        return freeBalance;
    }

    public void setFreeBalance(BigDecimal freeBalance) {
        this.freeBalance = freeBalance;
    }

    public BigDecimal getLockBalance() {
        return lockBalance;
    }

    public void setLockBalance(BigDecimal lockBalance) {
        this.lockBalance = lockBalance;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd == null ? null : loginPwd.trim();
    }

    public String getPayPwd() {
        return payPwd;
    }

    public void setPayPwd(String payPwd) {
        this.payPwd = payPwd == null ? null : payPwd.trim();
    }

    public Short getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Short userStatus) {
        this.userStatus = userStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public Short getbLongitude() {
        return bLongitude;
    }

    public void setbLongitude(Short bLongitude) {
        this.bLongitude = bLongitude;
    }

    public Short getbLatitude() {
        return bLatitude;
    }

    public void setbLatitude(Short bLatitude) {
        this.bLatitude = bLatitude;
    }

    public Short getdLongitude() {
        return dLongitude;
    }

    public void setdLongitude(Short dLongitude) {
        this.dLongitude = dLongitude;
    }

    public Short getdLatitude() {
        return dLatitude;
    }

    public void setdLatitude(Short dLatitude) {
        this.dLatitude = dLatitude;
    }

    public BigDecimal getScaleBalance() {
        return scaleBalance;
    }

    public void setScaleBalance(BigDecimal scaleBalance) {
        this.scaleBalance = scaleBalance;
    }

    public Short getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Short userLevel) {
        this.userLevel = userLevel;
    }

    public String getUserBank() {
        return userBank;
    }

    public void setUserBank(String userBank) {
        this.userBank = userBank == null ? null : userBank.trim();
    }

    public BigDecimal getUserCredit() {
        return userCredit;
    }

    public void setUserCredit(BigDecimal userCredit) {
        this.userCredit = userCredit;
    }

    public String getGeoHash() {
        return geoHash;
    }

    public void setGeoHash(String geoHash) {
        this.geoHash = geoHash == null ? null : geoHash.trim();
    }

	@Override
	public String toString() {
		return "{userName=" + userName + ", realName=" + realName + ", nickName=" + nickName + ", email="
				+ email + ", contact=" + contact + ", papersType=" + papersType + ", papersNo=" + papersNo
				+ ", provinceId=" + provinceId + ", cityId=" + cityId + ", districtId=" + districtId + ", townId="
				+ townId + ", countryId=" + countryId + ", address=" + address + ", freeBalance=" + freeBalance
				+ ", lockBalance=" + lockBalance + ", loginPwd=" + loginPwd + ", payPwd=" + payPwd + ", userStatus="
				+ userStatus + ", createTime=" + createTime + ", imgUrl=" + imgUrl + ", bLongitude=" + bLongitude
				+ ", bLatitude=" + bLatitude + ", dLongitude=" + dLongitude + ", dLatitude=" + dLatitude
				+ ", scaleBalance=" + scaleBalance + ", userLevel=" + userLevel + ", userBank=" + userBank
				+ ", userCredit=" + userCredit + ", geoHash=" + geoHash + "}";
	}
    
}