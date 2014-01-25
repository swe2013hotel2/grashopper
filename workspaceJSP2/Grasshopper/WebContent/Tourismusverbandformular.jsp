<%@include file="footer_template.jsp" %>


<h2>Registrierung</h2>
        <form name="form" action="#" method="post">
          <div class="form_settings">
            <p><span >Name</span><input type="text" class="validate[required,custom[onlyLetterSp]]" name="name" value="" required/></p>
             <p><span>Email Adresse</span><input type="text" class="validate[required,custom[email]]" name="name" value="" required/></p>
              <p><span>Telefonnummer</span><input type="text" class="validate[required]" name="name" value="" required/></p>
              <p><span>Strasse</span><input type="text" name="name" value="" required/></p>
               <p><span>PLZ</span><input type="text" class="validate[required,custom[integer]]" name="name" value="" required/></p>
               <p><span>Ort</span><input type="text" class="validate[required,custom[onlyLetterSp]]" name="name" value="" required/></p>
               <p><span>Land</span><input type="text" class="validate[required,custom[onlyLetterSp]]" name="name" value="" required/></p>
               <p><span>Ort Beschreibung</span><textarea rows="8" cols="50" name="name"></textarea></p>
               <p><span>Passwort:</span><input type="password" class="validate[required,minsize[6]]" name="name" value="" required/></p>
               <p><span>Passwort wiederholen:</span><input type="password" class="validate[required,equals[name]]" name="name" value="" required/></p>

            <p style="padding-top: 15px"><span>&nbsp;</span><input class="submit" type="submit" name="name" value="Speichern" /></p>
          </div>
        </form>



<%@include file="footer_template.jsp" %>