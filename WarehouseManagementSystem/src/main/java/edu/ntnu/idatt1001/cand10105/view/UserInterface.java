package edu.ntnu.idatt1001.cand10105.view;

import edu.ntnu.idatt1001.cand10105.model.Item;
import edu.ntnu.idatt1001.cand10105.model.ItemRegister;
import java.util.Objects;

/**
 * UserInterface class to handle the interactions of the user of the
 * App WMS [WarehouseManagementSystem].
 * Goal: interpret user input and act accordingly
 *
 * @author 10105
 * @version 1.0
 * @since 0.3
 */
public class UserInterface {
  private static ItemRegister itemRegister;

  //Static variables for frequently used strings
  private static final String RETURN_MESSAGE =
      "Please type '0' to go back to the main menu:";
  private static final String EMPTY_MESSAGE = "It seems that the register is empty, "
      + "please return \n and add some items before attempting to display it.";
  private static final String FAIL_MESSAGE = """
      The alteration failed with the message:
      """;
  private static final String AMOUNT_MESSAGE = """
       
      Current stock amount:""";

  //Static variables for the main menu
  private static final String PRINT_REGISTER = "1";
  private static final String SEARCH_FOR_ITEM = "2";
  private static final String ADD_ITEM = "3";
  private static final String ALTER_AMOUNT = "4";
  private static final String DELETE_ITEM = "5";
  private static final String EDIT_ITEM = "6";
  private static final String SORT_REGISTER = "7";

  //Static variables for the search menu
  private static final String NUMBER_SEARCH = "1";
  private static final String DESCRIPTION_SEARCH = "2";
  private static final String DUAL_SEARCH = "3";

  //Static variables for the alter menu
  private static final String INCREASE_AMOUNT = "1";
  private static final String DECREASE_AMOUNT = "2";
  private static final String SET_AMOUNT = "3";

  //Static variables for the edit menu
  private static final String EDIT_DISCOUNT = "1";
  private static final String EDIT_PRICE = "2";
  private static final String EDIT_DESCRIPTION = "3";

  //Static variables for the sort register menu
  private static final String SORT_BY_NUMBER = "1";
  private static final String SORT_BY_DISCOUNTED_PRICE = "2";
  private static final String SORT_BY_DISCOUNT = "3";
  private static final String SORT_BY_AMOUNT = "4";

  /**
   * Private constructor to ensure no construction objects of UserInterface.
   */
  private UserInterface() {}

  /**
   * Initializes default data for an array of items which is added to the register.
   */
  private static void registerDefaultItems() {
    try {
      itemRegister.addItem("AU1", "Large wall-planks with green tint.",
          100, "Woodland", 3.2, 2, 2.1, "Gray-green",
          200, 0, 4);
      itemRegister.addItem("AI1", "Large floor-laminate with stone appearance.",
          150, "Stoneland", 2.2, 3, 0.23, "Gray",
          20, 0, 1);
      itemRegister.addItem("BC2", "Small floor-laminate with grass coloring.",
          50, "Grassland", 0.2, 2, 0.2, "Green",
          2000, 20, 1);
      itemRegister.addItem("YB2", "Medium window-panes with yellow tinted "
              + "glass.", 150, "Stoneland", 10.2, 1, 2.03,
          "Black (frames)", 20, 20, 2);
      itemRegister.addItem("QB3", "Extremely large window-panes (bulletproof"
              + " grade A-certification).", 15000, "WWWindows",
          100.2, 10, 15.03, "White (frames)",
          2, 25, 2);
      itemRegister.addItem("PBQ5", "Small window-frames with doubly hinged "
              + "opening-mechanisms", 100, "WWWindows",
          1.2, 0.5, 1.03, "White (frames)",
          3, 30, 2);
      itemRegister.addItem("OC2", "Green door with small peek-hole",
          1002, "Hodor", 11.2, 0.8, 2.03, "Grass-green",
          17, 50, 3);
      itemRegister.addItem("OCF2", "Yellow door with small peek-hole",
          1000, "Hodor", 11.2, 0.9, 2, "Pale-yellow",
          20, 45, 3);
    } catch (IllegalArgumentException | NullPointerException e) {
      System.out.println("One of items could not be added to the register for the "
          + "following reason: " + e.getMessage());
    }
  }

  /**
   * Runs the main menu by printing the main menu options. Interprets the user input against a
   * switch-case to perform the user requested action.
   */
  private static void runStart() {
    MenuPrinter.displayMainMenu();
    boolean exit = false;
    while (!exit) {
    String choice = Scan.scanString();
      switch (choice) {
        case PRINT_REGISTER -> {
          printItemRegister();
          waitToReturn();
        }
        case SEARCH_FOR_ITEM -> searchRegister();
        case ADD_ITEM -> addItem();
        case ALTER_AMOUNT -> alterAmountInStock();
        case DELETE_ITEM -> deleteItem();
        case EDIT_ITEM -> editItem();
        case SORT_REGISTER -> sortRegister();
        default -> exit = true;
      }
    }
    System.out.println("Exiting Warehouse Management System (W.M.S.)...");
    System.exit(0);
  }

  /**
   * Launches the application by initializing default data for an array of items in the register
   * and initiating the {@link #runStart()} method that runs the main loop of the main menu in
   * the app.
   */
  public static void launch() {
    itemRegister = new ItemRegister();
    registerDefaultItems();
    runStart();
  }

  /**
   * Prints the contents of the register if there are items in it, and prints that it is empty
   * otherwise.
   */
  private static void printItemRegister() {
    if (itemRegister.isEmpty()) {
      System.out.println(EMPTY_MESSAGE);
    } else {
      System.out.println(itemRegister);
    }
  }

  /**
   * Prints a message to let the user know that the application is returning to the main menu.
   * Runs the {@link #runStart()} method to initiate the main menu.
   */
  private static void returnToMainMenu() {
    System.out.println("Returning to main menu...");
    runStart();
  }

  /**
   * Polls the user to input the string "0" to return to main menu. If the user inputs another
   * string, the instruction is re-printed.
   */
  private static void waitToReturn() {
    System.out.println(RETURN_MESSAGE);
    try {
      String input = Scan.scanString();
      assert input != null;
      if (input.equals("0")) {
        returnToMainMenu();
      } else {
        waitToReturn();
      }
    } catch (IllegalArgumentException e) {
      System.out.println("The scanner failed to get the input as a string.");
      waitToReturn();
    }
  }

  /**
   * Help method utilized in the {@link #searchRegister()} to guide the user on how to perform a
   * search for an item in the register by the item number.
   *
   * @return false if the user wants to retry to search and true if the search was successful or if
   *         the user wishes to go the main menu.
   */
  private static boolean numberSearch() {
    System.out.println("Item-numbers in the register: " + itemRegister.getNumbers());
    System.out.println("Please enter a string to search for an item number:");
    String searchNumber = Scan.scanString();
    if (itemRegister.searchForNumber(searchNumber) == null) {
      System.out.println("The register did not contain any match for the search: \n"
          + searchNumber);
      if (Scan.promptRetry()) {
        return false;
      } else {
        returnToMainMenu();
        return true;
      }
    } else {
      System.out.println("Search for '" + searchNumber + "' resulted in: "
          + itemRegister.searchForNumber(searchNumber));
      waitToReturn();
      return true;
    }
  }

  /**
   * Help method utilized in the {@link #searchRegister()} to guide the user on how to perform a
   * search for an item in the register by the item description.
   *
   * @return false if the user wants to retry to search and true if the search was successful or if
   *         the user wishes to go the main menu.
   */
  private static boolean descriptionSearch() {
    System.out.println("Please enter a string to search for an item description:");
    String searchDescription = Scan.scanString();
    if (itemRegister.searchForDescription(searchDescription) == null) {
      System.out.println("The register did not contain any match for the search: \n"
          + searchDescription);
      if (Scan.promptRetry()) {
        return false;
      } else {
        returnToMainMenu();
        return true;
      }
    } else {
      System.out.println("The search resulted in: \n"
          + itemRegister.searchForDescription(searchDescription));
      waitToReturn();
      return true;
    }
  }

  /**
   * Help method utilized in the {@link #searchRegister()} to guide the user on how to perform a
   * search for an item in the register by the item number and description.
   *
   * @return false if the user wants to retry to search and true if the search was successful or if
   *         the user wishes to go the main menu.
   */
  private static boolean dualSearch() {
    System.out.println("Item-numbers in the register: " + itemRegister.getNumbers());
    System.out.println("Please enter a string to search for an item number:");
    String searchNumber = Scan.scanString();
    System.out.println("Please enter a string to search for an item description:");
    String searchDescription = Scan.scanString();
    if (itemRegister.searchForNumberAndDescription(searchNumber,
        searchDescription) == null) {
      System.out.println("The register did not contain any match for search: ");
      System.out.println("Item number: " + searchNumber);
      System.out.println("Description: " + searchDescription);
      if (Scan.promptRetry()) {
        return false;
      } else {
        returnToMainMenu();
        return true;
      }
    } else {
      System.out.println("The search resulted in the item: \n"
          + itemRegister.searchForNumberAndDescription(searchNumber,
          searchDescription));
      waitToReturn();
      return true;
    }
  }

  /**
   * Utilizes the Printer.displaySearchMenu() to print the search menu.
   * Thereafter, checks the user input according to the options of the search menu with a
   * switch-case. Each case prompts the user for additional input to satisfy the specified search.
   */
  private static void searchRegister() {
    boolean exit = false;
    boolean scanAgain = true;
    String searchType = "";
    while (!exit) {
      if (scanAgain) {
        MenuPrinter.displaySearchMenu();
        searchType = Scan.scanString();
      }
      switch (Objects.requireNonNull(searchType)) {

        case NUMBER_SEARCH -> scanAgain = numberSearch();

        case DESCRIPTION_SEARCH -> scanAgain = descriptionSearch();

        case DUAL_SEARCH -> scanAgain = dualSearch();

        default -> exit = true;
      }
    }
    returnToMainMenu();
  }

  /**
   * Takes all the inputs needed for an item to be registered to the item register from user inputs
   * consecutively. The user is prompted for which type of data that should be entered. The method
   * throws exceptions if the item could not in fact be inputted into the register due to illegal
   * input.
   *
   * @throws IllegalArgumentException if any of the String inputs are blank, or the number inputs
   *                                  (either integer or double) are less than zero.
   *                                  Will also throw this exception if the item which is attempted
   *                                  to add does already exist in the register.
   * @throws NullPointerException if the given integer was outside the set of integers corresponding
   *                              to actual enum values in Category.
   */
  private static void addItemFromInput() throws IllegalArgumentException, NullPointerException {
    String number = Scan.scanForItemNumber(itemRegister);
    String description = Scan.scanForItemString("description");
    int price = Scan.scanForItemInt("price");
    String brandName = Scan.scanForItemString("brand name");
    double weight = Scan.scanForItemDouble("weight");
    double length = Scan.scanForItemDouble("length");
    double height = Scan.scanForItemDouble("height");
    String color = Scan.scanForItemString("color");
    int amountInStock = Scan.scanForItemInt("amount in stock");
    System.out.println("Discounts represent a percent (the input integer must be in the "
        + "interval [0,100]). ");
    int discount = Scan.scanForItemInt("discount");
    System.out.println("Please enter an integer corresponding to one of the following "
        + "item categories:");
    int category = Scan.scanForItemCategory(itemRegister);
    itemRegister.addItem(number, description, price, brandName, weight,
        length, height, color, amountInStock, discount, category);
  }

  /**
   * Utilizes the method {@link #addItemFromInput()} to fetch user input for a new item to the
   * register. This method is tried, and if the inputs fail, the message is printed and the user
   * can retry registration of an item.
   */
  private static void addItem() {
    try {
      addItemFromInput();
      System.out.println("The following item was registered: " + itemRegister.getLastItem());
      waitToReturn();
    } catch (IllegalArgumentException | NullPointerException e) {
      System.out.println("The inputted data could not be entered as an item in the register,\n"
          + "for the reason: " + e.getMessage());
      if (Scan.promptRetry()) {
        addItem();
      } else {
        returnToMainMenu();
      }
    }
  }

  /**
   * Utilizes the search method for retrieving an item with a corresponding item number to that of
   * the string input issued by the user. This is done repeatedly in a while loop
   * until the input number is matched with an item number in the register. If the user inputs '0',
   * the user returns to the main menu.
   *
   * @return the item if it is found or null if the runStart method is initiated instead
   */
  private static Item getItemByNumber() {
    Item foundItem = null;
    while (foundItem == null) {
      System.out.println("-> or enter '0' to return to main menu");
      String searchNumber = Scan.scanString();
      foundItem = itemRegister.searchForNumber(searchNumber);
      if (foundItem == null) {
        System.out.println("An item with number '" + searchNumber + "' could not be "
            + "found in the register, please try again: ");
      }
      if (Objects.equals(searchNumber, "0")) {
        returnToMainMenu();
      }
    }
    return foundItem;
  }

  /**
   * Handles the computation logic of the alteration chosen by the user. If the user wishes to
   * increase the amount in stock, the boolean 'increase' is true and the amount is increased by
   * the integer value 'change'. To decrease the amount by 'change' the 'increase' is false.
   * After the new amount is computed based on the configuration of the parameters, the amount in
   * stock of the 'foundItem' is set to the newAmount.
   *
   * @param itemNumber is the item number for the item which is subjected to the change
   *                   issued by user input
   * @param increase is a boolean signifying whether the change is to be added or subtracted to the
   *                 original amount in stock.
   * @param change is an integer of the change in amount in stock of the item
   * @throws IllegalArgumentException is thrown if the there an attempted to set the item amount to
   *                                  a negative integer value
   */
  private static void alterItemAmount(String itemNumber, boolean increase, int change) throws
      IllegalArgumentException {
    Item foundItem = itemRegister.searchForNumber(itemNumber);
    int newAmount = foundItem.getAmountInStock();
    if (increase) {
      newAmount += change;
    } else {
      newAmount -= change;
    }
    itemRegister.setItemAmountInStock(foundItem.getNumber(), newAmount);
    System.out.println("The new amount was updated to: " + newAmount);
  }

  /**
   * Increases the amount of an item found by user input of an item number in the register. Then
   * alters the amount with the help method {@link #alterItemAmount(String, boolean, int)} with the
   * parameter 'increase' set to true to communicate that the user inputted integer is supposed to
   * be added to the amount in stock for the given item.
   *
   * @return false if the user has inputted an integer resulting in a negative amount, and true
   *          if the user wishes to return to main menu after a failed attempt to alter the amount
   *          or if the user has altered the amount successfully.
   */
  private static boolean increaseAmount() {
    System.out.println("Please enter one of the following item-numbers to increment:"
        + "\n " + itemRegister.getNumbers());
    Item foundItem = getItemByNumber();
    System.out.println("Please enter a positive integer value for the increase"
        + " in items of type: " + foundItem.getNumber() + AMOUNT_MESSAGE
        + foundItem.getAmountInStock());
    int change = Scan.scanInt();
    try {
      alterItemAmount(foundItem.getNumber(), true, change);
      waitToReturn();
    } catch (IllegalArgumentException e) {
      System.out.println(FAIL_MESSAGE + e.getMessage());
      if (Scan.promptRetry()) {
        return false;
      } else {
        returnToMainMenu();
      }
    }
    return true;
  }

  /**
   * Decreases the amount of an item found by user input of an item number in the register. Alters
   * the amount with the help method {@link #alterItemAmount(String, boolean, int)} with the
   * parameter 'increase' set to true to communicate that the user inputted integer is supposed to
   * be added to the amount in stock for the given item.
   *
   * @return false if the user has inputted an integer resulting in a negative amount, and true
   *          if the user wishes to return to main menu after a failed attempt to alter the amount
   *          or if the user has altered the amount successfully.
   */
  private static boolean decreaseAmount() {
    System.out.println("Please enter one of the following item-numbers to decrement:"
        + "\n " + itemRegister.getNumbers());
    Item foundItem = getItemByNumber();
    System.out.println("Please enter a positive integer value for the decrease"
        + " in items of type: " + foundItem.getNumber() + AMOUNT_MESSAGE
        + foundItem.getAmountInStock());
    int change = Scan.scanInt();
    try {
      alterItemAmount(foundItem.getNumber(), false, change);
      waitToReturn();
    } catch (IllegalArgumentException e) {
      System.out.println(FAIL_MESSAGE + e.getMessage());
      if (Scan.promptRetry()) {
        return false;
      } else {
        returnToMainMenu();
      }
    }
    return true;
  }

  /**
   * Sets the amount of an item found by user input of an item number in the register. Then
   * alters the amount by setting the item's amount with the item register set method.
   *
   * @return false if the user has inputted an integer resulting in a negative amount, and true
   *          if the user wishes to return to main menu after a failed attempt to alter the amount
   *          or if the user has altered the amount successfully.
   */
  private static boolean setAmount() {
    System.out.println("Please enter one of the following item-numbers to"
        + " set amount in stock: \n " + itemRegister.getNumbers());
    Item foundItem = getItemByNumber();
    System.out.println("Please enter a positive integer value for the new amount"
        + " of items of type: " + foundItem.getNumber() + AMOUNT_MESSAGE
        + foundItem.getAmountInStock());
    int newAmount = Scan.scanInt();
    try {
      itemRegister.setItemAmountInStock(foundItem.getNumber(), newAmount);
      System.out.println("Updated stock amount to: " + newAmount);
      waitToReturn();
    } catch (IllegalArgumentException e) {
      System.out.println(FAIL_MESSAGE + e.getMessage());
      if (Scan.promptRetry()) {
        return false;
      } else {
        returnToMainMenu();
      }
    }
    return true;
  }

  /**
   * Prints the menu for altering the amount of an item in stock. Thereafter, utilizes a
   * switch case to interpret user input to choose what action to proceed with.
   */
  private static void alterAmountInStock() {
    boolean exit = false;
    boolean scanAgain = true;
    String option = "";
    while (!exit) {
      if (scanAgain) {
        MenuPrinter.displayAlterAmountMenu();
        option = Scan.scanString();
      }
      switch (Objects.requireNonNull(option)) {

        case INCREASE_AMOUNT -> scanAgain = increaseAmount();

        case DECREASE_AMOUNT -> scanAgain = decreaseAmount();

        case SET_AMOUNT -> scanAgain = setAmount();

        default -> exit = true;
      }
    }
    returnToMainMenu();
  }

  /**
   * Prints the item register as well as a list of all the item numbers present to enable the user 
   * to choose which item to delete from the register. After the item number which must be present
   * in the register is chosen by the user, the user is prompted to confirm the action by: 
   * inputting the string 'y' (or 'Y') to confirm
   * or inputting the string 'n' (or 'N') to abort the deletion
   */
  private static void deleteItem() {
    printItemRegister();
    System.out.println("Please enter the item number of the item you want to delete.\n"
        + "Item numbers in system: " + itemRegister.getNumbers());
    Item foundItem = getItemByNumber();
    boolean confirmed = Scan.confirmDeletion(foundItem.getNumber());
    if (confirmed) {
      itemRegister.deleteItem(foundItem.getNumber());
      System.out.println("This item was deleted:\n" + foundItem);
    } else {
      System.out.println("The started deletion was aborted before it was confirmed.");
    }
    waitToReturn();
  }

  /**
   * Help method that prints directions to the user to edit the discount for the given item in
   * the register by referencing the item number of the parameter which maps to the actual item in
   * the register. The item foundItem is a deep copy to hinder the UserInterface from handling
   * actual items in the register.
   *
   * @param foundItem holds the information on the previous item in the register.
   *                  This is used to access which item is supposed to be mutated by the set method
   *                  of the register
   * @return false if the user wants to retry the edit, and true otherwise or if the edit was
   *          successful.
   */
  private static boolean editDiscount(Item foundItem) {
    System.out.println("Please enter an integer for the item discount,"
        + " in the interval [0,100]");
    int prevDiscount = foundItem.getDiscount();
    System.out.println("Current discount: " + prevDiscount + "%");
    int newDiscount = Scan.scanInt();
    try {
      itemRegister.setItemDiscount(foundItem.getNumber(), newDiscount);
      System.out.println("Updated " + foundItem.getNumber() + "-discount from "
          + prevDiscount + "% to " + newDiscount + "%");
    } catch (IllegalArgumentException e) {
      System.out.println("The discount could not be updated due to: " + e.getMessage());
      if (Scan.promptRetry()) {
        return false;
      }
    }
    waitToReturn();
    return true;
  }

  /**
   * Help method that prints directions to the user to edit the price for the given item in the
   * register by referencing the item number of the parameter which maps to the actual item in the
   * register. The item foundItem is a deep copy to hinder the UserInterface from handling actual
   * items in the register.
   *
   * @param foundItem holds the information on the previous item in the register.
   *                  This is used to access which item is supposed to be mutated by the set method
   *                  of the register.
   * @return false if the user wants to retry the edit, and true otherwise or if the edit was
   *          successful.
   */
  private static boolean editPrice(Item foundItem) {
    System.out.println("Please enter a positive integer for the item price");
    int prevPrice = foundItem.getPrice();
    System.out.println("Current price: " + prevPrice);
    int newPrice = Scan.scanInt();
    try {
      itemRegister.setItemPrice(foundItem.getNumber(), newPrice);
      System.out.println("Updated " + foundItem.getNumber() + "-price from "
          + prevPrice + " to " + newPrice);
    } catch (IllegalArgumentException e) {
      System.out.println("The price could not be updated due to: " + e.getMessage());
      if (Scan.promptRetry()) {
        return false;
      }
    }
    waitToReturn();
    return true;
  }

  /**
   * Help method that prints directions to the user to edit the description for the given item in
   * the register by referencing the item number of the parameter which maps to the actual item in
   * the register. The item foundItem is a deep copy to hinder the UserInterface from
   * handling actual items in the register.
   *
   * @param foundItem holds the information on the previous item in the register.
   *                  This is used to access which item is supposed to be mutated by the set method
   *                  of the register.
   * @return false if the user wants to retry the edit, and true otherwise or if the edit was
   *          successful.
   */
  private static boolean editDescription(Item foundItem) {
    System.out.println("Please enter a string for the item description");
    System.out.println("Current description: " + foundItem.getDescription());
    String newDescription = Scan.scanString();
    try {
      itemRegister.setItemDescription(foundItem.getNumber(), newDescription);
      System.out.println("Updated the item-description to " + newDescription);
    } catch (IllegalArgumentException e) {
      System.out.println("The description could not be updated due to: " + e.getMessage());
      if (Scan.promptRetry()) {
        return false;
      }
    }
    waitToReturn();
    return true;
  }

  /**
   * Prints the menu to guide the user of the options associated with editing an item of 
   * the register. Thereafter, a switch-case interprets the user input against the menu. Handles
   * the inputs from the user to perform the action chosen.
   */
  private static void editItem() {
    printItemRegister();
    System.out.println("Please enter the item number of the item you want to edit.\n"
        + "Item numbers in system: " + itemRegister.getNumbers());
    Item foundItem = getItemByNumber();
    boolean exit = false;
    boolean scanAgain = true;
    String choice = "";
    while (!exit) {
      if (scanAgain) {
        MenuPrinter.displayEditOptions(foundItem.getNumber());
        choice = Scan.scanString();
      }
      switch (choice) {

        case EDIT_DISCOUNT -> scanAgain = editDiscount(foundItem);

        case EDIT_PRICE -> scanAgain = editPrice(foundItem);

        case EDIT_DESCRIPTION -> scanAgain = editDescription(foundItem);

        default -> exit = true;
      }
    }
    returnToMainMenu();
  }

  /**
   * Prints the menu associated with the options the user has to print a sorted version of the
   * register. Prompts the user if it wants the chosen sort to be either ascending or descending.
   */
  private static void sortRegister() {
    MenuPrinter.displaySortOptions();
    String choice = Scan.scanString();
    boolean asc = true;
    if (!choice.equals("0")) {
      asc = Scan.promptAscending();
    }
    switch (choice) {
      case SORT_BY_NUMBER -> System.out.println(itemRegister.printSortedByNumber(asc));

      case SORT_BY_DISCOUNTED_PRICE ->
          System.out.println(itemRegister.printSortedByDiscountedPrice(asc));

      case SORT_BY_DISCOUNT -> System.out.println(itemRegister.printSortedByDiscount(asc));

      case SORT_BY_AMOUNT -> System.out.println(itemRegister.printSortedByAmount(asc));

      default -> returnToMainMenu();
    }
    if (!choice.equals("0")) {
      waitToReturn();
    }
  }
}
