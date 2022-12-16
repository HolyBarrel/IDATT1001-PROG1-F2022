package edu.ntnu.idatt1001.cand10105.view;

import edu.ntnu.idatt1001.cand10105.model.ItemRegister;
import java.util.Scanner;

/**
 * Utility class with static methods that utilize the java.util.Scanner
 * to receive user input to the run terminal.
 * Goal: Get input from the user of the application
 *
 * @author 10105
 * @version 1.0
 * @since 0.3
 */
public class Scan {

  /**
   * Private constructor to ensure that no objects of the class Scan can be made by other classes.
   */
  private Scan() {}

  /**
   * Scans for user input and includes user guidance that a string is supposed to be entered.
   * Utilizes the util method {@link #scanString()} to create the scanner.
   *
   * @param parameterName is a string describing the type of input expected,
   *                      e.g. 'number', 'description'.
   * @return a string which the scanString method has returned.
   */
  protected static String scanForItemString(String parameterName) {
    System.out.println("Please enter a string for the item " + parameterName + ":");
    return scanString();
  }


  /**
   * Scans for user input by checking that the string is not equal to "" [enter] by creating a new
   * java.util.Scanner and fetching the next line inputted by the user.
   *
   * @return the string that the user has inputted
   */
  protected static String scanString() {
    String out = "";
    while (out.equals("")) {
      Scanner scan = new Scanner(System.in);
      if (scan.hasNextLine()) {
        out = scan.nextLine();
      }
      if (out.equals("")) {
        System.out.println("The string was blank, please try again.");
      }
    }
    return out;
  }

  /**
   * Scans for user input and includes user guidance that an integer is supposed to be entered.
   * Utilizes the util method {@link #scanInt()} to create the scanner.
   *
   * @param parameterName is a string describing the type of input expected,
   *                      e.g. 'amount in stock', 'price'.
   * @return an integer which the scanInt method has returned.
   */
  protected static int scanForItemInt(String parameterName) {
    System.out.println("Please enter a positive integer value for the item " + parameterName + ":");
    return scanInt();
  }

  /**
   * Scans for user input of an integer by checking that the inputted string could be parsed
   * to an integer value. Unless this is possible, the method will prompt the user to retry the
   * input. The java.util.Scanner is used to fetch the next line inputted by the user.
   *
   * @return the integer that is eventually inputted
   */
  protected static int scanInt() {
    int out = -1;
    String inStr = "";
    while (out < 0) {
      Scanner scan = new Scanner(System.in);
      if (scan.hasNextLine()) {
        inStr = scan.nextLine();
      }
      try {
        out = Integer.parseInt(inStr);
        if (out < 0) {
          System.out.println("The integer was negative, please enter a positive integer.");
        }
      } catch (NumberFormatException e) {
        System.out.println("The inputted string could not be parsed to an integer: "
            + e.getMessage());
      }
    }
    return out;
  }

  /**
   * Scans for user input and includes user guidance that a double is supposed to be entered.
   * Utilizes the util method {@link #scanDouble()} to create the scanner.
   *
   * @param parameterName is a string describing the type of input expected,
   *                      e.g. 'length', 'weight'.
   * @return a string which the scanString method has returned.
   */
  protected static double scanForItemDouble(String parameterName) {
    System.out.println("Please enter a positive double for the item " + parameterName + ":");
    return scanDouble();
  }

  /**
   * Scans for user input of a double by checking that the inputted string could be parsed
   * to a double value. Unless this is possible, the method will prompt the user to retry the
   * input. The java.util.Scanner is used to fetch the next line inputted by the user.
   *
   * @return the double that is eventually inputted
   */
  protected static double scanDouble() {
    double out = -1;
    String inStr = "";
    while (out < 0) {
      Scanner scan = new Scanner(System.in);
      if (scan.hasNextLine()) {
        inStr = scan.nextLine();
      }
      try {
        out = Double.parseDouble(inStr);
        if (out < 0) {
          System.out.println("The double was negative, please enter a positive double.");
        }
      } catch (NumberFormatException e) {
        System.out.println("The inputted string could not be parsed to a double: "
            + e.getMessage());
      }
    }
    return out;
  }

  /**
   * Scans for user input and includes user guidance that a string for an item number
   * which does not exist in the listed entries is supposed to be entered.
   * Utilizes the method {@link #scanForItemString(String)}.
   *
   * @param register is the register that is used to check whether already has the item number,
   *                 in case the user has to be prompted to input another string.
   * @return a string which the scanForItemString method has returned.
   */
  protected static String scanForItemNumber(ItemRegister register) {
    System.out.println("The string cannot be present in the already existing numbers: \n"
        + register.getNumbers());
    String number = scanForItemString("number");
    while (register.isNumberPresent(number)) {
      System.out.println("The string cannot be present in the already existing numbers: \n"
          + register.getNumbers() + " Please try again.");
      number = scanForItemString("number");
    }
    return number;
  }

  /**
   * Scans for user input and includes user guidance that an integer value for an item category
   * corresponding to a registered category is supposed to be entered.
   * Utilizes the method {@link #scanInt()}.
   *
   * @param register is the register that calls the getAllCategories-method.
   * @return a string which the scanForItemString method has returned.
   */
  protected static int scanForItemCategory(ItemRegister register) {
    System.out.println("The integer must be one of the existing categories: \n"
        + register.getAllCategories());
    int categoryInteger = scanInt();
    while (!register.mapsToCategory(categoryInteger)) {
      System.out.println("The integer must be one of the existing categories: \n"
          + register.getAllCategories() + " Please try again.");
      categoryInteger = scanInt();
    }
    return categoryInteger;
  }

  /**
   * Prints user guidance that specifies that these actions are available for the user to input:
   * input string 'r' (or 'R') to retry the attempted registration, or
   * input any other string to return to main menu.
   *
   * @return true if the user inquires a retry, and false otherwise.
   */
  protected static boolean promptRetry() {
    System.out.println("Please input 'r' to retry, or anything else to return to main menu.");
    String choice = scanString();
    return choice.equalsIgnoreCase("r");
  }

  /**
   * Prints user guidance to confirm the deletion of the given item from the register. This action
   * grants the user the following choices:
   * input 'y' (or 'Y') to confirm deletion of the item
   * input 'n' (or 'N') to abort the deletion of the item
   *
   * @param itemNumber is the number of item which is attempted to be deleted from the register.
   * @return true if the user inputs 'y' to confirm deletion, and false if the user aborts by
   *          inputting 'n'.
   */
  protected static boolean confirmDeletion(String itemNumber) {
    System.out.println("Please confirm deletion of the item: \n"
        + itemNumber + " \n Type 'y' to proceed, and 'n' to abort.");
    String confirm = scanString();
    while (!(confirm.equalsIgnoreCase("y")
        || confirm.equalsIgnoreCase("n"))) {
      confirm = scanString();
    }
    return confirm.equalsIgnoreCase("y");
  }

  /**
   * Prints user guidance to inform the user of the following option for input:
   * 'a' to sort the register in an ascending manner
   * any other string to sort the register in a descending manner.
   *
   * @return true if the user has inputted 'a' (or 'A'), false otherwise
   */
  protected static boolean promptAscending() {
    System.out.println("Please enter 'a' to sort by ascending order, or any other "
        + "symbol for descending:");
    String choice = scanString();
    return choice.equalsIgnoreCase("a");
  }
}
