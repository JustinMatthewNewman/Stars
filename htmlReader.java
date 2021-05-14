
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * HTML READER TAKES IN NAMES FROM WEB LINK
 * 
 * @author justin newman
 * @version 11/24/2020
 *
 */
public class htmlReader {

    protected static ArrayList<String> names;
    protected static int playerCount;

    /**
     * Gets the HTML source code from site.
     * 
     * @param url
     * @return the source code.
     * @throws IOException catches exception if there is no network connection
     *     or in the case that incorrect link is provided.
     */
    public static String htmlSource(String url) throws IOException {
        try {
            System.out.println("Establishing connection to server...\n\n");
            Document document = Jsoup.connect(url).get();
            String html = document.toString();
            System.out.println("Connection Succesfull! ☆\n");
            return html;
        } catch (Exception e) {
            System.out.println(
                    "Error: You are not connected to the internet.\n Or the link is not a Roster.");
            return "Please check your internet connection + \n and try again.";
        }

    }

    /**
     * READS IN THE HTML OF THE PLAYER ROSTER AND GETS ARRAYLIST OF NAMES
     * 
     * @param url
     * @return
     * @throws IOException
     */
    public static void nameReader(String url) throws IOException {
        names = new ArrayList<String>();
        Scanner htmlParsePlayer = new Scanner(Team.source);
        playerCount = 0;
        while (htmlParsePlayer.hasNext()) {
            String line = htmlParsePlayer.next();
            if (line.contains("data-player-id")
                    && !htmlParsePlayer.next().equals(
                            "aria-live=\"polite\">")) {
                while (!htmlParsePlayer.next().contains("/sports/")) {
                    htmlParsePlayer.next();
                }
                String firstName = htmlParsePlayer.next();
                String player_name = firstName.substring(
                        firstName.indexOf("=") + 2, firstName.length()) + " "
                        + htmlParsePlayer.next();
                if (player_name.contains(",")) {
                    player_name += " " + htmlParsePlayer.next();
                }
                if (player_name.contains("\"")) {
                    player_name += htmlParsePlayer.next() + " "
                            + htmlParsePlayer.next();
                }
                String n = player_name;
                n = n.replace("=", "");
                n = n.replace("\"", "");
                names.add(n);
                playerCount++;
                //System.out.println("Player name added...   " + playerCount);
            }
        }
        System.out.println("\n");
        System.out.println("Listing Players...\n");
        System.out.println(names + "\n");

    }

    /**
     * Another method for getting player's names.
     * 
     * @param url
     * @return
     * @throws IOException
     */
    public static ArrayList<String> nameExcp(String url) throws IOException {
        ArrayList<String> namesE = new ArrayList<String>();
        Scanner htmlParsePlayer = new Scanner(htmlSource(url));
        playerCount = 0;
        while (htmlParsePlayer.hasNext()) {
            String line = htmlParsePlayer.next();
            if (line.contains("data-player-url")
                    && !htmlParsePlayer.next().equals(
                            "aria-live=\"polite\">")) {
                String n = line.substring(line.indexOf("roster/") + 7,
                        line.length());
                n = n.substring(0, n.indexOf("/"));
                namesE.add(n);
                playerCount++;
            }
        }
        return namesE;
    }

    /**
     * Yet again another method for getting player's names.
     * 
     * @param url
     * @return
     * @throws IOException
     */
    public static String extraNameExcpt(String source, String name) {
        String name1 = "";
        Scanner in = new Scanner(source);
        while (in.hasNext()) {
            String line = in.next();
            if (line.contains(name) && line.contains(" - View Full Bio")) {
                name1 = line;
            }
            if (line.contains(name.substring(0, name.indexOf(" ")))
                    && line.contains(" - View Full Bio")) {
                name1 = line;
            }
        }
        return name1;
    }

}
