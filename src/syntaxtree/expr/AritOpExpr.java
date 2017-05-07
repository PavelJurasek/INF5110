package syntaxtree.expr;

import syntaxtree.Expr;
import typesystem.TypeChecker;
import typesystem.TypeError;
import java.util.Hashtable;
import bytecode.instructions.*;
import bytecode.CodeProcedure;
import bytecode.CodeFile;
import bytecode.CodeStruct;

public class AritOpExpr extends BinaryExpr {

    private String type;

    public AritOpExpr(Expr left, String op, Expr right) {
        super(left, op, right);
    }

    @Override
    protected String getAstName() {
        return "ARIT_OP";
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public void setType(String type) {

    }

    @Override
    public void setType(Hashtable<String, String> types) throws TypeError {
        left.setType(types);
        right.setType(types);

        String[] allowed = {"float", "int"};

        if (!TypeChecker.isOneOf(left.getType(), allowed)) {
            throw new TypeError("LHS of arithmetic operation must be int or float, "+ left.getType() +" given.");
        }

        if (!TypeChecker.isOneOf(right.getType(), allowed)) {
            throw new TypeError("RHS of arithmetic operation must be int or float, "+ right.getType() +" given.");
        }

        if (left.getType().equals("float") || right.getType().equals("float") || this.op.equals("#")) {
            this.type = "float";
        } else {
            this.type = "int";
        }

//        if (!TypeChecker.isValid(left.getType(), right.getType())) {
//            throw new TypeError("Invalid arithmetic operation arguments: "+ left.getType() +" and "+ right.getType());
//        }
    }

    @Override
    public void generateCode(CodeFile cf, CodeProcedure cp, CodeStruct cs){
        left.generateCode(cf, cp, null);
        right.generateCode(cf, cp, null);
        if(op.equals("+")){
            cp.addInstruction(new ADD());
        }
        if(op.equals("-")){
            cp.addInstruction(new SUB());
        }
        if(op.equals("*")){
            cp.addInstruction(new MUL());
        }
        if(op.equals("/")){
            cp.addInstruction(new DIV());
        }
        if(op.equals("#")){
            cp.addInstruction(new EXP());
        }
        cf.updateProcedure(cp);
    }
}
