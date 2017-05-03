package syntaxtree.expr;

import syntaxtree.Type;
import java.util.Hashtable;
import bytecode.instructions.*;
import bytecode.CodeProcedure;

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
    public void generateCode(CodeProcedure codeprocedure){
      int structNum=codeprocedure.structNumber(type.get());
      codeprocedure.addInstruction(new NEW(structNum));
    }

}
