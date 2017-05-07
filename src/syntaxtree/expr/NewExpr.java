package syntaxtree.expr;

import syntaxtree.Type;
import java.util.Hashtable;
import bytecode.instructions.*;
import bytecode.CodeProcedure;
import bytecode.CodeFile;
import bytecode.CodeStruct;

public class NewExpr extends UnaryExpr {

    Type type;

    public NewExpr(Type type) {
        this.type = type;
    }

    @Override
    public String printAst(int depth) {
        return "(NEW "+ type.printAst(depth+1) +")";
    }

    @Override
    public String getType() {
        return type.get();
    }

    @Override
    public void setType(String type) {

    }

    @Override
    public void setType(Hashtable<String, String> types) {

    }

    @Override
    public void generateCode(CodeFile cf, CodeProcedure cp, CodeStruct cs){
      int structNum = cp.structNumber(type.get());
      cp.addInstruction(new NEW(structNum));
    }

}
