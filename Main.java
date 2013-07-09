import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.lang.String;
import java.util.Properties;
import java.io.*;

public class Main {
  public static void main(String[] args) throws Exception {
    fancyMOTD();
  }

  private static void fancyMOTD() throws Exception {
    String filename = "./resources/multiplayer_screen.png";
    final Image background = ImageIO.read(new File(filename));

    JPanel p = new JPanel() {
      public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(background, -57, -57, this);
      }
    };

    JFrame f = new JFrame();
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setTitle("Minecraft Server MOTD");
    f.add(p);
    f.pack();
    f.setSize(854, 502);
    f.show();
  }

  private static void basicMOTD() throws Exception {
    File server = new File("/Users/nwolfe/Downloads/server.properties");
    Properties config = new Properties();
    config.load(new FileInputStream(server));

    String motd = config.getProperty("motd");
    motd = modifyMOTD(motd);

    config.setProperty("motd", motd);
    config.store(new FileOutputStream(server), "");
  }

  private static String modifyMOTD(String motd) {
    String modifiedMOTD = JOptionPane.showInputDialog("Enter the MOTD", motd);
    // TODO: Inject appropriate spacing into motd
    return modifiedMOTD;
  }
}
