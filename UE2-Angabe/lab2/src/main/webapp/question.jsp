<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html>


<%@page contentType="text/html" pageEncoding="MacRoman"%>
<jsp:useBean id="game" scope="session"
	class="at.ac.tuwien.big.we14.lab2.api.impl.Game" />


<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="de" lang="de">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Business Informatics Group Quiz</title>
<link rel="stylesheet" type="text/css" href="style/screen.css" />
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/framework.js" type="text/javascript"></script>
</head>
<body id="questionpage">
	<a class="accessibility" href="#question">Zur Frage springen</a>
	<header role="banner" aria-labelledby="mainheading">
		<h1 id="mainheading">
			<span class="accessibility">Business Informatics Group</span> Quiz
		</h1>
	</header>
	<nav role="navigation" aria-labelledby="navheading">
		<h2 id="navheading" class="accessibility">Navigation</h2>
		<ul>
			<li><a id="logoutlink" title="Klicke hier um dich abzumelden"
				href="#" accesskey="l">Abmelden</a></li>
		</ul>
	</nav>

	<!-- round info -->
	<section role="main">
		<section id="roundinfo" aria-labelledby="roundinfoheading">
			<h2 id="roundinfoheading" class="accessibility">Spielerinformationen</h2>
			<div id="player1info">
				<span id="player1name">Spieler 1</span>
				<ul class="playerroundsummary">
					<li><span class="accessibility">Frage 1:</span><span
						id="player1answer1"
						class='${game.getOneRound().getPlayer_question1()}'>Richtig</span></li>
					<li><span class="accessibility">Frage 2:</span><span
						id="player1answer2"
						class='${game.getOneRound().getPlayer_question2()}'>Falsch</span></li>
					<li><span class="accessibility">Frage 3:</span><span
						id="player1answer3"
						class='${game.getOneRound().getPlayer_question3()}'>Unbekannt</span></li>
				</ul>
			</div>
			<div id="player2info">
				<span id="player2name">Spieler 2</span>
				<ul class="playerroundsummary">
					<li><span class="accessibility">Frage 1:</span><span
						id="player2answer1"
						class='${game.getOneRound().getPlayer2_question1()}'>Richtig</span></li>
					<li><span class="accessibility">Frage 2:</span><span
						id="player2answer2"
						class='${game.getOneRound().getPlayer2_question2()}'>Richtig</span></li>
					<li><span class="accessibility">Frage 3:</span><span
						id="player2answer3"
						class='${game.getOneRound().getPlayer2_question3()}'>Unbekannt</span></li>
				</ul>
			</div>
			<div id="currentcategory">
				<span class="accessibility">Kategorie:</span><%=game.getCategory().getName()%></div>
		</section>

		<!-- Question -->
		<section id="question" aria-labelledby="questionheading">

			<form id="questionform" action="BigQuizServlet" method="post">
				<h2 id="questionheading" class="accessibility">Frage</h2>
				<p id="questiontext"><%=game.getQuestion().getText()%></p>

				<%--
				<ul id="answers">

					<c:forEach items="${game.getAllAnswers()}" var="question"
						varStatus="status">

						<li><input id="option${status.index}" type="checkbox"
							name="checkedRows" value="${question.getText()}" /><label
							for="option${status.index}">${question.getText()}</label></li>

					</c:forEach>
				</ul>
				--%>

				<ul id="answers">
					<%
						for (int i = 0; i < game.getAllAnswers().size(); i++) {
					%>
					<li><input id="option<%=i%>" type="checkbox"
						name="checkedRows" value="<%=game.getAnswers(i)%>" /><label
						for="option<%=i%>"> <%=game.getAnswers(i)%></label></li>
					<%
						}
					%>
				</ul>
				
				<input id="timeleftvalue" type="hidden" value="100" /> <input
					id="next" type="submit" value="weiter" accesskey="s" /> <input
					type="hidden" name="action" value="weiter" />
			</form>
		</section>

		<section id="timer" aria-labelledby="timerheading">
			<h2 id="timerheading" class="accessibility">Timer</h2>
			<p>
				<span id="timeleftlabel">Verbleibende Zeit:</span>
				<time id="timeleft"><%=game.getQuestion().getMaxTime()%></time>
			</p>
			<meter id="timermeter" min="0" low="20" value="100" max="100" />
		</section>

		<section id="lastgame">
			<p>Letztes Spiel: Nie</p>
		</section>
	</section>

	<!-- footer -->
	<footer role="contentinfo">&copy 2014 BIG Quiz</footer>

	<script type="text/javascript">
		//<![CDATA[

		// initialize time
		$(document).ready(function() {
			var maxtime = '${maxtime}';
			var hiddenInput = $("#timeleftvalue");
			var meter = $("#timer meter");
			var timeleft = $("#timeleft");

			hiddenInput.val(maxtime);
			meter.val(maxtime);
			meter.attr('max', maxtime);
			meter.attr('low', maxtime / 100 * 20);
			timeleft.text(secToMMSS(maxtime));
		});

		// update time
		function timeStep() {
			var hiddenInput = $("#timeleftvalue");
			var meter = $("#timer meter");
			var timeleft = $("#timeleft");

			var value = $("#timeleftvalue").val();
			if (value > 0) {
				value = value - 1;
			}

			hiddenInput.val(value);
			meter.val(value);
			timeleft.text(secToMMSS(value));

			if (value <= 0) {
				$('#questionform').submit();
			}
		}

		window.setInterval(timeStep, 1000);

		//]]>
	</script>
</body>
</html>
