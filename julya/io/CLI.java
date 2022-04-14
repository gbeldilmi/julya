package julya.io;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import julya.utils.TextUtils;

public class CLI {
  public static void clearScreen() {
    /* Clear the terminal (Linux only). */
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
  public static boolean getConfirm() {
    /* Request a confirmation from the user by entering a character ('y' or 'n')
      in the terminal. If the input is not correct, return 'true' by default. */
    String input;
    System.out.print("  [Y/n] >> ");
    input = scan();
    return input.isEmpty() ? true : input.equals("y");
  }
  public static int getInt() {
    /* Ask the user to enter an integer in the terminal. If the input is not
      correct, the error is printed and the user is asked for another input. */
    boolean c;
    int num;
    num = 0;
    do {
      try {
        System.out.print("\t>> ");
        num = Integer.parseInt(scan());
        c = false;
      } catch (Exception error) {
        printError(error);
        c = true;
      }
    } while (c);
    return num;
  }
  public static int getInt(int min) {
    /* Ask the user to enter an integer bigger than 'min' in the terminal. If
      the input is not correct, the error is printed and the user is asked for
      another input. */
    int num;
    do {
      num = getInt();
      if (num < min) {
        printError("You have entered something lower than " + min);
      }
    } while (num < min);
    return num;
  }
  public static int getInt(int min, int max) {
    /* Ask the user to enter an integer bigger than 'min' and lower than 'max'
      in the terminal. If the input is not correct, the error is printed and
      the user is asked for another input. */
    int num;
    do {
      num = getInt(min);
      if (num > max) {
        printError("You have entered something higher than " + max);
      }
    } while (num > max);
    return num;
  }
  public static String getString() {
    /* Ask the user to enter a string in the terminal. It can be empty. */
    System.out.print("\t>> ");
    return scan();
  }
  public static void pause() {
    /* Wait until the user press ENTER in the terminal. */
    System.out.print("\tPress ENTER to continue...");
    scan();
  }
  public static void printError(Object message) {
    /* Print an error message in the terminal. */
    printMessage("Error", message, ANSIEscapeCode.FOREGROUND_RED);
  }
  public static void printInfo(Object message) {
    /* Print an information message in the terminal. */
    printMessage("Info", message, ANSIEscapeCode.FOREGROUND_GREEN);
  }
  public static void printMessage(String title, Object message) {
    printMessage(title, message, "");
  }
  private static void printMessage(String title, Object message, ANSIEscapeCode escapeCode) {
    printMessage(title, message, escapeCode.toString());
  }
  private static void printMessage(String title, Object message, String escapeSeq) {
    /* Print a message in the terminal with a specific title and format */
    StringBuilder messageBuilder;
    messageBuilder = new StringBuilder();
    messageBuilder.append(escapeSeq);
    messageBuilder.append(title);
    messageBuilder.append(ANSIEscapeCode.RESET);
    messageBuilder.append(": ");
    messageBuilder.append(message);
    System.out.println(messageBuilder.toString());
  }
  public static void printWarning(Object message) {
    /* Print a warning message in the terminal. */
    printMessage("Warning", message, ANSIEscapeCode.FOREGROUND_YELLOW);
  }
  private static String scan() {
    /* Read a string entered by the user in the terminal. It can be empty. */
    String input;
    input = "";
    try {
      input = new BufferedReader(new InputStreamReader(System.in)).readLine();
    } catch (Exception error) {
      printError(error);
    }
    return input;
  }
}
