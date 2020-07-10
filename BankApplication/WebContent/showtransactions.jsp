<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
				<style>
					table, tr, td{
						 border: 1px solid black;
						 width : 40%;
						 text-align: center;
					}
				</style>
</head>
<body>
				<table>
					<c:forEach var="transaction" items="${list}">
						
							<tr>
								<td>
									<h5><c:out value="${transaction.getAccno()}"></c:out></h5>
								</td>
								<td>
									<h5><c:out value="${transaction.getTransaction()}"></c:out></h5>
								</td>
								<td>
									<h5><c:out value="${transaction.getAmount()}"></c:out></h5>
								</td>	
							</tr>
					
						</c:forEach>
					</table>
				
</body>
</html>