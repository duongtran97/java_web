/**
 * Copyright(C) 2020  Luvina Software
 *MstGroup.java, Feb 25, 2020 DuongTV
 */
package dao;

import java.util.List;

import entities.MstGroupEntities;

/**
 * thao tác với DB bảng mst_group
 * @author DuongTV
 * 
 */
public interface MstGroupDAO {
	/**
	 * Lấy ra groupName theo groupId
	 * @param groupId
	 * @return MstGroupEntities
	 * @throws Exception
	 */
	public MstGroupEntities getGroupName(int groupId) throws Exception;
	/**
	 * Lấy ra tất cả các giá trị của bảng mst_group
	 * @return List<MstGroupEntities>
	 * @throws Exception
	 */
	public List<MstGroupEntities> getAllGroup() throws Exception;
}
