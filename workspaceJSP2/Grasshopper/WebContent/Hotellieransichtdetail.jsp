

 <jsp:include page="header_template.jsp">
    <jsp:param name="activePage" value="Hotellieransicht" />
</jsp:include>

<div class="content">

    <form name="Hotelupdate" action="Hotelupdate" method="post">
      <div class="form_settings">


        <p><span>Views seit letztem Login:</span><input type="text" name="name" value="120" /></p>
        <p><span>Buchungen seit letztem Login:</span><input type="text" name="name" value="11" /><br><br></p>
        <p><span>Views diesen Monat:</span><input type="text" name="name" value="2" /></p>
        <p><span>Buchungen diesen Monat</span><input type="text" name="name" value="120" /></p>
     
        <p><h2>Hoteldaten �ndern</h2></p>
        <p><span>Hotelname</span><input type="text" name="hotelName" value="${hotelName}" /></p>
        <p><span>Email Adresse</span><input type="text" name="email" value="${email}" /></p>
        <p><span>Telefonnummer</span><input type="text" name="telephone" value="${telephone}" /></p>

        <p><span>Zimmeranzahl 1 Bett</span><input type="text" name="oneBedRoom" value="${oneBedRoom}" /></p>
        <p><span>Zimmeranzahl 2 Bett</span><input type="text" name="twoBedRoom" value="${twoBedRoom}" /></p>
		
		<p><span>Preis 1 Bett Zimmer</span><input type="text" name="priceOneBedRoom" value="${priceOneBedRoom}" /></p>
        <p><span>Preis 2 Bett Zimmer</span><input type="text" name="priceTwoBedRoom" value="${priceTwoBedRoom}" /></p>

        <p><span>Strasse</span><input type="text" name="street" value="${street} }" /></p>
        <p><span>PLZ</span><input type="text" name="zipCode" value="${zipCode}" /></p>
        <p><span>Ort</span><input type="text" name="city" value="${city}" /></p>
        <p><span>Land</span><input type="text" name="country" value="${country}" /></p>

        <p><span>altes Passwort:</span><input type="text" name="oldPW" value="" /></p>
        <p><span>neues Passwort:</span><input type="text" name="newPW" value="" /></p>
        <p><span>Passwort wiederholen:</span><input type="text" name="repeatPW" value="" /></p>
        <p style="padding-top: 15px"><span>&nbsp;</span><input class="submit" type="submit" name="name" value="Speichern" /></p>
      </div>
    </form>
  </div>
  
<%@include file="footer_template.jsp" %>