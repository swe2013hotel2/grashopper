 <jsp:include page="header_template.jsp">
    <jsp:param name="activePage" value="Hotelsuche" />
</jsp:include>
<%@ page import="java.util.ArrayList" %>
<% ArrayList<String[]> hotels = (ArrayList<String[]>)request.getAttribute("results"); %>
    
	
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
			
	  		<div class="resultcolumn">
				<h2>ab <%=hotel[6] %> EUR</h2><br>
				<a class="actionbutton" href="Detailansicht?hotelid=<%=hotel[0] %>&hotelname=<%=hotel[1] %>&city=<%=hotel[2] %>&country=<%=hotel[3] %>&roomnumber=<%=hotel[4] %>&roomsize=<%=hotel[5] %>&roomcost=<%=hotel[6] %>&begindate=<%=hotel[7] %>&enddate=<%=hotel[8] %>">Ansehen</a>
				<%if((Integer)session.getAttribute("UserClass")==1){ %>
				<a class="actionbutton" href="Buchen.jsp?hotelid=<%=hotel[0] %>&hotelname=<%=hotel[1] %>&city=<%=hotel[2] %>&country=<%=hotel[3] %>&roomnumber=<%=hotel[4] %>&roomsize=<%=hotel[5] %>&roomcost=<%=hotel[6] %>&begindate=<%=hotel[7] %>&enddate=<%=hotel[8] %>">Buchen</a>
				<%}else{ %>
				<a class="deactivatedbutton" href="#">Buchen</a>
				<%} %>
			</div>
			
		</div>
  		<%}
		}
		else{ %>
  		<h2>Keine Ergebnisse gefunden</h2>
  		<%} %>
	</div>

  <%@include file="footer_template.jsp" %>