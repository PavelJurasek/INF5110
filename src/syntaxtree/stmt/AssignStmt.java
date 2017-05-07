package syntaxtree.stmt;

import syntaxtree.Expr;
import syntaxtree.Stmt;
import syntaxtree.StringUtils;
import syntaxtree.decl.ProcDecl;
import syntaxtree.expr.CallExpr;
import syntaxtree.expr.VarExpr;
import typesystem.TypeChecker;
import typesystem.TypeError;
import bytecode.CodeFile;
import bytecode.CodeProcedure;
import bytecode.CodeStruct;
import bytecode.instructions.STORELOCAL;
import bytecode.instructions.STOREGLOBAL;
import java.util.Hashtable;

public class AssignStmt extends Stmt {

    private VarExpr var;

    private Expr expr;

    public AssignStmt(VarExpr var, Expr expr) {
        this.var = var;
        this.expr = expr;
    }

    @Override
    public String printAst(int depth) {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.repeat('\t', depth));
        sb.append("(ASSIGN_STMT\n");
        sb.append(StringUtils.repeat('\t', depth+1));
        sb.append(var.printAst(depth+1));
        sb.append("\n");
        sb.append(StringUtils.repeat('\t', depth+1));
        sb.append(expr.printAst(depth+1));
        sb.append("\n");
        sb.append(StringUtils.repeat('\t', depth));
        sb.append(")");

        return sb.toString();
    }

    @Override
    public void typeCheck(Hashtable<String, String> types, Hashtable<String, ProcDecl> procedures) throws TypeError {
        var.setType(types);

        if (expr instanceof CallExpr) {
            ((CallExpr) expr).getCallStmt().typeCheck(types, procedures);
        } else {
            expr.setType(types);
        }

        if (var.getType() == null) {
            throw new TypeError("Undeclared variable "+ var.name);
        }

        if (expr.getType() == null) {
            System.out.println(types);
            throw new TypeError("Unknown type for "+ expr.printAst(0));
        }

        if (!TypeChecker.isValidAssignment(var.getType(), expr.getType())) {
            throw new TypeError(expr.getType() +" cannot be assigned to "+ var.getType());
        }
    }

    @Override
    public String getType() {
        return var.getType();
    }

    @Override
    public void generateCode(CodeFile cf, CodeProcedure cp, CodeStruct cs) {
        expr.generateCode(cf, cp, cs);
        if (cp.variableNumber(var.name) != -1) {
            cp.addInstruction(new STORELOCAL(cp.variableNumber(var.name)));
        } else if (cp.globalVariableNumber(var.name) != -1) {
            cp.addInstruction(new STOREGLOBAL(cp.globalVariableNumber(var.name)));
        } else {
            System.out.println("Could not find variable.");
        }
    }

}
