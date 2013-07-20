package org.minecraft.server.motd;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.lang.String;
import java.util.Properties;
import java.io.*;

public class Main {
  private static final String PROGRAM_TITLE = "Minecraft Server MOTD";

  public static void main(String[] args) throws Exception {
    fancyMOTD();
  }

  private static void fancyMOTD() throws Exception {
    JFrame f = new JFrame();
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setTitle(PROGRAM_TITLE + " - Hit ENTER to save");
    f.add(display());
    f.pack();
    f.setSize(854, 502);
    f.show();
  }

  private static JComponent display() throws Exception {
    JComponent background = backgroundComponent();
    background.setBounds(0, 0, 1000, 600);
    JComponent text = textField();
    text.setBounds(214, 155, 3000, 30);
    JLayeredPane lp = new JLayeredPane();
    lp.add(background, new Integer(1));
    lp.add(text, new Integer(2));
    return lp;
  }

  private static JTextField textField() throws Exception {
    final JTextField tf = new JTextField();
    tf.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e) {
        try {
          setMOTD(tf.getText());
        } catch (Exception exc) {
          throw new RuntimeException(exc);
        }
      }
    });
    tf.setText(getMOTD());
    tf.setFont(minecraftFont());
    tf.setBorder(null);
    Color bgc = Color.BLACK;
    bgc = new Color(bgc.getRed(), bgc.getGreen(), bgc.getBlue(), 128);
    tf.setBackground(bgc);
    tf.setForeground(Color.GRAY);
    tf.setCaretColor(Color.WHITE);
    return tf;
  }

  private static Font minecraftFont() throws Exception {
    File fontFile = new File("./resources/Minecraftia.ttf");
    Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
    return font.deriveFont(Font.PLAIN, 15);
  }

  private static JComponent backgroundComponent() throws Exception {
    String filename = "./resources/multiplayer_screen.png";
    final Image background = ImageIO.read(new File(filename));
    return new JPanel() {
      public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(background, -57, -57, this);
      }
    };
  }

  private static File propertyFile;

  private static File getPropertyFile() {
    JFileChooser fc = new JFileChooser();
    fc.setFileFilter(new FileFilter() {
      public boolean accept(File f) {
        return f.isDirectory() || f.getAbsolutePath().endsWith(".properties");
      }

      public String getDescription() {
        return ".properties files";
      }
    });
    fc.setDialogTitle(PROGRAM_TITLE + " - Select server.properties file");
    fc.showOpenDialog(null);
    return propertyFile = fc.getSelectedFile();
  }

  private static String getMOTD() throws Exception {
    Properties properties = new Properties();
    properties.load(new FileInputStream(getPropertyFile()));
    return properties.getProperty("motd");
  }

  private static void setMOTD(String motd) throws Exception {
    Properties properties = new Properties();
    properties.load(new FileInputStream(propertyFile));
    properties.setProperty("motd", motd);
    properties.store(new FileOutputStream(propertyFile), "");
  }
}
