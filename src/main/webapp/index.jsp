<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
</body>
</html>

<%--.jsp文件是Java Server Pages的缩写，它是一种在服务端渲染Web内容的技术。 --%>
<%--JSP允许你将Java代码嵌入到HTML页面中，然后这些页面在服务器上执行，生成动态内容并发送给客户端（浏览器）。--%>
<%--这使得Web开发者可以快速地开发基于Java的Web应用程序，而无需将表示层（HTML或其他标记）和业务逻辑层完全分离。--%>
<%--当一个请求指向一个JSP页面时，服务器（如Apache Tomcat）会处理JSP文件，执行其中的Java代码，并将其转换为HTML。--%>
<%--然后，服务器将生成的HTML发送到客户端浏览器。这个过程允许在用户看到的页面中动态地插入数据，如数据库查询结果、用户输入处理结果等。--%>