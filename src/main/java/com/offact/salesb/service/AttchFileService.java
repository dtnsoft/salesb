/**
 * 
 */
package com.offact.salesb.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import com.offact.framework.exception.BizException;
import com.offact.salesb.vo.AttchFileVO;
import com.offact.salesb.vo.MultipartFileVO;

/**
 * @author 4530
 *
 */
public interface AttchFileService {

	/**
	 * 파일 첨부
	 * @param multipartFile
	 * @param boardId
	 * @param seqNo
	 * @throws BizException
	 */
	public void insertAttchFile(MultipartFileVO fileVO, String boardId, String seqNo) throws BizException, IOException;
	
	/**
	 * 첨부 파일 목록 조회
	 * @param fileVO
	 * @return
	 * @throws BizException
	 */
	public List<AttchFileVO> getAttchFileList(AttchFileVO fileVO) throws BizException;
	
	/**
	 * 첨부 파일 삭제
	 * @param fileVO
	 * @throws BizException
	 */
	public void deleteAttchFile(AttchFileVO fileVO) throws BizException;
}
