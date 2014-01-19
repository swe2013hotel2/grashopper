<%@ page import="swe2013.location.Review" %>
<%@ page import="java.util.ArrayList" %>

<%ArrayList<Review> reviews =(ArrayList<Review>) request.getAttribute("reviews"); %>

		<h2>Reviews</h2>
		
		<%if(reviews!= null && reviews.size()!=0) {
		for(int i=0; i<reviews.size();i++) { %>
		<%Review review = reviews.get(i); %>
		<div class="result_row"> 

 			<div class="double_resultcolumn">
				<h2>Von: <%=review.getUsername() %></h2>
				<%=review.getStars() %> Sterne
				<br>
				<%= review.getReviewText()%>
			</div>
 
		</div>
		<br>
		<div class="separator"></div>
  		<%}
		}
		else{ %>
  		<h2>Keine Rezensionen</h2>
  		<%} %>
