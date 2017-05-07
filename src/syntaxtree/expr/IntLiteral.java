package syntaxtree.expr;
import bytecode.CodeProcedure;
import bytecode.CodeFile;
import bytecode.CodeStruct;
import bytecode.instructions.PUSHINT;

public class IntLiteral extends LiteralExpr {

	private Integer name;

	public IntLiteral (Integer name){
		this.name = name;
	}

	@Override
	protected String getAstName() {
		return "INT_LITERAL " + name;
	}

	@Override
	public String getType() {
		return "int";
	}

	@Override
	public void generateCode(CodeFile cf, CodeProcedure cp, CodeStruct cs){
		cp.addInstruction(new PUSHINT(name));
	}

}
