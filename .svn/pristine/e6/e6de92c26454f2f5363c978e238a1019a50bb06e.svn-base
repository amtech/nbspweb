package com.cabletech.common.base;

import java.io.Serializable;

/**
 * 基类业务接口
 * @author wangt
 *
 * @param <T>
 * @param <PK>
 */
public interface BaseService<T, PK extends Serializable>  {
	/**
	 * 获取单个实体
	 * @param id 编号id
	 * @return
	 */
	public T get(final PK id); 


	/**
	 * 保存实体
	 * @param entity 实体
	 */
	public void save(final T entity);
	/**
	 * 通过主键删除
	 * @param id 
	 */
	public void delete(final PK id);
}
