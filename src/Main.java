import javafx.util.Pair;
import jdk.nashorn.internal.runtime.Debug;

import java.util.ArrayList;

/**
 * Created by andy on 11/21/2015.
 */

public class Main {

    public static final String TOKEN_FILE = "C:\\Users\\andy\\IdeaProjects\\Descarte Interpreter\\token.dat";

    public static void main(String[] args) {
        TokenAnalyzer analyzer = new TokenAnalyzer(TOKEN_FILE);
        analyzer.initialize();
        TokenList tokens = analyzer.getTokens();
        prog program = new prog(tokens);

        if (program.isValid()) {
            System.out.println("\nProgram Validated!\n\nExecuting Program:\n");
            program.execute(new Variables());
        } else{
            System.out.println("The Program is NOT valid!");
        }
    }
}
