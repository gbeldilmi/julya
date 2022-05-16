package julya.io;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CLI {
  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public static boolean getConfirm() {
    String input;
    System.out.print("  [Y/n] >> ");
    input = scan();
    return input.isEmpty() ? true : input.equals("y");
  }

  public static int getInt() {
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
    System.out.print("\t>> ");
    return scan();
  }

  public static void pause() {
    System.out.print("\tPress ENTER to continue...");
    scan();
  }

  public static void printError(Object message) {
    printMessage("Error", message, ANSIEscapeCode.FOREGROUND_RED);
  }

  public static void printInfo(Object message) {
    printMessage("Info", message, ANSIEscapeCode.FOREGROUND_GREEN);
  }

  public static void printMessage(String title, Object message) {
    printMessage(title, message, "");
  }

  private static void printMessage(String title, Object message, ANSIEscapeCode escapeCode) {
    printMessage(title, message, escapeCode.toString());
  }

  private static void printMessage(String title, Object message, String escapeSeq) {
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
    printMessage("Warning", message, ANSIEscapeCode.FOREGROUND_YELLOW);
  }

  private static String scan() {
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
