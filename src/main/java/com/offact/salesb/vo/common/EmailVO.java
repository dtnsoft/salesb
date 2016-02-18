package com.offact.salesb.vo.common;

import java.io.Serializable;
import java.io.File;
import java.util.List;

import com.offact.salesb.vo.AbstractVO;

public class EmailVO extends AbstractVO {

	private static final long serialVersionUID = 1L;

	 String toEmail;
	 String fromEmail;
	 String subject;
	 String msg;
	 
	 List<String> toEmails;
	 List<String> toEmail_Ccs;
	 List<String> attcheFileName;
	 List<File> file;

	 public List<String> getToEmails() {
	  return toEmails;
	 }
	 public void setToEmails(List<String> toEmails) {
	  this.toEmails = toEmails;
	 }
	 public List<File> getFile() {
	  return file;
	 }
	 public void setFile(List<File> file) {
	  this.file = file;
	 }
	 public String getToEmail() {
	  return toEmail;
	 }
	 public void setToEmail(String toEmail) {
	  this.toEmail = toEmail;
	 }
	 public String getFromEmail() {
	  return fromEmail;
	 }
	 public void setFromEmail(String fromEmail) {
	  this.fromEmail = fromEmail;
	 }
	 public String getSubject() {
	  return subject;
	 }
	 public void setSubject(String subject) {
	  this.subject = subject;
	 }
	 public List<String> getAttcheFileName() {
	  return attcheFileName;
	 }
	 public void setAttcheFileName(List<String> attcheFileName) {
	  this.attcheFileName = attcheFileName;
	 }
	 public String getMsg() {
	  return msg;
	 }
	 public void setMsg(String msg) {
	  this.msg = msg;
	 }
	 public static long getSerialversionuid() {
	  return serialVersionUID;
	 }
	 
	 public List<String> getToEmail_Ccs() {
		return toEmail_Ccs;
	}
	public void setToEmail_Ccs(List<String> toEmail_Ccs) {
		this.toEmail_Ccs = toEmail_Ccs;
	}
	@Override
	 public String toString() {
	  return "EmailVO [toEmail=" + toEmail + ", subject=" + subject
	    + ", msg=" + msg + "]";
	 }

}
