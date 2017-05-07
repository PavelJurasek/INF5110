package syntaxtree.expr;

import syntaxtree.Expr;
import syntaxtree.StringUtils;
import typesystem.TypeError;
import bytecode.instructions.*;
import bytecode.CodeProcedure;
import bytecode.CodeFile;
import bytecode.CodeStruct;
import java.util.Hashtable;

public class NotExpr extends UnaryExpr {

    Expr expr;

    public NotExpr(Expr expr) {
        this.expr = expr;
    }

    @Override
    public String printAst(int depth) {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.repeat('\t', depth));
        sb.append("(NOT \n");
        sb.append(expr.printAst(depth+1));
        sb.append("\n");
        sb.append(StringUtils.repeat('\t', depth));
        sb.append(")");

        return sb.toString();
    }

    @Override
    public String getType() {
        return "bool";
    }

    @Override
    public void setType(String type) {
        // has type on its own
    }

    @Override
    public void setType(Hashtable<String, String> types) throws TypeError {
        this.expr.setType(types);

        if (!this.expr.getType().equals("bool")) {
            throw new TypeError("Not: operand type must be bool, "+ this.expr.getType() +" given.");
        }
    }

    @Override
    public void generateCode(CodeFile cf, CodeProcedure cp, CodeStruct cs){
      expr.generateCode(cf, cp, null);
      cp.addInstruction(new NOT());
    }


}
