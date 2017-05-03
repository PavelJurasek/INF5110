package syntaxtree.expr;

import syntaxtree.Expr;
import syntaxtree.StringUtils;
import typesystem.TypeError;
import bytecode.CodeProcedure;
import java.util.Hashtable;

public class ParenthesesExpr extends UnaryExpr {

    Expr expr;

    public ParenthesesExpr(Expr expr) {
        this.expr = expr;
    }

    @Override
    public String printAst(int depth) {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.repeat('\t', depth));
        sb.append("(PARENTHESES \n");
        sb.append(expr.printAst(depth+1));
        sb.append("\n");
        sb.append(StringUtils.repeat('\t', depth));
        sb.append(")");

        return sb.toString();
    }

    @Override
    public String getType() {
        return expr.getType();
    }

    @Override
    public void setType(String type) throws TypeError {
        expr.setType(type);
    }

    @Override
    public void setType(Hashtable<String, String> types) throws TypeError {
        expr.setType(types);
    }

    @Override
    public void generateCode(CodeProcedure codeprocedure){
      expr.generateCode(codeprocedure);
    }

}
