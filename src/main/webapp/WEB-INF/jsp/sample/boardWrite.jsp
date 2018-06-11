<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file = "/WEB-INF/include/include-header.jspf" %>
</head>
<body>
<form id="frm" name="frm" enctype="multipart/form-data">
<input type="hidden" name="idx" id="idx" value="${data.IDX}" />
<input type="hidden" name="file_idx" id="file_idx" value="" />
    <table class="board_view">
        <colgroup>
            <col width="15%">
            <col width="*"/>
        </colgroup>
        <caption>게시글 작성</caption>
        <tbody>
            <tr>
                <th scope="row">제목</th>
                <td><input type="text" id="title" name="title" class="wdp_90" value="${data.TITLE}" /></td>
            </tr>
            <tr>
                <th scope="row">첨부파일</th>
                <td>
                	<div id="file_div">
                		<p><input type="file" name="file" /> <a href="#this" id="file_add">파일추가</a></p>
                	</div>
                	
                </td>
            </tr>
            <tr>
            	<th>조회수</th>
            	<td>${data.HIT_CNT}</td>
            </tr>
            <tr>
                <td colspan="2" class="view_text">	
                    <textarea rows="20" cols="100" title="내용" id="contents" name="contents">${data.CONTENTS}</textarea>
                </td>
            </tr>
            <tr>
            	<th>첨부파일${fn:length(fileList)}</th>
            	<td>
            		<c:choose>
            			<c:when test="${fn:length(fileList) > 0}">
            				<div id="fileList">
            					<c:forEach items="${fileList}" var="row">
            						<p id="file_${row.idx}"><a href="javascript:;" onClick="fn_fileDown('${row.IDX}')">${row.ORIGINAL_FILE_NAME} (${row.FILE_SIZE}KB)<a href="#this" onClick="fileDel('${row.IDX}')">[파일삭제]</a></a></p>
            					</c:forEach>
            				</div>
            			</c:when>
            			<c:otherwise>
            				<p>등록된 파일이 없습니다.</p>
            			</c:otherwise>
            		</c:choose>
            	</td>
            </tr>
        </tbody>
    </table>
    
    <c:choose>
	    <c:when test="${empty data.IDX}">
	    	<a href="#this" class="btn" id="write" >작성하기</a>
	    </c:when>
	    <c:otherwise>
	    	<a href="#this" class="btn" id="edit" >수정하기</a>
	    	<a href="#this" class="btn" id="del" >삭제하기</a>
	    </c:otherwise>
    </c:choose>
    <a href="#this" class="btn" id="list" >목록으로</a>
</form>
     
<%@ include file="/WEB-INF/include/include-body.jspf" %>
<script type="text/javascript">
    $(document).ready(function(){
    	$("#list").on("click",function(e){
    		e.preventDefault();
    		fn_openBoardList();
    	})
    	
    	$("#write").on("click",function(e){
    		e.preventDefault();
    		fn_insertBoard();
    	})
    	
    	$("#edit").on("click",function(e){
    		e.preventDefault();
    		fn_updateBoard();
    	})
    	
    	$("#del").on("click",function(e){
    		e.preventDefault();
    		fn_deleteBoard();
    	})
    	
    	$("#file_add").on("click",function(e) {
    		e.preventDefault();
    		fn_addFile();
    	})
    	
    });
    
    function fn_insertBoard() {
    	var comSubmit = new ComSubmit("frm");
    	comSubmit.setUrl("<c:url value='/sample/insertBoard.do' />");
    	comSubmit.submit();
    }
    
    function fn_openBoardList() {
    	var comSubmit = new ComSubmit();
    	comSubmit.setUrl("<c:url value='/sample/openBoardList.do' />");
    	comSubmit.submit();
    }
    
    function fn_updateBoard() {
    	var comSubmit = new ComSubmit("frm");
    	comSubmit.setUrl("<c:url value='/sample/updateBoard.do' />");
    	comSubmit.submit();
    }
    
    function fn_deleteBoard() {
    	var comSubmit = new ComSubmit("frm");
    	comSubmit.setUrl("<c:url value='/sample/deleteBoard.do' />");
    	comSubmit.submit();
    }
    
    function fn_fileDown(idx) {
    	var comSubmit = new ComSubmit("");
    	comSubmit.setUrl("<c:url value='/common/downloadFile.do' />");
    	comSubmit.addParam("file_idx",idx);
    	comSubmit.submit();
    }
    
    var fileNameCnt = 1;
    
    function fn_addFile() {
    	$("#file_div").append("<p><input type='file' name='file"+fileNameCnt+++"'><a href='#this' class='file_del'>[파일삭제]</a></p>");
    	
    	$(".file_del").on("click",function(e){
    		fn_delFile($(this));
    	})
    }
    
    function fn_delFile(obj) {
    	obj.parent().remove();
    }
    
    function fileDel(idx) {
    	var param = "file_idx="+idx;
    	 
    	$.ajax({
    		url:"/common/fileDelete.do"
    		,data:param
    		,dataType:"text"
    		,type:"POST"
			,success:function(d) {
				if(d=="success") {
					$("#file_"+idx).remove();
				}
				
			},error:function(e) {
				console.log(e)
			}
    		
    	})
    }
    
</script>
</body>
</html>