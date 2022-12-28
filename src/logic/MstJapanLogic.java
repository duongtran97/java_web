/**
 * Copyright(C) 2020  Luvina Software
 *MstJapanLogic.java, Feb 25, 2020 DuongTV
 */
package logic;

import java.util.List;

import entities.MstJapanEntities;

/**
 * thực hiện các logic của đối tượng MstGroup
 * @author DuongTV
 * 
 */
public interface MstJapanLogic {
	/**
	 * @throws Exception 
	 * Kiểm tra có tồn tại level không
	 * @param codeLevel giá trị cần kiểm tra
	 * @return true - tồn tại, false - không tồn tại
	 */
	public boolean checkExistCodeLevel(String codeLevel) throws Exception;
	/**
	 * @throws Exception 
	 * Lấy giá trị nameLevel
	 * @param codeLevel id user nhập vào
	 * @return nameLevel
	 */
	public String getNameLevelByCodeLevel(String codeLevel) throws Exception ;
	/**
	 * Lấy ra tất cả các trình độ tiếng nhật
	 * @return list trình độ tiếng nhật
	 * @throws Exception
	 */
	public List<MstJapanEntities> getAllJapan() throws Exception;
}
