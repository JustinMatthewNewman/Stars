import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

public class imageWriter {
    public static void writeBio(Team team) throws IOException {
        for (int i = 0; i < team.size(); i++) {
            final BufferedImage image = ImageIO.read(new File(
                    "/Users/justinnewman/Documents/JMU_blank_HeroSide.png"));
            final BufferedImage img = ImageIO.read(
                    new URL(Team.getHeadShot(Team.getName(i))));
            Graphics g = image.getGraphics();
            g.setFont(g.getFont().deriveFont(40f));
            g.drawString("Current Statistics : ", 123, 820);
            String atrib = team.getPlayerAtribs(Team.getName(i));
            Color c = new Color(0, 0, 0);
            g.setColor(c);
            g.setFont(g.getFont().deriveFont(100f));
            g.drawImage(img, 660, 1369, 1040, 1879, 0, 0, img.getWidth(),
                    img.getHeight(), null);
            Color x = new Color(255, 255, 255);
            g.setColor(x);
            Color y = new Color(255, 255, 255);
            y = y.darker();
            y = y.darker();
            g.setColor(y);
            g.drawString(Team.getcurClass(Team.getName(i)), 1075, 1445);
            g.setColor(x);
            String hw = team.getPlayerHw(Team.getName(i));
            g.drawString(hw, 1000, 1725);
            g.setColor(c);
            g.setFont(g.getFont().deriveFont(140f));

            g.drawString("# " + Team.getJersyNum(Team.getName(i)), 685, 1900);
            g.setFont(g.getFont().deriveFont(40f));
            g.setColor(x);
            g.drawString(Team.getHomeTown(Team.getName(i)), 1600, 1725);
            g.setFont(g.getFont().deriveFont(90f));
            g.setColor(x);
            String p = Team.getPosition(Team.getName(i));
            if (p.length() > 1) {
                g.drawString(p, 2069, 1600);
            } else {
                g.drawString(p, 2130, 1600);
            }
            g.dispose();
            String a = String.format("Rendering Graphic... %30s\t%s",
                    Team.getName(i), atrib);
            System.out.println(a);
            if (ImageIO.write(image, "png",
                    new File("/Users/justinnewman/Documents/Graphics/Type2-"
                            + Team.getName(i) + ".png"))) {
                System.out.println("Export Successful!");
            }
            System.out.println("\n");

        }
    }

    public static void writeHero(Team team) throws MalformedURLException, IOException {
        for (int i = 0; i < team.size(); i++) {
            final BufferedImage temp = ImageIO.read(new File(
            "/Users/justinnewman/Documents/JMU_blank_HeroSide.png"));
            final BufferedImage img = ImageIO.read(
            new URL (Team.getHeadShot(Team.getName(i))));
            Graphics g = temp.getGraphics();
            String atrib = team.getPlayerAtribs(Team.getName(i));
            g.drawImage(img, 235, 77, 350, 497, null);
            g.setColor(new Color(255, 255, 255));
            g. setFont(g.getFont().deriveFont(50f));
            String na = Team.getName(i);
            if (na. length() > 17) {
            g. setFont(g.getFont().deriveFont(40f));
            g.drawString(na, 125, 635);
            } else {
            }
            g.drawString(na, 125, 635);
            g. setFont(g.getFont().deriveFont (40f));
            String j = Team.getJersyNum (Team.getName(i));
            g.drawString("#" + j + " |", 123, 700);
            int a1 = 99;
            try {
              a1 = Integer.parseInt(j.replace(" ", ""));
            } catch (Exception e) {
              a1 = 99;
            }
            if (a1 <= 9) {
                String p = Team.getPosition(Team.getName(i)) + "|";
            
                if (p.length() > 3) {
                  g.drawString(p, 230, 700);
                 } else {
                  g.drawString(p, 230, 700);
            }
         } else {
            String p = Team.getPosition (Team.getName(i)) + " ";
            if (p.length() > 3) {
                g.drawString(p, 235, 700);
            } else {
                g.drawString(p, 235, 700);
            }
         }
                g.drawString("Current Statistics:", 123, 820);
                g.drawString(Team.getHomeTown(Team.getName(i)), 123, 760);
                g.drawString(Team.getHeight(Team.getName(i)), 123, 900);
                g.drawString(Team.getWeight(Team.getName(i)), 223, 900);
                g.dispose();
                String a = String.format("* Rendering graphic... ☆ %30s\t%s",
                Team.getName(i), atrib);
                System.out.println(a);
                if (ImageIO.write(temp, "png",
                new File("/Users/justinnewman/Documents/Graphics/"
                + Team.getName(i) + ".png"))) {
                System.out.println("Congratulations Export Successful!");
                
            }
        }
    }

  public static void writeToday (Team team)
          throws MalformedURLException, IOException {
   // begins iteration through team size
      BufferedImage logo = null;
      for (int i = 0; i < team.size(); i++) {
      // creates a temporary space for the template image
      final BufferedImage temp = ImageIO.read(new File(
      "/Users/justinnewman/Documents/cs240/PA-playerStatistics/today.png"));
      try {
          logo = ImageIO.read(new URL(imageDriver.logoLink.getText()));
      } catch (Exception e) {
          logo = ImageIO.read(new URL("https://logodownload.org/wp-content/uploads/2015/05/espn-logo-4.png"));
      }
      // Creates a graphic object for the Buffered Image
      Graphics g = temp.getGraphics();
      // Parameters are (X, Y, Width, Height)
      g.fillRect(518, 1658, 2824, 314);
      // Sets the pen color to White and then
      Color white = new Color(255, 255, 255);
      // draws the white Rectangle.
      g.setColor(white);
      g.setColor(team.teamColor2);
   // Parameters are (x, y, Width, Height)
      g.fillRect(520, 1660, 2820, 310);
      // Sets the pen color to White and then
      // draws the white Rectangle.
      g.setColor(white);
      // Parameters are (X, Y, Width, Height)
      g.fillRect(538, 1638, 2784, 344);
      g.setColor(Team.teamColor);
      g.fillRect(540, 1640, 2780, 340);
      // Sets the pen color to Grey and then
      // draws the light Grey Rectangle.
      g.setColor(Color. LIGHT_GRAY);
      // Parameters are (X, Y, Width, Height)
      g.fillRect(540, 1640, 2780, 170);
      // Sets the pen color to lighter Grey and then
      // draws the light Grey Rectangle.
      g.setColor(Color.LIGHT_GRAY.darker());
      // Parameters are (X, Y, Width, Height)
      g.fillRect(540, 1640, 600, 340);
      // A string holds the player statistics
      String currentName = Team.getName(i);
      String atrib = team.getPlayerAtribs (currentName);
      g.setColor(white);
      // Draws the name
      // Sets the font size.
      g. setFont(g.getFont().deriveFont(90f));
      g.drawString(Team.getName(i), 1200, 1750);
      g. setFont(g.getFont().deriveFont(80f));
      if (currentName. length() < 15) {
      g.drawString(team.getTop(Team.getName(i)), 2000, 1750);
      } else {
      g.drawString(team.getTop (Team.getName(i)), 2500, 1750);
      }
      g.drawString(team.getBottom (Team.getName(i)), 1200, 1920);
      // draws the team logo
      g.drawImage(logo, 565, 1710, 560, 200, null);
      // Cleans up memory
      g.dispose();
      // Standard Output for Rendering Graphic
      String a = String.format("* Rendering graphic... $ %30s\t%s",
      Team.getName(i), atrib);
      System.out.println(a);
      if (ImageIO.write(temp, "png",
      new File("/Users/justinnewman/Documents/Graphics/"
      + Team.getName(i) + ".png"))) {
      System.out.println("Congratulations Export Successful!");
      }
      }
  }
  
  public static void writeESPN(Team team)
          throws MalformedURLException, IOException {
   // begins iteration through team size
      BufferedImage logo = null;
      for (int i = 0; i < team.size(); i++) {
      // creates a temporary space for the template image
      final BufferedImage temp = ImageIO.read(new File(
      "/Users/justinnewman/Documents/CS_159/PA-playerStatistics/graphicG/Translucent.png"));
      try {
          logo = ImageIO.read(new URL(imageDriver.logoLink.getText()));
      } catch (Exception e) {
          logo = ImageIO.read(new URL("https://logodownload.org/wp-content/uploads/2015/05/espn-logo-4.png"));
      }

      // Creates a graphic object for the Buffered Image
      Graphics g = temp.getGraphics();
      
      g.drawImage(ImageIO.read(new File(
              "/Users/justinnewman/Documents/CS_159/PA-playerStatistics/graphicG/logobox.png")), 565, 1710, 560, 200, null);
      // Sets the pen color to White and then
      g.setColor(team.teamColor2);
      // Parameters are (X, Y, Width, Height)
      g.setColor(Team.teamColor);
      // Parameters are (X, Y, Width, Height)
      g.fillRect(600, 1610, 2750, 120);
      // Sets the pen color to lighter Grey and then
      // draws the light Grey Rectangle.
      g.setColor(Color.LIGHT_GRAY.darker());
      // A string holds the player statistics
      String currentName = Team.getName(i);
      String atrib = team.getPlayerAtribs (currentName);
      // Draws the name
      // Sets the font size.
      g.setColor(Color.WHITE);
      g.setFont(g.getFont().deriveFont(70f));
      g.drawString(Team.getName(i), 1200, 1710);
      g. setFont(g.getFont().deriveFont(60f));
      if (currentName. length() < 15) {
      g.drawString(team.getTop(Team.getName(i)), 2000, 1710);
      } else {
      g.drawString(team.getTop (Team.getName(i)), 2500, 1710);
      }
      g.drawString(team.getBottom (Team.getName(i)), 1200, 1810);
      // draws the team logo
      BufferedImage box = ImageIO.read(new File(
              "/Users/justinnewman/Documents/CS_159/PA-playerStatistics/graphicG/logobox.png"));
      g.drawImage(box, 0, 0, null);
      
      // draws the team logo
      g.drawImage(logo, 565, 1710, 560, 200, null);
      // Cleans up memory
      g.dispose();
      // Standard Output for Rendering Graphic
      String a = String.format("* Rendering graphic... $ %30s\t%s",
      Team.getName(i), atrib);
      System.out.println(a);
      if (ImageIO.write(temp, "png",
      new File("/Users/justinnewman/Documents/Graphics/"
      + Team.getName(i) + ".png"))) {
      System.out.println("Congratulations Export Successful!");
      }
      }
  }

    public static void dataOutput(Team team) throws IOException {
        for (int i = 0; i < team.size(); i++) {
            // A string holds the player statistics
            String atrib = team.getPlayerAtribs(Team.getName(i));
            // Standard Output for Rendering Graphic
            String a = String.format("*==================$ %30s\t%s",
                    Team.getName(i), atrib);
            System.out.println(a);
            if (true) {
                System.out.println("");
            }
        }
    }
}
