package edu.ntnu.idatt1001.cand10105.launcher;

import edu.ntnu.idatt1001.cand10105.view.UserInterface;

/**
 * Wrapper-class for the static main-method to run the application.
 * Goal: launch the user interface via a main method
 *
 * @author 10105
 * @version 1.0
 * @since 0.1
 */
public class App {

  /**
   * Main-method that runs the application.
   *
   * @param args are the arguments for the main-method.
   */
  public static void main(String[] args) {
    UserInterface.launch();
  }
}
