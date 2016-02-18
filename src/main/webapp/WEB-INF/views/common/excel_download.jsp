<%@page import="com.smartxls.WorkBook"%>
<%@page import="java.io.OutputStream"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%
    System.out.println("########################################CLIENT FILE DOWN START");
    WorkBook wb = (WorkBook)request.getAttribute("excel");
    System.out.println("########################################CLIENT FILE DOWN END");
    if (wb != null) {
        out.clear(); //out--> jsp자체 객체
        out=pageContext.pushBody(); //out--> jsp자체 객체
        OutputStream fout = response.getOutputStream(); 
        wb.writeXLSX(fout);
//        wb.writeXLSX(fout, "12345");
    }
%>