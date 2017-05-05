package syntaxtree.decl;

import syntaxtree.Decl;
import syntaxtree.StringUtils;
import typesystem.TypeError;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import bytecode.CodeFile;
import bytecode.CodeProcedure;
import bytecode.CodeStruct;
import bytecode.type.*;

public class ClassDecl extends Decl {

    private final List<VarDecl> vars;

    public ClassDecl(String name, List<VarDecl> vars) {
        super(name);
        this.vars = vars;
    }

    public ClassDecl(String name) {
        this(name, new LinkedList<VarDecl>());
    }

    public String printAst(int depth) {
        StringBuilder sb = new StringBuilder();
        sb.append("(CLASS_DECL ");
        sb.append("(NAME ");
        sb.append(this.name);
        sb.append(")");
        for (VarDecl var: vars) {
            sb.append("\n");
            sb.append(var.printAst(depth+1));
        }

        if (vars.size() > 0) {
            sb.append("\n");
            sb.append(StringUtils.repeat('\t', depth));
        }

        sb.append(")");
        return sb.toString();
    }

    @Override
    public String getType() {
        return this.name;
    }

    @Override
    public void typeCheck(Hashtable<String, String> types, Hashtable<String, ProcDecl> procedures) throws TypeError {
        for (VarDecl decl: vars) {
            String name = this.name+"."+decl.name;

            if (types.containsKey(name)) {
                throw new TypeError("Duplicate symbol "+ name);
            }

            types.put(name, decl.getType());
        }
    }

    @Override
    public void generateCode(CodeFile cf, CodeProcedure cp, CodeStruct cs){
      cf.addStruct(name);

      CodeStruct newStruct = new CodeStruct(name);
      for(VarDecl decl: vars){
        decl.generateCode(null, null, newStruct);
      }
      cf.updateStruct(newStruct);
    }
}
