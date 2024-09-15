package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import edu.grinnell.csc207.util.CipherUtils;

/**
 * Class to handle encoding and decoding of strings using Caesar cipher. Provides functionality to
 * encode or decode a string with all possible Caesar cipher shifts.
 */
public class AllCaesar {

  /**
   * Main method to process command line arguments for encoding or decoding.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);

    // Check if the number of command line arguments is correct
    if (args.length != 2) {
      handleError("Incorrect number of parameters", pen);
      return;
    } // end of 'if' block checking argument count

    String action = args[0];
    String inputString = args[1];

    // Validate the action parameter
    if (!action.equals("encode") && !action.equals("decode")) {
      handleError("Error: Incorrect action parameter", pen);
      return;
    } // end of 'if' block checking action parameter

    // Validate the input string
    if (!inputString.matches("[a-z]+") && !inputString.isEmpty()) {
      handleError("Input string contains invalid characters", pen);
      return;
    } // end of 'if' block checking input string

    // Process encoding or decoding based on the action parameter
    if (action.equals("encode")) {
      for (char ch = 'a'; ch <= 'z'; ch++) {
        pen.printf("n = %c: %s\n", ch, CipherUtils.caesarEncrypt(inputString, ch));
      } // end of 'for' loop for encoding
    } else if (action.equals("decode")) {
      for (char ch = 'a'; ch <= 'z'; ch++) {
        pen.printf("n = %c: %s\n", ch, CipherUtils.caesarDecrypt(inputString, ch));
      } // end of 'for' loop for decoding
    } // end of 'if' block checking action

    pen.close(); // end of 'main' method
  } // end of 'main' method

/**
 * Handles error printing and closing the PrintWriter.
 *
 * @param errorMessage the error message to display
 * @param pen          the PrintWriter to close
 */
  private static void handleError(String errorMessage, PrintWriter pen) {
    System.err.println("Error: " + errorMessage);
    pen.close();
  } // end of method handleError
} // end of 'AllCaesar' class
