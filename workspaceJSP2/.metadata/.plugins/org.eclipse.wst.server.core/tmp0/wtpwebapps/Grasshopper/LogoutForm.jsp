 <% String username =(String)session.getAttribute("username"); %>
<h2> Eingeloggt als <%=username %></h2>
<form name="logoutform" action="Logout" method="post">
<p style="padding-top: 0px; padding-bottom: 0px"><span>&nbsp;</span><input class="submit" type="submit" name="name" value="Logout" /></p>
</form>