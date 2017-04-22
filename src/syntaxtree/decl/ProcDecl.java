package syntaxtree.decl;

import syntaxtree.Decl;
import syntaxtree.Stmt;
import syntaxtree.StringUtils;
import syntaxtree.Type;
import syntaxtree.stmt.ReturnStmt;
import typesystem.TypeError;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

public class ProcDecl extends Decl {

    private final List<ParamDecl> params;
    private final List<Decl> decls;
    private final List<Stmt> stmts;
    private Type returnType;

    public ProcDecl(String name) {
        super(name);
        params = new LinkedList<ParamDecl>();
        decls = new LinkedList<Decl>();
        stmts = new LinkedList<Stmt>();
    }

    public ProcDecl(String name, List<ParamDecl> params) {
        super(name);
        this.params = params;
        decls = new LinkedList<Decl>();
        stmts = new LinkedList<Stmt>();
    }

    public ProcDecl(String name, List<ParamDecl> params, List<Decl> decls) {
        super(name);
        this.params = params;
        this.decls = decls;
        stmts = new LinkedList<Stmt>();
    }

    public ProcDecl(String name, List<ParamDecl> params, List<Decl> decls, List<Stmt> stmts) {
        super(name);
        this.params = params;
        this.decls = decls;
        this.stmts = stmts;
    }

    public ProcDecl(String name, List<ParamDecl> params, List<Decl> decls, List<Stmt> stmts, Type returnType) {
        super(name);
        this.params = params;
        this.decls = decls;
        this.stmts = stmts;
        this.returnType = returnType;
    }

    public List<ParamDecl> getParams() {
        return this.params;
    }

    public String printAst(int depth) {
        StringBuilder sb = new StringBuilder();

        if (depth > 1) { // nested procedure
            sb.append(StringUtils.repeat('\t', depth));
        }
        sb.append("(PROC_DECL ");

        if (returnType != null) {
            sb.append(returnType.printAst(depth+1));
            sb.append(" ");
        }

        sb.append("(NAME ");
        sb.append(this.name);
        sb.append(")\n");

        if (params.size() > 0) {
            for (ParamDecl param : params) {
                sb.append(param.printAst(depth+1));
                sb.append("\n");
            }
            sb.append("\n");
        }

        if (decls.size() > 0) {
            for (Decl decl : decls) {
                sb.append(decl.printAst(depth+1));
                sb.append("\n");
            }
            sb.append("\n");
        }

        if (stmts.size() > 0) {
            for (Stmt stmt : stmts) {
                sb.append(stmt.printAst(depth+1));
                sb.append("\n");
            }
        }

        sb.append(StringUtils.repeat('\t', depth));
        sb.append(")");

        return sb.toString();
    }

    @Override
    public String getType() {
        return this.returnType == null ? "void" : this.returnType.get();
    }

    public void typeCheck(Hashtable<String, String> types, Hashtable<String, ProcDecl> procedures) throws TypeError {
        procedures.put(this.name, this);

        for (ParamDecl param : params) {
            if (types.containsKey(param.name)) {
                throw new TypeError("Duplicate parameter "+ param.name +" in procedure "+ this.name);
            }

            types.put(param.name, param.getType());
        }

        for (Decl decl : decls) {
            if (types.containsKey(decl.name)) {
                throw new TypeError("Duplicate declaration of "+ decl.name +" in procedure "+ this.name);
            }

            types.put(decl.name, decl.getType());
            decl.typeCheck(types, procedures);
        }

        for (Stmt stmt : stmts) {
            if (stmt instanceof ReturnStmt) {
                ((ReturnStmt) stmt).setExpectedType(this.getType());
            }

            stmt.typeCheck(types, procedures);
        }

        // pop out when type checking ok
        for (ParamDecl param : params) {
            types.put(this.name +"."+ param.name, types.remove(param.name));
        }

//        for (Decl decl : decls) {
//            types.remove(decl.name);
//        }
    }

}
