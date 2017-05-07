package syntaxtree;
import bytecode.CodeFile;
import bytecode.type.*;

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

    public CodeType getByteType(CodeFile cf) {
        switch (get()) {
            case "int":     return IntType.TYPE;
            case "float":   return FloatType.TYPE;
            case "string":  return StringType.TYPE;
            case "bool":    return BoolType.TYPE;
            case "void":    return VoidType.TYPE;
            default:        return new bytecode.type.RefType(cf.structNumber(get()));
        }
}

}
