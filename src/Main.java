import javafx.util.Pair;
import jdk.nashorn.internal.runtime.Debug;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Andrew Becker and Costadinos Argiris
 * Class: Main
 * This class is the main class used to check a programs validity, as well as begin the execution of a program
 */

public class Main {

    public static final String TOKEN_FILE = "token.dat";

    public static void main(String[] args) {
        TokenAnalyzer analyzer = new TokenAnalyzer(TOKEN_FILE);
        analyzer.initialize();
        TokenList tokens = analyzer.getTokens();
        analyzer.printListOfTokens();
        prog program = new prog(tokens);

        if (program.isValid()) {
            System.out.println("\r\nProgram Validated!\r\n\r\nExecuting Program:\r\n");
            Variables variables = new Variables();
            program.execute(variables);
            variables.print();
            analyzer.printListOfTokens();
        } else{
            System.out.println("The Program is NOT valid!");
        }
    }
}
