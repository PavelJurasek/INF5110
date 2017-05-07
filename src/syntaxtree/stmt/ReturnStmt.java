package syntaxtree.stmt;

import syntaxtree.Expr;
import syntaxtree.Stmt;
import syntaxtree.StringUtils;
import syntaxtree.decl.ProcDecl;
import typesystem.TypeChecker;
import typesystem.TypeError;
import bytecode.CodeFile;
import bytecode.CodeProcedure;
import bytecode.CodeStruct;
import bytecode.instructions.RETURN;
import java.util.Hashtable;

public class ReturnStmt extends Stmt {

    private Expr expr;

    private String expectedType;

    public ReturnStmt(Expr expr) {
        this.expr = expr;
    }

    public ReturnStmt() {

    }

    @Override
    public String printAst(int depth) {
        if (expr != null) {
            return StringUtils.repeat('\t', depth) + "(RETURN_STMT "+ expr.printAst(depth) +")\n";
        }

        return StringUtils.repeat('\t', depth) + "(RETURN_STMT)\n";
    }

    @Override
    public String getType() {
        return this.expr == null ? "void" : this.expr.getType();
    }

    public void setExpectedType(String expectedType) {
        this.expectedType = expectedType;
    }

    @Override
    public void typeCheck(Hashtable<String, String> types, Hashtable<String, ProcDecl> procedures) throws TypeError {
        if (this.expr != null) {
            this.expr.setType(types);
        }

        if (!TypeChecker.isValid(this.expectedType, this.getType())) {
            throw new TypeError(this.expectedType +" should be returned, "+ this.getType() +" given.");
        }
    }

    @Override
    public void generateCode(CodeFile cf, CodeProcedure cp, CodeStruct cs) {
        if (expr != null) {
            expr.generateCode(cf, cp, cs);
        }
        cp.addInstruction(new RETURN());
    }

}
