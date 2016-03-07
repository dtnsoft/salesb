/**
 *
 */
package com.offact.salesb.service.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.offact.framework.exception.BizException;
import com.offact.salesb.vo.business.ProductMasterVO;

/**
 * @author
 */
public interface ProductService {
    /**
     * 품목 목록 조회
     * 
     * @return
     * @throws BizException
     */
    public List<ProductMasterVO> getProductPageList(ProductMasterVO product) throws BizException;

    /**
     * 품목 전체 갯수
     * 
     * @return
     * @throws BizException
     */
    public int getProductCnt(ProductMasterVO product) throws BizException;

    /**
     * 품목상세조회
     * 
     * @param productCode
     * @return
     * @throws BizException
     */
    public ProductMasterVO getProductDetail(ProductMasterVO product) throws BizException;
    
    /**
     * 품목 업로드
     * 
     * @param ProductMasterVO
     * @return
     * @throws BizException
     */
    public abstract Map regiExcelUpload(List<ProductMasterVO> paramList)
    	    throws BizException;
    

    /**
     * 상품 상세정보 저장
     * 
     * @return
     * @throws BizException
     */
    public int productInsertProc(ProductMasterVO productDetail) throws BizException;


}
