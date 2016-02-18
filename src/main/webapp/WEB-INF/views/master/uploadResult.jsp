<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<SCRIPT>
  
  var errorMsg='${errorMsgList}';
  errorMsg = errorMsg.substring(0,errorMsg.lastIndexOf('^'));
  var errorMsgs=errorMsg.split('^');

  var msg='업로드 성공 : ${rtnSuccessList.size()} 건\n업로드 실패 : ${rtnErrorList.size()} 건 ';
  
  parent.uploadClose(msg,errorMsgs);
   
</SCRIPT>
