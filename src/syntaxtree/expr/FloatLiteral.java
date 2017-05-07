package syntaxtree.expr;
import bytecode.CodeProcedure;
import bytecode.CodeFile;
import bytecode.CodeStruct;
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
	public void generateCode(CodeFile cf, CodeProcedure cp, CodeStruct cs){
		cp.addInstruction(new PUSHFLOAT(name));
	}
}
