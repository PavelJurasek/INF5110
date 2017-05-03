package syntaxtree;

import bytecode.CodeFile;
import syntaxtree.decl.ParamDecl;
import syntaxtree.decl.ProcDecl;
import typesystem.TypeError;

import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

public class Program implements PrintAst {

    private String name;

    private List<Decl> decls;

    public Program(String name, List<Decl> decls) {
        this.decls = decls;
        this.name = name;
    }

    public void generateCode(CodeFile codefile) {
      for(Decl decl: decls){
        decl.generateCode(codefile);
      }
      
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

    public boolean typeCheck() {
        Hashtable<String, String> types = new Hashtable<>();
        Hashtable<String, ProcDecl> procedures = new Hashtable<>();

        types.put("printint", "void");
        types.put("printfloat", "void");
        types.put("print_float", "void");
        types.put("print_str", "void");

        ProcDecl printint = new ProcDecl("printint", Collections.singletonList(new ParamDecl("int", new Type("int"))));
        ProcDecl printfloat = new ProcDecl("printfloat", Collections.singletonList(new ParamDecl("float", new Type("float"))));
        ProcDecl print_float = new ProcDecl("print_float", Collections.singletonList(new ParamDecl("float", new Type("float"))));
        ProcDecl print_str = new ProcDecl("print_str", Collections.singletonList(new ParamDecl("string", new Type("string"))));

        procedures.put("printint", printint);
        procedures.put("printfloat", printfloat);
        procedures.put("print_float", print_float);
        procedures.put("print_str", print_str);

        try {
            for (Decl decl : decls) {
                decl.typeCheck(types, procedures);
            }
        } catch (TypeError e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

}
