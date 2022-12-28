/**
 * Copyright(C) 2020  Luvina Software
 * TblDetailUserJapan.java, Feb 25, 2020 DuongTV
 */
package entities;

import java.sql.Date;

/**
 * bảng tbl_detailUserJapan
 * @author DuongTV
 * 
 */
public class TblDetailUserJapanEntities {
	private int detailUserJapan;
	private int userId;
	private String codeLevel;
	private Date startDate;
	private Date endDate;
	private int total;
	/**
	 * Phương thức khởi tạo không tham số
	 */
	public TblDetailUserJapanEntities() {
		super();
	}
	/**
	 * @return the detailUserJapan
	 */
	public int getDetailUserJapan() {
		return detailUserJapan;
	}
	/**
	 * @param detailUserJapan the detailUserJapan to set
	 */
	public void setDetailUserJapan(int detailUserJapan) {
		this.detailUserJapan = detailUserJapan;
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
	
	
	
}
