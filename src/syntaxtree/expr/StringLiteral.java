package syntaxtree.expr;
import bytecode.CodeProcedure;
import bytecode.CodeFile;
import bytecode.CodeStruct;
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
	public void generateCode(CodeFile cf, CodeProcedure cp, CodeStruct cs){
		int i = cp.addStringConstant(name);
		cp.addInstruction(new PUSHSTRING(i));
	}

}
