/**
 * <copyright>
 * 
 * Copyright (c) 2014 http://www.big.tuwien.ac.at All rights reserved. This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * </copyright>
 */
package at.ac.tuwien.big.we14.lab2.tests;

import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.browserlaunchers.Sleeper;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

// DO NOT MODIFY THIS FILE, UNLESS IT IS EXPLICITLY ALLOWED

/**
 * <p>
 * Main JUnit test class you could use for testing. This test uses <a
 * href="http://docs.seleniumhq.org/">Selenium</a>, a browser automation
 * framework.
 * </p>
 * 
 * <p>
 * <b>DO NOT MODIFY THIS FILE, UNLESS IT IS EXPLICITLY ALLOWED</b>
 * </p>
 * 
 * <p>
 * <h2>Usage</h2>
 * <ol>
 * <li>Modify the configuration options in the configuration section below to
 * match your server configuration.</li>
 * <li>Start your Tomcat server and deploy your web application and make sure
 * you are able to access your web application with your browser.</li>
 * <li>Run this test-class with JUnit4 from your IDE (Eclipse: right click on
 * the class, then select "Run as..." and "JUnit").</li>
 * </ol>
 * </p>
 * <p>
 * The Test runs multiple checks, a failure on these checks will not abort the
 * test run. However, if the selenium web driver is not able to find a specific
 * element, the test will be aborted. A check error message follows always the
 * following format
 * <code>[Check: &lt;CheckCategory&gt;, run: &lt;runnumber&gt;, round: &lt;roundnumber&gt;, answeredQuestions: &lt;#questions&gt;] &lt;Message&gt;</code>
 * <em>CheckCategory</em> is the name of the check category, <em>runnumber</em>
 * is the game run where 0 is the first run, <em>roundnumber</em> is the game
 * round where 0 is the first round, and <em>#questions</em> is the number of
 * already answered questions.
 * </p>
 * <h2>Notes</h2>
 * <p>
 * By default, this test uses Firefox for the test cases. If Firefox is not
 * available, it tries to use Chrome or Internet explorer, but both of them need
 * additional configuration to work with selenium, the wiki pages provide more
 * detailed information on how to set them up correctly (<a
 * href="http://code.google.com/p/selenium/wiki/InternetExplorerDriver">
 * IE</a>,<a
 * href="http://code.google.com/p/selenium/wiki/ChromeDriver">Chrome</a>)
 * </p>
 * 
 * <p>
 * Performed Checks:
 * <ul>
 * <li>Game Run checks
 * <ul>
 * <li>Question page checks
 * <ul>
 * <li>Player info checks
 * <ul>
 * <li>Player name consistency check</li>
 * <li>Question result check</li>
 * </ul>
 * </li>
 * <li>Question checks
 * <ul>
 * <li>Category name check</li>
 * <li>Question text check</li>
 * <li>Choice text check</li>
 * </ul>
 * </li>
 * <li>timer checks
 * <ul>
 * <li>time counter format check</li>
 * <li>time counter value check</li>
 * <li>time counter update check</li>
 * </ul>
 * </li>
 * </ul>
 * </li>
 * <li>Round page checks
 * <ul>
 * <li>Player info check</li>
 * <li>Round winner checks
 * <ul>
 * <li>Round victories check</li>
 * <li>Round winner name/draw check</li>
 * </ul>
 * </li>
 * </ul>
 * </li>
 * <li>Game over page checks
 * <ul>
 * <li>Player name check</li>
 * <li>Round victories check (derived)</li>
 * <li>Winner check (derived)</li>
 * </ul>
 * </li>
 * </ul>
 * </li>
 * </ul>
 * </p>
 * 
 */
public class SeleniumTest {

	/*
	 * ########################################################################
	 * Configuration options - you may modify these fields to match your server
	 * configuration or for debugging
	 * ########################################################################
	 * 
	 * The following options are already set to the default values for the use
	 * with eclipse and a default Tomcat7 installation.
	 */

	/**
	 * the host - either the name or the ip-address your server is bound to. For
	 * example, if the URL of your first page is
	 * 'http://localhost:8080/we14-lab2/BigQuizServlet' the host would be
	 * 'localhost'
	 */
	private String host = "localhost";
	/**
	 * the port your server is listening to. For example, if the URL of your
	 * first page is 'http://localhost:8080/we14-lab2/BigQuizServlet' the port
	 * would be '8080'
	 */
	private int port = 8080;
	/**
	 * the url to the first page of your solution without the host, port, and
	 * context path. For example, if the URL of your first page is
	 * 'http://localhost:8080/we14-lab2/BigQuizServlet' then mainUrl would be
	 * '/BigQuizServlet'
	 */
	private String mainUrl = "/BigQuizServlet";
	/**
	 * the context path of your solution. See the assignment description or your
	 * server documentation on how to retrieve the context path for your
	 * solution. For example, if the URL of your first page is
	 * 'http://localhost:8080/we14-lab2/BigQuizServlet' the context path would
	 * be '/we14-lab2'
	 */
	private String contextPath = "/we14-lab2-solution";

	// Debugging options

	/**
	 * indicates if a screenshot should be made for every check that fails. The
	 * screenshot is saved to the current working directory. Errors generated by
	 * selenium will not trigger a screenshot!
	 */
	private boolean takeScreenshotOnError = false;

	/**
	 * indicates if a html dump should be created for every check that fails.
	 * The dump file is saved to the current working directory. Errors generated
	 * by selenium will not trigger a html dump!
	 */
	private boolean dumpHTMLOnError = false;

	/*
	 * ########################################################################
	 * End of configuration options - DO NOT MODIFY ANYTHING BELOW THIS LINE
	 * ########################################################################
	 */

	// Element IDs
	private final static String ID_START_GAME = "startgame";
	private final static String ID_NEXT_QUESTION = "next";
	private final static String ID_NEXT_ROUND = "next";
	private final static String ID_NEW_GAME = "next";
	private final static String ID_PLAYER_NAME_1 = "player1name";
	private final static String ID_PLAYER_NAME_2 = "player2name";
	private final static String ID_PREFIX_PLAYER_ANSWER_1 = "player1answer";
	private final static String ID_PREFIX_PLAYER_ANSWER_2 = "player2answer";
	private final static String ID_QUESTION_ID = "questionid";
	private final static String ID_CATEGORY_NAME = "currentcategory";
	private final static String ID_QUESTION_TEXT = "questiontext";
	// ID_PREFIX_CHOICE is currently not used - the id is derived from the "for"
	// attribute of the label
	@SuppressWarnings("unused")
	private final static String ID_PREFIX_CHOICE = "option"; //
	private final static String ID_PREFIX_CHOICE_LABEL = "labeloption";
	private final static String ID_ROUND_WINNER = "roundwinner";
	private final static String ID_ROUND_VICTORIES_PLAYER_1 = "player1wonrounds";
	private final static String ID_ROUND_VICTORIES_PLAYER_2 = "player2wonrounds";
	private final static String ID_WINNER = "roundwinner";
	private final static String ID_TIMER_VALUE = "timeleft";

	// Expected values
	private static String[] EXPECTED_PLAYER_NAMES = { "Spieler 1", "Spieler 2" };
	private static String EXPECTED_ANSWER_VALUE_UNKNOWN = "Unbekannt";
	private static String EXPECTED_ANSWER_VALUE_CORRECT = "Richtig";
	private static String EXPECTED_ANSWER_VALUE_INCORRECT = "Falsch";

	private static String TIMER_REGEX = "(\\d{2}):(\\d{2})";

	/**
	 * the number of rounds in a game
	 */
	private static int NUM_ROUNDS = 5;
	/**
	 * the number of questions during a round
	 */
	private static int NUM_QUESTIONS = 3;

	/**
	 * list of all loaded categories used for testing
	 */
	private static List<Category> categories;

	/**
	 * a map of questions with the question id as keys
	 */
	private static Map<Integer, Question> questions;

	/**
	 * the ressource path to the json data file
	 */
	private static String jsonFileName = "data.json";

	/**
	 * the number of runs during a test
	 */
	private int numberOfRuns = 3;

	/**
	 * the selenium web driver instance.
	 */
	private WebDriver driver;

	/**
	 * the given answers during the test
	 */
	private List<Answer> answers;

	/**
	 * the current run number
	 */
	private int currentRun = 0;

	/**
	 * the total number of answered questions
	 */
	private int totalQuestions = 0;

	/**
	 * the current check category name
	 */
	private Stack<String> currentCheckCategory;

	/**
	 * the number of round victories of player 1
	 */
	private int player1Victories = 0;

	/**
	 * the number of round victories of player 2;
	 */
	private int player2Victories = 0;

	/**
	 * the number of time-counter tests left to execute
	 */
	private int timeCounterTests = 3;

	/**
	 * the maximum sleep time before a timer value is checked again - this value
	 * must be smaller than the shortest question duration!
	 */
	private int maxTimeCounterTestSleep = 5;

	/**
	 * the tolerance used when checking the time counter value
	 */
	private int timeCounterValueTolerance = 2;

	/**
	 * the name of player 1
	 */
	private String namePlayer1;

	/**
	 * the name of player 2
	 */
	private String namePlayer2;

	/**
	 * the error collector used to catch all errors such that the unit-test
	 * could proceed after an error
	 */
	@Rule
	public ErrorCollector errorCollector = new ErrorCollector() {

		private int numFails = 0;

		@Override
		public void addError(Throwable error) {

			if (driver != null) {
				Calendar calendar = GregorianCalendar.getInstance();
				if (takeScreenshotOnError) {
					// take screenshot
					File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
					File destFile = new File("screenshot-"
							+ SimpleDateFormat.getDateTimeInstance().format(calendar.getTime()) + "-fail"
							+ numFails++ + ".png");
					try {
						FileUtils.copyFile(srcFile, destFile);
						System.out.println("Saved Screenshot to " + destFile.getAbsolutePath());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (dumpHTMLOnError) {
					// dump current html
					File destFile = new File("html-dump-"
							+ SimpleDateFormat.getDateTimeInstance().format(calendar.getTime()) + "-fail"
							+ numFails++ + ".html");
					FileWriter writer;
					try {
						writer = new FileWriter(destFile);
						writer.append(driver.getPageSource());
						writer.close();
						System.out.println("Saved HTML dump to " + destFile.getAbsolutePath());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			super.addError(error);
		}
	};

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		// load JSON category data

		Gson gson = new Gson();
		Type collectionType = new TypeToken<List<Category>>() {
		}.getType();
		categories = gson.fromJson(new InputStreamReader(SeleniumTest.class.getClassLoader()
				.getResourceAsStream(jsonFileName)), collectionType);

		// update cyclic references and maps for fast access
		questions = new HashMap<Integer, Question>();
		for (Category category : categories) {
			for (Question question : category.getQuestions()) {
				question.setCategory(category);
				questions.put(question.getId(), question);
				for (Choice choice : question.getCorrectChoices()) {
					choice.setQuestion(question);
				}
				for (Choice choice : question.getWrongChoices()) {
					choice.setQuestion(question);
				}
			}
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		try {
			driver = new FirefoxDriver();
		} catch (Exception ffExceptions) {
			try {
				driver = new ChromeDriver();
			} catch (Exception chromeExceptions) {
				try {
					driver = new InternetExplorerDriver();
				} catch (Exception ieExceptions) {
					throw new Exception(
							"Couldn't create a valid webdriver for Firefox, Chrome, or Internet Explorer");
				}
			}
		}
		driver.get("http://" + host + ":" + port + contextPath + mainUrl);
		currentCheckCategory = new Stack<String>();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	/**
	 * tests the game according to some of the specifications of lab2. (Check
	 * category: Main)
	 * 
	 * Performed checks:
	 * <ul>
	 * <li>Game Run checks</li>
	 * </ul>
	 */
	@Test
	public void gameTest() {
		currentCheckCategory.push("Main");
		for (currentRun = 0; currentRun < numberOfRuns; currentRun++) {

			if(currentRun == 0) {
				driver.findElement(By.id(ID_START_GAME)).submit();
			}
			
			checkGameRun();

			// restart game
			driver.findElement(By.id(ID_NEW_GAME)).click();
		}
		currentCheckCategory.pop();
	}

	/**
	 * checks a single game run (Check Category: GameRun)
	 * 
	 * Performed checks:
	 * <ul>
	 * <li>Question page checks</li>
	 * <li>Round page checks</li>
	 * <li>Game over page checks</li>
	 * </ul>
	 */
	private void checkGameRun() {
		currentCheckCategory.push("GameRun");
		answers = new ArrayList<>();
		totalQuestions = 0;

		player1Victories = 0;
		player2Victories = 0;

		namePlayer1 = null;
		namePlayer2 = null;

		determinePlayerNames();

		for (int n = 0; n < NUM_ROUNDS; n++) {

			for (int i = 0; i < NUM_QUESTIONS; i++) {
				// totalQuestions = n * NUM_QUESTIONS + i;
				Question question = checkQuestionPage();
				answerQuestion(question, totalQuestions);
				driver.findElement(By.id(ID_NEXT_QUESTION)).submit();
				totalQuestions++;
			}

			if (n < NUM_ROUNDS - 1) { // round complete

				checkRoundPage();
				driver.findElement(By.id(ID_NEXT_ROUND)).click();
			} else { // game over
				checkGameOverPage();
			}
		}
		currentCheckCategory.pop();
	}

	/**
	 * checks the game over page. (Check Category: GameOverPage)
	 * 
	 * Performed checks:
	 * <ul>
	 * <li>Player name check</li>
	 * <li>Round victories check (derived)</li>
	 * <li>Winner check (derived)</li>
	 * </ul>
	 */
	private void checkGameOverPage() {
		currentCheckCategory.push("GameOverPage");

		// check player name
		errorCollector.checkThat(getTestInfoPrefix() + "Invalid Player name",
				driver.findElement(By.id(ID_PLAYER_NAME_1)).getText(), isIn(EXPECTED_PLAYER_NAMES));
		errorCollector.checkThat(getTestInfoPrefix() + "Invalid Player name",
				driver.findElement(By.id(ID_PLAYER_NAME_2)).getText(), isIn(EXPECTED_PLAYER_NAMES));

		// check round victories - derived, as the result of the last
		// round can't be determined in a reliable way

		// try to derive the result of the last round
		if (driver.findElement(By.id(ID_ROUND_VICTORIES_PLAYER_1)).getText().trim()
				.contains("" + (player1Victories + 1))) {
			player1Victories++;
		} else if (driver.findElement(By.id(ID_ROUND_VICTORIES_PLAYER_2)).getText().trim()
				.contains("" + (player2Victories + 1))) {
			player2Victories++;
		}

		errorCollector.checkThat("Invalid number of round victories",
				driver.findElement(By.id(ID_ROUND_VICTORIES_PLAYER_1)).getText(), containsString(""
						+ player1Victories));
		errorCollector.checkThat("Invalid number of round victories",
				driver.findElement(By.id(ID_ROUND_VICTORIES_PLAYER_2)).getText(), containsString(""
						+ player2Victories));

		// Winner check - derived, as the result of the last
		// round can't be determined in a reliable way
		Matcher<String> player1nameMatcher = containsString(namePlayer1);
		Matcher<String> player2nameMatcher = containsString(namePlayer2);
		Matcher<String> drawMatcher = containsString("Unentschieden");

		if (player1Victories == player2Victories) {
			errorCollector.checkThat(getTestInfoPrefix() + "Invalid winner",
					driver.findElement(By.id(ID_WINNER)).getText(),
					allOf(drawMatcher, not(player1nameMatcher), not(player2nameMatcher)));
		} else if (player1Victories > player2Victories) {
			errorCollector.checkThat(getTestInfoPrefix() + "Invalid winner",
					driver.findElement(By.id(ID_WINNER)).getText(),
					allOf(player1nameMatcher, not(drawMatcher), not(player2nameMatcher)));
		} else {
			errorCollector.checkThat(getTestInfoPrefix() + "Invalid winner",
					driver.findElement(By.id(ID_WINNER)).getText(),
					allOf(player2nameMatcher, not(drawMatcher), not(player1nameMatcher)));
		}

		currentCheckCategory.pop();
	}

	/**
	 * checks the round page (Check Category: RoundPage)
	 * 
	 * Performed checks:
	 * <ul>
	 * <li>Player info check</li>
	 * <li>Round winner checks</li>
	 * </ul>
	 */
	private void checkRoundPage() {
		currentCheckCategory.push("RoundPage");
		checkPlayerInfo(3, totalQuestions);
		checkRoundWinner(totalQuestions);
		currentCheckCategory.pop();
	}

	/**
	 * checks the round winner
	 * 
	 * Performed checks:
	 * <ul>
	 * <li>Round victories check</li>
	 * <li>Round winner name/draw check</li>
	 * </ul>
	 * 
	 * @param totalQuestionCount
	 */
	private void checkRoundWinner(int totalQuestionCount) {

		int correctPlayer1 = 0;
		int correctPlayer2 = 0;

		for (int i = 0; i < NUM_QUESTIONS; i++) {
			Answer answer = answers.get(totalQuestionCount - NUM_QUESTIONS + i);
			if (answer.isCorrect()) {
				correctPlayer1++;
			}

			String sResultP2 = driver.findElement(By.id(ID_PREFIX_PLAYER_ANSWER_1 + i)).getText();
			if (sResultP2.equalsIgnoreCase(EXPECTED_ANSWER_VALUE_CORRECT)) {
				correctPlayer2++;
			} else if (!sResultP2.equalsIgnoreCase(EXPECTED_ANSWER_VALUE_INCORRECT)) {
				errorCollector
						.addError(new Exception(
								getTestInfoPrefix()
										+ "Could not parse answer value of player 2, conditional errors may occur. Expected: \""
										+ EXPECTED_ANSWER_VALUE_CORRECT + "\" or \""
										+ EXPECTED_ANSWER_VALUE_INCORRECT + "\""));
			}
		}

		// check winner message
		if (correctPlayer1 == correctPlayer2) {

			// draw - we can't determine the actual winner because we have no
			// reliable time information

			Matcher<String> player1nameMatcher = containsString(namePlayer1);
			Matcher<String> player2nameMatcher = containsString(namePlayer2);
			Matcher<String> drawMatcher = containsString("Unentschieden");
			String roundWinner = driver.findElement(By.id(ID_ROUND_WINNER)).getText();

			errorCollector.checkThat(getTestInfoPrefix()
					+ "Invalid round winner, conditional errors may occur", roundWinner,
					anyOf(player2nameMatcher, player1nameMatcher, drawMatcher));

			// update the round score, assume the winner is correct
			if (player1nameMatcher.matches(roundWinner)) {
				player1Victories++;
			} else if (player2nameMatcher.matches(roundWinner)) {
				player2Victories++;
			}

		} else if (correctPlayer1 > correctPlayer2) {

			errorCollector.checkThat(getTestInfoPrefix() + "Invalid winner",
					driver.findElement(By.id(ID_ROUND_WINNER)).getText(), containsString(namePlayer1));
			player1Victories++;
		} else {

			errorCollector.checkThat(getTestInfoPrefix() + "Invalid winner",
					driver.findElement(By.id(ID_ROUND_WINNER)).getText(), containsString(namePlayer2));
			player2Victories++;
		}

		// check round victory counter
		errorCollector.checkThat("Inavlid number of round victories",
				driver.findElement(By.id(ID_ROUND_VICTORIES_PLAYER_1)).getText(), containsString(""
						+ player1Victories));
		errorCollector.checkThat("Inavlid number of round victories",
				driver.findElement(By.id(ID_ROUND_VICTORIES_PLAYER_2)).getText(), containsString(""
						+ player2Victories));

	}

	/**
	 * determines and checks the chosen player names
	 * 
	 * Performed checks:
	 * <ul>
	 * <li>Player name check</li>
	 * </ul>
	 */
	private void determinePlayerNames() {
		namePlayer1 = driver.findElement(By.id(ID_PLAYER_NAME_1)).getText();
		errorCollector.checkThat(getTestInfoPrefix()
				+ "Invalid Player name, tests will use the invalid name for following name dependent checks",
				namePlayer1, isIn(EXPECTED_PLAYER_NAMES));

		namePlayer2 = driver.findElement(By.id(ID_PLAYER_NAME_2)).getText();
		errorCollector
				.checkThat(
						getTestInfoPrefix()
								+ "Invalid Player name, tests will use the invalid name for following name dependent checks ",
						namePlayer2, isIn(EXPECTED_PLAYER_NAMES));
	}

	/**
	 * checks the player information contained on the question and round pages
	 * 
	 * Performed checks:
	 * <ul>
	 * <li>Player name consistency check</li>
	 * <li>Question result check</li>
	 * </ul>
	 * 
	 * @param questionCount
	 * @param totalQuestionCount
	 */
	private void checkPlayerInfo(int questionCount, int totalQuestionCount) {
		// check player name
		errorCollector.checkThat(getTestInfoPrefix() + "Inconsistent player name for player 1", driver
				.findElement(By.id(ID_PLAYER_NAME_1)).getText(), containsString(namePlayer1));
		errorCollector.checkThat(getTestInfoPrefix() + "Inconsistent player name for player 2", driver
				.findElement(By.id(ID_PLAYER_NAME_2)).getText(), containsString(namePlayer2));

		// check answers
		for (int i = 0; i < NUM_QUESTIONS; i++) {
			if (i < questionCount) {
				errorCollector.checkThat(getTestInfoPrefix() + "Invalid answer value for question " + i
						+ " in player statistics of player 1",
						driver.findElement(By.id(ID_PREFIX_PLAYER_ANSWER_1 + i)).getText(),
						not(equalTo(EXPECTED_ANSWER_VALUE_UNKNOWN)));
				errorCollector.checkThat(getTestInfoPrefix() + "Invalid answer value for round question " + i
						+ " in player statistics of player 2",
						driver.findElement(By.id(ID_PREFIX_PLAYER_ANSWER_2 + i)).getText(),
						not(equalTo(EXPECTED_ANSWER_VALUE_UNKNOWN)));

				// check exact value
				Answer answer = answers.get(totalQuestionCount - questionCount + i);

				if (answer.isCorrect()) {
					errorCollector.checkThat(getTestInfoPrefix() + "Invalid answer value for round question "
							+ i + " in player statistics of player 1. Given answer: " + answer, driver
							.findElement(By.id(ID_PREFIX_PLAYER_ANSWER_1 + i)).getText(),
							equalTo(EXPECTED_ANSWER_VALUE_CORRECT));
				} else {
					errorCollector.checkThat(getTestInfoPrefix() + "Invalid answer value for round question "
							+ i + " in player statistics of player 1. Given answer: " + answer, driver
							.findElement(By.id(ID_PREFIX_PLAYER_ANSWER_1 + i)).getText(),
							equalTo(EXPECTED_ANSWER_VALUE_INCORRECT));
				}

			} else {
				errorCollector.checkThat(getTestInfoPrefix() + "Invalid answer value for round question " + i
						+ " in player statistics of player 1",
						driver.findElement(By.id(ID_PREFIX_PLAYER_ANSWER_1 + i)).getText(),
						equalTo(EXPECTED_ANSWER_VALUE_UNKNOWN));
				errorCollector.checkThat(getTestInfoPrefix() + "Invalid answer value for round question " + i
						+ " in player statistics of player 2.",
						driver.findElement(By.id(ID_PREFIX_PLAYER_ANSWER_2 + i)).getText(),
						equalTo(EXPECTED_ANSWER_VALUE_UNKNOWN));
			}
		}

	}

	/**
	 * checks the question page
	 * 
	 * Performed checks:
	 * <ul>
	 * <li>Player info checks</li>
	 * <li>Question checks</li>
	 * <li>timer checks</li>
	 * </ul>
	 * 
	 * @return
	 */
	private Question checkQuestionPage() {
		currentCheckCategory.push("QuestionPage");

		checkPlayerInfo(totalQuestions % NUM_QUESTIONS, totalQuestions);
		Question question = checkQuestion();

		if (timeCounterTests > 0) {

			checkTimer(question);
			timeCounterTests--;
		}

		currentCheckCategory.pop();
		return question;
	}

	/**
	 * checks the timer
	 * 
	 * Performed checks
	 * <ul>
	 * <li>time counter format check</li>
	 * <li>time counter value check</li>
	 * <li>time counter update check</li>
	 * </ul>
	 * 
	 * @param question
	 *            the current question
	 * 
	 */
	private void checkTimer(Question question) {

		String timerValue = driver.findElement(By.id(ID_TIMER_VALUE)).getText().trim();
		int minutes = 0;
		int seconds = 0;

		// Format-Check
		java.util.regex.Matcher timerValueMatcher = Pattern.compile(TIMER_REGEX).matcher(timerValue);
		if (timerValueMatcher.matches()) {
			minutes = Integer.parseInt(timerValueMatcher.group(1));
			seconds = Integer.parseInt(timerValueMatcher.group(2));
		} else {
			errorCollector.addError(new Exception(getTestInfoPrefix()
					+ "Invalid timer format. Expected format(Regular expression): \"" + TIMER_REGEX + "\""));
		}
		
		// Value-Check
		if (question != null) {
			if(timerValueMatcher.matches()) {
				minutes = Integer.parseInt(timerValueMatcher.group(1));
				seconds = Integer.parseInt(timerValueMatcher.group(2));

				int timeInSeconds = minutes * 60 + seconds;

				errorCollector.checkThat(getTestInfoPrefix() + "Invalid timer value (in seconds)", timeInSeconds, 
						allOf(lessThanOrEqualTo(timeInSeconds + timeCounterValueTolerance), greaterThanOrEqualTo(timeInSeconds - timeCounterValueTolerance)));
			}
		} else {
			errorCollector
					.addError(new Exception(
							getTestInfoPrefix()
									+ "Consequential Error: Couldn't check timer value completely because the question could not be determined "));
		}

		// Update-Check
		// wait a random amount of time
		int sleepTime = (int) (Math.random() * maxTimeCounterTestSleep) + 1;
		Sleeper.sleepTightInSeconds(sleepTime);

		// check values again
		int newMinutes = 0;
		int newSeconds = 0;
		timerValue = driver.findElement(By.id(ID_TIMER_VALUE)).getText().trim();
		timerValueMatcher = Pattern.compile(TIMER_REGEX).matcher(timerValue);
		if (timerValueMatcher.matches()) {
			newMinutes = Integer.parseInt(timerValueMatcher.group(1));
			newSeconds = Integer.parseInt(timerValueMatcher.group(2));

			int expectedTime = minutes * 60 + seconds;
			int newTime = newMinutes * 60 + newSeconds;

			errorCollector.checkThat(
					getTestInfoPrefix() + "Invalid timer value (in seconds) after " + sleepTime + "s",
					newTime,
					allOf(lessThanOrEqualTo(expectedTime - sleepTime + timeCounterValueTolerance),
							greaterThanOrEqualTo(expectedTime - sleepTime - timeCounterValueTolerance)));

		} else {
			errorCollector.addError(new Exception(getTestInfoPrefix() + "Invalid timer format after "
					+ sleepTime + "s. Expected format(Regular expression): \"" + TIMER_REGEX + "\""));
		}
	}

	/**
	 * checks the question
	 * 
	 * Performed checks
	 * <ul>
	 * <li>Category name check</li>
	 * <li>Question text check</li>
	 * <li>Choice text check</li>
	 * </ul>
	 * 
	 * @return
	 */
	private Question checkQuestion() {
		int id = -1;
		try {
			id = Integer.parseInt(driver.findElement(By.id(ID_QUESTION_ID)).getAttribute("value"));
		} catch (NumberFormatException nfe) {
		}
		if (id >= 0) {
			Question question = questions.get(id);
			if (question != null) {

				// check question text and category

				errorCollector.checkThat(getTestInfoPrefix() + "Unexpected category name", driver
						.findElement(By.id(ID_CATEGORY_NAME)).getText(), containsString(question
						.getCategory().getName()));
				errorCollector.checkThat(getTestInfoPrefix() + "Unexpected question text", driver
						.findElement(By.id(ID_QUESTION_TEXT)).getText(), containsString(question.getText()));

				// check choice labels

				List<String> allChoices = new ArrayList<>();
				for (Choice choice : question.getWrongChoices()) {
					allChoices.add(choice.getText());
				}
				for (Choice choice : question.getCorrectChoices()) {
					allChoices.add(choice.getText());
				}

				for (int i = 0; i < allChoices.size(); i++) {
					errorCollector.checkThat(getTestInfoPrefix() + "Invalid choice label", driver
							.findElement(By.id(ID_PREFIX_CHOICE_LABEL + i)).getText().trim(),
							isIn(allChoices));
				}
				return question;
			} else {
				errorCollector.addError(new Exception(getTestInfoPrefix() + "Unknown question id"));
			}
		} else {
			errorCollector.addError(new Exception(getTestInfoPrefix() + "Couldn't determine question by id"));
		}
		return null;
	}

	/**
	 * answers the given question. If null is passed as question a consequential
	 * error is created.
	 * 
	 * @param question
	 *            the question to answer, passing null will create a junit error
	 * @param totalQuestionCount
	 *            the total number of answered questions
	 */
	private void answerQuestion(Question question, int totalQuestionCount) {
		if (question != null) {
			AnswerType[] answerTypes = AnswerType.values();
			if (totalQuestionCount < AnswerType.values().length) {
				// make sure that each type of answer is tested at least once
				answerQuestion(question, answerTypes[totalQuestionCount]);
			} else {
				// choose a random answer type
				answerQuestion(question, answerTypes[(int) Math.random() * answerTypes.length]);
			}
		} else {
			errorCollector
					.addError(new Exception(
							getTestInfoPrefix()
									+ "Consequential Error: Couldn't answer question correctly because the question could not be determined "));
			answers.add(new Answer(null, AnswerType.NONE, new ArrayList<Choice>()));
		}
	}

	/**
	 * answers the question with a specific answer type
	 * 
	 * @param question
	 *            the question to answer (not null)
	 * @param answerType
	 *            the answer type
	 */
	private void answerQuestion(Question question, AnswerType answerType) {
		List<Choice> choices = answerType.createChoices(question.getWrongChoices(),
				question.getCorrectChoices());
		int totalChoices = question.getCorrectChoices().size() + question.getWrongChoices().size();
		List<Choice> selectedChoices = new ArrayList<>();
		for (int i = 0; i < totalChoices; i++) {
			WebElement choiceLabel = driver.findElement(By.id(ID_PREFIX_CHOICE_LABEL + i));
			WebElement checkbox = driver.findElement(By.id(choiceLabel.getAttribute("for")));
			for (Choice choice : choices) {
				if (choiceLabel.getText().contains(choice.getText())) {
					// click on a potentially invisible checkbox
					((JavascriptExecutor) driver).executeScript("arguments[0].click()", checkbox);
					selectedChoices.add(choice);
					break;
				}
			}
		}

		if (selectedChoices.size() != choices.size()) {
			errorCollector.addError(new Exception(getTestInfoPrefix()
					+ "Couldn't select the following choices: " + choices));
		}
		answers.add(new Answer(question, answerType, choices));
	}

	private String getTestInfoPrefix() {
		return "[Check: " + currentCheckCategory.peek() + ", run: " + currentRun + ", round: "
				+ ((int) totalQuestions / NUM_QUESTIONS) + ", answeredQuestions: " + totalQuestions + "] ";
	}

	// getters and setters

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getMainUrl() {
		return mainUrl;
	}

	public void setMainUrl(String mainUrl) {
		this.mainUrl = mainUrl;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public static String getJsonFileName() {
		return jsonFileName;
	}

	public static void setJsonFileName(String jsonFileName) {
		SeleniumTest.jsonFileName = jsonFileName;
	}

	public int getNumberOfRuns() {
		return numberOfRuns;
	}

	public void setNumberOfRuns(int numberOfRuns) {
		this.numberOfRuns = numberOfRuns;
	}

	public int getTimeCounterTests() {
		return timeCounterTests;
	}

	public void setTimeCounterTests(int timeCounterTests) {
		this.timeCounterTests = timeCounterTests;
	}

	public boolean isTakeScreenshotOnError() {
		return takeScreenshotOnError;
	}

	public void setTakeScreenshotOnError(boolean takeScreenshotOnError) {
		this.takeScreenshotOnError = takeScreenshotOnError;
	}

	public boolean isDumpHTMLOnError() {
		return dumpHTMLOnError;
	}

	public void setDumpHTMLOnError(boolean dumpHTMLOnError) {
		this.dumpHTMLOnError = dumpHTMLOnError;
	}

	public int getMaxTimeCounterTestSleep() {
		return maxTimeCounterTestSleep;
	}

	public void setMaxTimeCounterTestSleep(int maxTimeCounterTestSleep) {
		this.maxTimeCounterTestSleep = maxTimeCounterTestSleep;
	}
}
