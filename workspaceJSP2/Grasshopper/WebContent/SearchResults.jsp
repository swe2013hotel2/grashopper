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
		<div class="result_row" style="height:140px;"> 

			<div class="resultcolumn">
	        	<img src="bild.jpg" >
	  		</div>
	  
 			<div class="resultcolumn">
				<h2><%=hotel[1] %></h2>
			</div>
			
	  		<div class="resultcolumn">
				<h2>ab <%=hotel[6] %> EUR</h2><br>
				<a class="actionbutton" href="Detailansicht?hotelid=<%=hotel[0] %>&hotelname=<%=hotel[1] %>&city=<%=hotel[3] %>&country=<%=hotel[4] %>&roomnumber=<%=hotel[2] %>&roomsize=<%=hotel[5] %>&roomcost=<%=hotel[6] %>&begindate=<%=request.getParameter("von") %>&enddate=<%=request.getParameter("bis") %>">Ansehen</a>
				<%if(session.getAttribute("UserClass")==null || (Integer)session.getAttribute("UserClass")!=1){ %>
				<a class="deactivatedbutton" href="#">Buchen</a>				
				<%}else{ %>
				<a class="actionbutton" href="Buchen.jsp?hotelid=<%=hotel[0] %>&hotelname=<%=hotel[1] %>&city=<%=hotel[3] %>&country=<%=hotel[4] %>&roomnumber=<%=hotel[2] %>&roomsize=<%=hotel[5] %>&roomcost=<%=hotel[6] %>&begindate=<%=request.getParameter("von") %>&enddate=<%=request.getParameter("bis") %>">Buchen</a>
		
				<%} %>
			</div>

			
		</div>
		<div class="separator"></div>
  		<%}
		}
		else{ %>
  		<h2>Keine Ergebnisse gefunden</h2>
  		<%} %>
	</div>

  <%@include file="footer_template.jsp" %>