package syntaxtree.expr;
import bytecode.instructions.*;
import bytecode.CodeProcedure;
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
		public void generateCode(CodeProcedure codeprocedure){
			codeprocedure.addInstruction(new PUSHNULL());
		}
}
