import java.util.Scanner;

/**
 * Created by Andrew Becker and Costadinos Argiris
 * Class: elsePart
 * This class is a node used for simple print and read statements
 */
public class idListTail implements Expression {
    idListTail idListTail;
    String token;
    Scanner input;

    public idListTail(TokenList tokens){
        System.out.println("IDLISTTAIL-----");
        tokens.printList();
        System.out.println();

        tokens.remove(0);
        token = tokens.get(0);
        int firstComma = tokens.indexOf(",");
        if(firstComma != -1 && firstComma > 0){
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
        if(variables.checkVariable(token)){
            System.out.print(variables.getValue(token) + " ");
        } else{
            System.out.print("null ");
        }
        if(idListTail != null){
            idListTail.execute(variables);
        }
    }

    public void executeRead(Variables variables){
        try{
            input = new Scanner(System.in);
            double d = input.nextDouble();
            variables.addVariable(token, d);
            input.close();
            if(idListTail != null) {
                idListTail.executeRead(variables);
            }
        } catch(Exception ex){
            System.out.println(ex);
        }
    }
}
