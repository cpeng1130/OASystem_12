<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>DepartmentList</title>
	<%@ include file="/WEB-INF/jsp/public/common.jspf" %>
</head>
<body>
 
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--Page title-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/>Department Management
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- Header-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
            	<td width="150px">Name</td>
				<td width="150px">Superior department</td>
				<td width="200px">Description</td>
				<td>Other Operation</td>
            </tr>
        </thead>

		<!--Content-->
        <tbody id="TableData" class="dataContainer" datakey="departmentList">
        
        <s:iterator value="#departmentList">
			<tr class="TableDetail1 template">
				<td><s:a action="departmentAction_list?parentId=%{id}">${name}</s:a> &nbsp;</td>
				<td>${parent.name}&nbsp;</td>
				<td>${description}&nbsp;</td>
				<td>
					<s:a action="departmentAction_delete?id=%{id}&parentId=%{parent.id}" onclick="return window.confirm('warning')">Delete</s:a>
					<s:a action="departmentAction_editUI?id=%{id}">edit</s:a>
				</td>
			</tr>
		</s:iterator>	
			
        </tbody>
    </table>
    
    <!-- Other hyper link -->
    <div id="TableTail">
        <div id="TableTail_inside">
            <s:a action="departmentAction_addUI?parentId=%{parentId}"><img src="${pageContext.request.contextPath}/style/images/createNew.png" /></s:a>
            <s:if test="#parent != null">
				<s:a action="departmentAction_list?parentId=%{#parent.parent.id}">Back</s:a>
            </s:if>
        </div>
    </div>
</div>


</body>
</html>
