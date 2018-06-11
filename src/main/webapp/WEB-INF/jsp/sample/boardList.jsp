<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file = "/WEB-INF/include/include-header.jspf" %>
</head>
<body>
<h2>게시판 목록</h2>
<table style="border:1px solid #ccc">
    <colgroup>
        <col width="10%"/>
        <col width="*"/>
        <col width="15%"/>
        <col width="20%"/>
    </colgroup>
    <thead>
        <tr>
            <th scope="col">글번호</th>
            <th scope="col">제목</th>
            <th scope="col">조회수</th>
            <th scope="col">작성일</th>
        </tr>
    </thead>
    <tbody>
    	<c:choose>
    		<c:when test="${fn:length(list) > 0}">
    			<c:forEach items="${list}" var="row">
    				<tr>
    					<td>${row.IDX}</td>
    					<td><a href="javascript:;" onClick="goView('${row.IDX}')">${row.TITLE}</a></td>
    					<td></td>
    					<td></td>
    				</tr>
    			</c:forEach>
    		</c:when>
    		<c:otherwise>
                <tr>
                    <td colspan="4">조회된 결과가 없습니다.</td>
                </tr>
            </c:otherwise>
    		
    	</c:choose>
    </tbody>
</table>

<p><a href="/sample/openBoardWrite.do">등록</a></p>

<c:if test="${not empty paginationInfo}">
	<ui:pagination paginationInfo="${paginationInfo}" type="text" jsFunction="fn_search"/>
</c:if>

<input type="hidden" id="currentPageNo" name="currentPageNo"/>

<%@ include file="/WEB-INF/include/include-body.jspf" %>
<script type="text/javascript">    
    function goView(n) {
    	var comSubmit = new ComSubmit();
    	console.log("idx="+n);
    	comSubmit.setUrl("<c:url value='/sample/openBoardView.do' />?idx="+n);
    	comSubmit.submit();
    }
    
    function fn_search(pageNo) {
    	 var comSubmit = new ComSubmit();
         comSubmit.setUrl("<c:url value='/sample/openBoardList.do' />");
         comSubmit.addParam("currentPageNo", pageNo);
         comSubmit.submit();
    }
</script>
</body>
</html>
