package com.offact.framework.taglib;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class DateTag extends TagSupport {

	/**
	 * 년도만 표시할경우
	 */
	public static final String YYYY = "YYYY";
	
	/**
	 * 년도만 표시할경우(역순)
	 */
	public static final String YYYY_Desc = "YYYY_Desc";

	/**
	 * 년도 / 월을 표시할경우
	 */
	public static final String YYYYMM = "YYYYMM";

	/**
	 * 년도 / 월 / 일 을 표시할 경우 
	 */
	public static final String YYYYMMDD = "YYYYMMDD";

	/**
	 * 년월일을 0으로 채우기 위함. 
	 */
	public static final String YYYYMMDD_INIT = "YYYYMMDD_INIT";

	/**
	 * 
	 */
	private static final long serialVersionUID = -8375127618847230542L;

	/**
	 * 
	 */
	private String name = null;

	/**
	 * 
	 */
	private String value = null;

	/**
	 * 
	 */
	private String format = null;

	/**
	 * 
	 */
	private String addString = null;

	/**
	 * 
	 */
	private String isEdit = null;

	/**
	 * 
	 */
	private String isNull = null;

	/**
	 * 
	 */
	private String defaultName = "전체";
	
	private String contextRoot = "";

	/**
	 * @param defaultName The defaultName to set.
	 */
	public void setDefaultName(String defaultName) {
		if (defaultName == null) {
			defaultName = "전체";
		}

		this.defaultName = defaultName;
	}

	public DateTag() {
		super();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspTagException {
		return EVAL_PAGE;
	}

	/**
	 * @return
	 */
	private String getThisDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd",
				Locale.KOREA);
		return formatter.format(new Date());
	}

	/**
	 * 
	 * 받은 value 가 날짜 타입이 아닐경우 
	 * 오늘 날짜로 맞춰서 리턴한다.
	 * 
	 * @return
	 */
	private String[] parseDate() {

		String thisTime = getThisDate();

		String[] defualt = {thisTime.substring(0, 4), thisTime.substring(4, 6),
				thisTime.substring(6, 8)};

		if (YYYY.equals(format) || YYYY_Desc.equals(format)) {

			String[] str = new String[1];
			if (value != null && value.length() == 4 && check(value + "0101")) {

				str[0] = value;
				return str;
			}

			str[0] = "";
			return str;
		}

		if (YYYYMM.equals(format)) {

			String[] str = new String[2];
			if (value != null && value.length() == 6 && check(value + "01")) {

				str[0] = value.substring(0, 4);
				str[1] = value.substring(4, 6);
				return str;
			}

			str[0] = defualt[0];
			str[1] = defualt[1];
			return str;
		}

		if (YYYYMMDD.equals(format)) {
			String[] str = new String[3];

			value = value.replace("-", "");
			if (value != null && value.length() == 8 && check(value)) {

				str[0] = value.substring(0, 4);
				str[1] = value.substring(4, 6);
				str[2] = value.substring(6, 8);
				return str;
			}

			str[0] = defualt[0];
			str[1] = defualt[1];
			str[2] = defualt[2];
			return str;
		}

		if (YYYYMMDD_INIT.equals(format)) {
			// 년월일이 없는 경우 현재일자를 초기값으로 사용하지 않고 0000-00-00으로 리턴하도록 추가
			String[] str = new String[3];

			if (value != null && value.length() == 8) {
				if (check(value)) {
					str[0] = value.substring(0, 4);
					str[1] = value.substring(4, 6);
					str[2] = value.substring(6, 8);
				}
				return str;
			}

			str[0] = defualt[0];
			str[1] = defualt[1];
			str[2] = defualt[2];
			return str;
		}

		return defualt;

	}

	/**
	 * YYYY 형식을 리턴한다.
	 * 
	 * @return
	 */
	private String YYYYHtml() {

		String thisTime = getThisDate();

		int maxYear = Integer.parseInt(thisTime.substring(0, 4)) + 1;

		String select = parseDate()[0];
		if ("false".equals(isEdit) || "readonly".equals(isEdit)) {

			StringBuffer sb = new StringBuffer();
			sb.append("<input type='hidden' name = '" + name + "' value = '"
					+ select + "'>");
			sb.append(select + "년");
			return sb.toString();
		}

		StringBuffer sb = new StringBuffer();

		sb.append("<select name ='" + name + "' " + addString + " >");

		sb.append("<option value=''>" + defaultName + "</option>");

		for (int i = 1998; i <= maxYear; i++) {

			String selected = "";
			if (select.equals("" + i)) {
				selected = "selected";
			}

			sb.append("<option value='" + i + "' " + selected + " >" + i
					+ "년</option>");

		}
		sb.append("</select>");

		return sb.toString();
	}
	
	/**
	 * YYYY 형식을 리턴한다.(역순)
	 * 
	 * @return
	 */
	private String YYYYHtml_Desc() {

		String thisTime = getThisDate();

		int maxYear = Integer.parseInt(thisTime.substring(0, 4)) + 1;

		String select = parseDate()[0];
		if ("false".equals(isEdit) || "readonly".equals(isEdit)) {

			StringBuffer sb = new StringBuffer();
			sb.append("<input type='hidden' name = '" + name + "' value = '"
					+ select + "'>");
			sb.append(select + "년");
			return sb.toString();
		}

		StringBuffer sb = new StringBuffer();

		sb.append("<select name ='" + name + "' " + addString + " >");

		sb.append("<option value=''>" + defaultName + "</option>");

		for (int i = maxYear ; i>1998; i--) {

			String selected = "";
			if (select.equals("" + i)) {
				selected = "selected";
			}

			sb.append("<option value='" + i + "' " + selected + " >" + i
					+ "년</option>");

		}
		sb.append("</select>");

		return sb.toString();
	}

	/**
	 * @return
	 */
	private String YYYYMMDDHtml() {

		String select1 = "";
		String select2 = "";

		if (value != null && !"".equals(value)) {
			String[] parseDate = parseDate();
			select1 = parseDate[0] + "-" + parseDate[1] + "-" + parseDate[2];
			select2 = parseDate[0] + "" + parseDate[1] + "" + parseDate[2];
		} else {

			if (!"true".equals(isNull)) {
				String[] parseDate = parseDate();
				select1 = parseDate[0] + "-" + parseDate[1] + "-"
						+ parseDate[2];
				select2 = parseDate[0] + "" + parseDate[1] + "" + parseDate[2];
			}

		}

		if ("false".equals(isEdit) || "readonly".equals(isEdit)) {

			StringBuffer sb = new StringBuffer();
			sb.append("<input type='hidden' name = '" + name + "' value = '"
					+ select2 + "'>");
			sb.append(select1);
			return sb.toString();
		}

		StringBuffer sb = new StringBuffer();

		sb.append("<input type='hidden' name='" + name + "' value='" + select2
				+ "'>");

		sb.append("<input type='text' class='input' name='" + name + "YYYYMMDD' value='"
				+ select1 + "' maxlength='10' size='10' " + addString + " onFocus='javascript:selectText(this)' onkeypress='onlyNumber();'  onkeyup='javascript:dateFormat(this);document.all."+name+".value=extractNumber(this.value)' style='ime-mode:disabled'>");
/*
		sb.append(" <span class='calendar' onclick='Calendar_D(document.all."
				+ name + "YYYYMMDD,document.all." + name + ");'>달력</span>");
				
sb.append(" <img src='/cs/js/calendar.gif' id='btn_cal_" + name + "' border='0' align='absmiddle' style='margin-bottom:2px' onclick='Calendar_D(document.all."
				+ name + "YYYYMMDD,document.all." + name + ");'>");				
*/		
		
		sb.append(" <a href=\"javascript:Calendar_D(document.all."
				+ name
				+ "YYYYMMDD,document.all."
				+ name
				+ ");\"><img src='/cs/js/calendar.gif' id='btn_cal_"
				+ name
				+ "' border='0' align='absmiddle' style='margin-bottom:2px'></a>");

		return sb.toString();

	}

	/**
	 * @return
	 */
	private String YYYYMMDD_INIT_Html() {

		String select1 = "";
		String select2 = "";

		if (value != null && !"".equals(value)) {
			String[] parseDate = parseDate();
			select1 = parseDate[0] + "-" + parseDate[1] + "-" + parseDate[2];
			select2 = parseDate[0] + "" + parseDate[1] + "" + parseDate[2];
		} 

		if ("false".equals(isEdit) || "readonly".equals(isEdit)) {

			StringBuffer sb = new StringBuffer();
			sb.append("<input type='hidden' name = '" + name + "' value = '"
					+ select2 + "'>");
			sb.append(select1);
			return sb.toString();
		}

		StringBuffer sb = new StringBuffer();

		sb.append("<input type='hidden' name='" + name + "' value='" + select2
				+ "'>");

		sb.append("<input type='text' class='input' name='" + name + "YYYYMMDD' value='"
				+ select1 + "' maxlength='10' size='10' " + addString + " onFocus='javascript:selectText(this)' onkeypress='onlyNumber();'  onkeyup='javascript:dateFormat(this);document.all."+name+".value=extractNumber(this.value)' style='ime-mode:disabled'>");

		sb.append(" <img src='"+contextRoot+"/js/calendar.gif' id='btn_cal_" + name + "' border='0' align='absmiddle' style='margin-bottom:2px' onclick='Calendar_D(document.all."
				+ name + "YYYYMMDD,document.all." + name + ");'>");

		return sb.toString();
	}

	/**
	 * @return
	 */
	private String YYYYMMHtml() {

		String[] parseDate = parseDate();
		String selectYYYY = parseDate[0];
		String selectMM = parseDate[1];

		if ("false".equals(isEdit) || "readonly".equals(isEdit)) {

			StringBuffer sb = new StringBuffer();
			sb.append("<input type='hidden' name = '" + name + "' value = '"
					+ selectYYYY + selectMM + "'>");
			sb.append(selectYYYY + "-" + selectMM);
			return sb.toString();
		}

		StringBuffer sb = new StringBuffer();

		sb.append("<input type='hidden' name='" + name + "' value='"
				+ selectYYYY + selectMM + "'>");

		sb.append("<input type='text' class='input' name='" + name + "YYYYMM' value='"
				+ selectYYYY + "-" + selectMM + "' maxlength='7' size='7' "
				+ addString + " onFocus='javascript:selectText(this)' onkeypress='onlyNumber();' onkeyup='javascript:dateFormat_YYYYMM(this);document.all."+name+".value=extractNumber(this.value)' style='ime-mode:disabled'>");

		sb.append(" <img src='"+contextRoot+"/js/calendar.gif' border='0' align='absmiddle' onclick='Calendar_M(document.all."
				+ name + "YYYYMM,document.all." + name + ");'>");

		return sb.toString();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspTagException {

		contextRoot = pageContext.getServletContext().getContextPath();
		contextRoot = contextRoot.equals("/") ? "" : contextRoot;
		
		try {

			if (addString == null) {
				addString = "";
			}
			addString += " style=\"vertical-align: middle;\"";
			
			if (isEdit == null) {
				isEdit = "true";
			}

			String printHtml = "";

			if (YYYY.equals(format)) {
				printHtml = YYYYHtml();
			}else if (YYYY_Desc.equals(format)) {
				printHtml = YYYYHtml_Desc();
			}else if (YYYYMM.equals(format)) {
				printHtml = YYYYMMHtml();
			} else if (YYYYMMDD.equals(format)) {
				printHtml = YYYYMMDDHtml();
			} else if (YYYYMMDD_INIT.equals(format)) {
				// 년월일이 없는 경우 현재일자를 초기값으로 사용하지 않고 0000-00-00으로 리턴하도록 추가
				printHtml = YYYYMMDD_INIT_Html();
			}

			//pageContext.getOut().flush();
			JspWriter out = pageContext.getOut();
			out.write(printHtml);
		} catch (Exception e) {
			throw new JspTagException(e.getMessage());
		}

		return EVAL_PAGE;
	}

	/**
	 * @param addString The addString to set.
	 */
	public void setAddString(String addString) {
		this.addString = addString;
	}

	/**
	 * @param format The format to set.
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	/**
	 * @param isEdit The isEdit to set.
	 */
	public void setIsEdit(String isEdit) {
		this.isEdit = isEdit;
	}

	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param value The value to set.
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @param isNull
	 */
	public void setIsNull(String isNull) {
		this.isNull = isNull;
	}

	/**
	 * 
	 * 날짜인지 아닌지 체크한다. 
	 * 
	 * @param s
	 * @param format
	 * @return
	 */
	boolean check(String s) {

		if (s == null) {
			return false;
		}

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd",
				Locale.KOREA);

		Date date = null;

		try {
			date = formatter.parse(s);
		} catch (ParseException e) {
			return false;
		}

		if (!formatter.format(date).equals(s)) {
			return false;
		}

		return true;
	}
}
