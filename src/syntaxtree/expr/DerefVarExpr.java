package syntaxtree.expr;

import bytecode.CodeFile;
import bytecode.CodeProcedure;
import bytecode.CodeStruct;

public class DerefVarExpr extends VarExpr {

    VarExpr expr;

    public DerefVarExpr(VarExpr var) {
        this.expr = var;
    }

    @Override
    public String printAst(int depth) {
        return "(DEREF "+ expr.printAst(depth+1) +")";
    }

    @Override
    String getName() {
        return expr.getName();
    }

    @Override
    public String getType() {
        if (super.getType().startsWith("(REF ")) {
            String tmp = super.getType().substring(5);

            return tmp.substring(0, tmp.length()-1);
        }

        return "(DEREF "+super.getType()+")";
    }

    @Override
    public void generateCode(CodeFile cf, CodeProcedure cp, CodeStruct cs) {}

}
