/**
 *
 */
package com.offact.salesb.service.impl.business;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offact.framework.util.StringUtil;
import com.offact.framework.db.SqlSessionCommonDao;
import com.offact.framework.exception.BizException;
import com.offact.salesb.service.business.ProductService;
import com.offact.salesb.vo.business.ProductMasterVO;

/**
 * @author 4530
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final Logger        logger = Logger.getLogger(getClass());

    @Autowired
    private SqlSessionCommonDao commonDao;

    @Override
    public List<ProductMasterVO> getProductPageList(ProductMasterVO productcondition) throws BizException {
    	
        List<ProductMasterVO> productMaster = commonDao.selectList("Product.getProductPageList", productcondition);

        return productMaster;
    }

    @Override
    public int getProductCnt(ProductMasterVO productcondition) throws BizException {
        return commonDao.selectOne("Product.getProductCnt", productcondition);
    }

    @Override
    public ProductMasterVO getProductDetail(ProductMasterVO product) throws BizException {
    	
    	ProductMasterVO productDetailVO = commonDao.selectOne("Product.getProductDetail", product);

        return productDetailVO;
    }
   
    public Map<Object, Object> regiExcelUpload(List<ProductMasterVO> excelUploadList)
    	    throws BizException
    	  {
    	    Map rtnMap = new HashMap();
    	    Map deleteMap = new HashMap();

    	    List rtnSuccessList = new ArrayList();
    	    List rtnErrorList = new ArrayList();
    	    String errorMsgList ="";
    	    
    	    deleteMap.put("updateUserId", "system");  //기존 데이타 삭제필드 업데이트
    	    this.commonDao.update("Product.productDeleteAll", deleteMap);

    	    int idx = 0;

    	    for (int i = 0; i < excelUploadList.size(); i++) {
    	        
    	    	idx = i + 2;
    	        ProductMasterVO productMasterVO = (ProductMasterVO)excelUploadList.get(i);
    	        productMasterVO.setErrMsg("");
    	        
      	      try 
      	      {
                this.commonDao.insert("Product.insertExcelProduct", productMasterVO);
                rtnSuccessList.add(productMasterVO);
    	      
    	      } catch (Exception e) {
    	        
    	    	e.printStackTrace();
    	        String errMsg = e.getMessage();
    	        errMsg = errMsg.substring(errMsg.lastIndexOf("Exception"));
    	        ((ProductMasterVO)excelUploadList.get(i)).setErrMsg(((ProductMasterVO)excelUploadList.get(i)).getErrMsg() + "\n\r(" + idx + ")" + errMsg);
    	        rtnErrorList.add((ProductMasterVO)excelUploadList.get(i));
    	        errorMsgList=errorMsgList+"["+(i+1)+"]번째 품목코드 :"+productMasterVO.getProductCode()+"\\^";
    	        
    	        this.logger.debug("[key]:"+ ((ProductMasterVO)excelUploadList.get(i)).getProductCode()+" [msg] : " + ((ProductMasterVO)excelUploadList.get(i)).getErrMsg());
    	        
    	      }
    	    
    	    }

    	    rtnMap.put("rtnSuccessList", rtnSuccessList);
    	    rtnMap.put("rtnErrorList", rtnErrorList);
    	    rtnMap.put("errorMsgList", errorMsgList);

    	    return rtnMap;
    	  }
    

    @Override
    public int productInsertProc(ProductMasterVO productDetail) throws BizException {
        // 사용자 추가

    	return commonDao.update("Product.productInsertProc", productDetail);

    }

}
