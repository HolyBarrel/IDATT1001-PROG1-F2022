package edu.ntnu.idatt1001.magnulal;

import java.util.Arrays;

/**
 * Enum containing distinct categories allowed for registration of an object of the Item class.
 * These enum constant values are constructed with an integer number attribute.
 *
 * @author Magnus Lutro Allison
 * @version 0.2
 * @since 0.1
 */
public enum Category {
  //Category enum constants:
  FLOOR_LAMINATES(1), WINDOWS(2), DOORS(3), LUMBER(4);

  private final int number;

  /**
   * Constructs an enum.
   *
   * @param value is the integer value which is assigned as an attribute of the enum constant.
   */
  Category(int value) {
    number = value;
  }

  /**
   * Returns the integer associated with this enum constant.
   *
   * @return an integer value representing a category.
   */
  public int getNumber() {
    return this.number;
  }

  /**
   * Static method that returns the enum constant associated with the integer categoryNumber
   * parameter, and null if the integer does not represent any enum constant.
   *
   * @param categoryNumber is an integer value.
   * @return the enum constant value if there was an integer value matching the categoryNumber,
   *          null otherwise
   */
  public static Category findCategory(int categoryNumber) {
    return Arrays
        .stream(Category.values())
        .filter(e -> e.getNumber() == categoryNumber)
        .findAny()
        .orElse(null);
  }
}
