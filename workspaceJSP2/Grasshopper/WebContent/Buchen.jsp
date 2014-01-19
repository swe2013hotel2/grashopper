<%@include file="header_template.jsp" %>
<%
String hotelname = (request.getParameter("hotelname")==null ? "":(String)request.getParameter("hotelname")); 
String roomnumber = (request.getParameter("roomnumber")==null ? "":(String)request.getParameter("roomnumber")); 
String city = (request.getParameter("city")==null ? "":(String)request.getParameter("city")); 
String country = (request.getParameter("country")==null ? "":(String)request.getParameter("country")); 
String roomcost = (request.getParameter("roomcost")==null ? "":(String)request.getParameter("roomcost"));
String roomsize = (request.getParameter("roomsize")==null ? "":(String)request.getParameter("roomsize"));
String begindate = (request.getParameter("begindate")==null ? "":(String)request.getParameter("begindate"));
String enddate = (request.getParameter("enddate")==null ? "":(String)request.getParameter("enddate"));

%>

    <h2>Buchung</h2>
    <h2>Buchungsdeteils überprüfen</h2>
    <form action="#" method="post">
      <div class="form_settings">
        <p><span>Hotelname:</span><input readonly="true" type="text" name="name" value="<%=hotelname %>" /></p>
        <p><span>Ort:</span><input readonly="true" type="text" name="name" value="<%=city %>" /></p>
        <p><span>Land:</span><input readonly="true" type="text" name="name" value="<%=country %>" /></p>
		<p><span>Kosten/Nacht:</span><input readonly="true" type="text" name="name" value="<%=roomcost %>"  /></p>
		<p><span>Raumgrösse</span><input readonly="true" type="text" name="name" value="<%=roomsize %>" /></p>
		<p><span>Beginndatum</span><input readonly="true" type="text" name="name" value="<%=begindate %>" /></p>
		<p><span>Enddatum</span><input readonly="true" type="text" name="name" value="<%=enddate %>" /></p>
      </div>
      </form>
      <a class="actionbutton" href="Booking?hotelid=<%=request.getParameter("hotelid") %>&hotelname=<%=request.getParameter("hotelname") %>&city=<%=request.getParameter("city") %>&country=<%=request.getParameter("country") %>&roomnumber=<%=request.getParameter("roomnumber") %>&roomsize=<%=request.getParameter("roomsize") %>&roomcost=<%=request.getParameter("roomcost") %>&begindate=<%=request.getParameter("begindate") %>&enddate=<%=request.getParameter("enddate") %>">Buchen</a>

  	<a class="actionbutton" href="Hotelsuche.jsp">Abbrechen</a>


<%@include file="footer_template.jsp" %>



