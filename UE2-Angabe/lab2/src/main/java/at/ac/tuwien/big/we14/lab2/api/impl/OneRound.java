package at.ac.tuwien.big.we14.lab2.api.impl;

public class OneRound {

	private String player_question1;
	private String player_question2;
	private String player_question3;

	private String player_question1_time;
	private String player_question2_time;
	private String player_question3_time;

	private int player_points;

	private String player2_question1;
	private String player2_question2;
	private String player2_question3;

	private String player2_question1_time;
	private String player2_question2_time;
	private String player2_question3_time;

	private int player2_points;

	private String winner;

	public OneRound() {
		player_question1 = "unknown";
		player_question2 = "unknown";
		player_question3 = "unknown";

		player2_question1 = "unknown";
		player2_question2 = "unknown";
		player2_question3 = "unknown";
		
		player_points = 0;
		player2_points = 0;
	}

	public void checkWinner() {
		if (player_points > player2_points) {
			winner = "Player 1";
		} else if (player2_points > player_points) {
			winner = "Player 2";
		} else {
			// Zeitunterschied vergleichen
			int playerTimeLeft = Integer.parseInt(player_question1_time) + Integer.parseInt(player_question2_time) + Integer.parseInt(player_question3_time);
			int player2TimeLeft = Integer.parseInt(player2_question1_time) + Integer.parseInt(player2_question2_time) + Integer.parseInt(player2_question3_time);
			if (playerTimeLeft > player2TimeLeft) {
				winner = "Player 1";
			} else if (playerTimeLeft < player2TimeLeft) {
				winner = "Player 2";
			} else
				winner = "Player 1 und Player 2";
		}
	}

	public void IncrementPlayer() {
		player_points++;
	}

	public void IncrementPlayer2() {
		player2_points++;
	}
	
	public String getWinner(){
		return winner;
	}
	
	public int getPlayer_points() {
		return player_points;
	}

	public void setPlayer_points(int player_points) {
		this.player_points = player_points;
	}

	public int getPlayer2_points() {
		return player2_points;
	}

	public void setPlayer2_points(int player2_points) {
		this.player2_points = player2_points;
	}

	public String getPlayer_question1() {
		return player_question1;
	}

	public void setPlayer_question1(String player_question1) {
		this.player_question1 = player_question1;
	}

	public String getPlayer_question2() {
		return player_question2;
	}

	public void setPlayer_question2(String player_question2) {
		this.player_question2 = player_question2;
	}

	public String getPlayer_question3() {
		return player_question3;
	}

	public void setPlayer_question3(String player_question3) {
		this.player_question3 = player_question3;
	}

	public String getPlayer_question1_time() {
		return player_question1_time;
	}

	public void setPlayer_question1_time(String player_question1_time) {
		this.player_question1_time = player_question1_time;
	}

	public String getPlayer_question2_time() {
		return player_question2_time;
	}

	public void setPlayer_question2_time(String player_question2_time) {
		this.player_question2_time = player_question2_time;
	}

	public String getPlayer_question3_time() {
		return player_question3_time;
	}

	public void setPlayer_question3_time(String player_question3_time) {
		this.player_question3_time = player_question3_time;
	}

	public String getPlayer2_question1() {
		return player2_question1;
	}

	public void setPlayer2_question1(String player2_question1) {
		this.player2_question1 = player2_question1;
	}

	public String getPlayer2_question2() {
		return player2_question2;
	}

	public void setPlayer2_question2(String player2_question2) {
		this.player2_question2 = player2_question2;
	}

	public String getPlayer2_question3() {
		return player2_question3;
	}

	public void setPlayer2_question3(String player2_question3) {
		this.player2_question3 = player2_question3;
	}

	public String getPlayer2_question1_time() {
		return player2_question1_time;
	}

	public void setPlayer2_question1_time(String player2_question1_time) {
		this.player2_question1_time = player2_question1_time;
	}

	public String getPlayer2_question2_time() {
		return player2_question2_time;
	}

	public void setPlayer2_question2_time(String player2_question2_time) {
		this.player2_question2_time = player2_question2_time;
	}

	public String getPlayer2_question3_time() {
		return player2_question3_time;
	}

	public void setPlayer2_question3_time(String player2_question3_time) {
		this.player2_question3_time = player2_question3_time;
	}
}
