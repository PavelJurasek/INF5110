package syntaxtree.expr;

import syntaxtree.Expr;
import typesystem.TypeChecker;
import typesystem.TypeError;
import bytecode.instructions.*;
import bytecode.CodeProcedure;
import java.util.Hashtable;

public class LogOpExpr extends BinaryExpr {

    public LogOpExpr(Expr left, String op, Expr right) {
        super(left, op, right);
    }

    @Override
    protected String getAstName() {
        return "LOG_OP";
    }

    @Override
    public String getType() {
        return "bool";
    }

    @Override
    public void setType(String type) {

    }

    @Override
    public void setType(Hashtable<String, String> types) throws TypeError {
        left.setType(types);
        right.setType(types);

        if (!left.getType().equals("bool")) {
            throw new TypeError("First argument of logic operation invalid: bool expected, "+ left.getType() +" given.");
        }

        if (!right.getType().equals("bool")) {
            throw new TypeError("Second argument of logic operation invalid: bool expected, "+ right.getType() +" given.");
        }
    }

    @Override
    public void generateCode(CodeProcedure codeprocedure){
      left.generateCode(codeprocedure);
      right.generateCode(codeprocedure);
      if(op.equals("&&")){
        codeprocedure.addInstruction(new AND());
      }
      if(op.equals("||")){
        codeprocedure.addInstruction(new OR());
      }
    }
}
