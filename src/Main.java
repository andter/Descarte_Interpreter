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

    public static final String TOKEN_FILE = "C:\\Users\\andre\\IdeaProjects\\Descarte_Interpreter\\token.dat";

    public static void main(String[] args) {
        TokenAnalyzer analyzer = new TokenAnalyzer(TOKEN_FILE);
        analyzer.initialize();
        TokenList tokens = analyzer.getTokens();
        prog program = new prog(tokens);

        if (program.isValid()) {
            System.out.println("\nProgram Validated!\n\nExecuting Program:\n");
            Variables variables = new Variables();
            program.execute(variables);
        } else{
            System.out.println("The Program is NOT valid!");
        }
    }
}
