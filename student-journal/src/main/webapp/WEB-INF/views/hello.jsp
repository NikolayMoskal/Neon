<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<body>
	<a href="?lang=en">English</a> | <a href="?lang=ru">Russian</a>

	<h1><spring:message code="welcome.title" /></h1>
	<h1><spring:message code="welcome.message" />: ${message}</h1>

	Current locale: ${pageContext.response.locale} / ${locale}
</body>
</html>