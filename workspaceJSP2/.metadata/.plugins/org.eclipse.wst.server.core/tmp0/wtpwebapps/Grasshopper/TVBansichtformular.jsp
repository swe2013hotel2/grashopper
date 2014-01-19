
<%@include file="header_template.jsp" %>

 <div class="content">

    
    <form action="#" method="post">
      <div class="form_settings">

      <p><h2>Statistik suchen nach:</h2></p>
          <p><span>Hotel mit Sterne</span><select id="id" name="name">
          <option value="1">-</option> 
          <option value="2">1</option>
          <option value="3">2</option>
          <option value="4">3</option>
          <option value="5">4</option>
          <option value="6">5</option>
        </select></p>
        
        <p><span>Land</span><select id="id" name="name">
          <option value="1">-</option> 
          <option value="2">1</option>
          <option value="3">2</option>
          <option value="4">3</option>
          <option value="5">4</option>
          <option value="6">5</option>
        </select></p>

        <p><span>Ort</span><select id="id" name="name">
          <option value="1">-</option> 
          <option value="2">1</option>
          <option value="3">2</option>
          <option value="4">3</option>
          <option value="5">4</option>
          <option value="6">5</option>
        </select></p>

        <p style="padding-top: 15px"><span>&nbsp;</span><input class="submit" type="submit" name="name" value="Suchen" /></p>

        <p><h2> Suchergebnis:</h2></p>
        <img class="img150" src="Statistik.png" border="10" alt="Statistik">


      </div>
    </form>
  </div>

<%@include file="footer_template.jsp" %>