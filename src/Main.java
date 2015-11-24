import jdk.nashorn.internal.runtime.Debug;

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
        System.out.println("Valid: " + program.isValid());
        //program.execute();
    }
}
