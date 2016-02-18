<%@ include file="/WEB-INF/views/salesb/base.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
<link href="css/issue_style.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
body {
	background-color: #ffffff;
}
-->
</style></head>

<body>
<table width="630" cellspacing="0" cellpadding="0" align="center" >
	<tr>
		<td><img src="<%= request.getContextPath() %>/images/error/error_boxtop.gif" width="630" height="32" /></td>
	</tr>
	<tr>
		<td align="center" background="<%= request.getContextPath() %>/images/error/error_boxcen.gif"><table width="500" cellspacing="0" cellpadding="0">
			<tr>
				<td height="30" class="tit_black_b">다음 사유로 오류가 발생했습니다.</td>
			</tr>
			<tr>
				<td height="1" bgcolor="a9a9a9"></td>
			</tr>
		</table>
				<table width="500" cellspacing="0" cellpadding="0" style="margin-top:18px">
					<tr>
						<td width="145"><img src="<%= request.getContextPath() %>/images/error/error_ico_error.gif" width="145" height="134" />&nbsp;&nbsp;</td>
						<td>&nbsp;&nbsp;<img src="<%= request.getContextPath() %>/images/common/bu_square.gif" alt="" /> 일시적인 장애로 시스템 오류가 발생했습니다.<br>
			                &nbsp;&nbsp;<img src="<%= request.getContextPath() %>/images/common/bu_square.gif" alt="" /> 관리자에게 문의해주세요.
							<!-- &nbsp;&nbsp;<textarea style='height:50px' row="3" class="form-control" id="defer_reason_div" disabled name="defer_reason_div"    placeholder="${errorDesc}"/-->
						</td>
					</tr>
			</table></td>
	</tr>
	<tr>
		<td><img src="<%= request.getContextPath() %>/images/error/error_boxbot.gif" width="630" height="32" /></td>
	</tr>
	<tr>
		<td height="300">&nbsp;</td>
	</tr>
</table>
</body>
</html>
