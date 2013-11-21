<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>科目管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/dialog.jsp" %>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<style type="text/css">.sort{color:#0663A2;cursor:pointer;}</style>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#treeTable").treeTable({expandLevel : 5});
		});
		
		function operation(href) {
			top.$.jBox.open("iframe:" + href, "添加分类",310,$(top.document).height()-540,{
				buttons:{"确定":"ok", "关闭":true}, submit:function(v, h, f){
					var name = h.find("iframe")[0].contentWindow.name;
					var code = h.find("iframe")[0].contentWindow.code;
					//nodes = selectedTree.getSelectedNodes();
					if (v=="ok"){
						if (name.trim() == "") {
							top.$.jBox.tip("名称不能为空", 'info');
							return false;
						}
						
						if (code.trim() == "") {
							top.$.jBox.tip("科目代码不能为空", 'info');
							return false;
						}
				    	// 执行保存
				    	loading('正在提交，请稍等...');
				    	$('#subjectForm').attr('action','${ctx}/account/subject/save?id=${role.id}&idsArr='+idsArr).submit();
				    	return true;
					} else if (v=="clear"){
						h.find("iframe")[0].contentWindow.clearAssign();
						return false;
	                }
				}, loaded:function(h){
					$(".jbox-content", top.document).css("overflow-y","hidden");
				}
			});
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li <c:if test="${type == 0 }"> class="active"</c:if>><a href="${ctx}/account/subject?type=0">收入</a></li>
		<li <c:if test="${type == 1 }"> class="active"</c:if>><a href="${ctx}/account/subject?type=1">支出</a></li>
	</ul>
	<tags:message content="${message}"/>
	<form:form id="subjectForm" action="" method="post" class="hidden">
		<table id="treeTable" class="table table-striped table-bordered table-condensed">
			<tr><th>科目名称</th><th>科目代码</th><th>备注</th><shiro:hasPermission name="sys:office:edit"><th>操作</th></shiro:hasPermission></tr>
			<c:forEach items="${subjectList}" var="subject">
				<tr id="${subject.id}" pId="${subject.parent.id ne 1 ? subject.parent.id : '0'}">
					<td><a href="javascript:" onclick="operation('${ctx}/account/subject/form?id=${subject.id}')">${subject.name}</a></td>
					<td>${subject.code}</td>
					<td>${subject.remarks}</td>
					<%-- <shiro:hasPermission name="sys:office:edit"><td>
						<a href="${ctx}/sys/office/form?id=${office.id}">修改</a>
						<a href="${ctx}/sys/office/delete?id=${office.id}" onclick="return confirmx('要删除该机构及所有子机构项吗？', this.href)">删除</a>
						<a href="${ctx}/sys/office/form?parent.id=${office.id}">添加下级机构</a> 
					</td></shiro:hasPermission> --%>
					<td>
						<a href="javascript:" onclick="operation('${ctx}/account/subject/form?id=${subject.id}')">修改</a>
						<a href="${ctx}/account/subject/delete?id=${subject.id}" onclick="return confirmx('要删除该分类吗？', this.href)">删除</a>
						<c:if test="${subject.level eq 1 }"><a href="javascript:" onclick="operation('${ctx}/account/subject/form?parent.id=${subject.id}')">添加分类</a></c:if> 
					</td>
				</tr>
			</c:forEach>
		</table>
	</form:form>
</body>
</html>