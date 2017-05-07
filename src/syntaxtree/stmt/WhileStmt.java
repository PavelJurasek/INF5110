package syntaxtree.stmt;

import syntaxtree.Expr;
import syntaxtree.Stmt;
import syntaxtree.StringUtils;
import syntaxtree.decl.ProcDecl;
import typesystem.TypeError;
import bytecode.CodeFile;
import bytecode.CodeProcedure;
import bytecode.CodeStruct;
import bytecode.instructions.*;
import java.util.Hashtable;
import java.util.List;

public class WhileStmt extends Stmt {

    private Expr expr;
    private final List<Stmt> stmts;

    public WhileStmt(Expr expr, List<Stmt> stmts) {
        this.expr = expr;
        this.stmts = stmts;
    }

    @Override
    public String printAst(int depth) {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.repeat('\t', depth));
        sb.append("(WHILE_STMT\n");
        sb.append(expr.printAst(depth+1));
        for (Stmt stmt: stmts) {
            sb.append("\t").append(stmt.printAst(depth+1));
            sb.append("\n");
        }
        sb.append("\n");
        sb.append(StringUtils.repeat('\t', depth));
        sb.append(")\n");
        return sb.toString();

    }

    @Override
    public void typeCheck(Hashtable<String, String> types, Hashtable<String, ProcDecl> procedures) throws TypeError {
        this.expr.setType(types);

        if (!this.getType().equals("bool")) {
            throw new TypeError("While condition must be boolean, "+expr.getType()+" given.");
        }
    }

    @Override
    public String getType() {
        return expr.getType();
    }

    @Override
    public void generateCode(CodeFile cf, CodeProcedure cp, CodeStruct cs) {
        int beforeStart = cp.addInstruction(new NOP());
        expr.generateCode(cf, cp, cs);
        int start = cp.addInstruction(new NOP());
        for (Stmt stmt: stmts) {
            stmt.generateCode(cf, cp, cs);
        }
        int beforeEnd = cp.addInstruction(new NOP());
        int end = cp.addInstruction(new NOP());

        cp.replaceInstruction(start, new JMPFALSE(end));
        cp.replaceInstruction(beforeEnd, new JMP(beforeStart));
    }

}
