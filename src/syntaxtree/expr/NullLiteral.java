package syntaxtree.expr;
import bytecode.instructions.*;
import bytecode.CodeProcedure;
import bytecode.CodeFile;
import bytecode.CodeStruct;
public class NullLiteral extends LiteralExpr {

	public NullLiteral() {

	}

    @Override
    protected String getAstName() {
        return "NULL_LITERAL";
    }

    @Override
    public String getType() {
        return "null";
    }

		@Override
		public void generateCode(CodeFile cf, CodeProcedure cp, CodeStruct cs){
			cp.addInstruction(new PUSHNULL());
		}
}
