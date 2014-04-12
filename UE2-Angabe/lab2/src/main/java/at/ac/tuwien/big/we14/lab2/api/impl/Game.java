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

//TODO Letztes Spiel: LocalStorage
//TODO Falls unentschieden, soll durch Zeitdifferenz ausgerechnet werden, wer gewonnen hat

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

	private List<Category> done_categories;
	// Mixed Answers to one Question
	private List<String> mix_answers;

	private int player_points;
	private int player2_points;

	OneRound one_round;

	/*
	 * Initialisieren alle Fragen mit Kategorien
	 */
	public void setQuestionsAndCategories(ServletContext context) {

		ServletQuizFactory sqf = new ServletQuizFactory(context);
		this.quentions_and_categories = sqf.createQuestionDataProvider();
		categories = quentions_and_categories.loadCategoryData();

		done_categories = new ArrayList<Category>();
	}

	/*
	 * Wird Am Anfang jeder Runde ausgeführt und initialisiert neue Kategorie
	 */
	public void nextCategory() {

		current_category = categories.get(getNewAvailableCategory());
		// generator.nextInt(categories.size() - 1)
		done_categories.add(current_category);

		current_question = current_category.getQuestions().get(
				generator.nextInt(current_category.getQuestions().size()));

		questions = new ArrayList<Question>();
		questions.add(current_question);

		mix_answers = new ArrayList<String>();
		mixAnswers();

		current_runde_frage = 1;

		one_round = new OneRound();
	}

	/*
	 * Wird Am Ende jeder Frage ausgeführt, wertet die Antworten und aus und startet eine neue
	 * Frage, die noch nicht gestellt worden ist.
	 */
	public Boolean nextQuestion(List<String> selected_choices) {
		
		Boolean correct_choices = checkChoicesMadePlayer1(selected_choices);
		checkChoicesMadePlayer2();
		
		current_question = current_category.getQuestions().get(
				getNewAvailableQuestion());
		current_runde_frage++;
		questions.add(current_question);
		mixAnswers();
		
		return correct_choices;
	}

	/*
	 * Auswertung der Antworten vom Spieler 1
	 */
	public Boolean checkChoicesMadePlayer1(List<String> selected_choices) {
		
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

		if (correct) {

			if (current_runde_frage == 1) {
				one_round.setPlayer_question1("correct");
				one_round.IncrementPlayer();
			} else if (current_runde_frage == 2) {
				one_round.setPlayer_question2("correct");
				one_round.IncrementPlayer();
			} else if (current_runde_frage == 3) {
				one_round.setPlayer_question3("correct");
				one_round.IncrementPlayer();
			}
			return true;
		} else if (current_runde_frage == 1) {
			one_round.setPlayer_question1("incorrect");
		} else if (current_runde_frage == 2) {
			one_round.setPlayer_question2("incorrect");
		} else if (current_runde_frage == 3) {
			one_round.setPlayer_question3("incorrect");
		}
		return false;
	}
	
	/*
	 * Auswertung der Antworten vom Spieler 2, also in unserem Fall des Computers
	 * 60% Chance, um auf die Frage richtig zu antworten
	 */
	private void checkChoicesMadePlayer2() {
		
		int zahl = generator.nextInt(5);
		
		if(zahl == 0 || zahl == 1 || zahl == 2){
			if (current_runde_frage == 1) {
				one_round.setPlayer2_question1("correct");
				one_round.IncrementPlayer2();
			} else if (current_runde_frage == 2) {
				one_round.setPlayer2_question2("correct");
				one_round.IncrementPlayer2();
			} else if (current_runde_frage == 3) {
				one_round.setPlayer2_question3("correct");
				one_round.IncrementPlayer2();
			}
		} else {
			if (current_runde_frage == 1) {
				one_round.setPlayer2_question1("incorrect");
			} else if (current_runde_frage == 2) {
				one_round.setPlayer2_question2("incorrect");
			} else if (current_runde_frage == 3) {
				one_round.setPlayer2_question3("incorrect");
			}
		}
	}

	/*
	 * Liefert nächste Frage zurück, die in dieser Runde noch nicht verwerdet wurde
	 */
	private int getNewAvailableQuestion() {

		boolean not_found = true;
		int location = 0;

		while (not_found) {

			// Eine Random Zahl
			location = generator
					.nextInt(current_category.getQuestions().size());

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

	/*
	 * Liefert nächste Categorie zurück, die in noch nicht verwerdet wurde
	 */
	private int getNewAvailableCategory() {

		boolean not_found = true;
		int location = 0;

		while (not_found) {

			// Eine Random Zahl
			location = generator.nextInt(categories.size());

			Category c = categories.get(location);

			boolean occured = false;

			for (Category cat : done_categories) {
				if (cat.equals(c)) {
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

	public String getAnswers(int nummer) {

		if (nummer < mix_answers.size()) {
			return mix_answers.get(nummer);
		} else {
			return "";
		}
	}
	
	public List<String> getAllAnswers(){
		return mix_answers;
	}

	public OneRound getOneRound() {
		return one_round;
	}

	public int getCurrentRundeFrage() {
		return current_runde_frage;
	}

	public int getPlayedCategories() {
		return done_categories.size();
	}

	/*
	 * Wird nach jeder Runde ausgeführt und aktialisiert den winner
	 */
	public void aktualizeRoundWinner() {
		
		one_round.checkWinner();
		
		if (one_round.getWinner().equals("Player 1")) {
			player_points++;
		} else if (one_round.getWinner().equals("Player 2")) {
			player2_points++;
		}
	}
	
	public String getWinner() {
		if (player_points > player2_points) {
			return "Player 1";
		} else if (player2_points > player_points) {
			return "Player 2";
		} else {
			return "Player 1 und Player 2";
		}
	}
	
	public int getPlayer_points(){
		return player_points;
	}
	
	public int getPlayer2_points(){
		return player2_points;
	}
}
