

 <jsp:include page="header_template.jsp">
    <jsp:param name="activePage" value="Hotellieransicht" />
</jsp:include>



    <form name="Hotellieransicht" action="Hotellieransicht" method="post">
      <div class="form_settings">
		
		<h2>Ihre Daten �ndern</h2>

        <p><span>Ihr Username</span><input type="text" name="username" value="${username}" /></p>
        <p><span>Ihr Vorname</span><input type="text" name="vorname" value="${vorname}" /></p>
        <p><span>Ihr Nachname</span><input type="text" name="nachname" value="${nachname}" /></p>
        		<p><span>Geschlecht</span><select id="id" name="sex">
          <option value="0">M�nnlich</option> 
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
     
        <p><h2>Hoteldaten �ndern</h2></p>
        <p><span>Hotelname</span><input type="text" name="hotelname" value="${hotelname}" /></p>

        <p><span>Zimmeranzahl 1 Bett</span><input type="text" name="oneBedRoom" value="${oneBedRoom}" readonly /></p>
        <p><span>Zimmeranzahl 2 Bett</span><input type="text" name="twoBedRoom" value="${twoBedRoom}" readonly/></p>
		
		<p><span>Preis 1 Bett Zimmer</span><input type="text" name="priceOneBedRoom" value="${priceOneBedRoom}" readonly/></p>
        <p><span>Preis 2 Bett Zimmer</span><input type="text" name="priceTwoBedRoom" value="${priceTwoBedRoom}" readonly/></p>


        <p style="padding-top: 15px"><span>&nbsp;</span><input class="submit" type="submit" name="name" value="Speichern" /></p>
      </div>
    </form>

  
<%@include file="footer_template.jsp" %>