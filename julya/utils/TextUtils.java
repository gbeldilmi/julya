package julya.utils;

public class TextUtils {
  public static String fitText(String text, int maxLength) {
    StringBuilder sb;
    String words[];
    int i, j, lineLength;
    if (text == null) {
      return null;
    }
    sb = new StringBuilder();
    words = text.split(" ");
    lineLength = 0;
    for (i = 0; i < words.length; i++) {
      if (words[i].length() > maxLength) {
        for (char c : words[i].toCharArray()) {
          if (lineLength + 2 >= maxLength) {
            sb.append(c + "-\n");
            lineLength = 0;
          } else {
            sb.append(c);
            lineLength++;
          }
        }
      } else {
        if (lineLength + words[i].length() < maxLength) {
          sb.append(words[i]);
          lineLength += words[i].length();
        } else {
          sb.append(nCharToString(maxLength - lineLength, ' ') + "\n" + words[i]);
          lineLength = words[i].length();
        }
      }
      if (lineLength != 0) {
        sb.append(" ");
        lineLength++;
      }
    }
    return sb.toString();
  }

  public static int getLineMaxLength(String text) {
    int i, c, max;
    for (i = c = max = 0; i < text.length(); i++) {
      if (text.charAt(i) == '\n' || i == text.length() - 1) {
        if (c > max) {
          max = c;
        }
        c = 0;
      } else {
        c++;
      }
    }
    return max;
  }

  public static int getNumberDigits(long i) {
    int n;
    if (i == 0) {
      n = 1;
    } else {
      for (n = 0; i != 0; i /= 10) {
        n++;
      }
    }
    return n;
  }

  public static String indentText(String text) {
    String result;
    char c;
    int i;
    result = "  ";
    for (i = 0; i < text.length(); i++) {
      c = text.charAt(i);
      result += c == '\n' ? "\n  " : c;
    }
    return result;
  }

  public static String nCharToString(int n, char c) {
    String result;
    for (result = ""; n > 0; n--) {
      result += c;
    }
    return result;
  }
}
