package webScrapingCode;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class testXML {
	
	public static void main(String[] args) {
		try {
			GetBasketData.getData();

			ArrayList<Player> players = GetBasketData.getPlayers();
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
    		System.out.println("Creating elements");

            // root element
            Element root = document.createElement("basketball");
            document.appendChild(root);
 
            for (Player playerfromList : players) {
            Element player = document.createElement("player");
            root.appendChild(player);
 
            Attr attr = document.createAttribute("id");
            attr.setValue(playerfromList.getID());
            player.setAttributeNode(attr);
             
            Element names = document.createElement("names");
            player.appendChild(names);
            
            Element firstName = document.createElement("firstname");
            firstName.appendChild(document.createTextNode(playerfromList.getFirstName()));
            names.appendChild(firstName);
 
            // lastname element
            Element lastname = document.createElement("lastname");
            lastname.appendChild(document.createTextNode(playerfromList.getLastName()));
            names.appendChild(lastname);
 
            // email element
            Element team = document.createElement("team");
            team.appendChild(document.createTextNode(playerfromList.getTeam()));
            player.appendChild(team);
 
            // department elements
            Element games = document.createElement("games");
            games.appendChild(document.createTextNode(playerfromList.getGames()));
            player.appendChild(games);
            }
            
            ArrayList<Coach> coaches = GetBasketData.getCoaches();
            for (Coach coachesFromList : coaches) { 
            	Element coach = document.createElement("coach");
                root.appendChild(coach);
     
                Attr attr = document.createAttribute("id");
                attr.setValue(coachesFromList.getID());
                coach.setAttributeNode(attr);
                 
                Element names = document.createElement("names");
                coach.appendChild(names);
                
                Element firstName = document.createElement("firstname");
                firstName.appendChild(document.createTextNode(coachesFromList.getFirstName()));
                names.appendChild(firstName);
     
                // lastname element
                Element lastname = document.createElement("lastname");
                lastname.appendChild(document.createTextNode(coachesFromList.getLastName()));
                names.appendChild(lastname);
     
                // email element
                Element team = document.createElement("team");
                team.appendChild(document.createTextNode(coachesFromList.getTeam()));
                coach.appendChild(team);
                
                Element won = document.createElement("wins");
                won.appendChild(document.createTextNode(coachesFromList.getWon()));
                coach.appendChild(won);
                
                Element lost = document.createElement("losses");
                lost.appendChild(document.createTextNode(coachesFromList.getLost()));
                coach.appendChild(lost);

                Element ratio = document.createElement("ratio");
                ratio.appendChild(document.createTextNode(coachesFromList.getRatio()));
                coach.appendChild(ratio);
     
                
                // department elements
//                Element games = document.createElement("games");
//                games.appendChild(document.createTextNode(playerfromList.getGames()));
//                player.appendChild(games);
            	
            }

            
            
            
            
            
            // create the xml file
            //transform the DOM Object to an XML File
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("lib/xmlbased/file.xml"));
            transformer.transform(domSource, streamResult);
 
            System.out.println("Done creating XML File");
 
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    
	}

}
