/**
 * Copyright(C) 2020  Luvina Software
 * MstGroupEntities.java, Feb 25, 2020 DuongTV
 */
package entities;

/**
 * bean chứa các thuộc tính của bảng mst_group trong db
 * @author DuongTV
 */
public class MstGroupEntities {
	private int groupId;
	private String groupName;
	/**
	 * Khởi tạo không tham số
	 */
	public MstGroupEntities() {
		super();
	}
	/**
	 * @return groupId
	 */
	public int getGroupId() {
		return groupId;
	}
	/**
	 * @param groupId set giá trị cho groupId
	 */
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	/**
	 * @return groupName lấy giá trị groupName
	 */
	public String getGroupName() {
		return groupName;
	}
	/**
	 * @param groupName đặt giá trị cho field groupName
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
}
