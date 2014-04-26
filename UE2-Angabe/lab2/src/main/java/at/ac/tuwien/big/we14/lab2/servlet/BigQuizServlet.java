package at.ac.tuwien.big.we14.lab2.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import at.ac.tuwien.big.we14.lab2.api.QuestionDataProvider;
import at.ac.tuwien.big.we14.lab2.api.impl.Game;
import at.ac.tuwien.big.we14.lab2.api.impl.ServletQuizFactory;

public class BigQuizServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Game game;
	ServletContext context;

	@Override
	public void init() throws ServletException {

		super.init();
	}

	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");

		Game game = (Game) request.getSession().getAttribute("game");

		Boolean correct_choises = null;

		if (action != null) {
			if (action.equals("weiter")) {

				// Die selektierten Antworten
				String[] value = request.getParameterValues("checkedRows");
				String timeLeft = request.getParameter("timeleftvalue");
				
				List<String> values = new ArrayList<String>();
				if (value != null) {
					for (int i = 0; i < value.length; i++) {
						values.add(value[i]);
					}
				}
				correct_choises = game.nextQuestion(values, timeLeft);

			}
		}
		
		if (game.getCurrentRundeFrage() == 4) {
			game.aktualizeRoundWinner();
			request.getRequestDispatcher("roundcomplete.jsp").forward(request,
					response);
		} else {
			request.setAttribute("maxtime", game.getQuestion().getMaxTime());
			request.getRequestDispatcher("question.jsp").forward(request,
					response);
		}
	}

	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");

		if (action != null) {
			if (action.equals("new")) {

				Game game = new Game();

				this.context = super.getServletContext();

				game.setQuestionsAndCategories(context);
				game.nextCategory();

				// Start Session
				HttpSession session = request.getSession(true);
				session.setAttribute("game", game);

				request.setAttribute("maxtime", game.getQuestion().getMaxTime());
				request.getRequestDispatcher("question.jsp").forward(request,
						response);

			} else if (action.equals("weiter")) {
				Game game = (Game) request.getSession()
						.getAttribute("game");
				
				if (game.getPlayedCategories() == 5) {
					request.getRequestDispatcher("finish.jsp").forward(request,
							response);
				} else {
					game.nextCategory();
					request.setAttribute("maxtime", game.getQuestion()
							.getMaxTime());
					request.getRequestDispatcher("question.jsp").forward(
							request, response);
				}
			}
		}
	}
}