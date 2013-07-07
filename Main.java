import javax.swing.JOptionPane;
import java.lang.String;
import java.util.Properties;
import java.io.*;

public class Main {

  public static void main(String[] args) throws Exception {
    File server = new File("/Users/nwolfe/Downloads/server.properties");
    Properties config = new Properties();
    config.load(new FileInputStream(server));

    String motd = config.getProperty("motd");
    motd = modifyMOTD(motd);

    config.setProperty("motd", motd);
    config.store(new FileOutputStream(server), "");
  }

  public static String modifyMOTD(String motd) {
    String modifiedMOTD = JOptionPane.showInputDialog("Enter the MOTD", motd);
    return modifiedMOTD;
  }
}
