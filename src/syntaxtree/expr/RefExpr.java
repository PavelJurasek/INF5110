package syntaxtree.expr;

import syntaxtree.StringUtils;
import java.util.Hashtable;
import bytecode.CodeFile;
import bytecode.CodeProcedure;
import bytecode.CodeStruct;

public class RefExpr extends UnaryExpr {

    private VarExpr var;

    public RefExpr(VarExpr var) {
        this.var = var;
    }

    @Override
    public String printAst(int depth) {
        return StringUtils.repeat('\t', depth) + "(REF "+ var.name +")";
    }

    @Override
    public String getType() {
        return "ref_"+var.getName();
    }

    @Override
    public void setType(String type) {

    }

    @Override
    public void setType(Hashtable<String, String> types) {
        var.setType(types);
    }

    public void generateCode(CodeFile cf, CodeProcedure cp, CodeStruct cs){
    }

}
