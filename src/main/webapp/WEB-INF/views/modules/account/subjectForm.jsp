<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		var code = "";
		var name = "";
		function onBlur() {
			name = $("#name").val();
			code = $("#code").val();
		}
	</script>
</head>
<body>
	<div style="text-align: center;">
		<br>
		<div class="control-group">
			<label class="control-label">分类名称:</label>
				<input name="name" id="name" onBlur="onBlur()" htmlEscape="false" maxlength="250"
					class="required" />
		</div>
		<div class="control-group">
			<label class="control-label">科目代码:</label>
				<input name="code" id="code" onBlur="onBlur()" htmlEscape="false" maxlength="250"
					class="required" />
		</div>
	</div>
</body>
</html>