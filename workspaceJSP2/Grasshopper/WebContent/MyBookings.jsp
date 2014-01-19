 <jsp:include page="header_template.jsp">
    <jsp:param name="activePage" value="MyBookings" />
</jsp:include>

<%@ page import="java.util.ArrayList" %>


<% ArrayList<String[]> bookings = (ArrayList<String[]>)request.getAttribute("bookings"); %>

<h2>Meine Buchungen</h2>

<div id=results>
		
		<%if(bookings!= null && bookings.size()!=0) {
		for(int i=0; i<bookings.size();i++) { %>
		<%String[] summary = bookings.get(i); %>
		<div class="result_row" style="height:140px">
			<div class="resultcolumn">
	        	<img src="bild.jpg" >
	  		</div>
	  
 			<div class="double_resultcolumn">
				<h2>Hotelname:  <%=summary[0] %></h2>
				<h2>Raumnummer: <%=summary[1] %></h2>
				<%=summary[2] %> - <%=summary[3] %>
			</div>

		</div>
		<div class="separator"></div>
  		<%}
		}
		else{ %>
  		<h2>Keine Buchungen gefunden</h2>
  		<%} %>
	</div>




<%@include file="footer_template.jsp" %>