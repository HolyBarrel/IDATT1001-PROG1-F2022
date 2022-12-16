package edu.ntnu.idatt1001.cand10105.model;

import java.util.Objects;

/**
 * Class describing an Item sold by Smart Housing AS.
 * Contains a constructor to create an item of a given Category (an enum) and all associated
 * attributes. The constructor verifies the input of the parameters entered, and throws exceptions
 * on illegal data.
 * The class includes getters for all attributes, and setters for those that are logical to mutate.
 * Goal: act as a model of an item
 *
 * @author 10105
 * @version 1.0
 * @since 0.1
 */
public class Item {
  private final String number;
  private String description;
  private int price;
  private final String brandName;
  private final double weight;
  private final double length;
  private final double height;
  private final String color;
  private int amountInStock;
  private int discount;
  private final Category category;

  /**
   * Verifies a given String for it containing information. The check fails if the String parameter
   * is blank.
   *
   * @param parameter is the String that is verified.
   * @param parameterName is the name of the String being checked. This is utilized throw a
   *                      specifying exception-message if the check fails.
   *
   * @throws IllegalArgumentException if the String parameter is either "" or only whitespaces.
   *                                  Then the String is considered illegal because it will contain
   *                                  insufficient information.
   */
  private void verifyStringParameter(String parameter, String parameterName)
      throws IllegalArgumentException {
    if (parameter.isBlank()) {
      throw new IllegalArgumentException("The string for the parameter '"
          + parameterName + "' was a blank string, please retry registration.");
    }
  }

  /**
   * Verifies a given Number by checking whether it is a positive number. The check fails if the
   * Number parameter is less than zero.
   *
   * @param parameter is the Number that is verified.
   * @param parameterName is the name of the Number being checked represented by a String. This is
   *                      utilized to throw a specifying exception-message if the check fails.
   *
   * @throws IllegalArgumentException if the Number parameter is a negative number. This is
   *                                  considered illegal because the information on the specs
   *                                  of an item or the amount in stock
   *                                  cannot be assigned as negative.
   */
  private void verifyPositiveNumber(Number parameter, String parameterName)
      throws IllegalArgumentException {
    if (parameter.doubleValue() < 0) {
      throw new IllegalArgumentException("The value for the parameter '"
          + parameterName + "' was a negative number, please retry registration.");
    }
  }

  /**
   * Verifies that a given integer that signifies a percent is in fact within the legal interval
   * for a percentage in the application [0,100]. Utilizes the
   * {@link #verifyPositiveNumber(Number, String)} method to check that the integer is positive.
   *
   * @param percentDiscount is the input integer which must be inside the interval [0,100].
   * @throws IllegalArgumentException if the integer is outside the allowed interval for the
   *                                  percentage of a discount.
   */
  private void verifyPercentInterval(int percentDiscount)
      throws IllegalArgumentException {
    verifyPositiveNumber(percentDiscount, "Discount percent");
    if (percentDiscount > 100) {
      throw new IllegalArgumentException("The value for the Discount percent was a number above"
          + " 100, please try again with a smaller positive integer value");
    }
  }

  /**
   * Verifies a given integer value that is used to find a corresponding Category. The verification
   * tests if there is a registered Category for the integer parameter value. Unless this is the
   * case, the check fails.
   *
   * @param enumIntVal is the integer value that is checked for a corresponding Category enum value.
   *
   * @throws NullPointerException if the given integer was outside the set of integers corresponding
   *                              to actual enum values in Category.
   */
  private void verifyEnumInterval(int enumIntVal)
      throws NullPointerException {
    if (Category.findCategory(enumIntVal) == null) {
      throw new NullPointerException("The integer '"
          + enumIntVal + "' parameter for Category did not correlate to any registered "
          + "categories, please try again.");
    }
  }

  /**
   * Constructs an Item by assigning the parameters below as attributes of the Item.
   * Does this only after verifying that all the inputted parameters are legal by performing the
   * methods:
   * {@link #verifyStringParameter(String, String)},
   * {@link #verifyPositiveNumber(Number, String)},
   * {@link #verifyEnumInterval(int)}.
   *
   * @param number is a combination of letters and integers in a String which represents the Item.
   * @param description is a String for describing the Item.
   * @param price is an integer value for the price of the Item.
   * @param brandName is a String with brand name which offers the Item.
   * @param weight is a double value representing the weight of the Item.
   * @param length is a double value representing the length of the Item.
   * @param height is a double value representing the height of the Item
   * @param color is a String with the color of the Item.
   * @param amountInStock is an integer number describing the amount of the Item remaining in stock.
   * @param categoryNumber is an integer value representing the corresponding enum value presented
   *                       by the enum Category. The correlating enum is assigned to the Item.
   *
   * @throws IllegalArgumentException if any of the String inputs are blank, or the number inputs
   *                                  (either integer or double) are less than zero, or the integer
   *                                  categoryNumber trying to assign the Item a category with an
   *                                  integer which is not represented by an enum Category value.
   * @throws NullPointerException if the given integer was outside the set of integers corresponding
   *                              to actual enum values in Category.
   */
  public Item(String number, String description, int price, String brandName, double weight,
      double length, double height, String color, int amountInStock, int discount,
      int categoryNumber) throws IllegalArgumentException, NullPointerException {
    verifyStringParameter(number, "Number");
    verifyStringParameter(description, "Description");
    verifyStringParameter(brandName, "Brand Name");
    verifyStringParameter(color, "Color");
    verifyPositiveNumber(price, "Price");
    verifyPositiveNumber(weight, "Weight");
    verifyPositiveNumber(length, "Length");
    verifyPositiveNumber(height, "Height");
    verifyPositiveNumber(amountInStock, "Amount in stock");
    verifyPercentInterval(discount);
    verifyEnumInterval(categoryNumber);

    this.number = number.trim();
    this.description = description.trim();
    this.price = price;
    this.brandName = brandName.trim();
    this.weight = weight;
    this.length = length;
    this.height = height;
    this.color = color;
    this.amountInStock = amountInStock;
    this.discount = discount;
    this.category = Category.findCategory(categoryNumber);
  }

  /**
   * Deep-copy constructor to create a deep copy of the parameter item. This deep copy is returned
   * as a new item. If values of the new item is altered, the original object will not be affected.
   *
   * @param item is the item which is deep-copied into the 'new' item created by
   *             the constructor.
   */
  public Item(Item item) {
    this.number = item.getNumber();
    this.description = item.getDescription();
    this.price = item.getPrice();
    this.brandName = item.getBrandName();
    this.weight = item.getWeight();
    this.length = item.getLength();
    this.height = item.getHeight();
    this.color = item.getColor();
    this.amountInStock = item.getAmountInStock();
    this.discount = item.getDiscount();
    this.category = item.getCategory();
  }

  /**
   * Returns the String representing the number of this object of Item.
   *
   * @return a String with a combination of integers and letters.
   */
  public String getNumber() {
    return number;
  }

  /**
   * Returns the String representing the description of this object of Item.
   *
   * @return a String with the item-description.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Returns the integer value representing the price of this object of Item.
   *
   * @return an integer of the item-price.
   */
  public int getPrice() {
    return price;
  }

  /**
   * Returns a String representing the brand name associated with the object of Item.
   *
   * @return a String with the name of the item-brand.
   */
  public String getBrandName() {
    return brandName;
  }

  /**
   * Returns a double representing the weight of this object of the Item class.
   *
   * @return a double being the item-weight.
   */
  public double getWeight() {
    return weight;
  }

  /**
   * Returns a double representing the length of this object of the Item class.
   *
   * @return a double being the item-length.
   */
  public double getLength() {
    return length;
  }

  /**
   * Returns a double representing the height of this object of the Item class.
   *
   * @return a double being the item-height.
   */
  public double getHeight() {
    return height;
  }

  /**
   * Returns a String with information on the color of this Item.
   *
   * @return a String with the item-color.
   */
  public String getColor() {
    return color;
  }

  /**
   * Returns an integer value of the total amount of this Item left in stock.
   *
   * @return an integer with the amount of the item in stock.
   */
  public int getAmountInStock() {
    return amountInStock;
  }

  /**
   * Returns the enum Category value with the category of this Item.
   *
   * @return the Category associated with this Item.
   */
  public Category getCategory() {
    return category;
  }

  /**
   * Returns a string representing the integer value associated with the category of the item as
   * well as the name of the category.
   *
   * @return string of category with integer value and name
   */
  public String getCategoryAsString() {
    return "(" + category.getNumber() + ")" + category;
  }

  /**
   * Returns the discount integer value for this Item.
   *
   * @return an integer of the item discount
   */
  public int getDiscount() {
    return discount;
  }

  /**
   * Returns the discounted price as a double value computed from the original price of the Item
   * and the current discount integer value.
   *
   * @return a double value for the discounted price
   */
  public double getDiscountedPrice() {
    return (double) Math.round(this.getPrice() * (100 - this.getDiscount())) / 100;
  }

  /**
   * Sets a String as the description attribute of this existing object of the Item class.
   * This allows for editing the description of the Item.
   *
   * @param description is a String for the description of the Item.
   *
   * @throws IllegalArgumentException if the String parameter is either "" or only whitespaces.
   *                                  Then the String is considered illegal because it contains
   *                                  insufficient information.
   */
  public void setDescription(String description) {
    verifyStringParameter(description, "Description");
    this.description = description;
  }

  /**
   * Help method to format the string of the item to at most be 44 characters on each line.
   * If there is a space-char after the line length of 34, a new line is added in the string for
   * the description.
   *
   * @param string is the String that is to be formatted
   * @return a formatted string with newlines to prevent a very long horizontal string from
   *          appearing in the toString of the item.
   */
  private String formatString(String string) {
    StringBuilder output = new StringBuilder();
    int counter = 0;
    for (char ch : string.toCharArray()) {
      output.append(ch);
      if ((counter > 35 && ch == ' ') || counter > 45) {
        output.append("\n             ");
        counter = 0;
      }
      counter++;
    }
    return output.toString();
  }

  /**
   * Sets an integer value as the price attribute of this existing object of the Item class.
   * This allows for editing the price of the Item.
   *
   * @param price is an integer value.
   *
   * @throws IllegalArgumentException if the attempted new price integer value is inputted as a
   *                                  negative value.
   */
  public void setPrice(int price) throws IllegalArgumentException {
    verifyPositiveNumber(price, "Price");
    this.price = price;
  }

  /**
   * Sets an integer value as the amount of an item object in stock.
   * Allows for editing of the total amount remaining in stock.
   *
   * @param amountInStock is an integer value.
   *
   * @throws IllegalArgumentException if the input parameter is a negative integer.
   */
  public void setAmountInStock(int amountInStock) throws IllegalArgumentException {
    verifyPositiveNumber(amountInStock, "Amount in stock");
    this.amountInStock = amountInStock;
  }

  /**
   * Sets an integer value in the range [0,100] as the discount percentage for the item.
   *
   * @param discount in the discount in percent.
   * @throws IllegalArgumentException if the input parameter is a negative integer or
   *                                    an integer above 100.
   */
  public void setDiscount(int discount) throws  IllegalArgumentException {
    verifyPercentInterval(discount);
    this.discount = discount;
  }

  /**
   * Checks if another item is considered the same as this object. The method produces true if
   * this is also the other object and if the other object has the same item number (primary key)
   * as this object. Returns false otherwise.
   *
   * @param another is the other object which is checked to see if it is equal as this object.
   * @return true if the object is the same as this object, and false otherwise.
   */
  @Override
  public boolean equals(Object another) {
    if (this == another) {
      return true;
    }
    if (another == null || getClass() != another.getClass()) {
      return false;
    }
    Item item = (Item) another;
    return Objects.equals(number, item.number);
  }

  /**
   * Creates a hash code for the object based on its uniqueness
   * (the primary key which is item number). Two items with the same item number will have the same
   * hash code.
   *
   * @return a unique integer hash code that represents the item.
   */
  @Override
  public int hashCode() {
    return Objects.hash(number);
  }

  /**
   * Prints a string that represents the item with all its attribute information.
   *
   * @return a string that represents the item information
   */
  @Override
  public String toString() {
    return
        "\nItem #:           " + formatString(getNumber()) + "\n"
            + "Description:      " + formatString(getDescription()) + "\n"
            + "Price:            " + getPrice() + "\n"
            + "Discounted price: " + getDiscountedPrice() + "\n"
            + "Brand Name:       " + formatString(getBrandName()) + "\n"
            + "Weight:           " + getWeight() + "\n"
            + "Length:           " + getLength() + "\n"
            + "Height:           " + getHeight() + "\n"
            + "Color:            " + formatString(getColor()) + "\n"
            + "In stock:         " + getAmountInStock() + "\n"
            + "Discount:         " + getDiscount() + " %" +  "\n"
            + "Category:         " + getCategoryAsString()
            + "\n_____________________________________";
  }
}



