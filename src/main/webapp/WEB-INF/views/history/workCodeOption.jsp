<%@ include file="/WEB-INF/views/salesb/base.jsp" %>
<label for="searchGubun">작업명 :</label>
<select class="form-control" title="작업명" id="con_workCode" name="con_workCode" value="" >
    <option value="">전체</option>
   	<c:forEach var="codeVO" items="${code_workcodelist}" >
    	<option value="${codeVO.codeId}">${codeVO.codeName}</option>
    </c:forEach>
</select>