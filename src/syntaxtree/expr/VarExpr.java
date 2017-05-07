package syntaxtree.expr;

import syntaxtree.Expr;
import java.util.Hashtable;
import bytecode.CodeFile;
import bytecode.CodeStruct;
import bytecode.CodeProcedure;
import bytecode.instructions.LOADLOCAL;
import bytecode.instructions.LOADGLOBAL;

public class VarExpr extends UnaryExpr {

    public String name;

    Expr expr;

    String type;

    public VarExpr(String name) {
        this.name = name;
    }

    public VarExpr(String name, Expr expr) {
        this.name = name;
        this.expr = expr;
    }

    protected VarExpr() {
    }

    @Override
    public String printAst(int depth) {
        if (expr != null) {
            return "( . "+ expr.printAst(depth+1) +" (NAME "+ name +"))";
        } else {
            return "(NAME "+ name +")";
        }
    }

    String getName() {
        Expr e = this.expr;

        if (e != null) {
            if (e instanceof ParenthesesExpr) {
                e = ((ParenthesesExpr) e).expr;
            }

            if (e instanceof VarExpr) {
                return ((VarExpr) e).getName() +"."+ this.name;
            }

            return this.name;
        }

        return this.name;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void setType(Hashtable<String, String> types) {
//        System.out.println("setting type to "+ this.getName());
//        System.out.println(types);

        String name;

        if (this.getName().contains(".")) {
            String[] parts = this.getName().split("\\.");

            name = types.get(parts[0]) +"."+ parts[1];
        } else {
            name = this.getName();
        }

        if (!types.containsKey(name)) {
//            System.out.println("variable not found: "+ name);

            return;
        } else if (!types.containsKey(this.getName())) {
            types.put(this.getName(), types.get(name));
        }


        this.type = types.get(this.getName());

//        System.out.println(this.getName() +" is "+ this.type);
    }

    @Override
    public void generateCode(CodeFile cf, CodeProcedure cp, CodeStruct cs) {
        if (expr != null) {
            expr.generateCode(cf, cp, cs);
        }
        cp.addInstruction(new LOADLOCAL(cp.variableNumber(name)));
    }
}
