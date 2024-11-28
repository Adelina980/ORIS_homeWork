<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/html">
<head>
    <title>${title_page}</title>
    <meta charset="UTF-8">
</head>
<body>
<h2>Hello!</h2>
<ul>
    <#list users as user>
        <li>${user.name}</li>
    </#list>
</ul>
</body>
</html>
