package com.offact.framework.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

public class PagingTag extends TagSupport {

    private String totalCount;

    private String rowCount;

    private String curPage;

    private String cbFnc;

    private final int maxPagingCount = 10;

    /**
     * edit 2014.02.12 HSH cbFnc 추가함
     */
    private static final long serialVersionUID = 1L;

    private final Logger logger = Logger.getLogger(getClass());

    @Override
    public int doEndTag() throws JspException {
        // TODO Auto-generated method stub
        return super.doEndTag();
    }

    @Override
    public int doStartTag() throws JspException {
        // TODO Auto-generated method stub
        String contextRoot = pageContext.getServletContext().getContextPath();
        contextRoot = contextRoot.equals("/") ? "" : contextRoot;

        StringBuffer html = new StringBuffer();

        // logger.info("\n doStartTag \n totalCount ==>" + totalCount
        // + "\n rowCount ==>" + rowCount
        // + "\n curPage ==>" + curPage
        // );

        if (totalCount.equals("") || rowCount.equals("") || curPage.equals("")) {
            return super.doStartTag();
        }

        if (cbFnc == null) {
            cbFnc = "goPage";
        }

        if (cbFnc.equals("")) {
            cbFnc = "goPage";
        }

        long lTotalCount = Long.parseLong(totalCount);
        long lRowCount = Long.parseLong(rowCount);
        long lCurPage = Long.parseLong(curPage);
        // 전체 페이지 수
        long lPageCount = lTotalCount % lRowCount;

        // logger.info("\n doStartTag \n lPageCount ==>" + lPageCount );

        if (lPageCount == 0L) {
            lPageCount = lTotalCount / lRowCount;
        } else {
            lPageCount = lTotalCount / lRowCount + 1L;
        }

        html.append("<div class=\"m_paginate_wrap\"><div class=\"m_paginate\">\n");

        if (lTotalCount > 0L)
        {
          if (lCurPage != 1L)
          {
            html.append("<a href=\"javascript:" + 
              this.cbFnc + 
              "('1');\" class=\"prev\"><span class=\"blind\"></span></a>");
            html.append("<a href=\"javascript:" + 
              this.cbFnc + 
              "('" + (
              lCurPage - 1L) + 
              "');\" class=\"prev\"><span class=\"blind\"></span></a>");
          }
          long startPage = lCurPage / 10L * 10L + 1L;
          if ((0L == lCurPage % 10L) && (lCurPage > 9L)) {
            startPage -= 10L;
          }
          long endPage = startPage + 10L - 1L;

          if (endPage > lPageCount) {
            endPage = lPageCount;
          }
          for (long page = startPage; page <= endPage; page += 1L)
          {
            if (lCurPage == page)
              html.append("<strong>" + page + "</strong>");
            else {
              html.append("<a href=\"javascript:" + this.cbFnc + "('" + page + 
                "');\">" + page + "</a>");
            }
          }

          if (lCurPage != lPageCount)
          {
            html.append("<a href=\"javascript:" + 
              this.cbFnc + 
              "('" + (
              lCurPage + 1L) + 
              "');\" class=\"next\"><span class=\"blind\"></span></a>");
            html.append("<a href=\"javascript:" + 
              this.cbFnc + 
              "('" + 
              lPageCount + 
              "');\" class=\"next\"><span class=\"blind\"></span></a>");
          }
        }

        html.append("</div></div>\n");

        try {

            // pageContext.getOut().flush();

            JspWriter out = pageContext.getOut();

            out.write(html.toString());
        } catch (IOException exx) {

            throw new JspTagException(exx.getMessage());

        }

        return super.doStartTag();
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String getRowCount() {
        return rowCount;
    }

    public void setRowCount(String rowCount) {
        this.rowCount = rowCount;
    }

    public String getCurPage() {
        return curPage;
    }

    public void setCurPage(String curPage) {
        this.curPage = curPage;
    }

    public String getCbFnc() {
        return cbFnc;
    }

    public void setCbFnc(String cbFnc) {
        this.cbFnc = cbFnc;
    }

}
