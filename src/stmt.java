/**
 * Created by andy on 11/22/2015.
 */
public class stmt implements Expression{
    boolean valid = false;

    public enum Type {
        IF,
        LOOP,
        BREAK,
        ASSIGNMENT,
        READ,
        PRINT,
        NONE
    }

    Type type;

    public stmt(TokenList tokens){
        if(tokens.size() > 0) {
            if (tokens.get(0).equals("IF")) {
                System.out.print("IF STATEMENT- ");
                tokens.printList();
                this.type = Type.IF;
                ifStmt statement = new ifStmt(tokens);
            } else if (tokens.get(0).equals("LOOP")) {
                System.out.print("LOOP STATEMENT- ");
                tokens.printList();
                this.type = Type.LOOP;
            } else if (tokens.get(0).equals("BREAK")) {
                System.out.print("BREAK STATEMENT- ");
                tokens.printList();
                this.type = Type.BREAK;

            } else if (tokens.get(0).equals("ID")) {
                System.out.print("ASSIGNMENT STATEMENT- ");
                tokens.printList();
                this.type = Type.ASSIGNMENT;

            } else if (tokens.get(0).equals("READ")) {
                System.out.print("READ STATEMENT- ");
                tokens.printList();
                this.type = Type.READ;

            } else if (tokens.get(0).equals("PRINT")) {
                System.out.print("PRINT STATEMENT: ");
                tokens.printList();
                this.type = Type.PRINT;
            }
        }
    }

    public stmt(){
        this.type = Type.NONE;
    }

    public void execute(){
        switch(type){
            case IF:
                System.out.println("Executing IF Statement");
                break;
            case LOOP:
                System.out.println("Executing LOOP Statement");
                break;
            case BREAK:
                System.out.println("Executing BREAK Statement");
                break;
            case ASSIGNMENT:
                System.out.println("Executing ASSIGNMENT Statement");
                break;
            case READ:
                System.out.println("Executing READ Statement");
                break;
            case PRINT:
                System.out.println("Executing PRINT Statement");
                break;
        }
    }
    @Override
    public boolean isValid() {
        return valid;
    }
}
