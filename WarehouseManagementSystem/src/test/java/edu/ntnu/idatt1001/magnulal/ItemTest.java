package edu.ntnu.idatt1001.magnulal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class ItemTest {

  @Nested
  @DisplayName("Negative tests for 'Item', throws IllegalArgument-exceptions on wrong input "
      + "parameters")
  public class methodsThrowsExceptions {

    @Test
    @DisplayName("Item-constructor throws 'Ill.Arg.Exc.' on blank string number")
    public void itemConstructorThrowsOnBlankNumber() {
      try {
        new Item("", "laminate floor with stone appearance",
            102, "Mikes", 20, 120, 20, "Gray",
            2, 1);
        fail("The test 'itemConstructorThrowsOnBlankNumber' failed since it did not throw "
            + "'Ill.Arg.Exc.' as expected.");
      } catch (IllegalArgumentException e) {
        assertEquals("The string for the parameter 'Number' was a blank string, "
                + "please retry registration.",
            e.getMessage());
      }
    }

    @Test
    @DisplayName("Item-constructor throws 'Ill.Arg.Exc.' on blank description")
    public void itemConstructorThrowsOnBlankDescription() {
      try {
        new Item("20929B", "",
            102, "Mikes", 20, 120, 20, "Gray",
            2, 1);
        fail("The test 'itemConstructorThrowsOnBlankDescription' failed since it did not throw "
            + "'Ill.Arg.Exc.' as expected.");
      } catch (IllegalArgumentException e) {
        assertEquals("The string for the parameter 'Description' was a blank string, "
                + "please retry registration.",
            e.getMessage());
      }
    }

    @Test
    @DisplayName("Item-constructor throws 'Ill.Arg.Exc.' on blank brand name")
    public void itemConstructorThrowsOnBlankBrandName() {
      try {
        new Item("AKdde2", "laminate floor with stone appearance",
            102, "", 20, 120, 20, "Gray",
            2, 1);
        fail("The test 'itemConstructorThrowsOnBlankBrandName' failed since it did not throw "
            + "'Ill.Arg.Exc.' as expected.");
      } catch (IllegalArgumentException e) {
        assertEquals("The string for the parameter 'Brand Name' was a blank string, "
                + "please retry registration.",
            e.getMessage());
      }
    }

    @Test
    @DisplayName("Item-constructor throws 'Ill.Arg.Exc.' on blank color parameter")
    public void itemConstructorThrowsOnBlankColorParameter() {
      try {
        new Item("AKSDe2", "laminate floor with stone appearance",
            102, "Mikes", 20, 120, 20, "",
            2, 1);
        fail("The test 'itemConstructorThrowsOnBlankColorParameter' failed since it did not throw "
            + "'Ill.Arg.Exc.' as expected.");
      } catch (IllegalArgumentException e) {
        assertEquals("The string for the parameter 'Color' was a blank string, "
                + "please retry registration.",
            e.getMessage());
      }
    }

    @Test
    @DisplayName("Item-constructor throws 'Ill.Arg.Exc.' on negative price integer")
    public void itemConstructorThrowsOnNegativePrice() {
      try {
        new Item("KESw2", "laminate floor with stone appearance",
            -102, "Mikes", 20, 120, 20, "Gray",
            2, 1);
        fail("The test 'itemConstructorThrowsOnNegativePrice' failed since it did not throw "
            + "'Ill.Arg.Exc.' as expected.");
      } catch (IllegalArgumentException e) {
        assertEquals("The value for the parameter 'Price' was a negative number, "
                + "please retry registration.",
            e.getMessage());
      }
    }

    @Test
    @DisplayName("Item-constructor throws 'Ill.Arg.Exc.' on negative weight parameter")
    public void itemConstructorThrowsOnNegativeWeight() {
      try {
        new Item("KDKDe2", "laminate floor with stone appearance",
            102, "Mikes", -20, 120, 20, "Gray",
            2, 1);
        fail("The test 'itemConstructorThrowsOnNegativeWeight' failed since it did not throw "
            + "'Ill.Arg.Exc.' as expected.");
      } catch (IllegalArgumentException e) {
        assertEquals("The value for the parameter 'Weight' was a negative number, "
                + "please retry registration.",
            e.getMessage());
      }
    }

    @Test
    @DisplayName("Item-constructor throws 'Ill.Arg.Exc.' on negative length parameter")
    public void itemConstructorThrowsOnNegativeLength() {
      try {
        new Item("LDLD3", "laminate floor with stone appearance",
            102, "Mikes", 20, -120, 20, "Gray",
            2, 1);
        fail("The test 'itemConstructorThrowsOnNegativeLength' failed since it did not throw "
            + "'Ill.Arg.Exc.' as expected.");
      } catch (IllegalArgumentException e) {
        assertEquals("The value for the parameter 'Length' was a negative number, "
                + "please retry registration.",
            e.getMessage());
      }
    }

    @Test
    @DisplayName("Item-constructor throws 'Ill.Arg.Exc.' on negative height parameter")
    public void itemConstructorThrowsOnNegativeHeight() {
      try {
        new Item("JDDw33", "laminate floor with stone appearance",
            102, "Mikes", 20, 120, -20, "Gray",
            2, 1);
        fail("The test 'itemConstructorThrowsOnNegativeHeight' failed since it did not throw "
            + "'Ill.Arg.Exc.' as expected.");
      } catch (IllegalArgumentException e) {
        assertEquals("The value for the parameter 'Height' was a negative number, "
                + "please retry registration.",
            e.getMessage());
      }
    }

    @Test
    @DisplayName("Item-constructor throws 'Ill.Arg.Exc.' on negative amount in stock")
    public void itemConstructorThrowsOnNegativeAmount() {
      try {
        new Item("WSSE", "laminate floor with stone appearance",
            102, "Mikes", 20, 120, 20, "Gray",
            -2, 1);
        fail("The test 'itemConstructorThrowsOnNegativeAmount' failed since it did not throw "
            + "'Ill.Arg.Exc.' as expected.");
      } catch (IllegalArgumentException e) {
        assertEquals("The value for the parameter 'Amount in stock' was a negative number,"
                + " please retry registration.",
            e.getMessage());
      }
    }

    @Test
    @DisplayName("Item-constructor throws 'Null.p.Exc.' on invalid category number")
    public void itemConstructorThrowsOnInvalidCategoryNumber() {
      try {
        new Item("ESER21", "laminate floor with stone appearance",
            102, "Mikes", 20, 120, 20, "Gray",
            2, -1);
        fail("The test 'itemConstructorThrowsOnInvalidCategoryNumber' failed since it did not "
            + "throw 'Null.p.Exc.' as expected.");
      } catch (NullPointerException e) {
        assertEquals("The integer '-1' parameter for Category did not correlate to any "
                + "registered categories, please try again.",
            e.getMessage());
      }
    }

    @Test
    @DisplayName("Item-setter for price throws 'Ill.Arg.Exc.'")
    public void itemPriceSetterThrows() {
      try {
        Item stoneLaminate = new Item("EWSSE2", "laminate floor with stone "
            + "appearance", 102, "Mikes", 20, 120, 20,
            "Gray", 2, 1);
        stoneLaminate.setPrice(-102);
        fail("The test 'itemPriceSetterThrows' failed since it did not "
            + "throw 'Ill.Arg.Exc.' as expected.");
      } catch (IllegalArgumentException e) {
        assertEquals("The value for the parameter 'Price' was a negative number, "
                + "please retry registration.",
            e.getMessage());
      }
    }

    @Test
    @DisplayName("Item-setter for amount in stock throws 'Ill.Arg.Exc.'")
    public void itemAmountInStockSetterThrows() {
      try {
        Item stoneLaminate = new Item("EWSSE2", "laminate floor with stone "
            + "appearance", 102, "Mikes", 20, 120, 20,
            "Gray", 2, 1);
        stoneLaminate.setAmountInStock(-2);
        fail("The test 'itemAmountInStockSetterThrows' failed since it did not "
            + "throw 'Ill.Arg.Exc.' as expected.");
      } catch (IllegalArgumentException e) {
        assertEquals("The value for the parameter 'Amount in stock' was a negative number,"
                + " please retry registration.",
            e.getMessage());
      }
    }
  }

  @Nested
  @DisplayName("Positive tests for Item and Category-method 'findCategory' used by Item")
  public class methodsDoesNotThrowException {
    @Test
    @DisplayName("Item-constructor creates an item without throwing 'Ill.Arg.Exc.'")
    public void itemConstructorThrowsOnBlankNumber() {
      try {
        Item stoneLaminate = new Item("2ssES2", "laminate floor with stone appearance",
            102, "Mikes", 20, 120, 20, "Gray",
            2, 1);
        assertNotNull(stoneLaminate);
      } catch (IllegalArgumentException e) {
        fail("The test failed with the exception-message" + e.getMessage());
      }
    }
    @Test
    @DisplayName("Item-setter for price updates price value without throwing 'Ill.Arg.Exc.'")
    public void itemSetterUpdatesPriceValueWithoutThrowing() {
      try {
        Item stoneLaminate = new Item("2ssES2", "laminate floor with stone appearance",
            102, "Mikes", 20, 120, 20, "Gray",
            2, 1);
        stoneLaminate.setPrice(150);
        assertEquals(150, stoneLaminate.getPrice());
      } catch (IllegalArgumentException e) {
        fail("The test failed with the exception-message" + e.getMessage());
      }
    }
    @Test
    @DisplayName("Item-setter for amount in stock updates amount value without throwing "
        + "'Ill.Arg.Exc.'")
    public void itemSetterUpdatesAmountInStockValueWithoutThrowing() {
      try {
        Item stoneLaminate = new Item("2ssES2", "laminate floor with stone appearance",
            102, "Mikes", 20, 120, 20, "Gray",
            2, 1);
        stoneLaminate.setAmountInStock(3);
        assertEquals(3, stoneLaminate.getAmountInStock());
      } catch (IllegalArgumentException e) {
        fail("The test failed with the exception-message" + e.getMessage());
      }
    }

    @Test
    @DisplayName("Category-findCategory method returns 'null' when the int input is not valid as a "
        + "category")
    public void findCategoryReturnsNullOnInvalidInt() {
      Enum<Category> category1 = Category.findCategory(-1);
      assertNull(category1);
      if(category1 != null){
        fail("The test 'findCategoryReturnsNullOnInvalidInt' returned an enum when 'null' was "
            + "expected.");
      }
    }
    @Test
    @DisplayName("Category-findCategory method returns correct enum value when the int input is"
        + " valid as a category")
    public void findCategoryReturnsEnumOnValidInt() {
      Enum<Category> category1 = Category.findCategory(1);
      assertEquals(Category.FLOOR_LAMINATES,category1);
      if(category1 == null){
        fail("The test 'findCategoryReturnsNullOnInvalidInt' returned 'null' when an enum was "
            + "expected.");
      }
    }

    //TODO: test toString
  }
}