package com.cabletech.common.util;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.cfg.ObjectNameNormalizer;
import org.hibernate.exception.JDBCExceptionHelper;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.PersistentIdentifierGenerator;
import org.hibernate.mapping.Table;
import org.hibernate.type.Type;
import org.hibernate.util.PropertiesHelper;
/**
 * 用法：
 * <id name="id" type="java.lang.String" column="id">
			<generator class="com.cabletech.commons.hb.StrSequenceGenerator">
               <param name="sequence">SEQ_WORKSHEET_ID</param>
               <param name="length">10</param>
            </generator>             
	</id>
 * @author YuLeyuan
 *
 */
public class StrSequenceGenerator  implements PersistentIdentifierGenerator,
		Configurable {
	/**
	 * 日志输出.
	 */
	private final Logger log = LoggerFactory
			.getLogger(StrSequenceGenerator.class);
	/**
	 * 缺省的Sequence的名.
	 */
	public static final String SEQUENCE = "sequence";
	
    /**
     * 表示生成的Sequence的长度的Key名.
     */
	private static final String LENGTH_KEY = "length";
	
	/**
	 * 表示生成的Sequence的前缀的Key名.
	 */
	private static final String PREFIX_KEY = "prefix";
	/**
	 * 默认的Sequence的长度的常量值.
	 */
	private static final int DEFAULT_SEQUENCE_LENGTH = 12;
	/**
	 * 默认生成的sequence字符串的长度.
	 */
	private int length = DEFAULT_SEQUENCE_LENGTH;
	/**
	 * 前缀.
	 */
	private String prefix = null;

	/**
	 * The parameters parameter, appended to the create sequence DDL. For
	 * example (Oracle):
	 * <tt>INCREMENT BY 1 START WITH 1 MAXVALUE 100 NOCACHE</tt>.
	 */
	public static final String PARAMETERS = "parameters";


	private String sequenceName;
	private String parameters;

	private String nextValueSql;
	private String createSequenceSql;
	private String checkExistSql;

	private boolean checked = false;

	@Override
	public void configure(Type type, Properties params, Dialect dialect)
			throws MappingException {

		length = PropertiesHelper.getInt(LENGTH_KEY, params,
				DEFAULT_SEQUENCE_LENGTH);
		prefix = PropertiesHelper.getString(PREFIX_KEY, params, null);

		ObjectNameNormalizer normalizer = (ObjectNameNormalizer) params
				.get(IDENTIFIER_NORMALIZER);
		sequenceName = normalizer.normalizeIdentifierQuoting(PropertiesHelper
				.getString(SEQUENCE, params, "hibernate_sequence"));

		parameters = params.getProperty(PARAMETERS);

		if (sequenceName.indexOf('.') < 0) {
			final String schemaName = normalizer
					.normalizeIdentifierQuoting(params.getProperty(SCHEMA));
			final String catalogName = normalizer
					.normalizeIdentifierQuoting(params.getProperty(CATALOG));
			sequenceName = Table.qualify(dialect.quote(catalogName),
					dialect.quote(schemaName), dialect.quote(sequenceName));
		}

		// this.identifierType = type;
		nextValueSql = getSequenceNextValString();
		createSequenceSql = sqlCreateStrings(dialect)[0];
		checkExistSql = getCheckExistString();
	}

	/**
	 * 获取下一个新序列号
	 * @return
	 */
	private String getSequenceNextValString() {
		String zeros = StringUtils.repeat("0", length);
		if (StringUtils.isEmpty(prefix)) {
			return String.format(
					"select to_char(%s.nextval,'FM%s') as value from dual",
					sequenceName, zeros);
		} else {
			return String
					.format("select '%s'||to_char(%s.nextval,'FM%s') as value from dual",
							prefix, sequenceName, zeros);
		}
	}

	private String getCheckExistString() {
		return String.format(
				"SELECT count(*) FROM user_sequences where sequence_name='%s'",
				sequenceName.toUpperCase());
	}

	@Override
	public Serializable generate(SessionImplementor session, Object obj) {
		// EntityEntry entry=session.getPersistenceContext().getEntry(obj);

		if (!checked) {
			if (!isExist(session)) {

				createSequence(session);
			}
			checked = true;
		}
		return makeNextValue(session);
	}

	/**
	 * sequence是否存在
	 * @param session 
	 * @return
	 */
	protected boolean isExist(SessionImplementor session) {
		try {

			PreparedStatement st = session.getBatcher().prepareSelectStatement(
					checkExistSql);
			try {
				ResultSet rs = st.executeQuery();
				try {
					rs.next();
					int flag = rs.getInt(1);
					return flag > 0;
				} finally {
					rs.close();
				}
			} finally {
				session.getBatcher().closeStatement(st);
			}

		} catch (SQLException sqle) {
			throw JDBCExceptionHelper.convert(session.getFactory()
					.getSQLExceptionConverter(), sqle,
					"could not get next sequence value", checkExistSql);
		}
	}

	/**
	 * 创建序列号
	 * @param session 
	 */
	protected void createSequence(SessionImplementor session) {
		log.info("Sequence[{}]不存在，自动创建", sequenceName);
		try {

			PreparedStatement st = session.getBatcher().prepareSelectStatement(
					createSequenceSql);
			try {
				st.executeUpdate();
			} finally {
				session.getBatcher().closeStatement(st);
			}

		} catch (SQLException sqle) {
			throw JDBCExceptionHelper.convert(session.getFactory()
					.getSQLExceptionConverter(), sqle,
					"could not get next sequence value", createSequenceSql);
		}
	}

	/**
	 * 获取下一个序列号
	 * @param session 
	 * @return
	 */
	protected String makeNextValue(SessionImplementor session) {
		try {

			PreparedStatement st = session.getBatcher().prepareSelectStatement(
					nextValueSql);
			try {
				ResultSet rs = st.executeQuery();
				try {
					rs.next();
					String result = rs.getString(1);
					if (log.isDebugEnabled()) {
						log.debug("Sequence identifier generated: " + result);
					}
					return result;
				} finally {
					rs.close();
				}
			} finally {
				session.getBatcher().closeStatement(st);
			}

		} catch (SQLException sqle) {
			throw JDBCExceptionHelper.convert(session.getFactory()
					.getSQLExceptionConverter(), sqle,
					"could not get next sequence value", nextValueSql);
		}
	}

	
	/**
	 * 产生生成sequence的ddl语句
	 * @param dialect 
	 */
	public String[] sqlCreateStrings(Dialect dialect) throws HibernateException {
		String[] ddl = dialect.getCreateSequenceStrings(sequenceName, 1, 1);
		if (parameters != null) {
			ddl[ddl.length - 1] += ' ' + parameters;
		}
		return ddl;
	}

	/**
	 * 产生删除sequence的ddl语句
	 *  @param dialect 
	 */
	public String[] sqlDropStrings(Dialect dialect) throws HibernateException {
		return dialect.getDropSequenceStrings(sequenceName);
	}

	@Override
	public Object generatorKey() {
		return sequenceName;
	}

	public String getSequenceName() {
		return sequenceName;
	}

}
