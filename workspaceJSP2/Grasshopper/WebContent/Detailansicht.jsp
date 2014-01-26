 <%@include file="header_template.jsp" %>

    <h2><%=request.getParameter("hotelname") %></h2>
    <div class=separator></div>
    <div id="detail_foto">
        <img src="bild.jpg">
  	</div>
  	
  	<div id="detail_summary">
	        <h4>Stadt:  <%=request.getParameter("city") %></h4><br>
	        <h4>Land:   <%=request.getParameter("country") %></h4><br>
	        <h4>Preis:  <%=request.getParameter("roomcost") %> EUR</h4><br>
	        <%if(session.getAttribute("UserClass")==null || (Integer)session.getAttribute("UserClass")!=1){ %>
  			<a class="actionbutton" href="Buchen.jsp?hotelid=<%=request.getParameter("hotelid") %>&hotelname=<%=request.getParameter("hotelname") %>&city=<%=request.getParameter("city") %>&country=<%=request.getParameter("country") %>&roomnumber=<%=request.getParameter("roomnumber") %>&roomsize=<%=request.getParameter("roomsize") %>&roomcost=<%=request.getParameter("roomcost") %>&begindate=<%=request.getParameter("begindate") %>&enddate=<%=request.getParameter("enddate") %>">Buchen</a>
  			<%} %>
  	</div>
 
 
<%@include file="reviewsTemplate.jsp" %>

<%@include file="footer_template.jsp" %>