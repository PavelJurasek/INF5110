package syntaxtree.stmt;

import syntaxtree.Stmt;
import syntaxtree.StringUtils;
import syntaxtree.decl.ProcDecl;
import typesystem.TypeError;
import java.util.Hashtable;

public class DummyStmt extends Stmt {

    @Override
    public String printAst(int depth) {
        return StringUtils.repeat('\t', depth) + "(DUMMY_STMT)\n";
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public void typeCheck(Hashtable<String, String> types, Hashtable<String, ProcDecl> procedures) throws TypeError {

    }

}
