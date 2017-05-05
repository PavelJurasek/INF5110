package syntaxtree.decl;

import syntaxtree.Decl;
import syntaxtree.StringUtils;
import syntaxtree.Type;

import typesystem.TypeAware;
import typesystem.TypeError;

import java.util.Hashtable;

import bytecode.CodeFile;
import bytecode.CodeProcedure;
import bytecode.CodeStruct;
import bytecode.type.*;

public class VarDecl extends Decl implements TypeAware {

    private Type type;

    public VarDecl(String name, Type type) {
        super(name);
        this.type = type;
    }

    public String printAst(int depth) {
        return StringUtils.repeat('\t', depth) + "(VAR_DECL "+ type.printAst(depth+1) +" (NAME " + name + "))";
    }

    @Override
    public String getType() {
        return type.get();
    }

    @Override
    public void typeCheck(Hashtable<String, String> types, Hashtable<String, ProcDecl> procedures) throws TypeError {
        // no need to check
    }

    @Override
    public void generateCode(CodeFile cf, CodeProcedure cp, CodeStruct cs){
        if (cp != null) {
            cp.addLocalVariable(name, type.getByteType(cf));
            cf.updateProcedure(cp);
        } else if (cs != null) {
            cs.addVariable(name, type.getByteType(cf));
        } else {
            cf.addVariable(name);
            cf.updateVariable(name, type.getByteType(cf));
        }
    }
}
