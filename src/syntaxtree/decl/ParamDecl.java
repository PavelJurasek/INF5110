package syntaxtree.decl;

import syntaxtree.Decl;
import syntaxtree.StringUtils;
import syntaxtree.Type;
import typesystem.TypeAware;
import typesystem.TypeError;
import bytecode.CodeFile;
import bytecode.CodeProcedure;
import java.util.Hashtable;
import bytecode.type.CodeType;
import bytecode.type.*;

public class ParamDecl extends Decl implements TypeAware {

    private Type type;
    String name;

    public ParamDecl(String name, Type type) {
        super(name);
        this.type = type;
    }

    public String printAst(int depth) {
        return StringUtils.repeat('\t', depth) + "(PARAM_DECL "+ type.printAst(depth+1) +" (NAME " + name + "))";
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
    public void generateCode(CodeProcedure codeprocedure){
      if(type.get().equals("int")){
        codeprocedure.addParameter(name, IntType.TYPE);
      }
      if(type.get().equals("string")){
        codeprocedure.addParameter(name, StringType.TYPE);
      }
      if(type.get().equals("bool")){
        codeprocedure.addParameter(name, BoolType.TYPE);
      }
      if(type.get().equals("float")){
        codeprocedure.addParameter(name, FloatType.TYPE);
      }
      if(type.get().equals("void")){
        codeprocedure.addParameter(name, VoidType.TYPE);
      }
    }

    @Override
    public void generateCode(CodeFile codefile){
    }
}
