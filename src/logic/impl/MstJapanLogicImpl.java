/**
 * Copyright(C) 2020  Luvina Software
 *MstJapanLogicImpl.java, Feb 25, 2020 DuongTV
 */
package logic.impl;

import java.util.ArrayList;
import java.util.List;

import dao.MstJapanDAO;
import dao.impl.MstJapanDAOImplement;
import entities.MstJapanEntities;
import logic.MstJapanLogic;
import utils.StringUtils;

/**
 * thực hiện logic khi lấy data từ db
 * @author DuongTV
 * 
 */
public class MstJapanLogicImpl implements MstJapanLogic {

	@Override
	/**
	 * Lấy tất cả các trình độ tiếng Nhật trong DB
	 */
	public List<MstJapanEntities> getAllJapan() throws Exception {
		List<MstJapanEntities> listMstJapanEntities = new ArrayList<MstJapanEntities>();
		MstJapanDAO mstJapanDAOImplement = new MstJapanDAOImplement();
		// lấy list từ hàm getAllJapan
		listMstJapanEntities = mstJapanDAOImplement.getAllJapan();
		return listMstJapanEntities;
	}

	@Override
	/**
	 * Lấy nameLevel từ codeLevel
	 */
	public String getNameLevelByCodeLevel(String codeLevel) throws Exception {
		// trả về giá trị nameLevel
		return new MstJapanDAOImplement().getNameLevelByCodeLevel(codeLevel).getNameLevel();
	}

	@Override
	/**
	 * Kiểm tra tồn tại
	 */
	public boolean checkExistCodeLevel(String codeLevel) throws Exception {
		MstJapanDAO mstJapanDAO = new MstJapanDAOImplement();
		MstJapanEntities mstJapanEntities = mstJapanDAO.getNameLevelByCodeLevel(codeLevel);
		// tồn tại trả về true
		if (!StringUtils.checkNullAndNotType(mstJapanEntities.getNameLevel())) {
			return true;
		}
		//không tồn tại trả về false
		return false;
	}

}
