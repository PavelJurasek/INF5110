package syntaxtree.decl;

import syntaxtree.Decl;
import syntaxtree.StringUtils;
import syntaxtree.Type;
import typesystem.TypeAware;
import typesystem.TypeError;
import bytecode.CodeFile;
import bytecode.CodeProcedure;
import bytecode.CodeStruct;
import bytecode.type.CodeType;
import java.util.Hashtable;
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


    public void generateCode(CodeFile codefile){
      codefile.addVariable(name);
      codefile.updateVariable(name, new RefType(type.get()));
    }

     //struct
    public void generateCode(CodeStruct codestruct, CodeFile codefile){
      if(this.type.get().equals("int")){
        codestruct.addVariable(name, IntType.TYPE);
      }
      if(this.type.get().equals("string")){
        codestruct.addVariable(name, StringType.TYPE);
      }
      if(this.type.get().equals("bool")){
        codestruct.addVariable(name, BoolType.TYPE);
      }
      if(this.type.get().equals("float")){
        codestruct.addVariable(name, FloatType.TYPE);
      }
      if(this.type.get().equals("void")){
        codestruct.addVariable(name, VoidType.TYPE);
      }
    }

    
    public void generateCode(CodeProcedure codeprocedure, CodeFile codefile){
      if(this.type.get().equals("int")){
        codeprocedure.addLocalVariable(name, IntType.TYPE);
      }
      if(this.type.get().equals("string")){
        codeprocedure.addLocalVariable(name, StringType.TYPE);
      }
      if(this.type.get().equals("bool")){
        codeprocedure.addLocalVariable(name, BoolType.TYPE);
      }
      if(this.type.get().equals("float")){
        codeprocedure.addLocalVariable(name, FloatType.TYPE);
      }
      if(this.type.get().equals("void")){
        codeprocedure.addLocalVariable(name, VoidType.TYPE);
      }

      codefile.updateProcedure(codeprocedure);
    }
}
