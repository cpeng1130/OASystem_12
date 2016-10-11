<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
	<title>UserInformationBoard</title>
    <%@ include file="/WEB-INF/jsp/public/common.jspf" %>
</head>
<body>

<!-- Title content -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--title-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> UserInformation
        </div>
        <div id="Title_End"></div>
    </div>
</div>


<!--static page content -->
<div id=MainArea>

  <%--   <s:form action="userAction_%{id == null ? 'add' : 'edit'}"> --%>
    <s:form action="userAction_%{'add'}">
    	<s:hidden name="id"></s:hidden>
    
        <div class="ItemBlock_Title1"><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> UserInformation </div> 
        </div>
        
        
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
                    	<td width="100">Department</td>
                        <td>
                            <s:select name="departmentId" cssClass="SelectStyle"
                            	list="#departmentList" listKey="id" listValue="name"
                            	headerKey="" headerValue="Select-->department">
                            </s:select>
                        </td>
                    </tr>
                    <tr><td>Login-name</td>
                        <td><s:textfield name="loginName" cssClass="InputStyle"/> *
							（unique）
						</td>
                    </tr>
                    <tr><td>name</td>
                        <td><s:textfield name="name" cssClass="InputStyle"/> *</td>
                    </tr>
					<tr><td>gender</td>
                        <td>
							<s:radio name="gender" list="%{ {'male', 'female'} }"></s:radio>
						</td>
                    </tr>
					<tr><td>phoneNumber</td>
                        <td><s:textfield name="phoneNumber" cssClass="InputStyle"/></td>
                    </tr>
                    <tr><td>E-mail</td>
                        <td><s:textfield name="email" cssClass="InputStyle"/></td>
                    </tr>
                    <tr><td>Note</td>
                        <td><s:textarea name="description" cssClass="TextareaStyle"></s:textarea></td>
                    </tr>
                </table>
            </div>
        </div>
        
		<div class="ItemBlock_Title1"><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> Position </div> 
        </div>
        
        <!-- form content display -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
						<td width="100">position</td>
                        <td>
                        	<s:select name="roleIds" cssClass="SelectStyle"
                        		multiple="true" size="10" 
                        		list="#roleList" listKey="id" listValue="name">
                            </s:select>
							+"Ctrl"  multiple choice
                        </td>
                    </tr>
                </table>
            </div>
        </div>		
		
        <!-- form operation -->
        <div id="InputDetailBar">
            <input type="image" src="${pageContext.request.contextPath}/style/images/save.png"/>
            <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
        </div>
    </s:form>
</div>

<div class="Description">
	Hint：<br />
	1，Login-Name is unique。<br />
	2，Initialisation password is "1234".<br />
	3，Using MD5 to encrypt password.<br />
	4，Login the system, user can reset the password "setting-->reset password".<br />
	5，Login the system,user can reset the icon ,"setting-->personal information---> change icon".<br />
</div>

</body>
</html>
