<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Spring MVC 5 - form handling | Java Guides</title>
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
            <div class="col-md-offset-2 col-md-7">
                <h2 class="text-center">Spring MVC 5 + Hibernate 5 + JSP + MySQL
                    Example</h2>
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <div class="panel-title">Add Customer</div>
                    </div>
                    <div class="panel-body">
                        <form:form action="saveCustomer" cssClass="form-horizontal"
                                   method="post" modelAttribute="customer">

                            <!-- need to associate this data with customer id -->
                            <form:hidden path="id" />

                            <div class="form-group">
                                <label for="firstname" class="col-md-3 control-label">First
                                    Name</label>
                                <div class="col-md-9">
                                    <form:input path="firstName" cssClass="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="lastname" class="col-md-3 control-label">Last
                                    Name</label>
                                <div class="col-md-9">
                                    <form:input path="lastName" cssClass="form-control" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="email" class="col-md-3 control-label">Email</label>
                                <div class="col-md-9">
                                    <form:input path="email" cssClass="form-control" />
                                </div>
                            </div>

                            <div class="form-group">
                                <!-- Button -->
                                <div class="col-md-offset-3 col-md-9">
                                    <form:button cssClass="btn btn-primary">Submit</form:button>
                                    </div>
                                </div>

                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
