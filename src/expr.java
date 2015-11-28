/**
 * Created by andy on 11/23/2015.
 */
public class expr implements Expression{
    boolTerm boolTerm;
    boolTermTail boolTermTail;

    public expr(TokenList tokens){
        System.out.println("EXPR-----");
        tokens.printList();
        System.out.println();

        int firstOr = tokens.indexOf("OR");
        if(firstOr == -1){
            boolTerm = new boolTerm(tokens);
            boolTermTail = new boolTermTail();
        }
        else{
            boolTerm = new boolTerm(tokens.between(0, firstOr));
            boolTermTail = new boolTermTail(tokens.between(firstOr, tokens.size()));
        }
    }

    @Override
    public boolean isValid() {
        System.out.println("CHECKING EXPR ISVALID()");
        if(boolTerm.isValid() && boolTermTail.isValid()){
            return true;
        }
        System.out.println("EXPR NOT VALID");
        return false;
    }

    public double executeDouble(Variables variables){
        return boolTerm.executeDouble(variables);
    }

    public String executeString(Variables variables){
        if(boolTermTail.executable){
            if(boolTerm.executeString(variables).equals("true") || boolTermTail.executeString(variables).equals("true")){
                return "true";
            } else{
                System.out.println(boolTerm.executeString(variables) + boolTermTail.executeString(variables));
                return boolTerm.executeString(variables) + boolTermTail.executeString(variables);
            }
        }
        else{
            if(boolTerm.executeString(variables).equals("true")){
                return "true";
            }
        }
        return "false";
    }
}
