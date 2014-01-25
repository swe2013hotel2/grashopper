

 <jsp:include page="header_template.jsp">
    <jsp:param name="activePage" value="Hotellieransicht" />
</jsp:include>



    <form name="form" action="Hotellieransicht" method="post">
      <div class="form_settings">
		

        <p><h2>Hoteldaten Ändern</h2></p>
        <p><span>Hotelname</span><input type="text" class="validate[required]" name="hotelname" value="${hotelname}" required /></p>

  <p style="padding-top: 15px"><span>&nbsp;</span><input class="submit" type="submit" name="name" value="Name ändern" /></p>
      

        <p><span>Zimmeranzahl 1 Bett</span><input type="text" class="validate[required]" name="oneBedRoom" value="${oneBedRoom}" readonly /></p>
        <p><span>Zimmeranzahl 2 Bett</span><input type="text" class="validate[required]" name="twoBedRoom" value="${twoBedRoom}" readonly/></p>
		
		<p><span>Preis 1 Bett Zimmer</span><input type="text" class="validate[required,custom[number]]" name="priceOneBedRoom" value="${priceOneBedRoom}" readonly/></p>
        <p><span>Preis 2 Bett Zimmer</span><input type="text" class="validate[required,custom[number]]" name="priceTwoBedRoom" value="${priceTwoBedRoom}" readonly/></p>


      </div>
    </form>
    
<%@include file="reviewsTemplate.jsp"%>

  
<%@include file="footer_template.jsp" %>