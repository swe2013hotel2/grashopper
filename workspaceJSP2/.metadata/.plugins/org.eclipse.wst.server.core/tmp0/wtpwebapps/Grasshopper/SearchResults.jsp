 <jsp:include page="header_template.jsp">
    <jsp:param name="activePage" value="Hotelsuche" />
</jsp:include>
<%@ page import="java.util.ArrayList" %>
<% ArrayList<String[]> hotels = (ArrayList<String[]>)request.getAttribute("results"); %>
    
<div class="content">
	
	<div id=results>
		
		<h2>Suchergebnisse</h2>
		
		<%if(hotels!= null && hotels.size()!=0) {
		for(int i=0; i<hotels.size();i++) { %>
		<%String[] hotel = hotels.get(i); %>
		<div class="result_row"> 

			<div class="resultcolumn">
	        	<img src="bild.jpg" >
	  		</div>
	  
 			<div class="resultcolumn">
				<h2><%=hotel[1] %></h2>
			</div>
 
	  		<div class="resultcolumn3">
				<h2>ab <%=hotel[6] %> EUR</h2>
				<a href="Detailansicht.jsp?hotelid=<%=hotel[0] %>&hotelname=<%=hotel[1] %>&city=<%=hotel[2] %>&country=<%=hotel[3] %>&roomnumber=<%=hotel[4] %>&roomsize=<%=hotel[5] %>&roomcost=<%=hotel[6] %>&begindate=<%=hotel[7] %>&enddate=<%=hotel[8] %>">Ansehen</a>
				<a href="Buchen.jsp?hotelid=<%=hotel[0] %>&hotelname=<%=hotel[1] %>&city=<%=hotel[2] %>&country=<%=hotel[3] %>&roomnumber=<%=hotel[4] %>&roomsize=<%=hotel[5] %>&roomcost=<%=hotel[6] %>&begindate=<%=hotel[7] %>&enddate=<%=hotel[8] %>">Buchen</a>
			</div>
		</div>
  		<%}
		}
		else{ %>
  		<h2>Keine Ergebnisse gefunden</h2>
  		<%} %>
	</div>
</div>
  <%@include file="footer_template.jsp" %>