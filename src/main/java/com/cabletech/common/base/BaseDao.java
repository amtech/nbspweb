package com.cabletech.common.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;

import com.cabletech.common.util.Page;
import com.cabletech.common.util.ReflectionUtils;

/**
 * 基础数据访问Dao
 * 
 * @author zhaobi
 * 
 * @param <T>
 * @param <PK>
 */
public class BaseDao<T, PK extends Serializable> extends
		SimpleHibernateDao<T, PK> {
	public Logger logger = Logger.getLogger(this.getClass());
	private static String dialect = "oracle"; // 数据库方言
	protected JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 用于Dao层子类的构造函数. 通过子类的泛型定义取得对象类型Class. eg. public class UserDao extends
	 * BaseDao<User, Long>{ }
	 */
	public BaseDao() {
		super();
	}

	/**
	 * 用于省略Dao层, Service层直接使用通用HibernateDao的构造函数. 在构造函数中定义对象类型Class. eg.
	 * BaseDao<User, Long> userDao = new HibernateDao<User,
	 * Long>(sessionFactory, User.class);
	 * 
	 * @param sessionFactory
	 *            sessionFactory
	 * @param entityClass
	 *            entityClass
	 */
	public BaseDao(final SessionFactory sessionFactory,
			final Class<T> entityClass) {
		super(sessionFactory, entityClass);
	}

	// -- 分页查询函数 --//

	/**
	 * 分页获取HQL全部对象.
	 * 
	 * @param page
	 *            page
	 * @param hql
	 *            hql
	 */
	public Page<T> getHQLPageAll(final Page<T> page, String hql) {
		return findHQLPage(page, hql);
	}

	/**
	 * 获取HQL全部对象.
	 * 
	 * @param hql
	 *            hql
	 */
	public List<T> getHQLAll(String hql) {
		return this.find(hql);
	}

	/**
	 * 分页获取SQL全部对象.
	 * 
	 * @param page
	 *            page
	 * @param sql
	 *            sql
	 */
	public Page<T> getSQLPageAll(final Page<T> page, String sql) {
		page.setSql(sql);
		return findSQLPage(page, sql);
	}

	/**
	 * 获取sql全部对象
	 * 
	 * @param sql
	 *            sql
	 * @return
	 */
	public List<Map<String, Object>> getSQLALL(String sql) {
		try {
			List<Map<String, Object>> list = this.jdbcTemplate
					.queryForList(sql);
			return list;
		} catch (Exception e) {
			throw new RuntimeException("SQL数据失败:" + e.getMessage());
		}

	}

	/**
	 * 按HQL分页查询.
	 * 
	 * @param page
	 *            分页参数. 注意不支持其中的orderBy参数.
	 * @param hql
	 *            hql语句.
	 * @param values
	 *            数量可变的查询参数,按顺序绑定.
	 * 
	 * @return 分页查询结果, 附带结果列表及所有查询输入参数.
	 */
	@SuppressWarnings("unchecked")
	public Page<T> findHQLPage(final Page<T> page, final String hql,
			final Object... values) {
		Assert.notNull(page, "page不能为空");

		Query q = createHQLQuery(hql, values);

		if (page.isAutoCount()) {
			long totalCount = countHQLResult(hql, values);
			page.setTotalCount(totalCount);
		}
		setPageParameterToQuery(q, page);
		List result = q.list();
		page.setResult(result);
		return page;
	}

	/**
	 * 按SQL分页查询.
	 * 
	 * @param page
	 *            分页参数. 注意不支持其中的orderBy参数.
	 * @param sql
	 *            sql语句.
	 * 
	 * @return 分页查询结果, 附带结果列表及所有查询输入参数.
	 */
	@SuppressWarnings("unchecked")
	public Page<T> findSQLPage(final Page<T> page, final String sql) {
		Assert.notNull(page, "page不能为空");
		page.setSql(sql);
		long totalCount = countSQLResult(sql);
		List result = this.jdbcTemplate.queryForList(preparePageSQL(sql, page));
		page.setTotalCount(totalCount);
		page.setResult(result);
		return page;
	}

	/**
	 * SQL获取总条数
	 * 
	 * @param sql
	 *            sql语句.
	 * @return
	 */
	protected long countSQLResult(String sql) {
		String countsql = prepareCountSQL(sql);
		try {
			Long count = this.jdbcTemplate.queryForLong(countsql);
			return count;
		} catch (Exception e) {
			throw new RuntimeException("hql can't be auto count, hql is:"
					+ countsql, e);
		}

	}

	/**
	 * 按HQL分页查询.
	 * 
	 * @param page
	 *            分页参数. 注意不支持其中的orderBy参数.
	 * @param hql
	 *            hql语句.
	 * @param values
	 *            命名参数,按名称绑定.
	 * 
	 * @return 分页查询结果, 附带结果列表及所有查询输入参数.
	 */
	@SuppressWarnings("unchecked")
	public Page<T> findHQLPage(final Page<T> page, final String hql,
			final Map<String, ?> values) {
		Assert.notNull(page, "page不能为空");

		Query q = createHQLQuery(hql, values);

		if (page.isAutoCount()) {
			long totalCount = countHQLResult(hql, values);
			page.setTotalCount(totalCount);
		}

		setPageParameterToQuery(q, page);

		List result = q.list();
		page.setResult(result);
		return page;
	}

	/**
	 * 按Criteria分页查询.
	 * 
	 * @param page
	 *            分页参数.
	 * @param criterions
	 *            数量可变的Criterion.
	 * 
	 * @return 分页查询结果.附带结果列表及所有查询输入参数.
	 */
	@SuppressWarnings("unchecked")
	public Page<T> findCriterionPage(final Page<T> page,
			final Criterion... criterions) {
		Assert.notNull(page, "page不能为空");

		Criteria c = createCriteria(criterions);

		if (page.isAutoCount()) {
			long totalCount = countCriteriaResult(c);
			page.setTotalCount(totalCount);
		}

		setPageParameterToCriteria(c, page);

		List result = c.list();
		page.setResult(result);
		return page;
	}

	/**
	 * 设置分页参数到Query对象,辅助函数.
	 * 
	 * @param q
	 *            q
	 * @param page
	 *            page
	 */
	protected Query setPageParameterToQuery(final Query q, final Page<T> page) {

		Assert.isTrue(page.getPageSize() > 0, "Page Size must larger than zero");

		// hibernate的firstResult的序号从0�?��
		q.setFirstResult(page.getFirst() - 1);
		q.setMaxResults(page.getPageSize());
		return q;
	}

	/**
	 * 设置分页参数到Criteria对象,辅助函数.
	 * 
	 * @param c
	 *            c
	 * @param page
	 *            page
	 */
	protected Criteria setPageParameterToCriteria(final Criteria c,
			final Page<T> page) {

		Assert.isTrue(page.getPageSize() > 0, "Page Size must larger than zero");
		// hibernate的firstResult的序号从0�?��
		c.setFirstResult(page.getFirst() - 1);
		c.setMaxResults(page.getPageSize());
		if (page.isOrderBySetted()) {
			String[] orderByArray = StringUtils.split(page.getOrderBy(), ',');
			String[] orderArray = StringUtils.split(page.getOrder(), ',');

			Assert.isTrue(orderByArray.length == orderArray.length, "分页多重排序");

			for (int i = 0; i < orderByArray.length; i++) {
				if (Page.ASC.equals(orderArray[i])) {
					c.addOrder(Order.asc(orderByArray[i]));
				} else {
					c.addOrder(Order.desc(orderByArray[i]));
				}
			}
		}
		return c;
	}

	/**
	 * 执行count查询获得本次Hql查询所能获得的对象总数.
	  * @param hql hql
	 * @param values values
	 * 本函数只能自动处理简单的hql语句,复杂的hql查询请另行编写count语句查询.
	 */
	protected long countHQLResult(final String hql, final Object... values) {
		String countHql = prepareCountHQL(hql);

		try {
			Long count = findHQLUnique(countHql, values);
			return count;
		} catch (Exception e) {
			throw new RuntimeException("hql can't be auto count, hql is:"
					+ countHql, e);
		}
	}

	/**
	 * 执行count查询获得本次Hql查询所能获得的对象总数.
	 * @param hql hql
	 * @param values values 
	 * 本函数只能自动处理简单的hql语句,复杂的hql查询请另行编写count语句查询.
	 */
	protected long countHQLResult(final String hql, final Map<String, ?> values) {
		String countHql = prepareCountHQL(hql);

		try {
			Long count = findHQLUnique(countHql, values);
			return count;
		} catch (Exception e) {
			throw new RuntimeException("hql can't be auto count, hql is:"
					+ countHql, e);
		}
	}

	/**
	 * 处理hql获取总条数语句
	 * @param orgHql orgHql
	 * @return
	 */
	private String prepareCountHQL(String orgHql) {
		String fromHql = orgHql;
		// select子句与order by子句会影响count查询,进行简单的排除.
		fromHql = "from " + StringUtils.substringAfter(fromHql, "from");
		fromHql = StringUtils.substringBefore(fromHql, "order by");

		String countHql = "select count(1) " + fromHql;
		return countHql;
	}

	/**
	 * 处理sql获取总条数语句
	 * @param orgsql orgsql
	 * @return
	 */
	private String prepareCountSQL(String orgsql) {
		String fromsql = orgsql;
		String countsql = "select count(1) from (" + fromsql + ") temp_tb";
		return countsql;
	}

	/**
	 * 处理分页SQL语句
	 * 
	 * @param orgsql orgsql
	 * @param page  page
	 * @return
	 */
	private String preparePageSQL(String orgsql, final Page<T> page) {
		if (StringUtils.isNotEmpty(dialect)) {
			StringBuffer pageSql = new StringBuffer();
			if ("oracle".equals(dialect)) {
				pageSql.append("select * from (select tmp_tb.*,ROWNUM row_id from (");
				pageSql.append(orgsql);
				pageSql.append(") tmp_tb where ROWNUM<=");
				pageSql.append(page.getFirst() + page.getPageSize());
				pageSql.append(") where row_id>");
				pageSql.append(page.getFirst());
			}
			return pageSql.toString();
		} else {
			return orgsql;
		}
	}

	/**
	 * 执行count查询获得本次Criteria查询所能获得的对象总数.
	 * @param c c
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected long countCriteriaResult(final Criteria c) {
		CriteriaImpl impl = (CriteriaImpl) c;
		Projection projection = impl.getProjection();
		ResultTransformer transformer = impl.getResultTransformer();
		List<CriteriaImpl.OrderEntry> orderEntries = null;
		try {
			orderEntries = (List) ReflectionUtils.getFieldValue(impl,"orderEntries");
			ReflectionUtils.setFieldValue(impl, "orderEntries", new ArrayList());
		} catch (Exception e) {
			logger.error("不可能抛出的异常:{}" + e.getMessage());
		}
		// 执行Count查询
		Long totalCountObject = (Long) c.setProjection(Projections.rowCount())
				.uniqueResult();
		long totalCount = (totalCountObject != null) ? totalCountObject : 0;
		c.setProjection(projection);
		if (projection == null)c.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		if (transformer != null)c.setResultTransformer(transformer);
		try {
			ReflectionUtils.setFieldValue(impl, "orderEntries", orderEntries);
		} catch (Exception e) {
			logger.error("不可能抛出的异常:{}" + e.getMessage());
		}
		return totalCount;
	}

}
