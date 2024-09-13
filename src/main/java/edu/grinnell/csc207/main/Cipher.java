package edu.grinnell.csc207.main;

import java.io.PrintWriter;

import edu.grinnell.csc207.util.CipherUtils;

public class Cipher {
	public static void main(String[] args) {
		PrintWriter pen = new PrintWriter(System.out, true);

		String action = null;
		String cipher = null;
		String inputString = null;
		String key = null;
    Boolean check = false;

    if(args.length != 4) {
			System.err.println("Error: Expect 4 parameters, received " + args.length);
		}

		for(int i = 0; i < args.length; i++) {
			String arg = args[i];

			if(arg.equals("-encode") || arg.equals("-decode")) {
				action = arg;
			} else if (arg.equals("-caesar") || arg.equals("-vigenere")) {
				//if the argument is a single character, it might be the key for (for Caesar)
				cipher = arg;
			} else if(check){
        key = arg;
      } else {
				//otherwise, assume it's the string to encode or decode
				inputString = arg;
        check = true;
			}

		}

    // error handling
		if ((cipher.equals("-caesar") && key.length() != 1)) {
      System.err.println("Error: caesar ciphers require a one-chracter key");
      pen.close();
    }

		// Switch between action and cipher type
    if (action.equals("-encode")) {
      if (cipher.equals("-caesar")) {
        char ch = key.charAt(0);
        pen.printf("%s\n", CipherUtils.caesarEncrypt(inputString, ch));
      }
      else if (cipher.equals("-vigenere")) {
        pen.printf("%s\n", CipherUtils.vigenereEncrypt(inputString, key));
        pen.printf("here");
      } else {
        System.err.println("Error: Incorrect number of parameters");
      }
    }

    if (action.equals("-decode")) {
      if (cipher.equals("-caesar")) {
          char ch = key.charAt(0);
          pen.printf("%s\n", CipherUtils.caesarDecrypt(inputString, ch));
        }
      else if (cipher.equals("-vigenere")) {
        pen.printf("%s\n", CipherUtils.vigenereDecrypt(inputString, key));
      } else {
        System.err.println("Error: Incorrect number of parameters");
      }
    }

    pen.close();
  }
}



		// switch (action) {
		//   case "-encode":System.err.println("Error: Incorrect number of parameters");"n = %c: %s\n", ch, CipherUtils.caesarEncrypt(inputString, ch));
    //         }
    //         break;
    //       case "-vigenere":
    //         for (char ch = 'a'; ch <= 'z'; ch++) {
    //           pen.printf("n = %c: %s\n", ch, CipherUtils.vigenereEncrypt(inputString, key));
    //         }
    //         break;
    //       default:
    //         System.err.println("Error: Incorrect number of parameters");
    //       }
    //       break;

    //   case "-decode":
    //     switch (cipher) {
    //       case "-caesar":
    //         for (char ch = 'a'; ch <= 'z'; ch++) {
    //           pen.printf("n = %c: %s\n", ch, CipherUtils.caesarDecrypt(inputString, ch));
    //         }
    //         break;
    //       case "-vigenere":
    //         for(char ch = 'a'; ch <= 'z'; ch++){
    //           pen.printf("n = %c: %s\n", ch, CipherUtils.vigenereDecrypt(inputString, key));
    //         }
    //         break;
    //       default:
    //         System.errSystem.err.println("Error: Incorrect number of parameters");tln("Error: Incorrect number of parameters");
    //   }





