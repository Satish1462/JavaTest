<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import ="com.comcast.client.EmployeeInfo" %>
    <%@ page import ="com.comcast.pojo.Employee" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>This is Dashboard page</title>

<!-- <link href="media/dataTables/demo_page.css" rel="stylesheet" type="text/css" />
        <link href="media/dataTables/demo_table.css" rel="stylesheet" type="text/css" />
        <link href="media/dataTables/demo_table_jui.css" rel="stylesheet" type="text/css" /> -->
        <link href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css" rel="stylesheet" 

           type="text/css" media="all" />
        <link href="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery-ui-1.7.2.custom.css" 

           rel="stylesheet" type="text/css" media="all" />
           
           <link href="https://cdn.jsdelivr.net/jquery.datatables/1.10.10/css/dataTables.jqueryui.min.css" 

           rel="stylesheet" type="text/css" media="all" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"

           type="text/javascript"></script>
           <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js" type="text/javascript"></script>
        <script src="https://cdn.jsdelivr.net/jquery.datatables/1.10.10/js/jquery.dataTables.min.js" 

           type="text/javascript"></script>
        <script type="text/javascript">
        $(document).ready(function () {
            $("#companies").dataTable({
                "sPaginationType": "full_numbers",
                "bJQueryUI": true
            });
        });
        </script>

</head>

<body>
<h4>Welcome!!!!</h4>
 <div id="container">
            <div id="demo_jui">
                <table id="companies" class="display">
                    <thead>
                        <tr>
                            <th>Employee name</th>
                            <th>Age</th>
                            <th>Salary</th>
                        </tr>
                    </thead>
                    <tbody>
                      <% 
                      EmployeeInfo info = new EmployeeInfo();
                      for(Employee o: info.getEmployeeDeatails()){ %>
                         
                    <tr>
                             <td><%=o.getName()%></td>
                         <td><%=o.getAge()%></td>
                         <td><%=o.getSalary()%></td>
                    </tr>
                <% } %>
                    </tbody>
                </table>
         </div>
        </div> 

</body>
</html>