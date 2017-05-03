package syntaxtree.expr;
import bytecode.CodeProcedure;
import bytecode.instructions.PUSHSTRING;

public class StringLiteral extends LiteralExpr {

	private String name;

	public StringLiteral (String name){
		this.name = name;
	}

	@Override
	protected String getAstName() {
		return "STRING_LITERAL \"" + name +"\"";
	}

	@Override
	public String getType() {
		return "string";
	}

	@Override
	public void generateCode(CodeProcedure codeprocedure){
		int i=codeprocedure.addStringConstant(name);
		codeprocedure.addInstruction(new PUSHSTRING(i));
	}

}
