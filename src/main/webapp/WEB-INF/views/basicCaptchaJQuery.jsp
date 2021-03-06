<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <%@ taglib uri="https://captcha.com/java/jsp/simple-api" prefix="botDetect"%> --%>

<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>BotDetect Java CAPTCHA Validation: Spring MVC Basic Code Example</title>
 
</head>
<body>
  <c:url value="/captcha/controller/example/validateQuery" var="uriCaptcha"></c:url>
  <form:form action="${uriCaptcha}" modelAttribute="basicExample" method="POST" cssClass="column" id="form1">
    <h1>BotDetect Java CAPTCHA Validation: <br /> Spring MVC Basic Code Example</h1>
    <fieldset>
      <legend>Java CAPTCHA validation</legend>
      <label for="captchaCode">Ingresa los caracteres de la imagen:</label>

       <!-- OBTENER LA IMAGEN DEL CAPTCHA POR MEDIO DE JQUERY -->
      <div id="botdetect-captcha" data-stylename="exampleCaptcha"></div>

      <div class="validationDiv">
        <input name="captchaCode" type="text" id="captchaCode" value="${basicExample.captchaCode}" />
        <input type="submit" name="validateCaptchaButton" value="Validate" id="validateCaptchaButton" />
        <span class="correct">${basicExample.captchaCorrect}</span>
        <span class="incorrect">${basicExample.captchaIncorrect}</span>
      </div>
    </fieldset>
  </form:form>
  <script src="/js/jquery-1.11.1.min.js"></script>
  <script src="/js/jquery.backstretch.min.js"></script>
  <script src="/js/global.js"></script>
  <script src="/js/jquery-captcha.js"></script>
  <script src="/js/login.js"></script>
</body>
</html>