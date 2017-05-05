package syntaxtree;

import typesystem.TypeAware;
import typesystem.TypeCheck;
import bytecode.CodeFile;
import bytecode.CodeProcedure;
import bytecode.CodeStruct;

public abstract class Decl extends Node implements TypeAware, TypeCheck {

    public String name;

    public Decl(String name) {
        this.name = name;
    }

    public abstract void generateCode(CodeFile cf, CodeProcedure cp, CodeStruct cs);
}
