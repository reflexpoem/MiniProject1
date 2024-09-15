package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import edu.grinnell.csc207.util.CipherUtils;

/**
 * Class to handle encoding and decoding of strings using different ciphers.
 * Supports Caesar and Vigenère ciphers for encoding and decoding.
 */
public class Cipher {

  /**
   * Constant for the number of expected command line arguments.
   * This value is used to validate that the correct number of parameters
   * are provided when running the program.
   */
  private static final int ARG_COUNT = 4;

  /**
   * Main method to process command line arguments for encoding or decoding.
   *
   * @param args command line arguments
   *             args[0] - action to perform (-encode or -decode)
   *             args[1] - cipher type to use (-caesar or -vigenere)
   *             args[2] - input string to encode or decode
   *             args[3] - key for the cipher
   */
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);

    String action = null;
    String cipher = null;
    String inputString = null;
    String key = null;
    boolean check = false;

    // Check if the number of command line arguments is correct
    if (args.length != ARG_COUNT) {
      handleError("Error: Expect " + ARG_COUNT + " parameters, received " + args.length, pen);
      return;
    } // end of 'if' block checking argument count

    // Parse command line arguments
    for (String arg : args) {
      if (arg.equals("-encode") || arg.equals("-decode")) {
        action = arg; // Set action parameter
      } else if (arg.equals("-caesar") || arg.equals("-vigenere")) {
        cipher = arg; // Set cipher type
      } else if (check) {
        key = CipherUtils.checkLowercase(arg); // Set key if already checked
      } else {
        inputString = CipherUtils.checkLowercase(arg); // Set input string
        check = true; // Mark that input string has been set
      } // end of 'if' block for initializing args
    } // end of 'for' loop parsing arguments

    // Error handling for missing or invalid parameters
    if (cipher.equals("-caesar") && (key == null || key.length() != 1)) {
      handleError("Error: Caesar ciphers require a one-character key", pen);
      return;
    } // end of 'if' block checking Caesar cipher key length

    if (key == null || key.isEmpty()) {
      handleError("Error: Empty or missing key", pen);
      return;
    } // end of 'if' block checking empty or null key

    if (inputString == null) {
      handleError("Error: Invalid input string", pen);
      return;
    } // end of 'if' block checking null input string

    // Perform the encoding or decoding based on the action and cipher type
    if (action.equals("-encode")) {
      handleEncode(cipher, key, inputString, pen);
    } else if (action.equals("-decode")) {
      handleDecode(cipher, key, inputString, pen);
    } else {
      System.err.println("Error: Invalid action parameter");
    } // end of 'if' block for action

    pen.close(); // end of 'main' method
  } // end of 'main' method

  /**
   * Handles encoding based on the cipher type.
   *
   * @param cipher       the type of cipher to use (-caesar or -vigenere)
   * @param key          the key for the cipher
   * @param inputString  the string to encode
   * @param pen          the PrintWriter to output the result
   */
  private static void handleEncode(String cipher, String key, String inputString, PrintWriter pen) {
    if (cipher.equals("-caesar")) {
      char ch = key.charAt(0);
      pen.printf("%s\n", CipherUtils.caesarEncrypt(inputString, ch));
    } else if (cipher.equals("-vigenere")) {
      pen.printf("%s\n", CipherUtils.vigenereEncrypt(inputString, key));
    } else {
      System.err.println("Error: Invalid cipher type for encoding");
    } // end of 'if' block for cipher type
  } // end of method handleEncode

  /**
   * Handles decoding based on the cipher type.
   *
   * @param cipher       the type of cipher to use (-caesar or -vigenere)
   * @param key          the key for the cipher
   * @param inputString  the string to decode
   * @param pen          the PrintWriter to output the result
   */
  private static void handleDecode(String cipher, String key, String inputString, PrintWriter pen) {
    if (cipher.equals("-caesar")) {
      char ch = key.charAt(0);
      pen.printf("%s\n", CipherUtils.caesarDecrypt(inputString, ch));
    } else if (cipher.equals("-vigenere")) {
      pen.printf("%s\n", CipherUtils.vigenereDecrypt(inputString, key));
    } else {
      System.err.println("Error: Invalid cipher type for decoding");
    } // end of 'if' block for cipher type
  } // end of method handleDecode

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
} // end of 'Cipher' class
