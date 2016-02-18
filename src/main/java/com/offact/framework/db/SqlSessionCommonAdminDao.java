package com.offact.framework.db;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

/**
 * MyBatis를 통해 DB를 엑세스하는 공통 DAO 입니다.
 * MyBatis 제어 이외에 파라메터 조작과 같은 부가 기능을 포함하고 있습니다. 
 * @author AA
 * @see ParameterAffecter
 */
public class SqlSessionCommonAdminDao
	extends SqlSessionDaoSupport
	implements SqlSession {

	private ParameterAffecter	paramaterAffecter;
	
	/**
	 * 파라메터 조작자를 지정합니다.
	 * @param paramaterAffecter
	 */
	public void setParamaterAffecter(ParameterAffecter paramaterAffecter) {
		this.paramaterAffecter = paramaterAffecter;
	}

	/**
	 * 파라메터 조작자의 처리를 위해 오버라이드 되었습니다.
	 * 파라메터 조작자가 지정되지 않으면 아무일도 하지 않는 파라메터 조작자를 할당합니다.
	 * @see ParameterNoAffecter
	 */
	@Override
	protected void initDao() throws Exception {
		super.initDao();
		if (this.paramaterAffecter == null) {
			//this.paramaterAffecter	= new ScloudBasicParameterAffecter();
			this.paramaterAffecter	= new ParameterNoAffecter();
		}
	}

	/**
	 * SqlSession의 동일 메소드를 포장하였습니다.
	 * @see org.apache.ibatis.session.SqlSession
	 */
	public void clearCache() {
		this.getSqlSession().clearCache();
	}

	/**
	 * SqlSession의 동일 메소드를 포장하였습니다.
	 * @see org.apache.ibatis.session.SqlSession
	 */
	public void close() {
		this.getSqlSession().close();
	}
	
	/**
	 * SqlSession의 동일 메소드를 포장하였습니다.
	 * @see org.apache.ibatis.session.SqlSession
	 */
	public void commit() {
		this.getSqlSession().commit();
	}

	/**
	 * SqlSession의 동일 메소드를 포장하였습니다.
	 * @see org.apache.ibatis.session.SqlSession
	 */
	public void commit(boolean force) {
		this.getSqlSession().commit(force);
	}

	/**
	 * SqlSession의 동일 메소드를 포장하였습니다.
	 * 파라메터 조작자를 사용합니다.
	 * @see org.apache.ibatis.session.SqlSession
	 */
	public int delete(String statement, Object parameter) {
		int		rv;
		Object	affected;
		
		affected	= this.paramaterAffecter.doAffectParameter(parameter);
		ThreadSession.getSession().setQueryId(statement);
		rv	= this.getSqlSession().delete(statement, affected);
		this.feedbackParameter(parameter, affected);
		return rv;
	}

	/**
	 * SqlSession의 동일 메소드를 포장하였습니다.
	 * @see org.apache.ibatis.session.SqlSession
	 */
	public int delete(String statement) {
		ThreadSession.getSession().setQueryId(statement);
		return this.getSqlSession().delete(statement);
	}

	/**
	 * SqlSession의 동일 메소드를 포장하였습니다.
	 * @see org.apache.ibatis.session.SqlSession
	 */
	public List<BatchResult> flushStatements() {
		return this.getSqlSession().flushStatements();
	}

	/**
	 * Session configure 정보 리턴
	 */
	public Configuration getConfiguration() {
		return this.getSqlSession().getConfiguration();
	}

	/**
	 * SqlSession의 동일 메소드를 포장하였습니다.
	 * @see org.apache.ibatis.session.SqlSession
	 */
	public Connection getConnection() {
		return this.getSqlSession().getConnection();
	}

	/**
	 * SqlSession의 동일 메소드를 포장하였습니다.
	 * @see org.apache.ibatis.session.SqlSession
	 */
	public <T> T getMapper(Class<T> type) {
		return this.getSqlSession().getMapper(type);
	}

	/**
	 * SqlSession의 동일 메소드를 포장하였습니다.
	 * 파라메터 조작자를 사용합니다.
	 * @see org.apache.ibatis.session.SqlSession
	 */
	public int insert(String statement, Object parameter) {
		int		rv;
		Object	affected;
		
		affected	= this.paramaterAffecter.doAffectParameter(parameter);
		ThreadSession.getSession().setQueryId(statement);
		rv	= this.getSqlSession().insert(statement, affected);
		this.feedbackParameter(parameter, affected);
		return rv;
	}

	/**
	 * SqlSession의 동일 메소드를 포장하였습니다.
	 * @see org.apache.ibatis.session.SqlSession
	 */
	public int insert(String statement) {
		ThreadSession.getSession().setQueryId(statement);
		return this.getSqlSession().insert(statement);
	}

	/**
	 * SqlSession의 동일 메소드를 포장하였습니다.
	 * @see org.apache.ibatis.session.SqlSession
	 */
	public void rollback() {
		this.getSqlSession().rollback();
	}

	/**
	 * SqlSession의 동일 메소드를 포장하였습니다.
	 * @see org.apache.ibatis.session.SqlSession
	 */
	public void rollback(boolean force) {
		this.getSqlSession().rollback(force);
	}

	/**
	 * SqlSession의 동일 메소드를 포장하였습니다.
	 * 파라메터 조작자를 사용합니다.
	 * @see org.apache.ibatis.session.SqlSession
	 */
	public void select(String statement, Object parameter, ResultHandler handler) {
		Object	affected;
		
		affected	= this.paramaterAffecter.doAffectParameter(parameter);
		ThreadSession.getSession().setQueryId(statement);
		this.getSqlSession().select(statement, affected, handler);
		this.feedbackParameter(parameter, affected);
	}

	/**
	 * SqlSession의 동일 메소드를 포장하였습니다.
	 * 파라메터 조작자를 사용합니다.
	 * @see org.apache.ibatis.session.SqlSession
	 */
	public void select(String statement, Object parameter, RowBounds rowBounds,
			ResultHandler handler) {
		Object	affected;
		
		affected	= this.paramaterAffecter.doAffectParameter(parameter);
		ThreadSession.getSession().setQueryId(statement);
		this.getSqlSession().select(statement, affected, rowBounds, handler);
		this.feedbackParameter(parameter, affected);
	}

	/**
	 * SqlSession의 동일 메소드를 포장하였습니다.
	 * @see org.apache.ibatis.session.SqlSession
	 */
	public void select(String statement, ResultHandler handler) {
		ThreadSession.getSession().setQueryId(statement);
		this.getSqlSession().select(statement, handler);
	}

	/**
	 * SqlSession의 동일 메소드를 포장하였습니다.
	 * 파라메터 조작자를 사용합니다.
	 * @see org.apache.ibatis.session.SqlSession
	 */
	public <E> List<E> selectList(String statement, Object parameter, RowBounds rowBounds) {
		List<E>	rv;
		Object	affected;
		
		affected	= this.paramaterAffecter.doAffectParameter(parameter);
		ThreadSession.getSession().setQueryId(statement);
		rv	= this.getSqlSession().selectList(statement, affected, rowBounds);
		this.feedbackParameter(parameter, affected);
		return rv;
	}

	/**
	 * SqlSession의 동일 메소드를 포장하였습니다.
	 * 파라메터 조작자를 사용합니다.
	 * @see org.apache.ibatis.session.SqlSession
	 */
	public <E> List<E> selectList(String statement, Object parameter) {
		List<E>	rv;
		Object	affected;
		
		affected	= this.paramaterAffecter.doAffectParameter(parameter);
		ThreadSession.getSession().setQueryId(statement);
		rv	= this.getSqlSession().selectList(statement, affected);
		this.feedbackParameter(parameter, affected);
		return rv;
	}

	/**
	 * SqlSession의 동일 메소드를 포장하였습니다.
	 * @see org.apache.ibatis.session.SqlSession
	 */
	public <E> List<E> selectList(String statement) {
		ThreadSession.getSession().setQueryId(statement);
		return this.getSqlSession().selectList(statement);
	}

	/**
	 * SqlSession의 동일 메소드를 포장하였습니다.
	 * 파라메터 조작자를 사용합니다.
	 * @see org.apache.ibatis.session.SqlSession
	 */
	public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey,
			RowBounds rowBounds) {
		Map<K, V>	rv;
		Object		affected;
		
		affected	= this.paramaterAffecter.doAffectParameter(parameter);
		ThreadSession.getSession().setQueryId(statement);
		rv	= this.getSqlSession().selectMap(statement, affected, mapKey, rowBounds);
		this.feedbackParameter(parameter, affected);
		return rv;
	}

	/**
	 * SqlSession의 동일 메소드를 포장하였습니다.
	 * 파라메터 조작자를 사용합니다.
	 * @see org.apache.ibatis.session.SqlSession
	 */
	public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey) {
		Map<K, V>	rv;
		Object		affected;
		
		affected	= this.paramaterAffecter.doAffectParameter(parameter);
		ThreadSession.getSession().setQueryId(statement);
		rv	= this.getSqlSession().selectMap(statement, affected, mapKey);
		this.feedbackParameter(parameter, affected);
		return rv;
	}

	/**
	 * SqlSession의 동일 메소드를 포장하였습니다.
	 * @see org.apache.ibatis.session.SqlSession
	 */
	public <K, V> Map<K, V> selectMap(String statement, String mapKey) {
		ThreadSession.getSession().setQueryId(statement);
		return this.getSqlSession().selectMap(statement, mapKey);
	}

	/**
	 * SqlSession의 동일 메소드를 포장하였습니다.
	 * 파라메터 조작자를 사용합니다.
	 * @see org.apache.ibatis.session.SqlSession
	 */
	public <T> T selectOne(String statement, Object parameter) {
		T		rv;
		Object	affected;
		
		affected	= this.paramaterAffecter.doAffectParameter(parameter);
		ThreadSession.getSession().setQueryId(statement);
		rv	= this.getSqlSession().selectOne(statement, affected);
		this.feedbackParameter(parameter, affected);
		return rv;
	}

	/**
	 * SqlSession의 동일 메소드를 포장하였습니다.
	 * @see org.apache.ibatis.session.SqlSession
	 */
	public <T> T selectOne(String statement) {
		ThreadSession.getSession().setQueryId(statement);
		return this.getSqlSession().selectOne(statement);
	}

	/**
	 * SqlSession의 동일 메소드를 포장하였습니다.
	 * 파라메터 조작자를 사용합니다.
	 * @see org.apache.ibatis.session.SqlSession
	 */
	public int update(String statement, Object parameter) {
		int		rv;
		Object	affected;
		
		affected	= this.paramaterAffecter.doAffectParameter(parameter);
		ThreadSession.getSession().setQueryId(statement);
		rv	= this.getSqlSession().update(statement, affected);
		this.feedbackParameter(parameter, affected);
		return rv;
	}

	/**
	 * SqlSession의 동일 메소드를 포장하였습니다.
	 * @see org.apache.ibatis.session.SqlSession
	 */
	public int update(String statement) {
		ThreadSession.getSession().setQueryId(statement);
		return this.getSqlSession().update(statement);
	}
	
	/*
	public <E> List<E> selectPage(String statement, SearchConditionValueObject scVO) {
		List<E>	rv;
		Object	affected;
		
		affected	= this.paramaterAffecter.doAffectParameter(scVO);
		ThreadSession.getSession().setQueryId(statement);
		rv	= this.getSqlSession().selectList(statement, affected);
		this.feedbackParameter(scVO, affected);
		if (rv.size() > 0) {
			E		row;
			
			row			= rv.get(0);
			if (row instanceof TotalCountProvide) {
				scVO.setTotalCount(((TotalCountProvide)row).getTotalCount());
			} else {
				String	totalCount;
				
				try {
					totalCount	= BeanUtils.getProperty(row, "totalCount");
				} catch (Exception e) {
					totalCount	= null;
				}
				if (totalCount != null) {
					scVO.setTotalCount(Integer.valueOf(totalCount));
				}
			}
				
		}
		return rv;
	}
	*/
	
	private void feedbackParameter(Object src, Object affected) {
		if (src == null || src == affected) {
			return;
		}
		this.paramaterAffecter.doFeedbackParameter(src, affected);
	}
}
