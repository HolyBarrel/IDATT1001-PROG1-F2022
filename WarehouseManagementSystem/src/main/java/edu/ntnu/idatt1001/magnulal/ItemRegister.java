package edu.ntnu.idatt1001.magnulal;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class describing an Item Register with information on all Items sold by Smart Housing AS.

 * @author Magnus Lutro Allison
 * @version 0.2
 * @since 0.2
 */
public class ItemRegister {
  private ArrayList<Item> itemRegister;

  public ItemRegister() {
    this.itemRegister = new ArrayList<>();
  }

  /**
   *
   * @param number
   * @param description
   * @param price
   * @param brandName
   * @param weight
   * @param length
   * @param height
   * @param color
   * @param amountInStock
   * @param categoryNumber
   * @throws IllegalArgumentException
   * @throws NullPointerException
   */
  public void addItem(String number, String description, int price, String brandName, double weight,
      double length, double height, String color, int amountInStock, int categoryNumber)
      throws IllegalArgumentException, NullPointerException {
    itemRegister.add(new Item(number, description, price, brandName, weight, length, height,
        color, amountInStock, categoryNumber));
  }

  /**
   *
   * @param itemList
   */
  public void addMultipleItems(ArrayList<Item> itemList) {
    itemList.forEach(item -> itemRegister.add(new Item(item)));
  }


  /*

  public Item searchForItem(String... stringsToMatch) {
    Item returnItem;
    for (String searchString : stringsToMatch) {
      if (searchForItemString(searchString) != null) {
        return searchForItemString(searchString);
      }
    }
    return null;
  }


  private Item searchForItemString(String searchString){
    for (Item item : itemRegister) {
      if (item.getNumber().equalsIgnoreCase(searchString)
          || item.getDescription().equalsIgnoreCase(searchString)) {
        return item;
      }
    }
    return null;
  }

  private boolean checkIfSearchWordExists(String searchString){
    return itemRegister.stream ().anyMatch(item -> item.getNumber().equalsIgnoreCase(searchString)
        ||item.getDescription().equalsIgnoreCase(searchString));
  }


  */

  public Item searchForNumberAndDescription(String searchNumber, String searchDescription) {
    Item targetItem = searchForNumber(searchNumber);
    if (targetItem == null) {
      targetItem = searchForDescription(searchDescription);
    }
    return targetItem;
  }

  private Item searchForNumber(String searchString) {
    return itemRegister.stream()
        .filter(item -> item.getNumber()
            .contains(searchString.trim()))
        .findAny()
        .orElse(null);
  }

  private Item searchForDescription(String searchString){
    return itemRegister.stream()
        .filter(item -> item.getDescription()
            .contains(searchString.trim()))
        .findAny()
        .orElse(null);
  }

  public String printItems() {
    return itemRegister.stream().map(Item::toString).collect(Collectors.toList()).toString();
  }

  public void increaseAmountInStock(){

  }
}
