package syntaxtree;
import bytecode.CodeFile;
import bytecode.CodeProcedure;
import bytecode.type.CodeType;
import bytecode.type.BoolType;
import bytecode.type.FloatType;
import bytecode.type.IntType;
import bytecode.type.RefType;
import bytecode.type.StringType;
import bytecode.type.VoidType;
/**
 * Created by pjurasek on 28.02.17.
 */
public class Type extends Node {

    private Name name;
    private String stringName;

    public Type(String name) {
        stringName = name;
    }

    public Type(Name name) {
        this.name = name;
    }

    Type() {
    }

    public String get() {
        if (name != null) {
            return name.value;
        }

        return stringName;
    }

    @Override
    public String printAst(int depth) {
        return "(TYPE " + this.get() + ")";
    }

}
