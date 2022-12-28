/**
 * Copyright(C) 2020  Luvina Software
 *TblDetailUserJapanLogicImpl.java, Feb 25, 2020 DuongTV
 */
package logic.impl;

import java.util.List;

import dao.TblDetailUserJapanDAO;
import dao.impl.BaseDaoImpl;
import dao.impl.TblDetailUserJapanDAOImplement;
import entities.TblDetailUserJapanEntities;
import logic.TblDetailUserJapanLogic;

/**
 * thực hiện logic với dữ liệu lấy được từ DB
 * @author DuongTV
 * 
 */
public class TblDetailUserJapanLogicImpl extends BaseDaoImpl implements TblDetailUserJapanLogic {

	@Override
	public int insertDetailUserJapan(TblDetailUserJapanEntities tblDetailUserJapanEntities) throws Exception {
		return tblDetailUserJapanEntities.getUserId();
	}

	@Override
	public List<Integer> getListDetailId() throws Exception {
		// trả về list id
		return new TblDetailUserJapanDAOImplement().getListIdDetailUser();
	}

	@Override
	public boolean checkExistDetailJapan(int userId) throws Exception {
		TblDetailUserJapanDAO tblDetailUserJapanDAOImpl = new TblDetailUserJapanDAOImplement();
		if (tblDetailUserJapanDAOImpl.getTblDetailJapanByUserId(userId).getUserId() != 0) {
			return true;
		}
		return false;
	}

}
