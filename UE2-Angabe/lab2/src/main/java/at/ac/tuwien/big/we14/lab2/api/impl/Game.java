package at.ac.tuwien.big.we14.lab2.api.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletContext;

import at.ac.tuwien.big.we14.lab2.api.Category;
import at.ac.tuwien.big.we14.lab2.api.Choice;
import at.ac.tuwien.big.we14.lab2.api.Question;
import at.ac.tuwien.big.we14.lab2.api.QuestionDataProvider;

public class Game {

	private Random generator = new Random();

	// All Questions and Categories
	QuestionDataProvider quentions_and_categories;
	private List<Category> categories;

	// Current Data
	private Category current_category;
	private Question current_question;
	private int current_runde_frage;

	// Fragen, die in einer Runde gestellt worden sind
	private List<Question> questions;

	// Mixed Answers to one Question
	private List<String> mix_answers;

	private int player_points_round;
	private int computer_points_round;

	private String player_question1 = "unknown";
	private String player_question2 = "unknown";
	private String player_question3 = "unknown";

	public void setQuestionsAndCategories(ServletContext context) {

		ServletQuizFactory sqf = new ServletQuizFactory(context);
		this.quentions_and_categories = sqf.createQuestionDataProvider();
		categories = quentions_and_categories.loadCategoryData();

	}

	public void nextCategory() {

		current_category = categories
				.get(generator.nextInt(categories.size() - 1));
		current_question = current_category.getQuestions().get(
				generator.nextInt(current_category.getQuestions().size() - 1));

		questions = new ArrayList<Question>();
		questions.add(current_question);

		mix_answers = new ArrayList<String>();
		mixAnswers();

		current_runde_frage = 1;
		player_points_round = 0;

		player_question1 = "unknown";
		player_question2 = "unknown";
		player_question3 = "unknown";
	}

	public Boolean nextQuestion(List<String> selected_choices) {
		
		boolean correct = true;

		for (Choice correct_choices : current_question.getCorrectChoices()) {
			if (selected_choices.contains(correct_choices.getText())) {
				selected_choices.remove(correct_choices.getText());
			} else {
				correct = false;
				break;
			}
		}

		if (correct) {
			if (!selected_choices.isEmpty()) {
				correct = false;
			}
		}

		current_question = current_category.getQuestions().get(
				getNewAvailableQuestion());
		current_runde_frage++;
		questions.add(current_question);
		mixAnswers();

		if (correct) {
			player_points_round++;
			if (current_runde_frage - 1 == 1) {
				player_question1 = "correct";
			} else if (current_runde_frage - 1 == 2) {
				player_question2 = "correct";
			} else if (current_runde_frage - 1 == 3) {
				player_question3 = "correct";
			}
			return true;
		} else if (current_runde_frage - 1 == 1) {
			player_question1 = "incorrect";
		} else if (current_runde_frage - 1 == 2) {
			player_question2 = "incorrect";
		} else if (current_runde_frage - 1 == 3) {
			player_question3 = "incorrect";
		}
		return false;
	}

	// Liefert nächste Frage zurück, die in dieser Runde noch nicht verwerdet
	// wurde
	private int getNewAvailableQuestion() {

		boolean not_found = true;
		int location = 0;

		while (not_found) {

			// Eine Random Zahl
			location = generator
					.nextInt(current_category.getQuestions().size() - 1);

			Question q = current_category.getQuestions().get(location);

			boolean occured = false;

			for (Question que : questions) {
				if (que.equals(q)) {
					occured = true;
					break;
				}
			}

			if (!occured) {
				not_found = false;
			}
		}

		return location;
	}

	public Category getCategory() {
		return current_category;
	}

	public Question getQuestion() {
		return current_question;
	}

	public void mixAnswers() {
		List<Choice> choices = current_question.getAllChoices();
		List<String> list = new ArrayList<String>();

		for (Choice ch : choices) {
			list.add(ch.getText());
		}

		mix_answers = list;
	}

	public int getPlayerPointsRound() {
		return player_points_round;
	}

	public String getAnswers(int nummer) {

		if (nummer < mix_answers.size()) {
			return mix_answers.get(nummer);
		} else {
			return "";
		}
	}

	public String getPlayerQuestion1() {
		return player_question1;
	}

	public String getPlayerQuestion2() {
		return player_question2;
	}

	public String getPlayerQuestion3() {
		return player_question3;
	}

	public int getCurrentRundeFrage() {
		return current_runde_frage;
	}
}
