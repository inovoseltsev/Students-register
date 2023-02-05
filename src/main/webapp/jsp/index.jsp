<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>Student list</title>
    <link href="resources/bootstrap/bootstrap.min.css" type="text/css"
          rel="stylesheet">
    <link href="resources/css/main.css" type="text/css"
          rel="stylesheet">
</head>
<body>

<div class="table-wrapper" id="main-table">
    <button class="btn btn-primary add-btn" data-toggle="modal"
            data-target="#modal-form"
            onclick="showAddModal()">Add Student
    </button>
    <table class="table table-hover table-bordered">
        <thead>
        <tr>
            <th>First name</th>
            <th>Last name</th>
            <th>Age</th>
            <th>Birthday</th>
            <th>Faculty</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="student" items="${studentList}">
            <tr id="${student.getStudentId()}">
                <td>${student.getFirstName()}</td>
                <td>${student.getLastName()}</td>
                <td>${student.getAge()}</td>
                <td>${student.getBirthday()}</td>
                <td>${student.getFaculty()}</td>
                <td>
                    <button class="btn btn-primary"
                            onclick="showEditModal(${student.getStudentId()})"
                            data-toggle="modal" data-target="#modal-form">
                        Edit
                    </button>
                    <button class="btn btn-link">
                        <a
                                href="delete?id=${student.getStudentId()}&pageNumber=${navigationPage}">
                            Delete</a>
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="pagination">
        <p class="navigation-label">${navigationPage} page</p>
        <nav class="pagination-bar" aria-label="Page navigation example">
            <ul class="pagination">
                <c:forEach var="number" begin="1" end="${numberOfPages}">
                    <li onclick="setNavigationNumber(${number})"
                        class="page-item"><a class="page-link"
                                             href="page?number=${number}">
                            ${number}</a></li>
                </c:forEach>
            </ul>
        </nav>
    </div>
</div>
<div class="save-btn-wrapper">
    <a class="file-link" href="xlsx">
        <button
                class="btn btn-primary save-btn">Save to
            .xlsx
        </button>
    </a>
</div>

<div class="modal fade" id="modal-form" tabindex="-1" role="dialog"
     aria-labelledby="modalTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modalTitle">Add
                    Student</h5>
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form name="meForm" id="changeForm" action="redact" method="post">
                <div class="modal-body">
                    <p>Please fill the form</p>
                    <input type="hidden" name="id" id="studentId">
                    <input type="hidden" name="pageNumber"
                           value="${navigationPage}">
                    <hr>

                    <label for="firstName"><b>First Name</b></label>
                    <div class="form-group">
                        <input class="form-control form-control-sm" type="text"
                               id="firstName"
                               name="firstName" placeholder="Enter student name"
                               required>
                    </div>
                    <hr>

                    <label for="lastName"><b>Last Name</b></label>
                    <div class="form-group">
                        <input class="form-control form-control-sm" type="text"
                               id="lastName"
                               name="lastName"
                               placeholder="Enter student last name" required>
                    </div>
                    <hr>

                    <label for="birthday"><b>Birthday date</b></label>
                    <div class="form-group">
                        <input class="form-control form-control-sm" type="date"
                               id="birthday"
                               name="birthday"
                               placeholder="Enter student birthday"
                               min="1960-01-01" max="2004-01-01" required>
                    </div>
                    <hr>

                    <label for="faculty"><b>Faculty</b></label>
                    <div class="form-group">
                        <input class="form-control form-control-sm" type="text"
                               id="faculty" name="faculty"
                               placeholder="Enter student faculty"
                               required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" id="cancel-button"
                            name="cancel"
                            class="btn btn-secondary"
                            data-dismiss="modal">Close
                    </button>
                    <button id="formAddBtn" type="submit"
                            class="btn btn-primary">Add
                    </button>
                    <button id="formEditBtn" type="submit"
                            class="btn btn-primary">Edit
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
<script src="resources/js/script.js"></script>
</body>
</html>
