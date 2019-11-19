package webScrapingCode;

import java.util.ArrayList;

public class Coach {
	private String ID;
	private String lastName;
	private String firstName;
	private String team;
	private String won;
	private String lost;
	private String ratio;
	private ArrayList<String> playerRef;

	public Coach(String iD, String lastName, String firstName, String team, String won, String lost, String ratio) {
		super();
		ID = iD;
		this.lastName = lastName;
		this.firstName = firstName;
		this.team = team;
		this.won = won;
		this.lost = lost;
		this.ratio = ratio;
		playerRef = new ArrayList<String>();
	}

	public String getID() {
		return ID;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getTeam() {
		return team;
	}

	public String getWon() {
		return won;
	}

	public String getLost() {
		return lost;
	}

	public String getRatio() {
		return ratio;
	}
	public void addPlayer(String playerID) {
		playerRef.add(playerID);
	}


}
