 <jsp:include page="header_template.jsp">
    <jsp:param name="activePage" value="Hotelsuche" />
</jsp:include>

<%@ page import="java.util.ArrayList" %>
<% ArrayList<String> countries = (ArrayList<String>)request.getAttribute("countries"); %>

<div id="datepicker"></div>
   <form id="form" name="form" action="Hotelsuche" method="post">
   
		<div class="form_settings">
         
		<p><span>Bis</span><input id="input1" type="text"  class="validate[required]" name="von" value="" required  readonly/></p>
		 <p><span>Bis</span><input id="input2" type="text"  class="validate[required]" name="bis" value="" required readonly/></p>
       	 <p><span>Land</span><select id="id" name="land">
			<option value="">-</option>
			<%for(int i=0; i<countries.size(); i++) { %>
			 	<option value="<%=countries.get(i) %>"><%=countries.get(i) %></option> 
			<%} %>  
			</select></p>
      	 <p><span>Ort</span><input type="text" class="validate[custom[onlyLetterSp]]" name="ort" value="" /></p>
      	<p><span>Mindestens Betten</span><input type="text" class="validate[custom[integer]]" name="personen" value="" /></p>
         <p><span>Maximale Kosten</span><input type="text" class="validate[custom[integer]]" name="maxkosten" value="" /></p>
         
        <p style="padding-top: 15px"><span>&nbsp;</span><input class="submit" type="submit" name="name" value="Suchen" ></p>
     </div>
     
    </form>
  

    <%@include file="footer_template.jsp" %>
    
    
    
  