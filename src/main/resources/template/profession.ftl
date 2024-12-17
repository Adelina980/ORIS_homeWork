<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/html">
<head>
    <title>Професии</title>
    <meta charset="utf-8"/>

    <style type="text/css">
        .header {
            display: flex;
            justify-content: center;
        }

        .content {
            width: 100%;
            display: flex;
            justify-content: center;
            flex-wrap: wrap;
        }

        .leftblock {
            flex-grow: 1;
        }

        .centerblock {
            flex-grow: 8;
        }

        .rigthblock {
            flex-grow: 1;
        }

        .footer {
            display: flex;
            justify-content: center;
        }
    </style>
</head>
<body>
<div class="header">
    <h1>Поиск профессии</h1>
</div>
<div class="content">

    <div class="leftblock"></div>
    <div class="centerblock">

        <div>
            <form action="/pagination_war_exploded/profession" method="get">
                <input id="name" name="name" type="text" value="${profession!}">
                <input type="submit" value="Искать ">
            </form>
        </div>

        <table>
            <#list professions as prof>
                <tr>
                    <td>${prof.id}</td>
                    <td>${prof.name}</td>
                </tr>
            </#list>
        </table>
    </div>

    <div class="rigthblock"></div>

</div> <!-- content -->

<div class="footer">

    <div id="">
        <#list countPages as n>
            <#if n == "...">
                ${n}&nbsp;
            <#else>
                <span>
                    <a href="/pagination_war_exploded/profession?page=${n}&name=${profession!}">${n}&nbsp;</a>
                </span>
            </#if>
        </#list>


    </div>
    <div>&copy;ИТИС</div>
</div>
</body>
</html>