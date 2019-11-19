package webScrapingCode;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class GetBasketData {
	static WebDriver driver;
	static ArrayList<Player> players = new ArrayList<Player>();
	static ArrayList<Coach> coaches = new ArrayList<Coach>();
	static ArrayList<Team> teams = new ArrayList<Team>();



	public static void main(String[] args) {
		getData();
	}

	public static ArrayList<Player> getPlayers() {
		return players;
	}

	public static ArrayList<Coach> getCoaches() {
		return coaches;
	}

	public static void getData() {
		setBrowserConfig();
		driver.get("https://www.basketball-reference.com/leagues/NBA_2020_per_game.html");
		String pageSource = driver.getPageSource();  
		
		try {
			System.out.println("Getting players");

			Document doc = Jsoup.parse(pageSource);        
			for (Element row : doc.select("#per_game_stats tr")) {
				if (row.select("th.right").text().equals("")) {
					continue;
				}
				else {
					final String rank = row.select("th.right").text();        		
					final String name = row.select("td:nth-of-type(1)").text();        		
					final String team = row.select("td.left:nth-of-type(4)").text();
					final String games = row.select("td.right:nth-of-type(5)").text();
					final String firstName = name.substring(0, name.indexOf(" "));
					final String lastName = name.substring(name.indexOf(" "));
					players.add(new Player("p" + rank, lastName, firstName, team, games));
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace(); // don't normally do this bad way of exception handling
		}
		driver.get("https://www.basketball-reference.com/leagues/NBA_2020_coaches.html");
		pageSource = driver.getPageSource();  
		try {
			System.out.println("Getting coaches");
			Document doc = Jsoup.parse(pageSource);
			int count = 0;
			for (Element row : doc.select("#NBA_coaches tr")) {
				if (row.select("th.left").text().equals("") || row.select("th.left").text().equals("Coach")) {
					continue;
				}
				else {
					final String name = row.select("th.left").text();        		
					final String team = row.select("td.left").text();
					final String gamesWon = row.select("td.right:nth-of-type(13)").text();
					final String gamesLost = row.select("td.right:nth-of-type(14)").text();
					final String ratio = row.select("td.right:nth-of-type(15)").text();
					final String firstName = name.substring(0, name.indexOf(" "));
					final String lastName = name.substring(name.indexOf(" "));
					coaches.add(new Coach("c" + count++, lastName, firstName, team, gamesWon, gamesLost, ratio));
					for (Player player: players) {
						if (player.getTeam().equals(team)) {
							coaches.get(coaches.size()-1).addPlayer(player.getID());
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Getting teams");
		driver.get("https://www.basketball-reference.com/teams/");
		try {
		for (int i = 1; i < 30; i ++) {
			System.out.println(driver.findElement(By.xpath("//*[@id=\"teams_active\"]/tbody/tr["+i+"]/th/a")).getText());
		}}catch (Exception e) {
			System.out.println("Teams are not working yet");
		}
		
		
		
		
		
		driver.quit();
	}

	private static void setBrowserConfig() {
		System.out.println("Setting configurations");
		String projectLocation = System.getProperty("user.dir");
		ChromeOptions options = new ChromeOptions(); 
		options.addArguments("headless");
		System.setProperty("webdriver.chrome.driver", projectLocation + "/lib/chromedriver/chromedriver");
		System.out.println("Starting driver");
		driver = new ChromeDriver(options);
	}
}