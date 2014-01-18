 <jsp:include page="header_template.jsp">
    <jsp:param name="activePage" value="Registrierung" />
</jsp:include>

    <h2>Registrierung</h2>
    <form action="Hotelregistrierung" method="post">
      <div class="form_settings">
		<h2>Ihre Daten</h2>

        <p><span>Ihr Username</span><input type="text" name="username" value="" /></p>
        <p><span>Ihr Vorname</span><input type="text" name="vorname" value="" /></p>
        <p><span>Ihr Nachname</span><input type="text" name="nachname" value="" /></p>
        <p><span>Email Adresse</span><input type="text" name="email" value="" /></p>
        <p><span>Telefonnummer</span><input type="text" name="telephone" value="" /></p>
		<p><span>Geschlecht</span><select id="id" name="sex">
          <option value="0">Männlich</option> 
          <option value="1">Weiblich</option>
        </select></p>
        
        <p><span>Strasse</span><input type="text" name="street" value="" /></p>
        <p><span>PLZ</span><input type="text" name="zip" value="" /></p>
        <p><span>Ort</span><input type="text" name="city" value="" /></p>
        <p><span>Land</span><input type="text" name="country" value="" /></p>

		<h2>Hoteldaten</h2>

        <p><span>Hotelname</span><input type="text" name="hotelname" value="" /></p>
        <p><span>Zimmeranzahl 1 Bett</span><input type="text" name="bed1" value="" /></p>
        <p><span>Kosten pro Zimmer 1 Bett</span><input type="text" name="cost1" value="" /></p>
        <p><span>Zimmeranzahl 2 Bett</span><input type="text" name="bed2" value="" /></p>
        <p><span>Kosten pro Zimmer 2 Bett</span><input type="text" name="cost2" value="" /></p>

        <p><span>Ort</span><input type="text" name="hotelcity" value="" /></p>
        <p><span>Land</span><input type="text" name="hotelcountry" value="" /></p>

		<br>
		
         <p><span>Passwort:</span><input type="text" name="password1" value="" /></p>
        <p><span>Passwort wiederholen:</span><input type="text" name="password2" value="" /></p>
        <p style="padding-top: 15px"><span>&nbsp;</span><input class="submit" type="submit" name="name" value="Speichern" /></p>
      </div>
    </form>

  
<%@include file="footer_template.jsp" %>