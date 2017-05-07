package syntaxtree.expr;

import syntaxtree.stmt.CallStmt;
import typesystem.TypeError;
import bytecode.CodeProcedure;
import bytecode.CodeFile;
import bytecode.CodeStruct;
import java.util.Hashtable;

public class CallExpr extends UnaryExpr {

    private CallStmt callStmt;

    public CallExpr(CallStmt callStmt) {
        this.callStmt = callStmt;
    }

    public CallStmt getCallStmt() {
        return this.callStmt;
    }

    @Override
    public String printAst(int depth) {
        return callStmt.printAst(depth);
    }

    @Override
    public String getType() {
        return callStmt.getType();
    }

    @Override
    public void setType(String type) {

    }

    @Override
    public void setType(Hashtable<String, String> types) {

    }

    @Override
    public void generateCode(CodeFile cf, CodeProcedure cp, CodeStruct cs){
        callStmt.generateCode(cf, cp, null);
    }

}
