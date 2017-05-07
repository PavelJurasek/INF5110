package syntaxtree;

import typesystem.TypeAware;
import typesystem.TypeError;
import bytecode.CodeFile;
import bytecode.CodeProcedure;
import bytecode.CodeStruct;

import java.util.Hashtable;

abstract public class Expr extends Node implements TypeAware {

    abstract public void setType(String type) throws TypeError;

    abstract public void setType(Hashtable<String, String> types) throws TypeError;

    abstract public void generateCode(CodeFile cf, CodeProcedure cp, CodeStruct cs);

}
