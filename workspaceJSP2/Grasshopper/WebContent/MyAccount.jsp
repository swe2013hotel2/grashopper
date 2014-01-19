 <jsp:include page="header_template.jsp">
    <jsp:param name="activePage" value="MyAccount" />
</jsp:include>


    <h2>Mein Account</h2>
    <form action="MyAccount" method="post">
      <div class="form_settings">
		<h2>Ihre Daten</h2>

        <p><span>Ihr Username</span><input type="text" name="username" value="${username}" /></p>
        <p><span>Ihr Vorname</span><input type="text" name="vorname" value="${vorname}" /></p>
        <p><span>Ihr Nachname</span><input type="text" name="nachname" value="${nachname}" /></p>
        		<p><span>Geschlecht</span><select id="id" name="sex">
          <option value="0">Männlich</option> 
          <option value="1">Weiblich</option>
        </select></p>
        <p><span>Email Adresse</span><input type="text" name="email" value="${email}" /></p>
        <p><span>Telefonnummer</span><input type="text" name="telephone" value="${telephone}" /></p>
        <p><span>Strasse</span><input type="text" name="street" value="${street}" /></p>
        <p><span>PLZ</span><input type="text" name="zip" value="${zip}" /></p>
        <p><span>Ort</span><input type="text" name="city" value="${city}" /></p>
        <p><span>Land</span><input type="text" name="country" value="${country}" /></p>
        
               
        <p><span>altes Passwort:</span><input type="text" name="oldPW" value="" /></p>
        <p><span>neues Passwort:</span><input type="text" name="newPW" value="" /></p>
        <p><span>Passwort wiederholen:</span><input type="text" name="repeatPW" value="" /></p>
     
        <p style="padding-top: 15px"><span>&nbsp;</span><input class="submit" type="submit" name="name" value="Speichern" /></p>
      </div>
    </form>

  
<%@include file="footer_template.jsp" %>