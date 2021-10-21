<%@page import="org.eclipse.jdt.internal.compiler.batch.Main"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="style.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-1.9.1.min.js">
</script>
<link rel="shortcut icon" type="image/x-icon"
	href="http://anyang-gh.hs.kr/hosts/anyang-gh/topLogo.png" />
<title>anyang-gh.hs - timetable</title>
</head>

<body>
	
<div class = "all">
	
	<header class="header">
		<div class="container header_container">
			<div class="header_logo">
				<img class="header_img"
					src="http://anyang-gh.hs.kr/hosts/anyang-gh/topLogo.png">
				<h1 class="header_title">Timetable</h1>
			</div>

			<div class="header_menu">
			<nav id="navbar" class="header_nav collapse">
			<ul class="header_elenco">
				<li class="header_el"><a href="http://anyang-gh.hs.kr/main" class="btn btn--white">안양여고 홈페이지</a></li>
				<li class="header_el"><a href="https://edu.google.com/intl/ko/products/classroom/" class="btn btn--white">구글 클래스룸</a></li>
				</ul>
				</nav>
			</div>
		</div>
	</header>
	
	  <script>
    function updateClock(){
    var currentTime = new Date ();
    var currentHours = currentTime.getHours ( );
    var currentMinutes = currentTime.getMinutes ( );
    var monthNames = [ "January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December" ];
    	currentTime.setDate(currentTime.getDate());    
    	currentMinutes = ( currentMinutes < 10 ? "0" : "" ) + currentMinutes;
    	currentHours = ( currentHours > 12 ) ? currentHours - 12 : currentHours; 
    	currentHours = ( currentHours == 0 ) ? 12 : currentHours;
    	var currentTimeString = currentHours + ":" + currentMinutes;
    $(".time-cont").html(currentTimeString); 
    $('.date').html(currentTime.getDate() + ' ' + monthNames[currentTime.getMonth()]);
}
$.fn.togglePlaceholder = function(){
    return this.each(function() {
        $(this)
        .data("holder", $(this).attr("placeholder"))
        .focusin(function(){
            $(this).attr('placeholder','');
        })
        .focusout(function(){
            $(this).attr('placeholder',$(this).data('holder'));
        });
    });
};
$(document).ready(function(){
	setInterval('updateClock()', 200);
    $('.settings').click(function(){            	
        $('.leftside').addClass('flipl');
        $('.rightside').addClass('flipr');               
    });
    $('.return').click(function(){
        $('.leftside').removeClass('flipl');
        $('.rightside').removeClass('flipr');
    });
    $("[placeholder]").togglePlaceholder();           
});
  </script>
	<div class="wrapper">
		<div class="background">
			<div class="widget">
				<div class="leftside">
					<div class="front">
						<div class="time">
							<div class="time-cont"></div>
							<div class="date"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<table class="rwd-table">

		<tr>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td>&nbsp;&nbsp;&nbsp;</td>
			<th>MON</th>
			<th>TUE</th>
			<th>WED</th>
			<th>THU</th>
			<th>FRI</th>
			<td></td>
		</tr>
	<% 
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/School?useSSL=false&allowPublicKeyRetrieval=true&useSSL=false";
		String userid = "root";
		String userpw = "root";
		String sql = "SELECT * FROM data";
		
		try {
			conn = DriverManager.getConnection(url, userid, userpw);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		
			List<String[]> jobList = new ArrayList<String[]>();
			while (rs.next()) {
				String[] arrStr = { rs.getString("no"), rs.getString("name") };
				jobList.add(arrStr);
			}
	%>
	
		<tr>
			<th colspan="2">1교시</th>
			<td><%=jobList.get(0)[1]%></td>
			<td><%=jobList.get(7)[1]%></td>
			<td><%=jobList.get(14)[1]%></td>
			<td><%=jobList.get(20)[1]%></td>
			<td><%=jobList.get(27)[1]%></td>
			<td></td>
		</tr>
		<tr>
			<th colspan="2">2교시</th>
			<td><%=jobList.get(1)[1]%></td>
			<td><%=jobList.get(8)[1]%></td>
			<td><%=jobList.get(15)[1]%></td>
			<td><%=jobList.get(21)[1]%></td>
			<td><%=jobList.get(28)[1]%></td>
			<td></td>
		</tr>
		<tr>
			<th colspan="2">3교시</th>
			<td><%=jobList.get(2)[1]%></td>
			<td><%=jobList.get(9)[1]%></td>
			<td><%=jobList.get(16)[1]%></td>
			<td><%=jobList.get(22)[1]%></td>
			<td><%=jobList.get(29)[1]%></td>
		</tr>
		<tr>
			<th colspan="2">4교시</th>
			<td><%=jobList.get(3)[1]%></td>
			<td><%=jobList.get(10)[1]%></td>
			<td><%=jobList.get(17)[1]%></td>
			<td><%=jobList.get(23)[1]%></td>
			<td><%=jobList.get(30)[1]%></td>
		</tr>
		<tr>
			<th colspan="2">5교시</th>
			<td><%=jobList.get(4)[1]%></td>
			<td><%=jobList.get(11)[1]%></td>
			<td><%=jobList.get(18)[1]%></td>
			<td><%=jobList.get(24)[1]%></td>
			<td><%=jobList.get(31)[1]%></td>
		</tr>
		<tr>
			<th colspan="2">6교시</th>
			<td><%=jobList.get(5)[1]%></td>
			<td><%=jobList.get(12)[1]%></td>
			<td><%=jobList.get(19)[1]%></td>
			<td><%=jobList.get(25)[1]%></td>
			<td><%=jobList.get(32)[1]%></td>
		</tr>
		<tr>
			<th colspan="2">7교시</th>
			<td><%=jobList.get(6)[1]%></td>
			<td><%=jobList.get(13)[1]%></td>
			<td></td>
			<td><%=jobList.get(26)[1]%></td>
			<td><%=jobList.get(33)[1]%></td>
		</tr>
		<tr>
			<td></td>
		</tr>
	<%
		} catch (Exception e) {
		System.out.println("SQL Error : " + e.getMessage());
		} finally {
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
	%>
	</table>
	<p>
</div>
<h4 class="myname">Kwoun Mi-Joung</h4>
</body>
</html>