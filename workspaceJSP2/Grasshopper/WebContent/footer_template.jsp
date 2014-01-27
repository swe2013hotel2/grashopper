</div>
 </div>
    <footer>
      <p>Copyright &copy; scenic_photo | <a href="http://www.css3templates.co.uk">design from css3templates.co.uk</a></p>
      <p><h6><a href="#">Sitemap</a>   <a href="#">Impressum</a></h6></p>
    </footer>
  </div>
  <p>&nbsp;</p>
  <!-- javascript at the bottom for fast page loading -->

  <script type="text/javascript" src="js/jquery.easing-sooper.js"></script>
  <script type="text/javascript" src="js/jquery.sooperfish.js"></script>
  <script type="text/javascript">
    $(document).ready(function() {
      $('ul.sf-menu').sooperfish();
      
		$("#datepicker").datepicker({
			beforeShowDay: function(date) {
				var date1 = $.datepicker.parseDate($.datepicker._defaults.dateFormat, $("#input1").val());
				var date2 = $.datepicker.parseDate($.datepicker._defaults.dateFormat, $("#input2").val());
				return [true, date1 && ((date.getTime() == date1.getTime()) || (date2 && date >= date1 && date <= date2)) ? "dp-highlight" : ""];
			},
			onSelect: function(dateText, inst) {
				var date1 = $.datepicker.parseDate($.datepicker._defaults.dateFormat, $("#input1").val());
				var date2 = $.datepicker.parseDate($.datepicker._defaults.dateFormat, $("#input2").val());
				if (!date1 || date2) {
					$("#input1").val(dateText);
					$("#input2").val("");
					$(this).datepicker("option", "minDate", dateText);
				} else {
					$("#input2").val(dateText);
					$(this).datepicker("option", "minDate", null);
				}
			}
		});
		$("#form").validationEngine();
    });
</script>
</body>
</html>