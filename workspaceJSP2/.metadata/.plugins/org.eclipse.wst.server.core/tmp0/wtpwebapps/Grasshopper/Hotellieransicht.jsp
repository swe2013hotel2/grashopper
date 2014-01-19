
 <jsp:include page="header_template.jsp">
    <jsp:param name="activePage" value="Hotellieransicht" />
</jsp:include>


success: function () {
$.get("/Grasshopper/Servlet?action=Hotellieransicht", function() {

});
} 





<%@include file="footer_template.jsp" %>