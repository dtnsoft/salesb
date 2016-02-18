package com.offact.salesb.vo.common;

import java.io.Serializable;

import com.offact.salesb.vo.AbstractVO;

/**
 * @author 4530
 *
 */
public class GroupVO extends AbstractVO {
	
	private String groupId;
	private String groupName;
	private String upGroupId;
	private String groupStep;
	private String groupSort;
	
	private String createUserId;
	private String createDateTime;
	private String updateUserId;
	private String updateDateTime;
	private String deletedYn;
	
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getUpGroupId() {
		return upGroupId;
	}
	public void setUpGroupId(String upGroupId) {
		this.upGroupId = upGroupId;
	}
	public String getGroupStep() {
		return groupStep;
	}
	public void setGroupStep(String groupStep) {
		this.groupStep = groupStep;
	}
	public String getGroupSort() {
		return groupSort;
	}
	public void setGroupSort(String groupSort) {
		this.groupSort = groupSort;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public String getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}
	public String getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}
	public String getUpdateDateTime() {
		return updateDateTime;
	}
	public void setUpdateDateTime(String updateDateTime) {
		this.updateDateTime = updateDateTime;
	}
	public String getDeletedYn() {
		return deletedYn;
	}
	public void setDeletedYn(String deletedYn) {
		this.deletedYn = deletedYn;
	}

}
