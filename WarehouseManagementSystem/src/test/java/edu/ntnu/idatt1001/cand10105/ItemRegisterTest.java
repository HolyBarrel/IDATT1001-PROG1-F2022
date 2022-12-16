package edu.ntnu.idatt1001.cand10105;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import edu.ntnu.idatt1001.cand10105.model.Item;
import edu.ntnu.idatt1001.cand10105.model.ItemRegister;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class ItemRegisterTest {
  @Nested
  @DisplayName("Negative tests for 'ItemRegister', throws exceptions on wrong input parameters")
  public class methodsThrowsExceptions {

    @Test
    @DisplayName("ItemRegister addItem() throws 'Ill.Arg.Exc.' on blank string number")
    public void itemRegisterAddItemThrowsOnBlankNumber() {
      try {
        ItemRegister register = new ItemRegister();
        register.addItem("", "laminate floor with stone appearance",
            102, "Mikes", 20, 120, 20, "Gray",
            2, 12, 1);
        fail("The method 'itemRegisterAddItemThrowsOnBlankNumber' did not throw on blank item "
            + "number");
      } catch (IllegalArgumentException e) {
        assertEquals("The string for the parameter 'Number' was a blank string, please "
            + "retry registration.", e.getMessage());
      }
    }

    @Test
    @DisplayName("ItemRegister addItem() throws 'Ill.Arg.Exc.' on blank description")
    public void itemRegisterAddItemThrowsOnBlankDescription() {
      try {
        ItemRegister register = new ItemRegister();
        register.addItem("20929B", "",
            102, "Mikes", 20, 120, 20, "Gray",
            2, 10, 1);
        fail("The method 'itemRegisterAddItemThrowsOnBlankDescription' did not throw on blank item "
            + "description");
      } catch (IllegalArgumentException e) {
        assertEquals("The string for the parameter 'Description' was a blank string, "
            + "please retry registration.", e.getMessage());
      }
    }

    @Test
    @DisplayName("ItemRegister addItem() throws 'Ill.Arg.Exc.' on blank brand name")
    public void itemRegisterAddItemThrowsOnBlankBrandName() {
      try {
        ItemRegister register = new ItemRegister();
        register.addItem("20929B", "AJSJ1",
            102, "", 20, 120, 20, "Gray",
            2, 10, 1);
        fail("The method 'itemRegisterAddItemThrowsOnBlankBrandName' did not throw on blank item "
            + "brand name");
      } catch (IllegalArgumentException e) {
        assertEquals("The string for the parameter 'Brand Name' was a blank string,"
            + " please retry registration.", e.getMessage());
      }
    }

    @Test
    @DisplayName("ItemRegister addItem() throws 'Ill.Arg.Exc.' on blank color")
    public void itemRegisterAddItemThrowsOnBlankColor() {
      try {
        ItemRegister register = new ItemRegister();
        register.addItem("20929B", "AJSJ1",
            102, "Nike", 20, 120, 20, "",
            2, 10, 1);
        fail("The method 'itemRegisterAddItemThrowsOnBlankColor' did not throw on blank item "
            + "color");
      } catch (IllegalArgumentException e) {
        assertEquals("The string for the parameter 'Color' was a blank string, "
            + "please retry registration.", e.getMessage());
      }
    }

    @Test
    @DisplayName("ItemRegister addItem() throws 'Ill.Arg.Exc.' on negative price")
    public void itemRegisterAddItemThrowsOnNegativePrice() {
      try {
        ItemRegister register = new ItemRegister();
        register.addItem("20929B", "AJSJ1",
            -102, "Nike", 20, 120, 20, "Blue",
            2, 10, 1);
        fail("The method 'itemRegisterAddItemThrowsOnNegativePrice' did not throw on negative "
            + "price");
      } catch (IllegalArgumentException e) {
        assertEquals("The value for the parameter 'Price' was a negative number, "
            + "please retry registration.", e.getMessage());
      }
    }

    @Test
    @DisplayName("ItemRegister addItem() throws 'Ill.Arg.Exc.' on negative weight")
    public void itemRegisterAddItemThrowsOnNegativeWeight() {
      try {
        ItemRegister register = new ItemRegister();
        register.addItem("20929B", "AJSJ1",
            102, "Nike", -20.2, 120, 20, "Blue",
            2, 10, 1);
        fail("The method 'itemRegisterAddItemThrowsOnNegativeWeight' did not throw on negative "
            + "weight");
      } catch (IllegalArgumentException e) {
        assertEquals("The value for the parameter 'Weight' was a negative number, "
            + "please retry registration.", e.getMessage());
      }
    }

    @Test
    @DisplayName("ItemRegister addItem() throws 'Ill.Arg.Exc.' on negative length")
    public void itemRegisterAddItemThrowsOnNegativeLength() {
      try {
        ItemRegister register = new ItemRegister();
        register.addItem("20929B", "AJSJ1",
            102, "Nike", 20.2, -120.1, 20, "Blue",
            2, 10, 1);
        fail("The method 'itemRegisterAddItemThrowsOnNegativeLength' did not throw on negative "
            + "length");
      } catch (IllegalArgumentException e) {
        assertEquals("The value for the parameter 'Length' was a negative number, "
            + "please retry registration.", e.getMessage());
      }
    }

    @Test
    @DisplayName("ItemRegister addItem() throws 'Ill.Arg.Exc.' on negative height")
    public void itemRegisterAddItemThrowsOnNegativeHeight() {
      try {
        ItemRegister register = new ItemRegister();
        register.addItem("20929B", "AJSJ1",
            102, "Nike", 20.2, 120.1, -20, "Blue",
            2, 10, 1);
        fail("The method 'itemRegisterAddItemThrowsOnNegativeHeight' did not throw on negative "
            + "length");
      } catch (IllegalArgumentException e) {
        assertEquals("The value for the parameter 'Height' was a negative number, "
            + "please retry registration.", e.getMessage());
      }
    }

    @Test
    @DisplayName("ItemRegister addItem() throws 'Ill.Arg.Exc.' on negative amount in stock")
    public void itemRegisterAddItemThrowsOnNegativeAmountInStock() {
      try {
        ItemRegister register = new ItemRegister();
        register.addItem("20929B", "AJSJ1",
            102, "Nike", 20.2, 120.1, 20, "Blue",
            -2, 10, 1);
        fail("The method 'itemRegisterAddItemThrowsOnNegativeAmountInStock' "
            + "did not throw on negative length");
      } catch (IllegalArgumentException e) {
        assertEquals("The value for the parameter 'Amount in stock' was a negative number, "
            + "please retry registration.", e.getMessage());
      }
    }

    @Test
    @DisplayName("ItemRegister addItem() throws 'Ill.Arg.Exc.' on negative discount")
    public void itemRegisterAddItemThrowsOnNegativeDiscount() {
      try {
        ItemRegister register = new ItemRegister();
        register.addItem("20929B", "AJSJ1",
            102, "Nike", 20, 120, 20, "Blue",
            2, -10, 1);
        fail("The method 'itemRegisterAddItemThrowsOnNegativeDiscount' did not throw on negative "
            + "discount");
      } catch (IllegalArgumentException e) {
        assertEquals("The value for the parameter 'Discount percent' was a negative "
            + "number, please retry registration.", e.getMessage());
      }
    }

    @Test
    @DisplayName("ItemRegister addItem() throws 'Ill.Arg.Exc.' on too large discount (> 100)")
    public void itemRegisterAddItemThrowsOnTooLargeDiscount() {
      try {
        ItemRegister register = new ItemRegister();
        register.addItem("20929B", "AJSJ1",
            102, "Nike", 20, 120, 20, "Blue",
            2, 101, 1);
        fail("The method 'itemRegisterAddItemThrowsOnTooLargeDiscount' did not throw on "
            + "discount more than 100 (%)");
      } catch (IllegalArgumentException e) {
        assertEquals("The value for the Discount percent was a number above 100, "
            + "please try again with a smaller positive integer value", e.getMessage());
      }
    }

    @Test
    @DisplayName("ItemRegister addItem() throws 'Ill.Arg.Exc.' on unregistered integer for"
        + " a category")
    public void itemRegisterAddItemThrowsOnUnregisteredCategory() {
      try {
        ItemRegister register = new ItemRegister();
        register.addItem("20929B", "AJSJ1",
            102, "Nike", 20, 120, 20, "Blue",
            2, 10, 15);
        fail("The method 'itemRegisterAddItemThrowsOnUnregisteredCategory' did not throw on "
            + "wrong input for a category");
      } catch (NullPointerException e) {
        assertEquals("The integer '15' parameter for Category did not correlate to any "
            + "registered categories, please try again.", e.getMessage());
      }
    }

    @Test
    @DisplayName("ItemRegister addMultipleItems() throws 'Ill.Arg.Exc.' on already registered "
        + "item")
    public void itemRegisterAddMultipleItemThrowsOnAddingIdenticalItem() {
      try {
        ItemRegister register = new ItemRegister();
        register.addItem("20929B", "AJSJ1",
            102, "Nike", 20, 120, 20, "Blue",
            2, 10, 1);
        Item item1 = new Item("20929B", "AJSJ1",
            102, "Nike", 20, 120, 20, "Blue",
            2, 10, 1);
        ArrayList<Item> addList = new ArrayList<>();
        addList.add(new Item("ABCD1001", "Large wall-planks with green tint.",
            100, "Woodland", 3.2, 2, 2.1, "Gray-green",
            200, 0, 4));
        addList.add(new Item("AZCD1002", "Large floor-laminate with"
            + " stone appearance.",
            150, "Stoneland", 2.2, 3, 0.23, "Gray",
            20, 0, 1));
        addList.add(new Item("BCCD1003", "Small floor-laminate "
            + "with grass coloring.",
            50, "Grassland", 0.2, 2, 0.2, "Green",
            2000, 20, 1));
        addList.add(new Item("YBCXx002", "Medium window-panes"
            + " with yellow tinted glass.", 150, "Stoneland",
            10.2, 1, 2.03,
            "Black (frames)", 20, 20, 2));
        addList.add(new Item("QBCUo003", "Extremely large"
            + " window-panes (bulletproof grade A-certification).",
            15000, "WWWindows",
            100.2, 10, 15.03, "White (frames)",
            2, 25, 2));
        addList.add(item1);
        register.addMultipleItems(addList);
        fail("The method 'itemRegisterAddMultipleItemThrowsOnAddingIdenticalItem'"
            + " did not throw when the item was already in the register");
      } catch (IllegalArgumentException e) {
        assertEquals("At least one item in the list of items which was supposed to be "
            + "added was already in the register, please try again.", e.getMessage());
      }
    }

    @Test
    @DisplayName("ItemRegister deleteItem() throws 'Ill.Arg.Exc.' on attempt to delete item not"
        + " present in the register")
    public void itemRegisterDeleteItemThrowsWhenItemIsNotPresent() {
      try {
        ItemRegister register = new ItemRegister();
        register.addItem("20929B", "AJSJ1",
            102, "Nike", 20, 120, 20, "Blue",
            2, 10, 1);

        Item item2 = new Item("20jsjsj25", "Smooth flooring",
            102, "Nike", 20, 120, 20, "Blue",
            2, 10, 1);
        register.deleteItem(item2.getNumber());
        fail("The method 'itemRegisterDeleteItemThrowsWhenItemIsNotPresent' did not throw on "
            + "attempted deletion of an item not present");
      } catch (IllegalArgumentException e) {
        assertEquals("The item was not in the register, please try again."
            , e.getMessage());
      }
    }

    @Test
    @DisplayName("ItemRegister setItemAmountInStock() throws 'Ill.Arg.Exc.' on attempt to set "
        + "amount of an item not present in the register")
    public void itemRegisterSetAmountInStockThrowsWhenItemIsNotPresent() {
      try {
        ItemRegister register = new ItemRegister();
        register.addItem("20929B", "AJSJ1",
            102, "Nike", 20, 120, 20, "Blue",
            2, 10, 1);

        Item item2 = new Item("20jsjsj25", "Smooth flooring",
            102, "Nike", 20, 120, 20, "Blue",
            2, 10, 1);
        register.setItemAmountInStock(item2.getNumber(), 300);
        fail("The method 'itemRegisterSetAmountInStockThrowsWhenItemIsNotPresent' did not throw on "
            + "attempted set of amount of an item not present");
      } catch (IllegalArgumentException e) {
        assertEquals("The item was not in the register, please try again."
            , e.getMessage());
      }
    }

    @Test
    @DisplayName("ItemRegister setItemAmountInStock() throws 'Ill.Arg.Exc.' on attempt to set "
        + "amount of an item to negative integer")
    public void itemRegisterSetAmountToNegativeInteger() {
      try {
        ItemRegister register = new ItemRegister();
        register.addItem("20929B", "AJSJ1",
            102, "Nike", 20, 120, 20, "Blue",
            2, 10, 1);
        register.setItemAmountInStock("20929B", -300);
        fail("The method 'itemRegisterSetAmountToNegativeInteger' did not throw on "
            + "attempted set of amount of an item to negative integer");
      } catch (IllegalArgumentException e) {
        assertEquals("The value for the parameter 'Amount in stock' was a negative "
                + "number, please retry registration."
            , e.getMessage());
      }
    }

    @Test
    @DisplayName("ItemRegister setItemDiscount() throws 'Ill.Arg.Exc.' on attempt to set "
        + "discount of an item not present in the register")
    public void itemRegisterSetItemDiscountThrowsWhenItemIsNotPresent() {
      try {
        ItemRegister register = new ItemRegister();
        register.addItem("20929B", "AJSJ1",
            102, "Nike", 20, 120, 20, "Blue",
            2, 10, 1);

        Item item2 = new Item("20jsjsj25", "Smooth flooring",
            102, "Nike", 20, 120, 20, "Blue",
            2, 10, 1);
        register.setItemDiscount(item2.getNumber(), 300);
        fail("The method 'itemRegisterSetItemDiscountThrowsWhenItemIsNotPresent' did not throw on "
            + "attempted set of discount of an item not present");
      } catch (IllegalArgumentException e) {
        assertEquals("The item was not in the register, please try again."
            , e.getMessage());
      }
    }

    @Test
    @DisplayName("ItemRegister setItemAmountInStock() throws 'Ill.Arg.Exc.' on attempt to set "
        + "discount of an item to negative integer")
    public void itemRegisterSetDiscountToNegativeInteger() {
      try {
        ItemRegister register = new ItemRegister();
        register.addItem("20929B", "AJSJ1",
            102, "Nike", 20, 120, 20, "Blue",
            2, 10, 1);
        register.setItemDiscount("20929B", -300);
        fail("The method 'itemRegisterSetDiscountToNegativeInteger' did not throw on "
            + "attempted set of discount of an item to negative integer");
      } catch (IllegalArgumentException e) {
        assertEquals("The value for the parameter 'Discount percent' was a negative"
                + " number, please retry registration."
            , e.getMessage());
      }
    }

    @Test
    @DisplayName("ItemRegister setItemAmountInStock() throws 'Ill.Arg.Exc.' on attempt to set "
        + "discount of an item to integer more than 100")
    public void itemRegisterSetDiscountToIntegerAbove100() {
      try {
        ItemRegister register = new ItemRegister();
        register.addItem("20929B", "AJSJ1",
            102, "Nike", 20, 120, 20, "Blue",
            2, 10, 1);
        register.setItemDiscount("20929B", 300);
        fail("The method 'itemRegisterSetDiscountToIntegerAbove100' did not throw on "
            + "attempted set of discount of an item to more than 100");
      } catch (IllegalArgumentException e) {
        assertEquals("The value for the Discount percent was a number above 100, please "
                + "try again with a smaller positive integer value"
            , e.getMessage());
      }
    }

    @Test
    @DisplayName("ItemRegister setItemPrice() throws 'Ill.Arg.Exc.' on attempt to set "
        + "amount of an item not present in the register")
    public void itemRegisterSetPriceThrowsWhenItemIsNotPresent() {
      try {
        ItemRegister register = new ItemRegister();
        register.addItem("20929B", "AJSJ1",
            102, "Nike", 20, 120, 20, "Blue",
            2, 10, 1);

        Item item2 = new Item("20jsjsj25", "Smooth flooring",
            102, "Nike", 20, 120, 20, "Blue",
            2, 10, 1);
        register.setItemPrice(item2.getNumber(), 300);
        fail("The method 'itemRegisterSetPriceThrowsWhenItemIsNotPresent' did not throw on "
            + "attempted set of amount of an item not present");
      } catch (IllegalArgumentException e) {
        assertEquals("The item was not in the register, please try again."
            , e.getMessage());
      }
    }

    @Test
    @DisplayName("ItemRegister setItemPrice() throws 'Ill.Arg.Exc.' on attempt to set "
        + "price of an item to negative integer")
    public void itemRegisterSetPriceToNegativeInteger() {
      try {
        ItemRegister register = new ItemRegister();
        register.addItem("20929B", "AJSJ1",
            102, "Nike", 20, 120, 20, "Blue",
            2, 10, 1);
        register.setItemPrice("20929B", -300);
        fail("The method 'itemRegisterSetPriceToNegativeInteger' did not throw on "
            + "attempted set of amount of an item to negative integer");
      } catch (IllegalArgumentException e) {
        assertEquals("The value for the parameter 'Price' was a negative "
                + "number, please retry registration."
            , e.getMessage());
      }
    }

    @Test
    @DisplayName("ItemRegister setItemDescription() throws 'Ill.Arg.Exc.' on attempt to set "
        + "description of an item not present in the register")
    public void itemRegisterSetDescriptionThrowsWhenItemIsNotPresent() {
      try {
        ItemRegister register = new ItemRegister();
        register.addItem("20929B", "AJSJ1",
            102, "Nike", 20, 120, 20, "Blue",
            2, 10, 1);

        Item item2 = new Item("20jsjsj25", "Smooth flooring",
            102, "Nike", 20, 120, 20, "Blue",
            2, 10, 1);
        register.setItemDescription(item2.getNumber(), "test test test");
        fail("The method 'itemRegisterSetDescriptionThrowsWhenItemIsNotPresent' did not throw on "
            + "attempted set of amount of an item not present");
      } catch (IllegalArgumentException e) {
        assertEquals("The item was not in the register, please try again."
            , e.getMessage());
      }
    }

    @Test
    @DisplayName("ItemRegister setItemDescription() throws 'Ill.Arg.Exc.' on attempt to set "
        + "description of an item to a blank string")
    public void itemRegisterSetDescriptionToBlankString() {
      try {
        ItemRegister register = new ItemRegister();
        register.addItem("20929B", "AJSJ1",
            102, "Nike", 20, 120, 20, "Blue",
            2, 10, 1);
        register.setItemDescription("20929B", "");
        fail("The method 'itemRegisterSetDescriptionToBlankString' did not throw on "
            + "attempted set of amount of an item to negative integer");
      } catch (IllegalArgumentException e) {
        assertEquals("The string for the parameter 'Description' "
                + "was a blank string, please retry registration."
            , e.getMessage());
      }
    }
  }

  @Nested
  @DisplayName("Positive tests for 'ItemRegister'-class")
  public class methodsDoesNotThrowException {

    @Test
    @DisplayName("ItemRegister searchForNumber() finds the item if it is in the register")
    public void itemRegisterSearchForNumber() {
      ItemRegister register = new ItemRegister();
      register.addItem("20929B", "AJSJ1",
          102, "Nike", 20, 120, 20, "Blue",
          2, 10, 1);
      Item foundItem = register.searchForNumber("20929B");
      if (foundItem == null) {
        fail("The method 'itemRegisterSearchForNumber' failed since it did not find the item"
            + " when it was present in the register.");
      }
      assertEquals("""
                    
          Item #:           20929B
          Description:      AJSJ1
          Price:            102
          Discounted price: 91.8
          Brand Name:       Nike
          Weight:           20.0
          Length:           120.0
          Height:           20.0
          Color:            Blue
          In stock:         2
          Discount:         10 %
          Category:         (1)FLOOR_LAMINATES
          _____________________________________""", foundItem.toString());

    }

    @Test
    @DisplayName("ItemRegister searchForNumber() does return a deep copy of an item that is found")
    public void itemRegisterSearchForNumberReturnsDeepCopy() {
      ItemRegister register = new ItemRegister();
      register.addItem("20929B", "AJSJ1",
          102, "Nike", 20, 120, 20, "Blue",
          2, 10, 1);
      Item foundItem = register.searchForNumber("20929B");
      foundItem.setDescription("Other description");
      if ("""
                    
          Item #:           20929B
          Description:      AJSJ1
          Price:            102
          Discounted price: 91.8
          Brand Name:       Nike
          Weight:           20.0
          Length:           120.0
          Height:           20.0
          Color:            Blue
          In stock:         2
          Discount:         10 %
          Category:         (1)FLOOR_LAMINATES
          _____________________________________""".equals(foundItem.toString())) {
        fail("The returned item was not a deep copy since the set method applied to it mutated "
            + "the item in the register as well");
      }
      assertNotEquals("""
                    
          Item #:           20929B
          Description:      AJSJ1
          Price:            102
          Discounted price: 91.8
          Brand Name:       Nike
          Weight:           20.0
          Length:           120.0
          Height:           20.0
          Color:            Blue
          In stock:         2
          Discount:         10 %
          Category:         (1)FLOOR_LAMINATES
          _____________________________________""", foundItem.toString());

    }

    @Test
    @DisplayName("ItemRegister addItem() adds an item with correct parameters to the register"
        + " (also tests getLastItem())")
    public void itemRegisterAddItemAddsItem() {
      ItemRegister register = new ItemRegister();
      register.addItem("20929B", "AJSJ1",
          102, "Nike", 20, 120, 20, "Blue",
          2, 10, 1);
      String lastItemStr = register.getLastItem();
      if (!("""
                    
          Item #:           20929B
          Description:      AJSJ1
          Price:            102
          Discounted price: 91.8
          Brand Name:       Nike
          Weight:           20.0
          Length:           120.0
          Height:           20.0
          Color:            Blue
          In stock:         2
          Discount:         10 %
          Category:         (1)FLOOR_LAMINATES
          _____________________________________""".equals(lastItemStr))) {
        fail("The method 'itemRegisterAddItemAddsItem' failed since it did not add the item to the"
            + " last index of the register as expected.");
        assertEquals("""
                      
            Item #:           20929B
            Description:      AJSJ1
            Price:            102
            Discounted price: 91.8
            Brand Name:       Nike
            Weight:           20.0
            Length:           120.0
            Height:           20.0
            Color:            Blue
            In stock:         2
            Discount:         10 %
            Category:         (1)FLOOR_LAMINATES
            _____________________________________""", lastItemStr);
      }
    }

    @Test
    @DisplayName("ItemRegister deleteItem() deletes an that is in the register")
    public void itemRegisterDeleteItemDeletesItem() {
      ItemRegister register = new ItemRegister();
      register.addItem("20929B", "AJSJ1",
          102, "Nike", 20, 120, 20, "Blue",
          2, 10, 1);
      register.deleteItem("20929B");
      String numbersPresent = register.getNumbers();
      if (!numbersPresent.equals("No registered items in the register.")) {
        fail("The delete-method did not remove the last item of the register as expected");
      }
      assertEquals("No registered items in the register.", register.getNumbers());
    }


    @Test
    @DisplayName("ItemRegister getNumbers() returns a correct string")
    public void itemRegisterGetNumbersReturnsCorrectString() {
      ItemRegister register = new ItemRegister();
      register.addItem("20929B", "AJSJ1",
          102, "Nike", 20, 120, 20, "Blue",
          2, 10, 1);
      register.addItem("20zS2", "AJSJ1",
          102, "Nike", 20, 120, 20, "Blue",
          2, 10, 1);

      String numbersPresent = register.getNumbers();
      if (!numbersPresent.equals("[20929B, 20zS2]")) {
        fail("The getNumbers-method did not retrieve the correct string '[20929B, 20zS2]' "
            + "as expected");
      }
      assertEquals("[20929B, 20zS2]", register.getNumbers());
    }

    @Test
    @DisplayName("ItemRegister isNumberPresent() returns true if item number is present")
    public void itemRegisterIsNumberPresentReturnsTrue() {
      ItemRegister register = new ItemRegister();
      register.addItem("20929B", "AJSJ1",
          102, "Nike", 20, 120, 20, "Blue",
          2, 10, 1);
      boolean check = register.isNumberPresent("20929B");
      if (!check) {
        fail("The isnumberPresent-method did not return true when the item number was present");
      }
      assertTrue(check);
    }

    @Test
    @DisplayName("ItemRegister isNumberPresent() returns false if item number is present")
    public void itemRegisterIsNumberPresentReturnsFalse() {
      ItemRegister register = new ItemRegister();
      register.addItem("20929B", "AJSJ1",
          102, "Nike", 20, 120, 20, "Blue",
          2, 10, 1);
      boolean check = register.isNumberPresent("HEYHEY");
      if (check) {
        fail("The isnumberPresent-method did not return false when the item number was absent");
      }
      assertFalse(check);
    }

    @Test
    @DisplayName("ItemRegister getAllCategories() returns correct string")
    public void itemRegisterGetAllCategoriesReturnsCorrectString() {
      ItemRegister register = new ItemRegister();
      String categories = register.getAllCategories();
      if (!("[(1)FLOOR_LAMINATES, (2)WINDOWS, (3)DOORS, (4)LUMBER]").equals(categories)) {
        fail("The getAllCategories-method did not return the correct string for all categories"
            + " registered");
      }
      assertEquals("[(1)FLOOR_LAMINATES, (2)WINDOWS, (3)DOORS, (4)LUMBER]", categories);
    }

    @Test
    @DisplayName("ItemRegister setItemAmountInStock updates amount without throwing "
        + "'Ill.Arg.Exc.'")
    public void itemRegisterSetAmountInStockUpdatesAmountWithoutThrowing() {
      try {
        ItemRegister register = new ItemRegister();
        register.addItem("20929B", "AJSJ1",
            102, "Nike", 20, 120, 20, "Blue",
            2, 10, 1);
        register.setItemAmountInStock("20929B", 20);
        Item copy = register.searchForNumber("20929B");
        assertEquals(20, copy.getAmountInStock());
      } catch (IllegalArgumentException e) {
        fail("The test 'itemRegisterSetAmountInStockUpdatesAmountWithoutThrowing' "
            + "failed with the exception-message" + e.getMessage());
      }
    }

    @Test
    @DisplayName("ItemRegister setItemDiscount updates discount without throwing 'Ill.Arg.Exc.'")
    public void itemRegisterSetDiscountUpdatesDiscountWithoutThrowing() {
      try {
        ItemRegister register = new ItemRegister();
        register.addItem("20929B", "AJSJ1",
            102, "Nike", 20, 120, 20, "Blue",
            2, 10, 1);
        register.setItemDiscount("20929B", 30);
        Item copy = register.searchForNumber("20929B");
        assertEquals(30, copy.getDiscount());
      } catch (IllegalArgumentException e) {
        fail("The test 'itemRegisterSetDiscountUpdatesDiscountWithoutThrowing' "
            + "failed with the exception-message" + e.getMessage());
      }
    }

    @Test
    @DisplayName("ItemRegister setItemPrice updates price without throwing 'Ill.Arg.Exc.'")
    public void itemRegisterSetPriceUpdatesPriceWithoutThrowing() {
      try {
        ItemRegister register = new ItemRegister();
        register.addItem("20929B", "AJSJ1",
            102, "Nike", 20, 120, 20, "Blue",
            2, 10, 1);
        register.setItemPrice("20929B", 150);
        Item copy = register.searchForNumber("20929B");
        assertEquals(150, copy.getPrice());
      } catch (IllegalArgumentException e) {
        fail("The test 'itemRegisterSetPriceUpdatesPriceWithoutThrowing' "
            + "failed with the exception-message" + e.getMessage());
      }
    }

    @Test
    @DisplayName("ItemRegister setItemDescription updates description"
        + " without throwing 'Ill.Arg.Exc.'")
    public void itemRegisterSetDescriptionUpdatesWithoutThrowing() {
      try {
        ItemRegister register = new ItemRegister();
        register.addItem("20929B", "AJSJ1",
            102, "Nike", 20, 120, 20, "Blue",
            2, 10, 1);
        register.setItemDescription("20929B", "New description");
        Item copy = register.searchForNumber("20929B");
        assertEquals("New description", copy.getDescription());
      } catch (IllegalArgumentException e) {
        fail("The test 'itemRegisterSetDescriptionUpdatesWithoutThrowing' "
            + "failed with the exception-message" + e.getMessage());
      }
    }

    @Test
    @DisplayName("ItemRegister mapsToCategory returns true on correct integer"
        + " without throwing 'Ill.Arg.Exc.'")
    public void itemRegisterMapsToCategoryReturnsTrue() {
      try {
        ItemRegister register = new ItemRegister();
        boolean maps = register.mapsToCategory(1);
        assertTrue(maps);
      } catch (IllegalArgumentException e) {
        fail("The test 'itemRegisterMapsToCategoryReturnsTrue' "
            + "failed with the exception-message" + e.getMessage());
      }
    }

    @Test
    @DisplayName("ItemRegister mapsToCategory returns false on wrong integer"
        + " without throwing 'Ill.Arg.Exc.'")
    public void itemRegisterMapsToCategoryReturnsFalse() {
      try {
        ItemRegister register = new ItemRegister();
        boolean maps = register.mapsToCategory(100);
        assertFalse(maps);
      } catch (IllegalArgumentException e) {
        fail("The test 'itemRegisterMapsToCategoryReturnsFalse' "
            + "failed with the exception-message" + e.getMessage());
      }
    }

  }
}
