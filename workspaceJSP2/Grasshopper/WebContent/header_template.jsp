

<%String activePage = (String)request.getParameter("activePage"); %>

     <!DOCTYPE HTML>
<html>

<head>
  <title>Grashopper</title>
  <meta name="description" content="website description" />
  <meta name="keywords" content="website keywords, website keywords" />
  <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
  <link rel="stylesheet" type="text/css" href="./css/style.css" />
  <link rel="stylesheet" href="./css/validationEngine.jquery.css" type="text/css"/>
  <link rel="stylesheet" href="./css/jquery-ui.css" type="text/css"/>
  
  <!-- modernizr enables HTML5 elements and feature detects -->
<script type="text/javascript" src="js/modernizr-1.5.min.js"></script>
<script src="./js/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="./js/jquery.validationEngine-de.js" type="text/javascript" charset="utf-8"></script>
<script src="./js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>
<script src="./js/jquery-ui.js" type="text/javascript" charset="utf-8"></script>

<style type="text/css">
		.dp-highlight .ui-state-default {
			background: #484;
			color: #FFF;
		}
	</style>

</head>

<body>
  <div id="main">
    <header>
      <div id="logo">
        <div id="logo_text">
          <!-- class="logo_colour", allows you to change the colour of the text -->
          <h1><a href="index.jsp"> Grashopper </a></h1>
          <h2>Mit einem Sprung zum besten Urlaub.</h2>
        </div>
      </div>
      <nav>
        <div id="menu_container">
          <ul class="sf-menu" id="nav">

            <%if(activePage!=null && activePage.equals("index")) {%> 
            	 <li><a href="index.jsp"  class="highlighted">Home</a></li>
            <%}
            else{ %>
             	<li><a href="index.jsp"  >Home</a></li>
            <%} %>
            
<%-- ------------------------------------------------------------------------------------------------------ --%>
     		<%
     		Integer UserClass=(Integer)session.getAttribute("UserClass");
			if (UserClass==null) {
			%>
				<%if(activePage!=null && activePage.equals("Registrierung")) {%> 
	            	<li><a href="Registrierung.jsp" class="highlighted">Registrierung</a></li>
	            <%}
	            else{ %>
	             	<li><a href="Registrierung.jsp" >Registrierung</a></li>
	            <%} %>
			<%
			} else if(UserClass.equals(1)){ %>
	            <%if(activePage!=null && activePage.equals("MyBookings")) {%> 
	            	  <li><a href="MyBookings" class="highlighted" >Meine Buchungen</a></li>
	            <%}
	            else{ %>
	             	 <li><a href="MyBookings" >Meine Buchungen</a></li>
	            <%} %>
	            <%if(activePage!=null && activePage.equals("MyReviews")) {%> 
	            	  <li><a href="MyReviews" class="highlighted" >MeineRezensionen</a></li>
	            <%}
	            else{ %>
	             	 <li><a href="MyReviews" >MeineRezensionen</a></li>
	            <%} %>
			<%}
			else if(UserClass.equals(2)){
			%>
	            <%if(activePage!=null && activePage.equals("Hotellieransicht")) {%> 
	            	  <li><a href="Hotellieransicht" class="highlighted" >Mein Hotel</a></li>
	            <%}
	            else{ %>
	             	 <li><a href="Hotellieransicht" >Mein Hotel</a></li>
	            <%} %>
			<% }
			else if(UserClass.equals(3)){
			%>
	            <%if(activePage!=null && activePage.equals("TVBAnsicht")) {%> 
	            	  <li><a href="TVBAnsicht" class="highlighted" >TVB-Ansicht</a></li>
	            <%}
	            else{ %>
	             	 <li><a href="TVBAnsicht" >TVB-Ansicht</a></li>
	            <%} %>
			<% } %>

 <%-- ------------------------------------------------------------------------------------------------------ --%>
            
            <%if(activePage!=null && activePage.equals("Hotelsuche")) {%> 
            	  <li><a href="Hotelsuche"  >Hotelsuche</a></li>
            <%}
            else{ %>
             	 <li><a href="Hotelsuche"  >Hotelsuche</a></li>
            <%} %>
 <%-- ------------------------------------------------------------------------------------------------------ --%>
            
           	<%if (UserClass!=null) {
			%>
				<%if(activePage!=null && activePage.equals("MyAccount")) {%> 
	            	<li><a href="MyAccount" class="highlighted">Mein Account</a></li>
	            <%}
	            else{ %>
	             	<li><a href="MyAccount" >MeinAccount</a></li>
	            <%} %>
			<%}  %>
            
            <li><a href="#">Dokumentation</a>
              <ul>
                <li><a href="Projektplanung.pdf" target="_blank" >Projekt Planung</a></li>
                <li><a href="#">Projekt Details</a>
                  <ul>
                    <li><a href="Usecase_Diagramm.png">Use Case Diagramm</a></li>
                    <li><a href="Anforderungsanalyse.pdf">Anforderungsanalyse</a></li>

                    <li><a href="designmodell.pdf">Designmodell</a></li>
                  </ul>
                </li>
                <li><a href="#">Präsentationen</a>
                  <ul>
                    <li><a href="Presentation1.pdf">Präsentation 1</a></li>
                    <li><a href="Presentation2.pdf">Präsentation 2</a></li>
                    <li><a href="Presentation3.pdf">Präsentation 3</a></li>
                  </ul>
                </li>
                <li><a href="#">JDoc</a></li>
              </ul>
 <%-- ------------------------------------------------------------------------------------------------------ --%>
            
            <%if(activePage!=null && activePage.equals("Teammitglieder")) {%> 
            	  <li><a href="Teammitglieder.jsp" class="highlighted" >Team Mitglieder</a></li>
            <%}
            else{ %>
             	 <li><a href="Teammitglieder.jsp" >Team Mitglieder</a></li>
            <%} %>
 <%-- ------------------------------------------------------------------------------------------------------ --%>
            
             </ul> 
        </div>
      </nav>
     
    </header>
    <div id="site_content">
      <div id="sidebar_container">
        <div class="sidebar">
     		<%
     		String username=(String)session.getAttribute("username");
			if (username==null) {
			%>
				<jsp:include page="LoginForm.jsp" />
			<%
			} else { %>
				<jsp:include page="LogoutForm.jsp" />
			<%}
			%>
   		 
        </div>
        <div class="sidebar">
          <h2>Useful Links</h2>
          <ul>
            <li><a href="https://docs.google.com/spreadsheet/ccc?key=0AmtJOQ9DhchzdHJyaGRUaFpfZE9ydktJZmpzSjI2ZVE#gid=0">Projekt Tagebuch</a></li>
            <li><a href="https://github.com/swe2013hotel2/grashopper">GitHub</a></li>
            <li><a href="https://cewebs.cs.univie.ac.at/SWE/ws13/index.php?t=uebung">Cewebs</a></li>
          </ul>
        </div>
      </div>
      
      <%if(activePage!=null && activePage.equals("Registrierung")) {%> 
      	<div class="register_menu_container">
        <ul class="sf-menu" id="nav2">
          <li><a href="Kundenregistrierung.jsp">Kunde</a></li>
          <li><a href="Hotellierregistrierung.jsp">Hotellier</a></li>
          <li><a href="Tourismusverbandregistrierung.jsp">Tourismusverband</a></li>
        </ul>
     	 </div>
	  <%}%>


   <div class="content">
   
   <%if(request.getParameter("message")!=null) {%>
   <h2><%=request.getParameter("message") %> </h2>
   <%} %>
     
     
     
    
      