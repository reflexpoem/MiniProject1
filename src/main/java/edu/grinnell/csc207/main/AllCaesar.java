package edu.grinnell.csc207.main;

import java.io.PrintWriter;

import edu.grinnell.csc207.util.CipherUtils;

public class AllCaesar {
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);
    
    if (args.length != 2) {
      System.err.println("Error: Incorrect number of parameters");
      pen.close();
      return;
    }
    
    String action = args[0];
    String inputString = args[1];
    
    if(!action.equals("encode") && !action.equals("decode")){
      System.err.println("Error: Incorrect number of parameters");
      return;
    }

    if(!inputString.matches("[a-z]+") && !inputString.isEmpty()){
      System.err.println("Error: Incorrect number of parameters");
      return;
    }


    if (action.equals("encode")) {
      for (char ch = 'a'; ch <= 'z'; ch++) {
        pen.printf("n = %c: %s\n", ch, CipherUtils.caesarEncrypt(inputString, ch));
      }
    } else if (action.equals("decode")) {
      for (char ch = 'a'; ch <= 'z'; ch++) {
        pen.printf("n = %c: %s\n", ch, CipherUtils.caesarDecrypt(inputString, ch));
      }
    } 
    
    pen.close();
  }
}