package syntaxtree.expr;


import java.util.Hashtable;

public abstract class LiteralExpr extends UnaryExpr {

    abstract protected String getAstName();

    @Override
    public String printAst(int depth) {
        return "("+getAstName()+")";
    }

    @Override
    public void setType(String type) {
        // literals have type on their own
    }

    @Override
    public void setType(Hashtable<String, String> types) {
        // literals have type on their own
    }

}
