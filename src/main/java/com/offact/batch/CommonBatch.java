package com.offact.batch;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Controller;

import com.offact.framework.db.SqlSessionCommonDao;

@Controller
public class CommonBatch extends QuartzJobBean {

	@Autowired
	private SqlSessionCommonDao commonDao;

	private final Logger logger = Logger.getLogger(getClass());

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
	}
}
