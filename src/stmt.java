/**
 * Created by andy on 11/22/2015.
 */
public class stmt implements Expression{
    boolean valid = false;
    stmt statement;

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
        System.out.println("STMT-----");
        tokens.printList();
        System.out.println();

        if(tokens.size() > 0) {
            if (tokens.get(0).equals("IF")) {
                this.type = Type.IF;
                statement = new ifStmt(tokens);
            } else if (tokens.get(0).equals("LOOP")) {
                this.type = Type.LOOP;
            } else if (tokens.get(0).equals("BREAK")) {
                this.type = Type.BREAK;
            } else if (tokens.get(0).equals("READ")) {
                this.type = Type.READ;
                statement = new readStmt(tokens);
            } else if (tokens.get(0).equals("PRINT")) {
                this.type = Type.PRINT;
                statement = new printStmt(tokens);
            } else {
                this.type = Type.ASSIGNMENT;
                statement = new assignStmt(tokens);
            }
        }
    }

    public stmt(){
        this.type = Type.NONE;
        valid = true;
    }

    public void execute(Variables variables){
        switch(type){
            case IF:
                statement.execute(variables);
                break;
            case LOOP:
                System.out.println("Executing LOOP Statement");
                break;
            case BREAK:
                System.out.println("Executing BREAK Statement");
                break;
            case ASSIGNMENT:
                statement.execute(variables);
                break;
            case READ:
                statement.execute(variables);
                break;
            case PRINT:
                statement.execute(variables);
                break;
        }
    }
    @Override
    public boolean isValid() {
        System.out.println("CHECKING STATEMENT ISVALID()");
        if(type == Type.NONE){
            return true;
        }
        if(statement != null && statement.isValid()){
            return true;
        }
  /*      if(valid){
            System.out.println("STATEMENT DEEMED VALID");
            return true;
        }*/
        System.out.println("STMT NOT VALID");
        return false;
    }
}
