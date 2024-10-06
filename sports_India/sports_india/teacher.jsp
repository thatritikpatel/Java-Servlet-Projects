<%@ page import="java.util.*,models.*" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
        integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <title>Student</title>
    <link rel="stylesheet" href="css/common.css">
</head>

<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand btn btn-outline-secondary " href="admin.jsp">Admin Home</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <%@ include file="links.jsp" %>
            </ul>
            <form class="form-inline my-2 my-lg-0">
                <input class="form-control mr-sm-2 btn-dark" type="submit" name='all' id='all'
                    formaction="allstudents.do" value='Show all'>
                <input class="form-control mr-sm-2 btn-dark" type="button" name='add' id='add' value='Add Teacher'>
            </form>
        </div>
    </nav>



    <div id="maincontainer">
        <div id="addcontainer">
            <form action="addstudent.do" method="post">

                <div class="form-group">
                    <label for="stname">Full Name</label>
                    <input type="text" class="form-control" id="stname" name='stname' required>
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" class="form-control" id="stemail" name='email' required>
                </div>
                <div class="form-group">
                    <label for="pass">Password</label>
                    <input type="password" class="form-control" id="stpass" name='stpass' required>
                </div>

                <div class="form-group">
                    <label for="degree">Degree&nbsp;/&nbsp;Highest Qualification&nbsp;&nbsp;</label>
                    <input type="text" class="form-control" id="degree" name='degree' required>
                </div>

                <div class="form-group">
                    <label for="branch">Age</label>
                    <input type="text" class="form-control" id="branch" name='branch' required>
                </div>

                <div class="form-group">
                    <label for="semester">Sports Name</label>
                    <input type="text" class="form-control" id="semester" name='semester' required>
                </div>
                <div class="form-group">
                    <button type="submit" class='btn btn-block btn-dark btn-lg active'>Singup</button>
                </div>
            </form>
        </div>
        <div id="allcontainer" >
            <%-- ALL Students --%>
            <% ArrayList<Student> students =  (ArrayList<Student>)request.getAttribute("students"); %>

            <% if(students!=null){ %>
            <%-- <input type="hidden" id="studentsarehere" value='true' > --%>
            <script>
                allcontainer.style.display = 'block';
                addcontainer.style.display = 'none';
            </script>
            <table class='table table-dark table-striped table-bordered table-hover '>
                <caption>All Students</caption>
                <tr class='thead-dark'>
                    <th>studentId</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Password</th>
                    <th>Degree</th>
                    <th>Branch</th>
                    <th>Semester</th>
                    <th class="bg-transparent ">Delete</th>
                </tr>

                <% for(Student student: students){ %>
                <tr>
                    <td> <%= student.getStudentId() %> </td>
                    <td> <%= student.getName() %> </td>
                    <td> <%= student.getEmail() %> </td>
                    <td> <%= student.getPassword() %> </td>
                    <td> <%= student.getDegree() %> </td>
                    <td> <%= student.getBranch() %> </td>
                    <td> <%= student.getSemester() %> </td>
                    <td class='bg-transparent'> <a class=" font-weight-bolder   activate btn  btn-danger  btn-block" href="delete.do?deleteId=<%=student.getStudentId()%>&table=students&page=allstudents">Delete</a> </td>
                </tr>


                <% } %>
            </table> <% }else{ %>

            <h1 class="btn-dark">NO Records found</h1>

            <%  } %>
        </div>
    </div>

    <%@ include file="footer.jsp" %>
</body>