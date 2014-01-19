 <jsp:include page="header_template.jsp">
    <jsp:param name="activePage" value="MyReviews" />
</jsp:include>

<% String[][] summaries = (String[][])request.getAttribute("summaries"); %>

<h2>Meine Rezensionen</h2>

<div id=results>
		
		<%if(summaries!= null && summaries.length!=0) {
		for(int i=0; i<summaries.length;i++) { %>
		<%String[] summary = summaries[i]; %>
		<div class="result_row"> 

			<div class="resultcolumn">
	        	<img src="bild.jpg" >
	  		</div>
	  
 			<div class="resultcolumn">
				<h2><%=summary[1] %></h2>
			</div>
			<div class="resultcolumn">
			</div>
		
	  	<div class="result_row">
		        <form name="MyReviews" action="MyReviews" method="post">
		          <div class="form_settings">
		            <input type="hidden" name="hotelid" value=<%=summary[0] %> />
		            <input type="hidden" name="reviewid" value="<%=summary[2]%>" />
		         	
		        	<p><span>Sterne</span><select id="shortselect" name="stars">
		        														<option <%=summary[3].equals("1") ?"selected=\"selected\"":"" %> value="1">1</option>
																		<option <%=summary[3].equals("2") ?"selected=\"selected\"":"" %> value="2">2</option>
																		<option <%=summary[3].equals("3") ?"selected=\"selected\"":"" %> value="3">3</option>
																		<option <%=summary[3].equals("4") ?"selected=\"selected\"":"" %> value="4">4</option>
																		<option <%=summary[3].equals("5") ?"selected=\"selected\"":"" %> value="5">5</option>			
					</select></p>
		         	<p><span >Text</span><textarea id="text" name="text" cols="35" rows="4" ><%=summary[4]%></textarea></p> 
		         	

		            <p style="padding-top: 15px"><span>&nbsp;</span><input class="submit" type="submit" name="name" value="<%=summary[4].equals("")?"Speichern":"Ändern"%>" /></p>
		          </div> 
		        </form>
		</div>
		</div>
		<div class="separator"></div>
  		<%}
		}
		else{ %>
  		<h2>Keine Ergebnisse gefunden</h2>
  		<%} %>
	</div>




<%@include file="footer_template.jsp" %>