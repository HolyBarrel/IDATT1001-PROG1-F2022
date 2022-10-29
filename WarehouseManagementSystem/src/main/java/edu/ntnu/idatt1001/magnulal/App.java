package edu.ntnu.idatt1001.magnulal;

/**
 * Wrapper-class for the static main-method to run the application.
 */
public class App {

  /**
   * Main-method that runs the application.
   *
   * @param args are the arguments for the main-method.
   */
  public static void main(String[] args) {
    try {
      Item laminate = new Item("01Ase3", "laminate floor with stone appearance",
          102, "Mikes", 20, 120, 20, "Gray",
          2, 1);
      System.out.println(laminate.getAmountInStock());
      System.out.println(laminate.getBrandName());
      ItemRegister i1 = new ItemRegister();
      i1.addItem("01Ase3", "laminate floor with stone appearance",
          102, "Mikes", 20, 120, 20, "Gray",
          2, 1);
      i1.addItem("123456", "laminate2 floor with stone appearance",
          102, "Nikes", 30, 120, 20, "Grey",
          2, 1);

      System.out.println(i1.searchForNumberAndDescription("1234"
          , "stone"));
    } catch (IllegalArgumentException | NullPointerException e) {
      System.out.println(e.getMessage());
    }
  }
}
