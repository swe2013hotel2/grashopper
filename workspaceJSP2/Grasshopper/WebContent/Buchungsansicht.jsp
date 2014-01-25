
<%@include file="header_template.jsp" %>


    <h2>Buchung</h2>
    

    
    <form name="form" action="Gebucht" method="post">
      <div class="form_settings">
        <p>Hoteldaten überprüfen</p>
        <p><span>Hotelname</span><input type="text" class="validate[required]" name="name" value="${hotelname}" /></p>
        <p><span>Sterne</span><input type="text" class="validate[required,custom[integer],min[1],max[5]]" name="name" value="${sterne}" /></p>
        <p><span>Zimmeranzahl 1 Bett</span><input type="text" class="validate[required],custom[integer]" name="name" value="-" /></p>
        <p><span>Zimmeranzahl 2 Bett</span><input type="text" class="validate[required],custom[integer]" name="name" value="${zweibett}" /></p>
 
        <p>Persönliche Daten überprüfen</p>
        <p><span>Telefonnummer</span><input type="text" class="validate[required]" name="name" value="${telefon}" /></p>
       <p><span>Email Adresse</span><input type="text" class="validate[required,custom[email]]" name="name" value="${email }" /></p>
        <p><span>Strasse</span><input type="text" class="validate[required]" name="name" value="${strasse } 1" /></p>
        <p><span>PLZ</span><input type="text" class="validate[required],custom[integer]" name="name" value="${plz }" /></p>
        <p><span>Ort</span><input type="text" class="validate[required]" name="name" value="${ort }" /></p>
        <p><span>Land</span><input type="text" class="validate[required]" name="name" value="${land }" /></p>

        <p><span>Buchungs Text</span><textarea rows="8" cols="50" class="validate[required]" name="name"></textarea></p>
        <p style="padding-top: 15px"><span>&nbsp;</span><input class="submit" type="submit" name="name" value="Senden" /></p>
      </div>
    </form>

    
    <%@include file="footer_template.jsp" %>