 <jsp:include page="header_template.jsp">
    <jsp:param name="activePage" value="Registrierung" />
</jsp:include>

    <h2>Registrierung</h2>
    <form name="form" action="Hotelregistrierung" method="post">
      <div class="form_settings">
		<h2>Ihre Daten</h2>

        <p><span>Ihr Username</span><input 	type="text" class="validate[required]" name="username" value="" required /></p>
        <p><span>Ihr Vorname</span><input 	type="text" class="validate[required,custom[onlyLetterSp]]"	name="vorname" value="" required /></p>
        <p><span>Ihr Nachname</span><input 	type="text" class="validate[required,custom[onlyLetterSp]]" name="nachname" value="" required /></p>
        <p><span>Email Adresse</span><input type="text" class="validate[required,custom[email]]" name="email" value="" required /></p>
        <p><span>Telefonnummer</span><input type="text" class="validate[required]" name="telephone" value="" required /></p>
		<p><span>Geschlecht</span><select id="id" name="sex">
          <option value="0">Männlich</option> 
          <option value="1">Weiblich</option>
        </select></p>
        
        <p><span>Strasse</span><input type="text" name="street" value="" required /></p>
        <p><span>PLZ</span><input type="text" name="zip" class="validate[required,custom[integer]]" value="" required /></p>
        <p><span>Ort</span><input type="text" name="city" class="validate[required,custom[onlyLetterSp]]" value="" required /></p>
        <p><span>Land</span><input type="text" name="country" class="validate[required,custom[onlyLetterSp]]" value="" required /></p>

		<h2>Hoteldaten</h2>

        <p><span>Hotelname</span><input type="text" class="validate[required]" name="hotelname" value="" required /></p>
        <p><span>Zimmeranzahl 1 Bett</span><input type="text" class="validate[required,custom[integer]]" name="bed1" value="" required /></p>
        <p><span>Kosten pro Zimmer 1 Bett</span><input type="text" class="validate[required]" name="cost1" value="" required /></p>
        <p><span>Zimmeranzahl 2 Bett</span><input type="text" class="validate[required,custom[integer]]" name="bed2" value="" required /></p>
        <p><span>Kosten pro Zimmer 2 Bett</span><input type="text" class="validate[required]" name="cost2" value="" required /></p>

        <p><span>Ort</span><input type="text" class="validate[required,custom[onlyLetterSp]]" name="hotelcity" value="" required /></p>
        <p><span>Land</span><input type="text" class="validate[required,custom[onlyLetterSp]]" name="hotelcountry" value="" required /></p>

		<br>
		
         <p><span>Passwort:</span><input type="password" class="validate[required,minsize[6]]" name="password1" value="" required /></p>
        <p><span>Passwort wiederholen:</span><input type="password" class="validate[required,equals[passwort]]" name="password2" value="" required /></p>
        <p style="padding-top: 15px"><span>&nbsp;</span><input class="submit" type="submit" name="name" value="Speichern" required /></p>
      </div>
    </form>

  
<%@include file="footer_template.jsp" %>