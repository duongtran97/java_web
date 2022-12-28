/**
 * Copyright(C) 2020  Luvina Software
 * UserListEntities.java, 1 Mar 2020 DuongTV
 *
 */
package entities;

import java.sql.Date;
import java.util.List;


/**
 * đối tượng hiển thị dữ liệu trên màn hình ADM002
 * @author DuongTV
 *
 */
public class UserInforEntities {
	 private int userId;
	 private int detailUserId;
	 private String codeLevel;
	 private Date endDate;
	 private int groupId;
	 private int rule;
	 private String salt;
	 private String accountName;
	 private String fullName;
	 private Date birthday;
	 private String groupName;
	 private String kanaName;
	 private String email;
	 private String tel;
	 private String nameLevel;
	 private Date startDate;
	 private int total;
	 private String totalPoint;
	 private String password;
	 private String passwordConfirm;
	 private int year;
	 private int month;
	 private int day;
	 private List<Integer> lsBirthday;
	 private List<Integer> lsStartDate;
	 private List<Integer> lsEndDate;
	/**
	 * khởi tạo không tham số
	 */
	public UserInforEntities() {
		
	}

	/**
	 * @return the lsBirthday
	 */
	public List<Integer> getLsBirthday() {
		return lsBirthday;
	}

	/**
	 * @param lsBirthday the lsBirthday to set
	 */
	public void setLsBirthday(List<Integer> lsBirthday) {
		this.lsBirthday = lsBirthday;
	}

	/**
	 * @return the lsStartDate
	 */
	public List<Integer> getLsStartDate() {
		return lsStartDate;
	}

	/**
	 * @param lsStartDate the lsStartDate to set
	 */
	public void setLsStartDate(List<Integer> lsStartDate) {
		this.lsStartDate = lsStartDate;
	}

	/**
	 * @return the lsEndDate
	 */
	public List<Integer> getLsEndDate() {
		return lsEndDate;
	}

	/**
	 * @param lsEndDate the lsEndDate to set
	 */
	public void setLsEndDate(List<Integer> lsEndDate) {
		this.lsEndDate = lsEndDate;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @return the detailUserId
	 */
	public int getDetailUserId() {
		return detailUserId;
	}
	/**
	 * @param detailUserId the detailUserId to set
	 */
	public void setDetailUserId(int detailUserId) {
		this.detailUserId = detailUserId;
	}
	/**
	 * @return the codeLevel
	 */
	public String getCodeLevel() {
		return codeLevel;
	}
	/**
	 * @param codeLevel the codeLevel to set
	 */
	public void setCodeLevel(String codeLevel) {
		this.codeLevel = codeLevel;
	}
	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the groupId
	 */
	public int getGroupId() {
		return groupId;
	}
	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	/**
	 * @return the rule
	 */
	public int getRule() {
		return rule;
	}
	/**
	 * @param rule the rule to set
	 */
	public void setRule(int rule) {
		this.rule = rule;
	}
	/**
	 * @return the salt
	 */
	public String getSalt() {
		return salt;
	}
	/**
	 * @param salt the salt to set
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}
	/**
	 * @return the accountName
	 */
	public String getAccountName() {
		return accountName;
	}
	/**
	 * @param accountName the accountName to set
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}
	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	/**
	 * @return the birthDay
	 */
	public Date getBirthday() {
		return birthday;
	}
	/**
	 * @param birthDay the birthDay to set
	 */
	public void setBirthday(Date birthDay) {
		this.birthday = birthDay;
	}
	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}
	/**
	 * @param groupName the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	/**
	 * @return the kanaName
	 */
	public String getKanaName() {
		return kanaName;
	}
	/**
	 * @param kanaName the kanaName to set
	 */
	public void setKanaName(String kanaName) {
		this.kanaName = kanaName;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}
	/**
	 * @param tel the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	/**
	 * @return the nameLevel
	 */
	public String getNameLevel() {
		return nameLevel;
	}
	/**
	 * @param nameLevel the nameLevel to set
	 */
	public void setNameLevel(String nameLevel) {
		this.nameLevel = nameLevel;
	}
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the passwordConfirm
	 */
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	/**
	 * @param passwordConfirm the passwordConfirm to set
	 */
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}
	/**
	 * @return the month
	 */
	public int getMonth() {
		return month;
	}
	/**
	 * @param month the month to set
	 */
	public void setMonth(int month) {
		this.month = month;
	}
	/**
	 * @return the day
	 */
	public int getDay() {
		return day;
	}
	/**
	 * @param day the day to set
	 */
	public void setDay(int day) {
		this.day = day;
	}


	/**
	 * @return the totalPoint
	 */
	public String getTotalPoint() {
		return totalPoint;
	}


	/**
	 * @param totalPoint the totalPoint to set
	 */
	public void setTotalPoint(String totalPoint) {
		this.totalPoint = totalPoint;
	}
	 
	
	
}
