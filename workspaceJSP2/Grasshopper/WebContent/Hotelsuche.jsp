 <jsp:include page="header_template.jsp">
    <jsp:param name="activePage" value="Hotelsuche" />
</jsp:include>


 	<%if(request.getAttribute("status")!=null){ %>
 	<h2><%= request.getAttribute("status")%></h2>
 	<%} %>
 
   <form action="Hotelsuche" action="Hotelsuche" method="post">
		<div class="form_settings">
         <p><span>Von</span><input type="text" name="von" value="20.02.2014" /></p>
		 <p><span>Bis</span><input type="text" name="bis" value="20.03.2014" /></p>
       	 <p><span>Land</span><input type="text" name="land" value="Oesterreich" /></p>
      	 <p><span>Ort</span><input type="text" name="ort" value="Linz" /></p>
      	<p><span>Mindestens Betten</span><input type="text" name="personen" value="1" /></p>
         <p><span>Maximale Kosten</span><input type="text" name="maxkosten" value="500" /></p>
         
        <p style="padding-top: 15px"><span>&nbsp;</span><input class="submit" type="submit" name="name" value="Suchen" ></p>
     </div>
    </form>      

    <%@include file="footer_template.jsp" %>
    
    
    
  