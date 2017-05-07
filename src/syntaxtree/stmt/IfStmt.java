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

public class IfStmt extends Stmt {

    private Expr expr;
    private final List<Stmt> thenStmts;
    private final List<Stmt> elseStmts;

    public IfStmt(Expr expr, List<Stmt> thenStmts, List<Stmt> elseStmts) {
        this.expr = expr;
        this.thenStmts = thenStmts;
        this.elseStmts = elseStmts;
    }

    @Override
    public String printAst(int depth) {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.repeat('\t', depth));
        sb.append("(IF_STMT\n");
        sb.append(StringUtils.repeat('\t', depth+1));
        sb.append(expr.printAst(depth+1));
        sb.append("\n");

        sb.append(StringUtils.repeat('\t', depth+1));
        sb.append("(\n");

        for (Stmt stmt: thenStmts) {
            sb.append("\t").append(stmt.printAst(depth+1));
            if (sb.charAt(sb.length()-1) != '\n') {
                sb.append("\n");
            }
        }

        sb.append(StringUtils.repeat('\t', depth+1));
        sb.append(")\n");

        for (Stmt stmt: elseStmts) {
            sb.append("\t").append(stmt.printAst(depth+1));
            if (sb.charAt(sb.length()-1) != '\n') {
                sb.append("\n");
            }
        }
        sb.append(StringUtils.repeat('\t', depth));
        sb.append(")\n");
        return sb.toString();

    }

    @Override
    public void typeCheck(Hashtable<String, String> types, Hashtable<String, ProcDecl> procedures) throws TypeError {
        this.expr.setType(types);

        if (!getType().equals("bool")) {
            throw new TypeError("If condition must be boolean, "+expr.getType()+" given.");
        }
    }

    @Override
    public String getType() {
        return expr.getType();
    }

    @Override
    public void generateCode(CodeFile cf, CodeProcedure cp, CodeStruct cs) {
        expr.generateCode(cf, cp, cs);
        int ifCondition = cp.addInstruction(new NOP());
        for (Stmt stmt: thenStmts) {
            stmt.generateCode(cf, cp, cs);
        }
        int beforeElse = cp.addInstruction(new NOP());
        int elseJmp = cp.addInstruction(new NOP());
        for (Stmt stmt: elseStmts) {
            stmt.generateCode(cf, cp, cs);
        }
        int end = cp.addInstruction(new NOP());

        cp.replaceInstruction(ifCondition, new JMPFALSE(elseJmp));
        cp.replaceInstruction(beforeElse, new JMP(end));
    }

}
