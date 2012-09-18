package com.cabletech.common.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.cabletech.baseinfo.excel.ExportSupport;
import com.cabletech.business.base.service.CommonOrderCodeService;

/**
 * 基础服务实现
 * 
 * @author zhaobi
 * @author 杨隽 2012-05-15 提取导出公共方法
 * 
 * @param <T>
 * @param <PK>
 */
public abstract class BaseServiceImpl<T, PK extends Serializable> implements
		ExportSupport {
	public Logger logger = Logger.getLogger(getClass());
	// 序号生成记录信息业务处理接口
	@Resource(name = "commonOrderCodeServiceImpl")
	protected CommonOrderCodeService commonOrderCodeService;

	/**
	 * dao类
	 * 
	 * @return
	 */
	protected abstract BaseDao<T, PK> getBaseDao();

	/**
	 * 通过主键获取实体
	 * 
	 * @param id
	 *            PK
	 * @return T
	 */
	@Transactional
	public T get(final PK id) {
		T entity = getBaseDao().get(id);
		return entity;
	}

	/**
	 * 保存实体
	 * 
	 * @param entity
	 *            T
	 */
	@Transactional
	public void save(final T entity) {
		getBaseDao().save(entity);
	}

	/**
	 * 通过主键删除
	 * 
	 * @param id
	 *            PK
	 */
	@Transactional
	public void delete(final PK id) {
		getBaseDao().delete(id);
	}

	/**
	 * 
	 * excel 获取导出数据
	 * 
	 * @param parameters
	 *            条件
	 * @return List 导出数据列表
	 * **/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List getExportDatas(Map<String, Object> parameters) {
		String sql = (String) parameters.get("sql");
		return getBaseDao().getJdbcTemplate().queryForList(sql);
	}
}
