/**
 * Created by andy on 11/23/2015.
 */
public class boolTerm implements Expression {
    boolFactor boolFactor;
    boolFactorTail boolFactorTail;

    public boolTerm(TokenList tokens){
        System.out.println("BOOLTERM-----");
        tokens.printList();
        System.out.println();

        int firstAnd = tokens.indexOf("AND");
        if(firstAnd == -1){
            boolFactor = new boolFactor(tokens);
            boolFactorTail = new boolFactorTail();
        }
        else{
            boolFactor = new boolFactor(tokens.between(0, firstAnd));
            boolFactorTail = new boolFactorTail(tokens.between(firstAnd, tokens.size()));
        }
    }

    @Override
    public boolean isValid() {
        System.out.println("CHECKING BOOLTERM ISVALID()");
        if(boolFactor.isValid() && boolFactorTail.isValid()) {
            return true;
        }
        System.out.println("BOOLTERM NOT VALID");
        return false;
    }


    public double executeDouble(Variables variables){
        return boolFactor.executeDouble(variables);
    }

    public boolean executeBoolean(Variables variables){
        if(boolFactorTail.executable){
            if(boolFactor.executeBoolean(variables) && boolFactorTail.executeBoolean(variables)){
                return true;
            } else{
                return false;
            }
        }
        else{
            return boolFactor.executeBoolean(variables);
        }
    }
}
