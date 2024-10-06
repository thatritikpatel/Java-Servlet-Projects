<%@ page import="java.util.*,models.*" %>

    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta charset="utf-8">
            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

            <!-- Bootstrap CSS -->
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
                integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
                crossorigin="anonymous">
            <title>Student</title>
            <link rel="stylesheet" href="css/common.css">
        </head>

        <body>
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                <a class="navbar-brand btn btn-outline-secondary " href="admin.jsp">Admin Home</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse"
                    data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                    aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">

                        <%@ include file="links.jsp" %>
                    </ul>

                </div>
            </nav>

            <div class="main-container">


                <table class='table table-dark table-striped table-bordered table-hover '>
                    <caption>Queries</caption>
                    <tr class='thead-dark'>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Message</th>
                        <th class="bg-transparent ">Delete</th>
                    </tr>

                    <c:forEach items="${queries}" var="query">
                        <tr>
                            <td>${query.name}</td>
                            <td><a title="Click to send email and resolve the query" data-bs-toggle="tooltip"
                                    data-bs-placement="top" class="btn btn-outline-primary btn-block d-grid gap-2"
                                    href="mailto:${query.email}">${query.email}</a></td>
                            <td>${query.phone}</td>
                            <td>${query.message}</td>
                            <td class='bg-transparent'> <a title='Click to delete resolved queries'
                                    data-bs-toggle="tooltip" data-bs-placement="top"
                                    class=" font-weight-bolder   activate btn  btn-danger  btn-block"
                                    href="delete.do?deleteId=${query.queryId}&table=queries&page=query">Delete</a> </td>
                        </tr>

                    </c:forEach>
                </table>

            </div>

            <%@ include file="footer.jsp" %>
        </body>