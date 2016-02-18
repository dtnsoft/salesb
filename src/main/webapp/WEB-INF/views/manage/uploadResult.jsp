<%@ include file="/WEB-INF/views/admin/base.jsp" %>
<SCRIPT>

   var msg='업로드 성공 : ${rtnSuccessList.size()} 건\n업로드 실패 : ${rtnErrorList.size()} 건 ';
   parent.uploadClose(msg);
   
</SCRIPT>
