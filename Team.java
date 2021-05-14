import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * The team class extracts the player's data attributes from the source Code.
 * 
 * @author justin newman
 *
 */
public class Team {
    protected static HashMap<String, ArrayList<String>> hm;
    protected static String teamNameurl;
    protected static String source;
    protected static Color teamColor;
    protected static Color teamColor2;
    protected static Object[] team;

    /**
     * Team constructor takes input of Roster URL and creates HashMap linking
     * players to their distinct attributes.
     * 
     * @param teamNameurl
     * @throws IOException
     */
    @SuppressWarnings("static-access")
    public Team(String teamNameurl, Color c, Color c2) throws IOException {
        hm = new HashMap<String, ArrayList<String>>();
        this.teamNameurl = teamNameurl;
        this.source = htmlReader.htmlSource(teamNameurl);
        this.teamColor = c;
        this.teamColor2 = c2;
        htmlReader.nameReader(teamNameurl);
        ArrayList<String> names = htmlReader.names;
        System.out.println(
                "Establishing Player's Graphic's Database at : "
                + "\"/Users/justinnewman/Documents/Graphics/\""
                        + "\n");

        int i = 0;
        for (String a : names) {
            ArrayList<String> atribs = new ArrayList<String>();
            team = new Object[names.size() + 1];
            if (a != null) {
                atribs.add(getPosition(a));
                atribs.add(getJersyNum(a));
                atribs.add(getcurClass(a));
                atribs.add(getHeight(a));
                atribs.add(getWeight(a));
                atribs.add(getHomeTown(a));
                //System.out.print("Athlete (#" + i + ") - ");
                // Parse into athlete
                team[i] = new Athlete(a, atribs.get(1), atribs.get(2),
                        atribs.get(3), atribs.get(4), atribs.get(5),
                        getGenderId(a), atribs.get(0));
                System.out.print(((Athlete) team[i]).getName() + " - ");
                System.out.print(((Athlete) team[i]).getCurClass() + " - ");
                System.out.print(((Athlete) team[i]).getHomeTown() + "\n");
                i++;
            }
            hm.put(a, atribs);
        }
        System.out.println("\n\n");
        System.out.println("Checking URL HTML signature...");
        System.out.println();
    }

    

    /**
     * Method finds the player's original homeTown from URL.
     * 
     * @param name
     * @return
     * @throws IOException
     */
    public static String getHomeTown(String name) throws IOException {
        try {
            String playerCode = getPlayerCode(name);
            Scanner in = new Scanner(playerCode);
            String r = "";
            while (in.hasNext()) {
                String line = in.next();
                if (line.contains("sidearm-roster-player-hometown")) {
                    r = line.substring(line.indexOf(">") + 1, line.length());
                    String loc = in.next();
                    while (!loc.contains("<")) {
                        loc = loc + in.next();
                    }
                    r = r + " " + loc;
                }
            }
            return r.substring(0, r.indexOf("<"));
        } catch (Exception e) {
            return "N/A";
        }
        
    }
    
    /**
     * Returns the gender ID of the sports team
     * @param a
     * @return
     */
    private String getGenderId(String a) {
        if (isMen()) {
            return "Male";
        } else {
            return "Female";
        }
    }

    /**
     * Extracts the portion of HTML containing the Player's attributes.
     * 
     * @param name
     * @return
     * @throws IOException
     */
    private static String getPlayerCode(String name) throws IOException {
        try {
            try {
                String sourceCode = source;
                return sourceCode.substring(
                        sourceCode.indexOf(name + " - View Full Bio"),
                        sourceCode.indexOf(
                                "Hide/Show Additional Information For " + name));
            } catch (StringIndexOutOfBoundsException e) {
                try {
                    String sourceCode = source;
                    return sourceCode.substring(
                            sourceCode.indexOf(
                                    name.substring(name.indexOf(" "), name.length())
                                            + " - View Full Bio"),
                            sourceCode.indexOf(
                                    "Hide/Show Additional Information For "
                                            + name));
                } catch (Exception ex) {
                    String sourceCode = source;
                    return sourceCode.substring(
                            sourceCode.indexOf(
                                    htmlReader.extraNameExcpt(source, name)
                                            + " - View Full Bio"),
                            sourceCode.indexOf(
                                    "Hide/Show Additional Information For "
                                            + name));
                }
            }
        } catch (Exception E) {
            return "";
        }

    }

    /**
     * Gets the player position as a single character.
     * 
     * @param name of the player.
     * @return the char position.
     * @throws IOException
     */
    public static String getPosition(String name) throws IOException {
        try {
            String playerCode = getPlayerCode(name);
            Scanner in = new Scanner(playerCode);
            String e = "";
            while (in.hasNext()) {
                String line = in.next();
                if (line.contains("sidearm-roster-player-position")) {
                    e = in.next() + in.next() + in.next() + in.next();
                }
            }
            e = e.substring(4, e.length());
            e = e.substring(e.indexOf(">") + 1, e.indexOf("<"));
            return e;
        } catch (Exception e) {
            return "N/A";
        }


    }

    /**
     * gets the players jersy number.
     * 
     * @throws IOException
     */
    public static String getJersyNum(String name) throws IOException {
        try {
            if (isNonNumbered()) {
                return "";
            }
            String playerCode = getPlayerCode(name);
            String sub = playerCode.substring(
                    playerCode.indexOf("sidearm-roster-player-jersey-number") + 38,
                    playerCode.indexOf("sidearm-roster-player-jersey-number") + 40);
            return sub;
        } catch (Exception e) {
            return "N/A";
        }
        
    }

    private static boolean isNonNumbered() {
        if (teamNameurl.contains("swim") 
                || teamNameurl.contains("track")) {
            return true;
        }
        return false;
    }



    /**
     * gets the players current academic year.
     * 
     * @throws IOException
     */
    public static String getcurClass(String name) throws IOException {
        try {
            String playerCode = getPlayerCode(name);
            Scanner in = new Scanner(playerCode);
            String sub = "";
            while (in.hasNext()) {
                String line = in.next();
                if (line.contains("sidearm-roster-player-academic-year")
                        && !line.contains("hide-on-large")) {
                    sub = line;
                    if (!sub.contains("</span>")) {
                        sub += in.next();
                    }
                }
            }
            String year = sub.substring(sub.indexOf(">") + 1, sub.indexOf("<"));
            return year;
        } catch (Exception e) {
            return "";
        }
        
    }

    /**
     * gets the player height.
     * 
     * @throws IOException
     */
    public static String getHeight(String name) throws IOException {
        try {
            String playerCode = getPlayerCode(name);
            String sub = playerCode.substring(
                    playerCode.indexOf("sidearm-roster-player-height") + 30,
                    playerCode.indexOf("sidearm-roster-player-height") + 34);
            String exceptionCatcher = sub.substring(0, 1);

            try {
                Integer.parseInt(exceptionCatcher);
                return sub;
            } catch (Exception e) {
                return " ";
            }
        } catch (Exception e) {
            return "";
        }
        
    }
    
    /**
     * returns a string of text containing attributes of each player. ex.
     * position, jersy number, height, etc.
     * 
     * @param name
     * @return
     */

    public String getPlayerAtribs(String name) {
        ArrayList<String> a2;
        for (String a : hm.keySet()) {
            if (a.toLowerCase().equals(name.toLowerCase())) {
                a2 = hm.get(a);
                StringBuilder sb = new StringBuilder();
                a2.get(0);
                sb.append(String.format("%13s", a2.get(1)) + "     ");
                a2.get(1);
                sb.append(String.format("%5s", a2.get(0)));
                a2.get(2);
                sb.append(String.format("%18s", a2.get(2)));
                a2.get(3);
                sb.append(String.format("%10s", a2.get(3)));
                a2.get(4);
                sb.append(String.format("%10s", a2.get(4)));
                a2.get(5);
                sb.append(String.format("%30s", a2.get(5)));
                return sb.toString();
            }
        }
        // return a space if data not found
        return " ";
    }

    /**
     * gets the player weight.
     * 
     * @throws IOException
     */
    public static String getWeight(String name) throws IOException {
        try {
            if (isMen() == true) {
                String playerCode = getPlayerCode(name);
                Scanner in = new Scanner(playerCode);
                String r = "";
                while (in.hasNext()) {
                    String line = in.next();
                    if (line.contains("sidearm-roster-player-weight")) {
                        r = line.substring(line.indexOf(">") + 1,
                                line.length());
                        String loc = in.next();
                        while (!loc.contains("<")) {
                            loc = loc + in.next();
                        }
                        r = r + " " + loc;
                    }
                }
                return r.substring(0, r.indexOf("<"));
            } else {
                return "   ";
            }
        } catch (Exception e) {
            return "   ";
        }

    }

    /**
     * determines the gender of the team.
     * 
     * @return true if all mens team.
     */
    private static boolean isMen() {
        String a = source;
        int countm = 0;
        int countw = 0;
        Scanner in = new Scanner(a);
        while (in.hasNext()) {
            String line = in.next();
            if (line.contains("men") && !line.contains("women")) {
                countm++;
            }
            if (line.contains("women")) {
                countw++;
            }
        }
        if (countm > countw) {
            return true;
        } else {
            for (ArrayList<String> f : hm.values()) {
                f.set(4, "");
            }
            return false;
        }
    }


    /**
     * Method finds the number of players on the team. AKA team size.
     * 
     * @return
     * @throws IOException
     */
    public int size() throws IOException {
        return htmlReader.names.size();
    }

    /**
     * gets the name of the player given the index.
     * 
     * @param index
     * @return
     * @throws IOException
     */
    public static String getName(int index) throws IOException {
        return htmlReader.names.get(index);
    }

    /**
     * gets the original URL of the roster.
     * 
     * @return
     */
    public String getUrl() {
        return teamNameurl;
    }



    /**
     * get the link to the player head shot.
     * 
     * @param name
     * @return
     * @throws IOException
     */
    public static String getHeadShot(String name) throws IOException {
        try {
            try {
                String r = getPlayerCode(name);
                r = r.substring(r.indexOf("data-src"), r.indexOf("alt"));
                r = r.substring(r.indexOf("/"), r.indexOf(".") + 4);
                return teamNameurl.substring(0, teamNameurl.indexOf("/sports")) + r;
            } catch (Exception e) {
                String r = getPlayerCode(name);
                r = r.substring(r.indexOf("data-src"), r.indexOf("alt="));
                r = r.substring(r.indexOf("/"), r.indexOf(".") + 4);
                return teamNameurl.substring(0, teamNameurl.indexOf("com/") + 3)
                        + r;
            }
        } catch (Exception e) {
            return "https://www.edmundsgovtech.com/wp-content/uploads/2020/01/default-picture_0_0.png";
        }


    }

    /**
     * Gets the players position as a whole word.
     * 
     * @param name
     * @return
     * @throws IOException
     */
    public static String getPosition2(String name) throws IOException {
        String playerCode = getPlayerCode(name);
        if (playerCode.contains(
                "sidearm-roster-player-position-long-short hide-on-small-down")) {
            String sub = playerCode.substring(playerCode.indexOf(
                    "sidearm-roster-player-position-long-short hide-on-small-down"),
                    playerCode.indexOf(
                            "sidearm-roster-player-position-long-short hide-on-medium"));
            return sub.substring(sub.indexOf(">") + 2, sub.indexOf("<") - 1);
        } else {
            String sub = playerCode.substring(playerCode.indexOf("text-bold"),
                    playerCode.indexOf("text-bold") + 5);
            return sub;
        }

    }
    

    /**
     * Gets height and weight of the player.
     * 
     * @param name
     * @return
     */
    public String getPlayerHw(String name) {
        ArrayList<String> a2;
        for (String a : hm.keySet()) {
            if (a.toLowerCase().equals(name.toLowerCase())) {
                a2 = hm.get(a);
                StringBuilder sb = new StringBuilder();
                a2.get(3);
                sb.append(String.format("%10s", a2.get(3)));
                a2.get(4);
                sb.append(String.format("%10s", a2.get(4)));
                return sb.toString();
            }
        }
        return "ERROR: player not found.";
    }

    /**
     * Gets the link to the team's logo.
     * 
     * @return the string link.
     */
    public static String getLogo() {
        Scanner in = new Scanner(source);
        String line = "";
        String r = line;
        while (in.hasNext()) {
            line = in.next();
            if (line.contains("logo") && line.contains("https:")
                    && line.contains("main")) {
                r = line;
            }
        }
        r = r.substring(5, r.length() - 1);
        System.out.println(r);
        return r;
    }
   

    /**
     * gets the list of names on the given team. utilizes the htmlReader class.
     * 
     * @return
     * @throws IOException
     */
    public static ArrayList<String> getNames() throws IOException {
        return htmlReader.names;
    }

    /**
     * Method for exception coverage.
     * 
     * @return
     * @throws IOException
     */
    public static ArrayList<String> excepCov() throws IOException {
        return htmlReader.nameExcp(teamNameurl);
    }



    public String getTop(String name) {
        ArrayList<String> a2;
        for (String a : hm.keySet()) {
            if (a.toLowerCase().equals(name.toLowerCase())) {
                a2 = hm.get(a);
                StringBuilder sb = new StringBuilder();
                // position
                a2.get(0);
                sb.append(String.format("| %1s", a2.get(0)) + " |");
                //number
                a2.get(1);
                sb.append(String.format("  #%1s", a2.get(1)));
//                //Year
//                a2.get(2);
//                sb.append(String.format("%18s", a2.get(2)));
//                // Height
//                a2.get(3);
//                sb.append(String.format("%10s", a2.get(3)));
//                // Weight
//                a2.get(4);
//                sb.append(String.format("%10s", a2.get(4)));
//                // Home
//                a2.get(5);
//                sb.append(String.format("%30s", a2.get(5)));
                return sb.toString();
            }
        }
        return "";
    }
    
    public String getBottom(String name) {
        ArrayList<String> a2;
        for (String a : hm.keySet()) {
            if (a.toLowerCase().equals(name.toLowerCase())) {
                a2 = hm.get(a);
                StringBuilder sb = new StringBuilder();
                //Year
                a2.get(2);
               sb.append(String.format("%18s", a2.get(2)));
                // Height

               // Home
               a2.get(5);
               sb.append(String.format("%30s", a2.get(5)));
                return sb.toString();
            }
        }
        return "";
    }


}
