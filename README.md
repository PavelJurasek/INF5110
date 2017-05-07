# Compila17 compiler
## Contributors
- oddgfa - Odd Gunnar Fatland
- pavelju - Pavel Jurasek
- salihasa - Saliha Sajid

## Usage
Build using `ant build` and run using `ant compile-runme` followed by `ant run-runme`.

## Design
We did the code generation much like the building of the AST. Stmt, Expr and Decl all has an abstract codeGenerator procedure that takes a `CodeFile`, `CodeProcedure` and `CodeStruct` as parameter. This method is then overwritten in all sub classes.

## Print from test suite
```
test:
     [java] Testing compiler class.
     [java] Testing files in /Users/oddgunnarfatland/Google Drive/Studier/Master/1. semester/INF5110/INF5110/tests
     [java] Test[1] SHOULD FAIL: general1_fail.cmp
     [java] String should be returned, string given.
     [java] Test[2] SHOULD FAIL: general2_fail.cmp
     [java] Duplicate declaration of a in procedure Proc
     [java] Test[3] SHOULD FAIL: general3_fail.cmp
     [java] Undeclared variable bar
     [java] Test[4] SHOULD FAIL: general4_fail.cmp
     [java] BAD: Test no 4 didn't fail when it should!
     [java] Test[5] SHOULD FAIL: general5_fail.cmp
     [java] Proc: Parameter 1 must be of type (REF int), int given.
     [java] Test[6] SHOULD FAIL: general6_fail.cmp
     [java] Proc: Parameter 1 must be of type int, ref_tmp given.
     [java] Test[7] SHOULD FAIL: main_wrong_parameters_fail.cmp
     [java] Main procedure doesn't accept any parameter.
     [java] Test[8] SHOULD FAIL: main_wrong_type_fail.cmp
     [java] Main procedure can't return anyting but void.
     [java] Test[9] SHOULD FAIL: nested_class_shadow_fail.cmp
     [java] Could not find variable.
     [java] Could not find variable.
     [java] BAD: Test no 9 didn't fail when it should!
     [java] Test[10] SHOULD FAIL: procedure1_fail.cmp
     [java] Undeclared variable b
     [java] Test[11] SHOULD FAIL: procedure2_fail.cmp
     [java] string should be returned, int given.
     [java] Test[12] SHOULD FAIL: procedure3_fail.cmp
     [java] int should be returned, void given.
     [java] Test[13] SHOULD FAIL: procedure4_fail.cmp
     [java] Add: argument count mismatch. 2 expected, 1 given.
     [java] Test[14] SHOULD FAIL: procedure5_fail.cmp
     [java] Add: Parameter 2 must be of type int, bool given.
     [java] Test[15] SHOULD FAIL: procedure_calls_not_procedure_fail.cmp
     [java] Call to an undefined procedure Test
     [java] Test[16] SHOULD FAIL: procedure_does_not_exist_fail.cmp
     [java] Call to an undefined procedure yy
     [java] Test[17] SHOULD FAIL: procedure_name_used_fail.cmp
     [java] Duplicate declaration of Tmp in procedure Main
     [java] Test[18] SHOULD FAIL: procedure_no_return_fail.cmp
     [java] Procedure test should return string but there is no return statement.
     [java] Test[19] SHOULD PASS: procedure_return_null.cmp
     [java] Test[20] SHOULD FAIL: procedure_unknown_returntype_fail.cmp
     [java] Unknown return type notexist
     [java] Test[21] SHOULD FAIL: return_contradicting_expression_fail.cmp
     [java] void should be returned, int given.
     [java] Test[22] SHOULD PASS: test1.cmp
     [java] Test[23] SHOULD PASS: test3.cmp
     [java] Could not find variable.
     [java] Test[24] SHOULD PASS: test4.cmp
     [java] Test[25] SHOULD PASS: test5.cmp
     [java] Proc: Parameter 1 must be of type (REF int), ref_a given.
     [java] BAD: Test no 25 failed when it shouldn't!
     [java] Error: null
     [java] Test[26] SHOULD PASS: test_nested_scopes.cmp
     [java] Could not find variable.
     [java] Test[27] SHOULD FAIL: type1_fail.cmp
     [java] int cannot be assigned to bool
     [java] Test[28] SHOULD FAIL: type2_fail.cmp
     [java] If condition must be boolean, int given.
     [java] Test[29] SHOULD FAIL: type3_fail.cmp
     [java] float cannot be assigned to int
     [java] Test[30] SHOULD FAIL: type4_fail.cmp
     [java] While condition must be boolean, float given.
     [java] Test[31] SHOULD FAIL: type5_fail.cmp
     [java] Undeclared variable Foo
     [java] Test[32] SHOULD FAIL: type6_fail.cmp
     [java] Undeclared variable Foo
     [java] Test[33] SHOULD FAIL: type7_fail.cmp
     [java] Undeclared variable DoesntExist
     [java] Test[34] SHOULD FAIL: undeclared_class_fail.cmp
     [java] Undeclared variable bar
     [java] Test[35] SHOULD FAIL: undeclared_class_var_fail.cmp
     [java] Undeclared variable bar
     [java] Test[36] SHOULD FAIL: undeclared_new_argument_fail.cmp
     [java] void should be returned, foobar given.
     [java] Test[37] SHOULD FAIL: variable_scope_shadow_fail.cmp
     [java] Duplicate declaration of flag in procedure change_flag
     [java] Test[38] SHOULD FAIL: wrong_arith_operand_type_fail.cmp
     [java] LHS of arithmetic operation must be int or float, bool given.
     [java] Test[39] SHOULD FAIL: wrong_log_exp_type_fail.cmp
     [java] Second argument of logic operation invalid: bool expected, int given.
     [java] Test[40] SHOULD FAIL: wrong_new_arg_type_fail.cmp
     [java] void should be returned, f given.
     [java] Test[41] SHOULD FAIL: wrong_not_operand_type_fail.cmp
     [java] Not: operand type must be bool, string given.
     [java] Test[42] SHOULD FAIL: wrong_rel_operand_type_fail.cmp
     [java] Invalid relation operation arguments: int and bool
     [java] Some tests failed (3 / 42)
```

## Print from RunMe.bin
```
list-runme:
     [java] Loading from file: code-examples/RunMe.bin
     [java] Variables:
     [java] 0: var Complex dummy
     [java] Procedures:
     [java] 0: proc int readint()
     [java] 1: proc float readfloat()
     [java] 2: proc int readchar()
     [java] 3: proc string readstring()
     [java] 4: proc string readline()
     [java] 5: proc void printint()
     [java] 6: proc void printfloat()
     [java] 7: proc void printstr()
     [java] 8: proc void printline()
     [java] 9: proc Complex Add(Complex 0, Complex 1)
     [java]     var Complex 2
     [java]     0: new Complex
     [java]     3: storelocal 2
     [java]     6: loadlocal 0
     [java]     9: loadlocal -1
     [java]     12: loadlocal 1
     [java]     15: loadlocal -1
     [java]     18: add
     [java]     19: loadlocal 0
     [java]     22: loadlocal -1
     [java]     25: loadlocal 1
     [java]     28: loadlocal -1
     [java]     31: add
     [java]     32: loadlocal 2
     [java]     35: return
     [java] 10: proc int Max(int 0, int 1)
     [java]     var int 2
     [java]     0: loadlocal 0
     [java]     3: loadlocal 1
     [java]     6: gt
     [java]     7: jmpfalse 19
     [java]     10: loadlocal 0
     [java]     13: storelocal 2
     [java]     16: jmp 26
     [java]     19: nop
     [java]     20: loadlocal 1
     [java]     23: storelocal 2
     [java]     26: nop
     [java]     27: loadlocal 2
     [java]     30: return
     [java] 11: proc void printCmplx(Complex 0)
     [java]     0: pushstring "Real "
     [java]     3: call printstr {7}
     [java]     6: loadlocal 0
     [java]     9: loadlocal -1
     [java]     12: call printfloat {6}
     [java]     15: pushstring ""
     [java]     18: call printline {8}
     [java]     21: pushstring "Imag "
     [java]     24: call printstr {7}
     [java]     27: loadlocal 0
     [java]     30: loadlocal -1
     [java]     33: call printfloat {6}
     [java]     36: pushstring ""
     [java]     39: call printline {8}
     [java] 12: proc void test()
     [java]     var Complex 0
     [java]     var Complex 1
     [java]     var Complex 2
     [java]     var int 3
     [java]     var int 4
     [java]     var int 5
     [java]     0: new Complex
     [java]     3: storelocal 0
     [java]     6: new Complex
     [java]     9: storelocal 1
     [java]     12: pushint 1
     [java]     17: pushint 2
     [java]     22: pushint 3
     [java]     27: pushint 4
     [java]     32: loadlocal 0
     [java]     35: loadlocal 1
     [java]     38: call Add {9}
     [java]     41: call printCmplx {11}
     [java]     44: pushint 3
     [java]     49: storelocal 3
     [java]     52: pushint 7
     [java]     57: storelocal 4
     [java]     60: loadlocal 4
     [java]     63: loadlocal 3
     [java]     66: call Max {10}
     [java]     69: storelocal 5
     [java] 13: proc void printStr(string 0)
     [java]     0: loadlocal 0
     [java]     3: call printstr {7}
     [java] 14: proc void inOutTest()
     [java]     var int 0
     [java]     var int 1
     [java]     0: pushstring "skriv v1"
     [java]     3: call printline {8}
     [java]     6: call readint {0}
     [java]     9: storelocal 0
     [java]     12: pushstring "skriv v2"
     [java]     15: call printline {8}
     [java]     18: call readint {0}
     [java]     21: storelocal 1
     [java]     24: pushstring "Storst "
     [java]     27: call printstr {7}
     [java]     30: loadlocal 0
     [java]     33: loadlocal 1
     [java]     36: call Max {10}
     [java]     39: call printint {5}
     [java]     42: pushstring ""
     [java]     45: call printline {8}
     [java] 15: proc void Main()
     [java]     var float 0
     [java]     var int 1
     [java]     var string 2
     [java]     0: pushfloat 6.48074
     [java]     5: storelocal 0
     [java]     8: loadlocal 0
     [java]     11: call printfloat {6}
     [java]     14: pushstring ""
     [java]     17: call printline {8}
     [java]     20: pushint 7
     [java]     25: storelocal 1
     [java]     28: loadlocal 1
     [java]     31: call printint {5}
     [java]     34: pushstring ""
     [java]     37: call printline {8}
     [java]     40: pushstring "TestNavn"
     [java]     43: storelocal 2
     [java]     46: loadlocal 2
     [java]     49: call printStr {13}
     [java]     52: pushstring ""
     [java]     55: call printline {8}
     [java]     58: call test {12}
     [java]     61: call inOutTest {14}
     [java]     64: new Complex
     [java]     67: storeglobal dummy{Complex}
     [java]     70: pushfloat 1.0
     [java]     75: pushfloat 2.0
     [java]     80: loadlocal -1
     [java]     83: call printCmplx {11}
     [java]     86: pushstring "DONE"
     [java]     89: call printline {8}
     [java] Structs:
     [java] 0: Complex
     [java]     0: float
     [java]     1: float
     [java] Constants:
     [java] 0: Real
     [java] 1:
     [java] 2: Imag
     [java] 3:
     [java] 4: skriv v1
     [java] 5: skriv v2
     [java] 6: Storst
     [java] 7:
     [java] 8:
     [java] 9:
     [java] 10: TestNavn
     [java] 11:
     [java] 12: DONE
     [java] STARTWITH: Main
```
