/**
 * Copyright(C) 2020  Luvina Software
 *MstGroupLogic.java, Feb 25, 2020 DuongTV
 */
package logic;

import java.util.List;

import entities.MstGroupEntities;

/**
 * description
 * @author DuongTV
 * 
 */
public interface MstGroupLogic {
	/**
	 * Lấy ra groupName theo groupId
	 * @param groupId giá trị groupId cần lấy 
	 * @return groupName
	 * @throws Exception
	 */
	public String getGroupName(int groupId) throws Exception;
	/**
	 * Lấy ra toàn bộ groupName 
	 * @return List<MstGroupEntities list các giá trị groupName
	 * @throws Exception
	 */
	public List<MstGroupEntities> getAllGroup() throws Exception;

	/**
	 * @throws Exception 
	 * description
	 * @param name giải thích
	 * @return giai thích
	 * @exception 
	 */
	public boolean checkExistGroupId(int group) throws Exception;
}
