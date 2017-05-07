package syntaxtree.expr;

import syntaxtree.Expr;
import typesystem.TypeChecker;
import typesystem.TypeError;
import bytecode.instructions.*;
import bytecode.CodeProcedure;
import bytecode.CodeFile;
import bytecode.CodeStruct;

import java.util.Hashtable;

public class RelOpExpr extends BinaryExpr {

    public RelOpExpr(Expr left, String op, Expr right) {
        super(left, op, right);
    }

    @Override
    protected String getAstName() {
        return "REL_OP";
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

//        if (!left.getType().equals("int") && !left.getType().equals("float")) {
//            System.out.println(types);
//            throw new TypeError("First argument of relation operation invalid: int or float expected, "+ left.getType() +" given.");
//        }
//
//        if (!right.getType().equals("int") && !right.getType().equals("float")) {
//            throw new TypeError("Second argument of relation operation invalid: int or float expected, "+ right.getType() +" given.");
//        }

        if (!TypeChecker.isValid(left.getType(), right.getType())) {
            throw new TypeError("Invalid relation operation arguments: "+ left.getType() +" and "+ right.getType());
        }
    }

    @Override
    public void generateCode(CodeFile cf, CodeProcedure cp, CodeStruct cs){
      left.generateCode(cf, cp, null);
      right.generateCode(cf, cp, null);
      if(op.equals("=")){
        cp.addInstruction(new EQ());
      }
      if(op.equals("<>")){
        cp.addInstruction(new NEQ());
      }
      if(op.equals("<")){
        cp.addInstruction(new LT());
      }
      if(op.equals("<=")){
        cp.addInstruction(new LTEQ());
      }
      if(op.equals(">")){
        cp.addInstruction(new GT());
      }
      if(op.equals(">=")){
        cp.addInstruction(new GTEQ());
      }
    }

}
