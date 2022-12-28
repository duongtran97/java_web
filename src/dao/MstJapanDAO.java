/**
 * Copyright(C) 2020  Luvina Software
 *Mst_japanDAO.java, Feb 25, 2020 DuongTV
 */
package dao;

import java.util.List;

import entities.MstJapanEntities;


/**
 * thao tác với bảng mst_japan
 * @author DuongTV
 * 
 */
public interface MstJapanDAO {
	
	/**
	 * Lấy nameLevel theo codeLevel
	 * @param codeLevel id user chọn
	 * @return MstJapanEntities
	 * @exception
	 */
	public MstJapanEntities getNameLevelByCodeLevel(String codeLevel) throws Exception;
	/**
	 * Lấy tất cả các nameLevel từ bảng mst_japan
	 * @return List<MstJapanEntities> danh sacsh các nameLevel
	 * @exception
	 */
	public List<MstJapanEntities> getAllJapan() throws Exception;
}
