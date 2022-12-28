/**
 * Copyright(C) 2020  Luvina Software
 * MstJapanEntities.java, Feb 25, 2020 DuongTV
 */
package entities;

/**
 * bảng mst_japan
 * @author DuongTV
 * 
 */
public class MstJapanEntities {
	private String codeLevel;
	private String nameLevel;
	/**
	 * phương thức khởi tạo không tham số
	 */
	public MstJapanEntities() {
		super();
	}
	/**
	 * @return codeLevel trả về giá trị của mã level tiếng nhật
	 */
	public String getCodeLevel() {
		return codeLevel;
	}
	/**
	 * @param codeLevel set giá trị cho codeLevel
	 */
	public void setCodeLevel(String codeLevel) {
		this.codeLevel = codeLevel;
	}
	/**
	 * @return nameLevel trả về giá trị tên level
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
	
}
