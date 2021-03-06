package syntaxtree;

import typesystem.TypeAware;
import typesystem.TypeError;

import java.util.Hashtable;

abstract public class Expr extends Node implements TypeAware {

    abstract public void setType(String type) throws TypeError;

    abstract public void setType(Hashtable<String, String> types) throws TypeError;

}
