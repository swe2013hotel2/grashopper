 <jsp:include page="header_template.jsp">
    <jsp:param name="activePage" value="Registrierung" />
</jsp:include>

	<h2>Registrierung</h2>
        <form name="Kundenregistrierung" action="Kundenregistrierung" method="post">
          <div class="form_settings">
         	<p><span >Username</span><input type="text" name="username" value="" /></p>
			<p><span>Anrede</span><select id="id" name="anrede"><option value="1">Frau</option><option value="2">Herr</option></select></p>
			<p><span >Vorname</span><input type="text" name="vorname" value="" /></p>
			<p><span>Nachname</span><input type="text" name="nachname" value="" /></p>
			<p><span>Email Adresse</span><input type="text" name="email" value="" /></p>
			<p><span>Telefonnummer</span><input type="text" name="telefon" value="" /></p>
			<p><span>Strasse</span><input type="text" name="strasse" value="" /></p>
			<p><span>PLZ</span><input type="number" name="plz" value="" /></p>
			<p><span>Ort</span><input type="text" name="ort" value="" /></p>
			<p><span>Land</span><input type="text" name="land" value="" /></p>
			<p><span>Passwort:</span><input type="text" name="passwort" value="" /></p>
			<p><span>Passwort wiederholen:</span><input type="text" name="passwort2" value="" /></p>

            <p style="padding-top: 15px"><span>&nbsp;</span><input class="submit" type="submit" name="name" value="Speichern" /></p>
          </div> 
        </form>


<%@include file="footer_template.jsp" %>