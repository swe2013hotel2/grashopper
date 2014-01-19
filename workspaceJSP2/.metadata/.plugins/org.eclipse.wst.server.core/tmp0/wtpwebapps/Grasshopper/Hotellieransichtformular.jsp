
<%@include file="header_template.jsp" %>

  <div class="content">

    
    <form action="#" method="post">
      <div class="form_settings">


        <p><span>Views seit letztem Login:</span><input type="text" name="name" value="120" /></p>
        <p><span>Buchungen seit letztem Login:</span><input type="text" name="name" value="11" /><br><br></p>
        <p><span>Views diesen Monat:</span><input type="text" name="name" value="2" /></p>
        <p><span>Buchungen diesen Monat</span><input type="text" name="name" value="120" /></p>
        
     
        <p><h2>Hoteldaten Ändern</h2></p>
        <p><span>Hotelname</span><input type="text" name="name" value="" /></p>
        <p><span>Email Adresse</span><input type="text" name="name" value="" /></p>
        <p><span>Telefonnummer</span><input type="text" name="name" value="" /></p>

        <p><span>Sterne</span><select id="id" name="name">
          <option value="1">-</option> 
          <option value="2">1</option>
          <option value="3">2</option>
          <option value="4">3</option>
          <option value="5">4</option>
          <option value="6">5</option>
        </select></p>

        <p><span>Zimmeranzahl 1 Bett</span><input type="text" name="name" value="" /></p>
        <p><span>Zimmeranzahl 2 Bett</span><input type="text" name="name" value="" /></p>

        <p><span>Strasse</span><input type="text" name="name" value="" /></p>
        <p><span>PLZ</span><input type="text" name="name" value="" /></p>
        <p><span>Ort</span><input type="text" name="name" value="" /></p>
        <p><span>Land</span><input type="text" name="name" value="" /></p>

        <p><span>Beschreibung</span><textarea rows="8" cols="50" name="name"></textarea></p>
        <p><span>altes Passwort:</span><input type="text" name="name" value="" /></p>
        <p><span>neues Passwort:</span><input type="text" name="name" value="" /></p>
        <p><span>Passwort wiederholen:</span><input type="text" name="name" value="" /></p>
        <p style="padding-top: 15px"><span>&nbsp;</span><input class="submit" type="submit" name="name" value="Speichern" /></p>
      </div>


<%@include file="footer_template.jsp" %>