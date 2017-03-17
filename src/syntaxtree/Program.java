package syntaxtree;

import java.util.List;

public class Program implements PrintAst {

    String name;

    List<Decl> decls;

    public Program(String name, List<Decl> decls) {
        this.decls = decls;
        this.name = name;
    }

    public String printAst() {
        return this.printAst(0);
    }

    public String printAst(int depth) {
        StringBuilder sb = new StringBuilder();
        sb.append("(PROGRAM ");
        sb.append("(NAME ");
        sb.append(this.name);
        sb.append(")\n");
        for (Decl decl : decls) {
            sb.append("\t").append(decl.printAst(depth+1));
            sb.append("\n");
        }
        sb.append(")");
        return sb.toString();
        
    }
}
