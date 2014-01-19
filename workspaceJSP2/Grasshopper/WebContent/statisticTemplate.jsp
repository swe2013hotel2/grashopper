	<%@ page import="swe2013.location.Statistic" %>
	<%Statistic statistic = (Statistic)request.getAttribute("statistic"); %>
	<%if(statistic!=null){ %>
		<% int days = statistic.getAmountBookings().length; %>
		
		<%for(int i=0; i<days; i++) {%>
	      <div class="resultrow" style="height:18px">
	      	<div  style="width:100px; float:left;"><%=statistic.getDateBookingsByID(i) %></div><div class="statistic_bar" style="width:<%=30*(statistic.getAmountBookingsByID(i)+1) %>px; float:left;"> <%=statistic.getAmountBookingsByID(i) %> </div>
	      </div>
	      <br>
	    <%} %>
	  <%}else{ %>
	  <h2>Noch keine Buchungen vorhanden</h2>
	  <%} %>