package edu.ntnu.idatt1001.cand10105.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 * Class describing an Item Register with information on all Items sold by Smart Housing AS.
 * Goal: act as a model for an item register
 *
 * @author 10105
 * @version 1.0
 * @since 0.2
 */
public class ItemRegister {
  private final ArrayList<Item> itemRegisterList;

  /**
   * Constructs an object of the class ItemRegister which has an empty arraylist that
   * holds objects of the class 'Item'.
   */
  public ItemRegister() {
    this.itemRegisterList = new ArrayList<>();
  }

  /**
   * Help method to return a stream of all the items (deep-copied versions) in the register.
   * This method is used to ensure that none of the actual items in the register are returned to
   * other classes directly.
   *
   * @return a stream of deep-copies of all the items in the register
   */
  private Stream<Item> deepCopy() {
    return itemRegisterList.stream().map(Item::new);
  }

  /**
   * Returns the first found item in the register with a description that contains the
   * searchString parameter. Utilizes the deep copy constructor to ensure that the item itself is
   * not returned to other classes that can access the register.
   *
   * @param searchString is a String which is checked if is contained in any item description
   *                     in the register.
   *
   * @return the first Item that is found based on the search parameter, or 'null' if no items
   *         in the register was found.
   */
  public Item searchForDescription(String searchString) {
    return deepCopy()
        .filter(item -> item.getDescription()
            .contains(searchString.trim()))
        .findAny()
        .orElse(null);
  }

  /**
   * Returns a deep copy of the first found item in the register with a number that is equal to the
   * itemNumber string parameter. This method returns a deep copy in order to safely return an item
   * to classes as 'Interface'
   *
   * @param itemNumber is a String which is checked if is equal to any item number
   *                     in the register.
   *
   * @return the first item that is found based on the search parameter, or 'null' if no items
   *         in the register was found.
   *
   */
  public Item searchForNumber(String itemNumber) {
    return deepCopy()
        .filter(item -> item.getNumber()
            .equals(itemNumber.trim()))
        .findAny()
        .orElse(null);
  }

  /**
   * Utilizes the methods {@link ItemRegister#searchForNumber(String)} and
   * {@link ItemRegister#searchForDescription(String)} to return the item in the register which has
   * a number or description containing the string parameters respectively.
   * This method prioritizes the search for an item number. This means that if the
   * searchNumber string is contained in that of an item and the searchDescription is contained in
   * another item description in the register, the item which had a
   * matching number would be returned. In practice,
   * {@link ItemRegister#searchForDescription(String)} will only be executed if the
   * {@link ItemRegister#searchForNumber(String)} does not find any item in the register.
   *
   * @param searchNumber is a String which is checked if matches that of any item number
   *                     in the register.
   * @param searchDescription is a String which is checked if matches that of any item
   *                          description in the register.
   *
   * @return the Item that is found based on the search parameters, or 'null' if no items
   *         in the register was found
   */
  public Item searchForNumberAndDescription(String searchNumber, String searchDescription) {
    Item targetItem = searchForNumber(searchNumber);
    if (targetItem == null) {
      targetItem = searchForDescription(searchDescription);
    }
    return targetItem;
  }

  /**
   * Returns a boolean to represent if the item is present in the current register. Utilizes
   * the {@link Item#equals(Object)} method of the item to check if there are any objects in
   * the register which has the same number as the targetItem.
   *
   * @param targetItem is the item which is to be checked if is in the register.
   * @return true if the item is present in the register, and false otherwise.
   */
  private boolean isItemPresent(Item targetItem) {
    return itemRegisterList.contains(targetItem);
  }

  /**
   * Help method that verifies that the targetItem is in fact not in the current register.
   *
   * @param targetItem is the item to be checked for its absence.
   * @throws IllegalArgumentException if the item is in fact present in the register.
   */
  private void verifyItemAbsence(Item targetItem) throws IllegalArgumentException {
    if (isItemPresent(targetItem)) {
      throw new IllegalArgumentException("The item was already in the register and could not be "
          + "added once more, please try again.");
    }
  }

  /**
   * Adds a new item to the current item register based on all the required parameters to create an
   * item. This method utilizes the default constructor of the Item-class.
   *
   * @param number is a combination of letters and integers in a String which represents the item.
   * @param description is a String for describing the item.
   * @param price is an integer value for the price of the item.
   * @param brandName is a String with brand name which offers the item.
   * @param weight is a double value representing the weight of the item.
   * @param length is a double value representing the length of the item.
   * @param height is a double value representing the height of the item
   * @param color is a String with the color of the item.
   * @param amountInStock is an integer number describing the amount of the Item which is remaining
   *                      in stock.
   * @param categoryNumber is an integer value representing the corresponding enum value presented
   *                       by the enum Category. The correlating enum is assigned to the Item.
   * @throws IllegalArgumentException if any of the String inputs are blank, or the number inputs
   *                                  (either integer or double) are less than zero.
   *                                  Will also throw this exception if the item which is attempted
   *                                  to add does already exist in the register.
   * @throws NullPointerException if the given integer was outside the set of integers corresponding
   *                              to actual enum values in Category.
   */
  public void addItem(String number, String description, int price, String brandName,
      double weight, double length, double height, String color, int amountInStock,
      int discount, int categoryNumber)
      throws IllegalArgumentException, NullPointerException {
    Item addItem = new Item(number, description, price, brandName, weight, length, height,
        color, amountInStock, discount, categoryNumber);
    verifyItemAbsence(addItem);
    itemRegisterList.add(addItem);
  }

  /**
   * Method required to add some default info to the register. This is done by adding all elements
   * of an arraylist of Items if their numbers are not already present in the register. If there
   * are items that are already present, the method throws an IllegalArgumentException.
   *
   * @param itemList is the arraylist of items of which all items are added to the register.
   *
   * @throws IllegalArgumentException if any of the items in the arraylist has an item number that
   *                                  is already present in the register.
   */
  public void addMultipleItems(ArrayList<Item> itemList) throws IllegalArgumentException {
    boolean isAlreadyInRegister = itemList.stream().anyMatch(itemRegisterList::contains);
    if (!isAlreadyInRegister) {
      itemList.forEach(item -> addItem(item.getNumber(), item.getDescription(), item.getPrice(),
          item.getBrandName(), item.getWeight(), item.getLength(), item.getHeight(),
          item.getColor(), item.getAmountInStock(), item.getDiscount(),
          item.getCategory().getNumber()));

    } else {
      throw new IllegalArgumentException("At least one item in the list of items which was "
          + "supposed to be added was already in the register, please try again.");
    }
  }

  /**
   * Help method used to return an actual item in the register with the given item number. This
   * method is just used by the methods of the register which is responsible for mutating the items
   * within the register.
   *
   * @param itemNumber is the item number string which is checked if is equal to any of the items
   *                   in the register.
   * @return the item with the matched item number is found or null if no items were found in the
   *          register
   */
  private Item internalSearch(String itemNumber) {
    return itemRegisterList.stream()
        .filter(item -> item.getNumber()
            .equals(itemNumber.trim()))
        .findAny()
        .orElse(null);
  }

  /**
   * Help method that verifies that the targetItem is in fact in the current register.
   *
   * @param targetItem is the item to be checked for its presence.
   * @throws IllegalArgumentException if the item is in fact not present in the register.
   */
  private void verifyItemPresence(Item targetItem) throws IllegalArgumentException {
    if (!isItemPresent(targetItem)) {
      throw new IllegalArgumentException("The item was not in the register, please try again.");
    }
  }

  /**
   * Deletes an item from the current register if the item is in the register.
   *
   * @param itemNumber is the string item number for the item which is to be
   *                   removed from the register.
   * @throws IllegalArgumentException if the item is not present in the register.
   */
  public void deleteItem(String itemNumber) throws IllegalArgumentException {
    Item targetItem = internalSearch(itemNumber);
    verifyItemPresence(targetItem);
    itemRegisterList.remove(targetItem);
  }

  /**
   * Checks if the register is empty by utilizing the isEmpty method for an arraylist.
   *
   * @return true if the register does not contain any items, and false if some item(s) is present.
   */
  public boolean isEmpty() {
    return itemRegisterList.isEmpty();
  }

  /**
   * Retrieves a string of a list containing all the item numbers in the register.
   *
   * @return a string with all item numbers in the register.
   */
  public String getNumbers() {
    if (itemRegisterList.isEmpty()) {
      return "No registered items in the register.";
    }
    return itemRegisterList.stream().map(Item::getNumber).toList().toString();
  }

  /**
   * Checks whether the string itemNumber is present in the current register.
   *
   * @param itemNumber is the string to be checked
   * @return true if an item with the 'itemNumber' string as it's item number is present in the
   *          register and false if there are no items with that itemNumber
   */
  public boolean isNumberPresent(String itemNumber) {
    return itemRegisterList.stream().anyMatch(item -> item.getNumber().equals(itemNumber));
  }

  /**
   * Returns a string with the information on the item at the last index position of the register.
   *
   * @return a string of the last item in the register.
   */
  public String getLastItem() {
    if (itemRegisterList.isEmpty()) {
      return "No registered items in the register.";
    }
    return itemRegisterList.get(itemRegisterList.size() - 1).toString();
  }

  /**
   * Returns all the categories that has been registered as a given Category value of the
   * enum Category.
   *
   * @return a string of a list containing all the registered categories (information about both
   *          a category's integer number and the name of the category e.g. 'DOORS').
   */
  public String getAllCategories() {
    return Arrays.stream(Category.values())
        .map(category -> "(" + category.getNumber() + ")" + category).toList().toString();
  }

  /**
   * Sets the item (found by item number) stock amount to the parameter 'newAmount'.
   *
   * @param itemNumber is the item number for the item that is mutated.
   * @param newAmount is the new amount in stock for the item if found.
   * @throws IllegalArgumentException if the item is not present in the register or if the amount
   *                                   was a negative integer
   */
  public void setItemAmountInStock(String itemNumber, int newAmount)
      throws IllegalArgumentException {
    Item item = internalSearch(itemNumber);
    verifyItemPresence(item);
    item.setAmountInStock(newAmount);
  }

  /**
   * Sets the item (found by item number) discount to the parameter 'newDiscount'.
   *
   * @param itemNumber is the item number for the item that is mutated.
   * @param newDiscount is the new discount for the item if found.
   * @throws IllegalArgumentException if the item is not present in the register or if the
   *                                  newDiscount is outside the interval [0,100]
   */
  public void setItemDiscount(String itemNumber, int newDiscount) throws IllegalArgumentException {
    Item item = internalSearch(itemNumber);
    verifyItemPresence(item);
    item.setDiscount(newDiscount);
  }

  /**
   * Sets the item (found by item number) price to the parameter 'newAmount'.
   *
   * @param itemNumber is the item number for the item that is mutated.
   * @param newAmount is the new price for the item if found.
   * @throws IllegalArgumentException if the item is not present in the register or of the integer
   *                                  value for new amount is negative.
   */
  public void setItemPrice(String itemNumber, int newAmount) throws IllegalArgumentException {
    Item item = internalSearch(itemNumber);
    verifyItemPresence(item);
    item.setPrice(newAmount);
  }

  /**
   * Sets the item (found by item number) description to the parameter 'newDescription'.
   *
   * @param itemNumber is the item number for the item that is mutated.
   * @param newDescription is the new description for the item if found.
   * @throws IllegalArgumentException if the item is not present in the register or if the
   *                                  newDescription was a blank string
   */
  public void setItemDescription(String itemNumber, String newDescription)
      throws IllegalArgumentException {
    Item item = internalSearch(itemNumber);
    verifyItemPresence(item);
    item.setDescription(newDescription);
  }

  /**
   * Checks whether the integer value does map to a registered category or not.
   *
   * @param value is the integer value that is checked if corresponds with a category.
   * @return true if the integer is representing a valid category, false otherwise.
   */
  public boolean mapsToCategory(int value) {
    return Category.findCategory(value) != null;
  }

  /**
   * Returns a string of the item register ordered by the item-number attribute of the items.
   * The items are printed in either an ascending order (ascending -> true), or a descending
   * order (ascending -> false).
   *
   * @param ascending is true if the register is to be printed in ascending order, and false for
   *                  descending order.
   * @return a string with all the items in the register ordered by the item-number of the items.
   */
  public String printSortedByNumber(boolean ascending) {
    if (ascending) {
      return "Item register sorted by item number in ascending order: \n"
          + deepCopy()
          .sorted(Comparator.comparing(Item::getNumber)).toList();
    }
    return "Item register sorted by item number in descending order: \n"
        + deepCopy()
        .sorted(Comparator.comparing(Item::getNumber).reversed()).toList();
  }

  /**
   * Returns a string of the item register ordered by the discounted price of the items.
   * The items are printed in either an ascending order (ascending -> true), or a descending
   * order (ascending -> false).
   *
   * @param ascending is true if the register is to be printed in ascending order, and false for
   *                  descending order.
   * @return a string with all the items in the register ordered by the discounted price
   *          of the items.
   */
  public String printSortedByDiscountedPrice(boolean ascending) {
    if (ascending) {
      return "Item register sorted by item discounted price in ascending order: \n"
          + deepCopy()
          .sorted(Comparator.comparing(Item::getDiscountedPrice)).toList();
    }
    return "Item register sorted by item discounted price in descending order: \n"
        + deepCopy()
        .sorted(Comparator.comparing(Item::getDiscountedPrice)
            .reversed()).toList();
  }

  /**
   * Returns a string of the item register ordered by the discount attribute of the items.
   * The items are printed in either an ascending order (ascending -> true), or a descending
   * order (ascending -> false).
   *
   * @param ascending is true if the register is to be printed in ascending order, and false for
   *                  descending order.
   * @return a string with all the items in the register ordered by the discount of the items.
   */
  public String printSortedByDiscount(boolean ascending) {
    if (ascending) {
      return "Item register sorted by discount in ascending order: \n"
          + deepCopy()
          .sorted(Comparator.comparingInt(Item::getDiscount)).toList();
    }
    return "Item register sorted by discount in descending order: \n"
        + deepCopy()
        .sorted(Comparator.comparingInt(Item::getDiscount).reversed()).toList();
  }

  /**
   * Returns a string of the item register ordered by the amount in stock attribute of the items.
   * The items are printed in either an ascending order (ascending -> true), or a descending
   * order (ascending -> false).
   *
   * @param ascending is true if the register is to be printed in ascending order, and false for
   *                   descending order.
   * @return a string with all the items in the register ordered by the stock amount of the items.
   */
  public String printSortedByAmount(boolean ascending) {
    if (ascending) {
      return "Item register sorted by amount in stock in ascending order: \n"
          + deepCopy()
          .sorted(Comparator.comparingInt(Item::getAmountInStock)).toList();
    }
    return "Item register sorted by amount in stock in descending order: \n"
        + deepCopy()
        .sorted(Comparator.comparingInt(Item::getAmountInStock).reversed())
        .toList();
  }

  /**
   * Returns a string with information on all items in the register.
   *
   * @return string showing the item contents of the register.
   */
  @Override
  public String toString() {
    return "The register for W.M.S. contains the following items:\n"
        + deepCopy().map(Item::toString).toList();
  }
}
