<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Student Registration</title>
</head>
<body>

<h2>Student Registration</h2>

<!-- Message -->
<%
    String message = (String) request.getAttribute("message");
    if (message != null) {
%>
    <p><%= message %></p>
<%
    }
%>

<!-- Simple HTML Form -->
<form action="/student" method="post">

    <label>Name:</label><br>
    <input type="text" name="name" required /><br><br>

    <label>Email:</label><br>
    <input type="email" name="email" required /><br><br>

    <label>Age:</label><br>
    <input type="number" name="age" required /><br><br>

    <label>Course:</label><br>
    <input type="text" name="course" required /><br><br>

    <button type="submit">Submit</button>
</form>

</body>
</html>
