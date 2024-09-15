package edu.grinnell.csc207.util;

/**
 * Utility class for various cipher operations including Caesar and Vigenère ciphers.
 */
public class CipherUtils {

/**
 * ASCII value of 'a'.
 */
  private static final int ASCII_A = 97;

/**
 * Number of letters in the alphabet.
 */
  private static final int ALPHABET_SIZE = 26;

/**
 * Converts a lowercase letter to an integer (0-25).
 *
 * @param letter the lowercase letter to convert
 * @return the integer representation of the letter
 */
  private static int letter2int(char letter) {
    int result = ((int) letter - ASCII_A) % ALPHABET_SIZE;
    return result;
  } // end of 'letter2int' method

/**
 * Converts an integer (0-25) to a lowercase letter.
 *
 * @param i the integer to convert
 * @return the corresponding lowercase letter
 */
  private static char int2letter(int i) {
    return (char) (i + ASCII_A);
  } // end of 'int2letter' method


/**
 * Helper method to apply modulus and ensure positive result for character shifting.
 *
 * @param sum the result of shifting operation
 * @return a value wrapped around between 0 and ALPHABET_SIZE - 1
 */
  private static int convertSum(int sum) {
    sum = sum % ALPHABET_SIZE;
    if (sum < 0) {
      sum += ALPHABET_SIZE;
    } // end of if block to check whether it is negative
    return sum;
  } // end of 'convertSum' method

/**
 * Encrypts a string using the Caesar cipher with the given shift.
 *
 * @param str the string to encrypt
 * @param letter the shift character
 * @return the encrypted string
 */
  public static String caesarEncrypt(String str, char letter) {
    if (str.equals("")) {
      return "";
    } // end of if to check input is empty

    int key = letter2int(letter);
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < str.length(); i++) {
      int sum = key + letter2int(str.charAt(i));
      sum = convertSum(sum);
      sb.append(Character.toString(int2letter(sum)));
    } // end of for loop to combine the key and letter

    return sb.toString();
  } // end of 'caesarEncrypt' method

/**
 * Decrypts a string encrypted with the Caesar cipher using the given shift.
 *
 * @param str the string to decrypt
 * @param letter the shift character
 * @return the decrypted string
 */
  public static String caesarDecrypt(String str, char letter) {
    if (str.equals("")) {
      return "";
    } // end of if to check input is empty

    int key = letter2int(letter);
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < str.length(); i++) {
      int sum = letter2int(str.charAt(i)) - key;
      sum = convertSum(sum);
      sb.append(Character.toString(int2letter(sum)));
    } // end of for loop to substruct the key from letter
    return sb.toString();
  } // end of 'caesarDecrypt' method

/**
 * Encrypts a string using the Vigenère cipher with the given key.
 *
 * @param str the string to encrypt
 * @param key the key for encryption
 * @return the encrypted string
 */
  public static String vigenereEncrypt(String str, String key) {
    if (str.equals("")) {
      return "";
    } // end of if to check input is empty

    StringBuilder builder = new StringBuilder();
    String repeated = repeatStr(key, str.length());

    for (int i = 0; i < str.length(); i++) {
      int sum = letter2int(str.charAt(i)) + letter2int(repeated.charAt(i));
      sum = convertSum(sum);
      builder.append(Character.toString(int2letter(sum)));
    } // end of for loop to add the key into letter

    return builder.toString();
  } // end of 'vigenereEncrypt' method

/**
 * Decrypts a string encrypted with the Vigenère cipher using the given key.
 *
 * @param str the string to decrypt
 * @param key the key for decryption
 * @return the decrypted string
 */
  public static String vigenereDecrypt(String str, String key) {
    if (str.equals("")) {
      return "";
    } // end of if to check input is empty

    StringBuilder builder = new StringBuilder();
    String repeated = repeatStr(key, str.length());

    for (int i = 0; i < str.length(); i++) {
      int sum = letter2int(str.charAt(i)) - letter2int(repeated.charAt(i));
      sum = convertSum(sum);

      builder.append(Character.toString(int2letter(sum)));
    }  // end of for loop to substruct the key from letter

    return builder.toString();
  } // end of 'vigenereDecrypt' method

/**
 * Repeats a string to a specified length.
 *
 * @param str the string to repeat
 * @param limit the desired length
 * @return the repeated string
 */
  public static String repeatStr(String str, int limit) {
    StringBuilder builder = new StringBuilder();

    // Append the string repeatedly until the desired length is reached
    while (builder.length() + str.length() <= limit) {
      builder.append(str);
    } // end of while loop to make a repeat string

    if (builder.length() < limit) {
      builder.append(str, 0, limit - builder.length());
    } // end of if block to $$$

    return builder.toString();
  } // end of 'repeatStr' method

/**
 * Checks if a string is non-null and contains only lowercase letters.
 *
 * @param s the string to check
 * @return the string if valid, otherwise null
 */
  public static String checkLowercase(String s) {
    // Check if the string s is not null and contains only lowercase letters
    if (s != null && s.chars().allMatch(Character::isLowerCase)
        && s.chars().allMatch(Character::isLetter)) {
      return s;
    } else {
      return null;
    } // end of if condition that check whether its lowercase
  } // end of 'checkLowercase' method
} // end of 'CipherUtils' class
