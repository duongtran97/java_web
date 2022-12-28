/**
 * Copyright(C) 2020  Luvina Software
 *MstGroupLogicImpl.java, Feb 25, 2020 DuongTV
 */
package logic.impl;

import java.util.ArrayList;
import java.util.List;

import dao.MstGroupDAO;
import dao.impl.MstGroupDAOImplement;
import entities.MstGroupEntities;
import logic.MstGroupLogic;

/**
 * xử lý logic của MstGroup
 * @author DuongTV
 */
public class MstGroupLogicImpl implements MstGroupLogic {

	@Override
	/**
	 * Lấy tất cả các nhóm có trong DB
	 */
	public List<MstGroupEntities> getAllGroup() throws Exception {
		// Khai báo , khởi tạo list
		List<MstGroupEntities> listMstGroupEntities = new ArrayList<MstGroupEntities>();
		// khai báo , khởi tạo DAO
		MstGroupDAO mstGroupDAOImplement = new MstGroupDAOImplement();
		// gọi phương thức getAllGroup
		listMstGroupEntities = mstGroupDAOImplement.getAllGroup();
		return listMstGroupEntities;
	}

	/**
	 * Kiểm tra tồn tại của groupId khi sửa URL
	 * @param groupId id group
	 * @return true khi tồn tịa id này, false - không tồn tại
	 * @throws Exception
	 */
	public boolean checkExistGroupId(int groupId) throws Exception {
		List<MstGroupEntities> listMstGroup = new ArrayList<MstGroupEntities>();
		MstGroupDAOImplement mstGroupDAOImplement = new MstGroupDAOImplement();
		// gọi hàm getAllGroup để lấy ra các bản ghi
		listMstGroup = mstGroupDAOImplement.getAllGroup();
		// sử dụng for mở rộng để kiểm tra tồn tại groupId
		for (MstGroupEntities mstGroupEntities : listMstGroup) {
			if (mstGroupEntities.getGroupId() == groupId) {
				// tồn tại sẽ trả về true
				return true;
			}
		}
		return false;
	}

	@Override
	/**
	 * lấy ra tên group theo groupId
	 */
	public String getGroupName(int groupId) throws Exception {
//		trả về giá trị của groupName theo groupId
		return new MstGroupDAOImplement().getGroupName(groupId).getGroupName();
	}
}
