package edu.ntnu.idatt1001.cand10105.view;

/**
 * MenuPrinter class to print all menus displayed throughout WMS.
 * Goal: print information relevant to the user
 *
 * @author 10105
 * @version 1.0
 * @since 0.3
 */
public class MenuPrinter {
  //Static variables for frequently used strings
  private static final String OPTION_MESSAGE = """
        ________Please input one of the following options to continue:___
        """;
  private static final String ENTER = "\nPlease enter an option below:";

  /**
   * Private constructor to ensure that no other classes can create objects of the MenuPrinter
   * class.
   */
  private MenuPrinter() {}

  /**
   * Prints the user's options for input when in the main menu.
   */
  protected static void displayMainMenu() {
    System.out.println("""
        ________Welcome to Warehouse Management System (W.M.S.)__________
        """);
    System.out.println(OPTION_MESSAGE);
    System.out.println(" '1'  > Print all of the registered items");
    System.out.println(" '2'  > Search for an item in the register");
    System.out.println(" '3'  > Add an item to the register");
    System.out.println(" '4'  > Alter amount of an item in stock");
    System.out.println(" '5'  > Delete an item from the register");
    System.out.println(" '6'  > Edit item discount, price or description");
    System.out.println(" '7'  > Print registered items in order");
    System.out.println(" '8'  > Quit the W.M.S. application");
    System.out.println(ENTER);
  }

  /**
   * Prints the user's option for input when in the search menu.
   */
  protected static void displaySearchMenu() {
    System.out.println(OPTION_MESSAGE);
    System.out.println(" '1'  > Search for item number");
    System.out.println(" '2'  > Search for item description");
    System.out.println(" '3'  > Search for item number and description");
    System.out.println(" '0'  > To return to the main menu");
    System.out.println(ENTER);
  }

  /**
   * Prints the user's options for input when the user alters the amount of an item in stock.
   */
  protected static void displayAlterAmountMenu() {
    System.out.println(OPTION_MESSAGE);
    System.out.println(" '1'  > Increase the amount of an item in stock");
    System.out.println(" '2'  > Decrease the amount of an item in stock");
    System.out.println(" '3'  > Set the amount of an item in stock");
    System.out.println(" '0'  > To return to the main menu");
    System.out.println(ENTER);
  }

  /**
   * Prints the user's options for input when editing the item with the itemNumber.
   *
   * @param itemNumber is the item number of the item selected by the user for editing.
   */
  protected static void displayEditOptions(String itemNumber) {
    System.out.println(OPTION_MESSAGE);
    System.out.println(" '1'  > Edit the discount of " + itemNumber);
    System.out.println(" '2'  > Edit the price of " + itemNumber);
    System.out.println(" '3'  > Edit the description of " + itemNumber);
    System.out.println(" '0'  > To return to main menu");
    System.out.println(ENTER);
  }

  /**
   * Prints the user's options for input in order to print a sorted version of the register.
   */
  protected static void displaySortOptions() {
    System.out.println(OPTION_MESSAGE);
    System.out.println(" '1'  > Print register sorted by item number");
    System.out.println(" '2'  > Print register sorted by discounted price");
    System.out.println(" '3'  > Print register sorted by item discount");
    System.out.println(" '4'  > Print register sorted by item amount in stock");
    System.out.println(" '0'  > To return to main menu");
    System.out.println(ENTER);
  }


}
