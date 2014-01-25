

 <jsp:include page="header_template.jsp">
    <jsp:param name="activePage" value="TVBAnsicht" />
</jsp:include>


      <div class="form_settings">
	<form name="form" action="TVBAnsicht" method="post">
		 <h2>TVB Details:</h2>
		<p><span>Stadt</span><input type="text" class="validate[required,custom[onlyLetterSp]]" name="cityname" value="${cityname}" /></p>
		<p><span>Land</span><input type="text" class="validate[required,custom[onlyLetterSp]]" name="countryname" value="${countryname}" /></p>
		<p><span>Beschreibung</span><input type="text" class="validate[required,custom[onlyLetterSp]]" name="reviewtext" value="${reviewtext}" /></p>
		<p><span>Hotels in ${cityname}</span><input type="text" class="validate[required,custom[integer]]" name="hotelanzahl" value="${hotelanzahl}" /></p>
		
		<h2>Raum Buchungen für bestimmte Tage anzeigen:</h2>
		<p><span>Anfangs Datum</span><input type="text" name="von" format="dd.MM.yyyy" value="${von}" /></p>
		<p><span>End Datum</span><input type="text" name="bis" format="dd.MM.yyyy"value="${bis}" /></p>

        <p style="padding-top: 15px"><span>&nbsp;</span><input class="submit" type="submit" name="name" value="Anzeigen" /></p>
	</form>	
      </div>
      


<%@include file="statisticTemplate.jsp" %>

<%@include file="footer_template.jsp" %>