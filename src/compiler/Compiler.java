package compiler;

import java.io.*;
import runtime.VirtualMachine;

import bytecode.CodeFile;
import bytecode.CodeProcedure;
import bytecode.type.*;

import syntaxtree.*;
import oblig1parser.*;

public class Compiler {
	private String inFilename = null;
	private String astFilename = null;
	private String binFilename = null;
	public String syntaxError;
	public String error;
	public Compiler(String inFilename, String astFilename, String binFilename){
		this.inFilename = inFilename;
		this.astFilename = astFilename;
		this.binFilename = binFilename;
	}
	public int compile() throws Exception {
		InputStream inputStream = null;
		inputStream = new FileInputStream(this.inFilename);
		Lexer lexer = new Lexer(inputStream);
		parser parser = new parser(lexer);
		Program program;
		try {
			program = (Program)parser.parse().value;
		} catch (Exception e) {
			// Do something here?
			throw e; // Or something.
		}
		// Check semanics.
		if (program.typeCheck()) { // If it is all ok:
			writeAST(program);
			generateCode(program);
			return 0;
		} else if (false){ // If there is a SYNTAX ERROR (Should not get that for the tests):
			return 1;
		} else { // If there is a SEMANTIC ERROR (Should get that for the test with "_fail" in the name):
			return 2;
		}
	}
	private void writeAST(Program program) throws Exception {
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.astFilename));
		bufferedWriter.write(program.printAst());
		bufferedWriter.close();
	}
	private void generateCode(Program program) throws Exception {
		CodeFile codeFile = new CodeFile();
        buildLibrary(codeFile);
		program.generateCode(codeFile);
		byte[] bytecode = codeFile.getBytecode();
		DataOutputStream stream = new DataOutputStream(new FileOutputStream (this.binFilename));
		stream.write(bytecode);
		stream.close();
        VirtualMachine vm = new VirtualMachine(this.binFilename);
        //vm.list();
        //vm.run();
	}
	public static void main(String[] args) {
		Compiler compiler = new Compiler(args[0], args[1], args[2]);
		int result;
		try {
			result = compiler.compile();
			if(result == 1){
				System.out.println(compiler.syntaxError);
			} else if(result == 2){
				System.out.println(compiler.error);
			}
			System.exit(result);
		} catch (Exception e) {
			System.out.println("ERROR: " + e);
			e.printStackTrace();
			// If unknown error.
			System.exit(3);
		}
	}
	public static String indent(int indent){
		String result = "";
		for(int i=0;i<indent; i++){
			result+=" ";
		}
		return result;
	}

    private void buildLibrary(CodeFile cf) {
        cf.addProcedure("readint");
        cf.addProcedure("readfloat");
        cf.addProcedure("readchar");
        cf.addProcedure("readstring");
        cf.addProcedure("readline");
        cf.addProcedure("printint");
        cf.addProcedure("printfloat");
        cf.addProcedure("printstr");
        cf.addProcedure("printline");
        cf.updateProcedure(new CodeProcedure("readint", IntType.TYPE, cf));
        cf.updateProcedure(new CodeProcedure("readfloat", FloatType.TYPE, cf));
        cf.updateProcedure(new CodeProcedure("readchar", IntType.TYPE, cf));
        cf.updateProcedure(new CodeProcedure("readstring", StringType.TYPE, cf));
        cf.updateProcedure(new CodeProcedure("readline", StringType.TYPE, cf));
        cf.updateProcedure(new CodeProcedure("printint", VoidType.TYPE, cf));
        cf.updateProcedure(new CodeProcedure("printfloat", VoidType.TYPE, cf));
        cf.updateProcedure(new CodeProcedure("printstr", VoidType.TYPE, cf));
        cf.updateProcedure(new CodeProcedure("printline", VoidType.TYPE, cf));
    }
}
