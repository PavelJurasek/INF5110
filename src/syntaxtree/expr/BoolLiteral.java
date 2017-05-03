package syntaxtree.expr;
import bytecode.CodeProcedure;
import bytecode.instructions.PUSHBOOL;

public class BoolLiteral extends LiteralExpr {

	private Boolean name;

	public BoolLiteral (Boolean name){
		this.name = name;
	}

	@Override
	protected String getAstName() {
		return "BOOL_LITERAL " + name;
	}

	@Override
	public String getType() {
		return "bool";
	}

	@Override
	public void generateCode(CodeProcedure codeprocedure){
		codeprocedure.addInstruction(new PUSHBOOL(name));
	}
}
