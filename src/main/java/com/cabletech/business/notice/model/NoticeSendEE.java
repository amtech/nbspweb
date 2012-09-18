package com.cabletech.business.notice.model;

import com.cabletech.common.base.BaseEntity;

/**
 * 会议通知人员表
 * 
 * @author wangt
 * 
 */
public class NoticeSendEE extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1512040009342967781L;
	private String id;
	private String noticeid;
	private String personid;
	private String isread;

	public String getNoticeid() {
		return noticeid;
	}

	public void setNoticeid(String noticeid) {
		this.noticeid = noticeid;
	}

	public String getPersonid() {
		return personid;
	}

	public void setPersonid(String personid) {
		this.personid = personid;
	}

	public String getIsread() {
		return isread;
	}

	public void setIsread(String isread) {
		this.isread = isread;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((noticeid == null) ? 0 : noticeid.hashCode());
		result = prime * result + ((personid == null) ? 0 : personid.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		NoticeSendEE other = (NoticeSendEE) obj;

		if (noticeid == null) {
			if (other.noticeid != null) {
				return false;
			}
		} else if (!noticeid.equals(other.noticeid)) {
			return false;
		}
		if (personid == null) {
			if (other.personid != null) {
				return false;
			} else if (!personid.equals(other.personid)) {
				return false;
			}
		}
		return true;
	}
}
