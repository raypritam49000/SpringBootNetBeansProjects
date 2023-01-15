<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>javaguides.net</title>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <!-- Optional theme -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="col-md-offset-1 col-md-10">
                <h2>CRM - Customer Relationship Manager</h2>
                <hr />

                <input type="button" value="Add Customer"
                       onclick="window.location.href = 'showForm'; return false;"
                       class="btn btn-primary" />
                <br/><br/>
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <div class="panel-title">Customer List</div>
                    </div>
                    <div class="panel-body">
                        <table class="table table-striped table-bordered">
                            <tr>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Email</th>
                                <th>Action</th>
                            </tr>

                            <!-- loop over and print our customers -->
                            <c:forEach var="tempCustomer" items="${customers}">

                                <!-- construct an "update" link with customer id -->
                                <c:url var="updateLink" value="/customer/updateForm">
                                    <c:param name="customerId" value="${tempCustomer.id}" />
                                </c:url>

                                <!-- construct an "delete" link with customer id -->
                                <c:url var="deleteLink" value="/customer/delete">
                                    <c:param name="customerId" value="${tempCustomer.id}" />
                                </c:url>

                                <tr>
                                    <td>${tempCustomer.firstName}</td>
                                    <td>${tempCustomer.lastName}</td>
                                    <td>${tempCustomer.email}</td>

                                    <td>
                                        <!-- display the update link --> <a href="${updateLink}">Update</a>
                                        | <a href="${deleteLink}"
                                             onclick="if (!(confirm('Are you sure you want to delete this customer?')))
                                                         return false">Delete</a>
                                    </td>

                                </tr>

                            </c:forEach>

                        </table>

                    </div>
                </div>
            </div>

        </div>
    </body>
</html>
