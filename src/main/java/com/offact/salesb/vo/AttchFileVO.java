package com.offact.salesb.vo;

public class AttchFileVO extends AbstractVO  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String boardId;
	private String seqNo;
	private String attSeqNo;
	private String fileName;
	private String filePath;
	private String orgFileName;
	private String createDate;
	public String getBoardId() {
		return boardId;
	}
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
	public String getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}
	public String getAttSeqNo() {
		return attSeqNo;
	}
	public void setAttSeqNo(String attSeqNo) {
		this.attSeqNo = attSeqNo;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getOrgFileName() {
		return orgFileName;
	}
	public void setOrgFileName(String orgFileName) {
		this.orgFileName = orgFileName;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
