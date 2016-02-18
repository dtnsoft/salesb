package com.offact.salesb.service.impl.common;

import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offact.framework.db.SqlSessionCommonDao;
import com.offact.framework.exception.BizException;
import com.offact.framework.util.StringUtil;
import com.offact.salesb.service.common.MailService;
import com.offact.salesb.vo.common.EmailVO;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.MailException;

import javax.mail.internet.*;

/**
 * @author 4530
 *
 */
@Service
public class MailServiceImpl implements MailService {

	private final Logger        logger = Logger.getLogger(getClass());

	@Autowired

    private JavaMailSender mailSender;

	@Override

	public boolean sendMail(EmailVO mail) throws BizException{

        MimeMessage message = mailSender.createMimeMessage();

        try {

            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

            messageHelper.setSubject(mail.getSubject());

            // 수신인 다수
            String[] toArrayResult = new String[mail.getToEmails().size()];

            for(int i=0; i<mail.getToEmails().size(); i++){

            	toArrayResult[i] =  mail.getToEmails().get(i);

            }
            
            // 참조 수신인 다수
            String[] toArrayCcResult = new String[mail.getToEmail_Ccs().size()];
            
            logger.debug("mail.getToEmail_Ccs().size() : "+mail.getToEmail_Ccs().size());
            
            if(mail.getToEmail_Ccs().size()>0 ){
            	
                logger.debug("mail.getToEmail_Ccs().get(0) : "+mail.getToEmail_Ccs().get(0));
            	
            	if(!StringUtil.nvl(mail.getToEmail_Ccs().get(0),"").equals("")){
            		 
            		for(int j=0; j<mail.getToEmail_Ccs().size(); j++){

                     	toArrayCcResult[j] =  mail.getToEmail_Ccs().get(j);
                     	
                    	logger.debug("toArrayCcResult["+j+"] : "+toArrayCcResult[j]);

                     }
            		
            		messageHelper.setBcc(toArrayCcResult);
            	}
            }
            
            messageHelper.setTo(toArrayResult);
            messageHelper.setFrom(mail.getFromEmail(), mail.getSubject());
            messageHelper.setText(mail.getMsg(), true);

            logger.debug("mail.getFile().size() : "+mail.getFile().size());
            // 여러개의 파일첨부시
            if(mail.getFile()!=null){

            	for(int i=0; i< mail.getFile().size(); i++){

            		if( mail.getFile().get(i).isFile()){

            			messageHelper.addAttachment(mail.getAttcheFileName().get(i), mail.getFile().get(i));

            		}
            	}
            }

            logger.debug("Send Mail Subject : {}"+mail.getSubject());

            mailSender.send(message);

           

        } catch(Exception e){
	    	
	    	e.printStackTrace();
	    	throw new BizException(e.getMessage());
	
	    }

        return true;

	}

}