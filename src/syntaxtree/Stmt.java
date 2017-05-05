package syntaxtree;

import typesystem.TypeAware;
import typesystem.TypeCheck;
import bytecode.CodeFile;
import bytecode.CodeProcedure;
import bytecode.CodeStruct;
import bytecode.type.CodeType;

abstract public class Stmt extends Node implements TypeAware, TypeCheck {
    //public abstract void generateCode(CodeProcedure codeprocedure);

}
