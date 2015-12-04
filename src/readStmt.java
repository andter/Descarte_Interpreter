import java.util.Scanner;
import java.io.*;

/**
 * Created by Andrew Becker and Costadinos Argiris
 * Class: readStmt
 * This class is a node built for the special purpose of reading in input from the user and assigning said input to variables.
 */
public class readStmt extends stmt implements Expression{
    idListTail idListTail;
    String token;

    Scanner input;

    public readStmt(TokenList tokens){
        System.out.println("READSTMT-----");
        tokens.printList();
        System.out.println();

        token = tokens.get(1);
        int firstComma = tokens.indexOf(",");
        if(firstComma != -1 && firstComma > 1){
            idListTail = new idListTail(tokens.between(firstComma, tokens.size()));
        }
    }

    @Override
    public boolean isValid() {
        if(idListTail != null){
            if(idListTail.isValid()){
                return true;
            }
        }
        return true;
    }

    public void execute(Variables variables){
        try{
            input = new Scanner(System.in);
            double d = input.nextDouble();
            variables.addVariable(token, d);
            if(idListTail != null) {
                idListTail.executeRead(variables);
            }
        } catch(Exception ex){
            System.out.println(ex);
        }
    }
}
