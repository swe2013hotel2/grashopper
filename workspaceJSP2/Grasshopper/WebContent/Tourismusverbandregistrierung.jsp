
 <jsp:include page="header_template.jsp">
    <jsp:param name="activePage" value="Registrierung" />
</jsp:include>

<h2>Registrierung</h2>
        <form name="form" action="TVBRegistrierung" method="post">
          <div class="form_settings">
            <p><span>Username</span><input type="text" class="validate[required,custom[onlyLetterNumber]]" name="username" value="" required/></p>
			<p><span>Anrede</span><select id="id" name="sex"><option value="1">Frau</option><option value="2">Herr</option></select></p>
			<p><span >Vorname</span><input type="text" class="validate[required,custom[onlyLetterSp]]" name="vorname" value="" required/></p>
			<p><span>Nachname</span><input type="text" class="validate[required,custom[onlyLetterSp]]" name="nachname" value="" required/></p>
			<p><span>Email Adresse</span><input type="email" class="validate[required,custom[email]]" name="email" value="" required/></p>
			<p><span>Telefonnummer</span><input type="text" class="validate[required]" name="telefon" value="" required/></p>
			<p><span>Strasse</span><input type="text" class="validate[required]" name="strasse" value="" required/></p>
			<p><span>PLZ</span><input type="text" class="validate[required,custom[integer]]" name="plz" value="" required/></p>
			<p><span>Ort</span><input type="text" class="validate[required,custom[onlyLetterSp]]" name="ort" value="" required/></p>
			<p><span>Land</span><input type="text" class="validate[required,custom[onlyLetterSp]]" name="land" value="" required/></p>
			<p><span>Ort Beschreibung</span><textarea rows="8" cols="50" name="ortbeschreibung" ></textarea></p>
			<p><span>Ort Bewertung</span><select id="id" name="rating"><option value="1">1</option><option value="2">2</option><option value="3">3</option><option value="4">4</option><option value="5">5</option></select></p>
			<p><span>Passwort:</span><input type="text" name="passwort" class="validate[required,minsize[6]]" value="" required/></p>
			<p><span>Passwort wiederholen:</span><input type="text" class="validate[required,equals[passwort]]" name="passwort2" value="" required/></p>

            <p style="padding-top: 15px"><span>&nbsp;</span><input class="submit" type="submit" name="name" value="Speichern" /></p>
          </div>
        </form>


      
      <%@include file="footer_template.jsp" %>