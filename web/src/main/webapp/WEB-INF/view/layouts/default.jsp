
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<head>
    <title>E-LIBRARY</title>
</head>
<body>
<div><tiles:insertAttribute name="header"/></div>
<div style="float:left;padding:10px;width:80%;border-left:1px solid pink;">
    <tiles:insertAttribute name="body"/></div>
<div style="clear:both"><tiles:insertAttribute name="footer"/></div>
</body>
</html>