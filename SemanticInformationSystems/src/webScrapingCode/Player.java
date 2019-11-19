package webScrapingCode;

public class Player {
	private String ID;
	private String lastName;
	private String firstName;
	private String team;
	private String games;
	
	public Player(String iD, String lastName, String firstName, String team, String games) {
		super();
		this.ID = iD;
		this.lastName = lastName;
		this.firstName = firstName;
		this.team = team;
		this.games = games;
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

	public String getGames() {
		return games;
	}

}
