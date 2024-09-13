package edu.grinnell.csc207.util;

public class CipherUtils {
  
  private static int letter2int(char letter) {
    int result = ((int)letter - 97) % 26; 

		return result;

  }

  private static char int2letter(int i) {
    return (char) (i + 97);

  }

  public static String caesarEncrypt(String str, char letter) {

	if (str == "")
		return "";

    	int key = letter2int(letter);
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < str.length(); i++) {
			int sum = key + letter2int(str.charAt(i));
			sum = sum % 26;
			sb.append(Character. toString(int2letter(sum)));
		}

		return sb.toString(); // STUB

  }

  public static String caesarDecrypt(String str, char letter) {

	if (str == "")
		return "";

    int key = letter2int(letter);
		StringBuilder sb = new StringBuilder();

		for(int i = 0; i < str.length(); i++) {
			int sum = letter2int(str.charAt(i)) - key;
			sum = sum % 26;

			if(sum < 0)
				sum += 26;
			sb.append(Character. toString(int2letter(sum)));
		}
		return sb.toString(); // STUB

  }

  public static String vigenereEncrypt(String str, String key) {

	if (str == "")
		return "";

    StringBuilder builder = new StringBuilder();
		
		String repeated = repeatStr(key, str.length());
		
		for(int i = 0; i < str.length(); i++) {
		    
			int sum = letter2int(str.charAt(i)) + letter2int(repeated.charAt(i));
			sum = sum % 26;
			builder.append(Character. toString(int2letter(sum)));

		}

		return builder.toString();

  }

  public static String vigenereDecrypt(String str, String key) {

	if (str == "")
		return "";

    StringBuilder builder = new StringBuilder();
		
		String repeated = repeatStr(key, str.length());
		
		for(int i = 0; i < str.length(); i++) {
		    
			int sum = letter2int(str.charAt(i)) - letter2int(repeated.charAt(i));
			sum = sum % 26;
			
			if (sum < 0)
			    sum += 26;
			    
			builder.append(Character. toString(int2letter(sum)));
		}

		return builder.toString();

  }
  public static String repeatStr(String str, int limit) {
		StringBuilder builder = new StringBuilder();

		// Append the string repeatedly until the desired length is reached
		while (builder.length() + str.length() <= limit) {
			builder.append(str);
		}

		if (builder.length() < limit) {
			builder.append(str, 0, limit - builder.length());
		}

		return builder.toString();


	}


}
