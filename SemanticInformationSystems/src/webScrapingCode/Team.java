package webScrapingCode;

import java.util.List;

public class Team {
	private String tag;
	private String name;
	private String rank;
	private List<String> playersID;
	private String coachID;
	private String teamID;
	
	public Team(String tag, String name, String rank) {
		super();
		this.tag = tag;
		this.name = name;
		this.rank = rank;
	}
	public String getTag() {
		return tag;
	}
	public String getName() {
		return name;
	}
	public String getRank() {
		return rank;
	}
	public List<String> getPlayersID() {
		return playersID;
	}
	public String getCoachID() {
		return coachID;
	}
	public String getTeamID() {
		return teamID;
	}
	

}
