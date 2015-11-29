import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;


public class TokenAnalyzer {
    private static HashMap<String, Integer> tokensList = new HashMap<String, Integer>();
    private String file;
    private Scanner input;

    public TokenAnalyzer(String file){
        this.file = file;
    }

    public void initialize(){
        populateHashMap();
        createFileReader();
    }

    private void createFileReader(){
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File(file));
        } catch (FileNotFoundException e) {
            System.out.println("File Failed to Load");
            e.printStackTrace();
        }
        input = new Scanner(fis);
        input.useDelimiter("");
    }
    /*
    public String getToken(){
        String currentToken = "";
        boolean foundToken = false;
            while(input.hasNext()){
                String newChar = input.next();
                if(newChar.equals(" ")){
                    if(containsToken(currentToken)){
                        System.out.println("Contains token: " + currentToken + " : " + getTokenValue(currentToken));
                    }
                    else{
                        if(!(currentToken.equals(""))){
                            System.out.println("Doesnt: " + currentToken);
                        }
                    }
                    currentToken = "";
                }
                else{
                    currentToken += newChar;
                    if(containsToken(currentToken)){
                        System.out.println("Contains Token: " + currentToken + " : " + getTokenValue(currentToken));
                        currentToken = "";
                    }
                }
            }
        return null;
    }*/

    public TokenList getTokens(){
        TokenList printList = new TokenList();
        String currentToken = "";
        String nextChar = "";
        boolean foundToken = false;

        while(input.hasNext()){

            if (input.hasNext()){
                nextChar = input.next();
            }

            if(containsToken(currentToken) ){
                if(!currentToken.equals(" ") && containsToken(nextChar) && (nextChar.equals("=") || nextChar.equals(">") || nextChar.equals("+"))){
                    printList.add(getToken(currentToken + nextChar));
                    currentToken = "";
                    continue;
                }
                else {
                    printList.add(getToken(currentToken));
                    currentToken = nextChar;
                    continue;
                }
            }
            if(containsToken(currentToken)){
                printList.add(getToken(currentToken));
                currentToken = nextChar;
                continue;
            }

            if(!containsToken(currentToken) && currentToken.matches("[0-9]+\\.*[0-9]*") && containsToken(nextChar)){
                if (!nextChar.equals(".")){
                    //printList.add("number");
                    printList.add(currentToken);
                    currentToken = nextChar;
                    continue;
                }
                else{
                    String s = input.next();
                    while(input.hasNext() && !containsToken(s)){
                        s = input.next();
                    }
                    //printList.add("number");
                    printList.add(currentToken + s);
                    currentToken = s;
                    continue;
                }
            }

            if(!containsToken(currentToken) && currentToken.matches("\"")){
                if (nextChar.equals("\"")) {
                    printList.add("string");
                    currentToken = input.next();
                    continue;
                }
                else while(input.hasNext()){
                    nextChar = input.next();
                    if (nextChar.equals("\"")) {
                        printList.add("string");
                        currentToken = input.next();
                        break;
                    }
                }
                continue;
            }

            if(!containsToken(currentToken) && currentToken.matches("[a-zA-Z0-9]+") && containsToken(nextChar)){
                printList.add(currentToken);
                currentToken = nextChar;
                continue;
            }

            else{
                currentToken = currentToken+nextChar;
            }
        }
        if(containsToken(currentToken)){
            printList.add(getToken(currentToken));
        }

        printList.removeAll(Arrays.asList(null, ""));
        printList.removeAll(Arrays.asList(null, " "));
        printList.removeAll(Arrays.asList(null, "\n"));
        return printList;
    }


    public static void populateHashMap() {
        tokensList.put("IF", 1);
        tokensList.put("THEN", 2);
        tokensList.put("ELSE", 3);
        tokensList.put("FI", 4);
        tokensList.put("LOOP", 5);
        tokensList.put("BREAK", 6);
        tokensList.put("READ", 7);
        tokensList.put("REPEAT", 8);
        tokensList.put("PRINT", 9);
        tokensList.put("AND", 10);
        tokensList.put("OR", 11);
        tokensList.put(")", 12);
        tokensList.put("(", 13);
        tokensList.put("/", 14);
        tokensList.put("*", 15);
        tokensList.put("-", 16);
        tokensList.put("+", 17);
        tokensList.put("<>", 18);
        tokensList.put(">", 19);
        tokensList.put(">=", 20);
        tokensList.put("=", 21);
        tokensList.put("<=", 22);
        tokensList.put("<", 23);
        tokensList.put(":=", 24);
        tokensList.put(";", 25);
        tokensList.put(" ", 26);
        tokensList.put("\n", 27);
        tokensList.put("identifier", 28);
        tokensList.put("number", 29);
        tokensList.put("string", 30);
        tokensList.put(",", 32);
        tokensList.put(".", 31);
    }

    private static boolean containsToken(String currentToken) {
        for (String token : tokensList.keySet()) {
            if (currentToken.contains(token)) {
                return true;
            }
        }
        return false;
    }

    private static String getToken(String tokenContainer){
        for(String token : tokensList.keySet()){
            if(tokenContainer.contains(token)){
                return token;
            }
        }
        return null;
    }

    public static int getTokenValue(String tokenContainer){
        for(String token : tokensList.keySet()){
            if (tokenContainer.contains(token)) {
                return tokensList.get(token);
            }
        }
        return 0;
    }
}