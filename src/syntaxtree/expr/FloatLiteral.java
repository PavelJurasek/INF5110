package syntaxtree.expr;
import bytecode.CodeProcedure;
import bytecode.instructions.PUSHFLOAT;

public class FloatLiteral extends LiteralExpr {

	private Float name;

	public FloatLiteral (Float name){
		this.name = name;
	}

	@Override
	protected String getAstName() {
		return "FLOAT_LITERAL " + name;
	}

	@Override
	public String getType() {
		return "float";
	}

	@Override
	public void generateCode(CodeProcedure codeprocedure){
		codeprocedure.addInstruction(new PUSHFLOAT(name));
	}

}
