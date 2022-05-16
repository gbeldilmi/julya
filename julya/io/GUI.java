package julya.io;

import java.awt.Component;
import javax.swing.JOptionPane;

public class GUI {
  public static void displayError(Component parent, Object message) {
    JOptionPane.showMessageDialog(parent, message, "Error", JOptionPane.ERROR_MESSAGE);
  }

  public static void displayInfo(Component parent, Object message) {
    JOptionPane.showMessageDialog(parent, message, "Information", JOptionPane.INFORMATION_MESSAGE);
  }

  public static void displayMessage(Component parent, Object message) {
    JOptionPane.showMessageDialog(parent, message, "", JOptionPane.PLAIN_MESSAGE);
  }

  public static void displayMessage(Component parent, String title, Object message) {
    JOptionPane.showMessageDialog(parent, message, title, JOptionPane.PLAIN_MESSAGE);
  }

  public static void displayWarning(Component parent, Object message) {
    JOptionPane.showMessageDialog(parent, message, "Warning", JOptionPane.WARNING_MESSAGE);
  }

  public static boolean getConfirm(Component parent, Object message) {
    return JOptionPane.showConfirmDialog(parent, message, "", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
  }

  public static int getInt(Component parent, Object message) {
    boolean c;
    int num;
    num = 0;
    do {
      try {
        num = Integer.parseInt(getString(parent, message));
        c = false;
      } catch (Exception error) {
        displayError(parent, error);
        c = true;
      }
    } while (c);
    return num;
  }

  public static int getInt(Component parent, Object message, int min) {
    int num;
    do {
      num = getInt(parent, message);
      if (num < min) {
        displayError(parent, "You have entered something lower than " + min);
      }
    } while (num < min);
    return num;
  }

  public static int getInt(Component parent, Object message, int min, int max) {
    int num;
    do {
      num = getInt(parent, message, min);
      if (num > max) {
        displayError(parent, "You have entered something higher than " + max);
      }
    } while (num > max);
    return num;
  }

  public static String getString(Component parent, Object message) {
    String input;
    input = JOptionPane.showInputDialog(parent, message, "", JOptionPane.QUESTION_MESSAGE);
    return input == null ? "" : input;
  }

}
