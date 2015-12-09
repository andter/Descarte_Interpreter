# Descarte_Interpreter
##Introduction:
The following is an interpreter built to interpret the programming language Descartes 2

##Results:
https://drive.google.com/file/d/0B-8lZwCFPBggZzJra2hUbGlYTEk/view?usp=sharing

##Program Specifications:
The Assignment

By yourself, or with one other classmate, write the interpreter for the simple typeless programming language Descartes-2. Descartes (pronounced 'de cart') has assignment, if, loop, read, print, and break statements, and the following operators (listed in decreasing order of precedence).
    unary -
    *    /
    +    -
    <   <=   =   >=   >   <>
    and
    or
All operators associate to the left (except comparison operators, which do not associate at all). A typical Descartes program is:


    SUM     := 1;
    EPSILON := 0.000001;
    K       :=1;
    LOOP MAINLOOP:
        I     :=1;
        TERM  :=1;
        LOOP FACTORIAL:
            TERM := TERM * I;
            I    := I+1;
            IF I>K THEN BREAK FI
        REPEAT;
        NEWSUM    := SUM + 1/TERM;
        IF SUM - NEWSUM < EPSILON AND NEWSUM - SUM < EPSILON THEN
             RESULT := NEWSUM;
             BREAK MAINLOOP
        ELSE SUM   := NEWSUM;  K := K + 1    FI
    REPEAT;
    PRINT RESULT .

(This program is supposed to calculate the constant e=2.71828... by a rather inefficient method).
Lexical Syntax

The tokens of Descartes-2 are as follows:
the reserved words IF, THEN, ELSE, FI, LOOP, REPEAT, BREAK, READ, PRINT, AND, and OR;
the operators and separators ')', '(', '/', '*', '-', '+', ' <>', '>', '>=', '=', '<=', '<', ',', ':=, ':', ';', and '.' (The last four of these are represented in the grammar as BECOMES, COLON, SEMICOLON, AND PERIOD, respectively.);
constants, which consist of a non empty sequence of digits optionally followed by a decimal point and another non-empty sequence of digits;
and identifiers, which consist of any sequence of letters and digits starting with a letter and not equal to any of the reserved words.
You may assume:
No token contains any spaces and no token extends across more than one line.
A space or new-line is required between consecutive identifiers, constants, and reserved words, and between a constant and the token PERIOD; otherwise, spaces and line boundaries are ignored.
and that all letters are in UPPER CASE.
Context-free Syntax

The context-free syntax is given by the LL(1) grammar listed below.
Semantics

All identifiers represent real variables.
There are no declarations.
for the purposes of the IF statement, zero represents false, and any other value is considered true.
The relational operators (<, =, etc.) return 1 for true and zero for false.
The statement LOOP NAME : statements REPEAT repeatedly executes the statements until a BREAK statement is executed.
The statement BREAK (without a following identifier) terminates execution of the smallest enclosing LOOP statement; BREAK NAME terminates the smallest enclosing LOOP whose ID is NAME. A run-time error occurs if a BREAK NAME statement is executed not inside a LOOP NAME loop.
For example


    LOOP MAIN:
        ...
        LOOP INNER:
            ...
            BREAK
            BREAK INNER;
            BREAK MAIN;
            BREAK FOOBAR;
            ...
        REPEAT;
        X :=0
        ...
    REPEAT.
The first two BREAK statements would cause execution to continue with the assignment statement, the third would terminate the program, and the fourth would terminate the program with an error message.
READ A,B,C reads three numbers from the input and assigns their values to A, B, and C.
Input data immediately follows the program.
Numbers in the data have the same format as constants and are separated by spaces.
If the variables FOO and BAR have the values 1 and 2.71828, the statement PRINT FOO,BAR should print a line with something like :
FOO=1.000000 BAR=2.71828

You may use any format you like for the numbers (including "scientific notation", but each PRINT statement should begin a new line and each number printed should be labeled by the corresponding identifier in the PRINT statement.
Recommended Method

Use the LL(1) parsing method to translate the program into a tree; then interpret the tree with a procedure that "walks" through the tree using a stack. The tables given below are to help with the parser. Hints on building the tree can be gleaned from the sample program found here which builds trees for assignment statements (in C).
Grading

As usual, the grade will be based not only on correctness, but on programming style, robustness, and quality of documentation and test data. As a rough guide, the relative values of various features is given below. (But remember that a working modest feature is worth far more than a fancy feature with bugs).
Assignment and expressions	70%
IF-THEN-ELSE	10%
LOOP, BREAK	10%
READ, and PRINT	10%
You may assume all input programs are correct. Extra credit suggestions include detection of and recovery from errors, and new features such as goto's or parameterless procedures.

Email me your source code and test files. If your Descarte test program doesn't print, just dump the symbol table after the program is done executing.

The Descartes-2 Grammar


TERMINAL SYMBOLS             GRAMMAR
 0. PERIOD                      0.    prog : stmt-list PERIOD
 1. SEMICOLON                   1.    stmt-list : stmt stmt-tail
 2. IF                          2.    stmt-tail : SEMICOLON stmt stmt-tail
 3. THEN                        3.    stmt-tail :
 4. ELSE                        4.    stmt : if-stmt
 5. FI                          5.    stmt : loop-stmt
 6. LOOP                        6.    stmt : break-stmt
 7. ID                          7.    stmt : assign-stmt
 8. COLON                       8.    stmt : read-stmt
 9. REPEAT                      9.    stmt : print-stmt
10. BREAK                       10.   stmt :
11. BECOMES                     11.   if-stmt : IF expr THEN stmt-list else-part
12. PRINT                       12.   else-part : ELSE stmt-lit FI
13. READ                        13.   else-part : FI
14. ,                           14.   loop-stmt : LOOP ID COLON stmt-list REPEAT
15. OR                          15.   break-stmt : BREAK id-option
16. AND                         16.   id-option : ID
17. <                           17.   id-option :
18. <=                          18.   assign-stmt : ID BECOMES expr
19. =                           19.   print-stmt : PRINT ID id-list-tail
20. >=                          20.   read-stmt : READ ID id-list-tail
21. >                           21.   id-list-tail : , ID id-list-tail
22. <>                          22.   id-list-tail :
23. +                           23.   expr : bool-term bool-term-tail
24. -                           24.   bool-term-tail :
25. *                                       OR bool-term bool-term-tail
26. /                           25.   bool-term-tail :
27. (                           26.   bool-term : bool-factor bool-factor-tail
28. )                           27.   bool-factor-tail :
29. CONST                                   AND bool-factor bool-factor-tail
                                28.   bool-factor-tail :
NON-TERMINAL SYMBOLS            29.   bool-factor : arith-expr relation-option
30. prog                        30.   relation-option : < arith-expr
31. stmt-list                   31.   relation-option : <= arith-expr
32. stmt                        32.   relation-option : = arith-expr
33. stmt-tail                   33.   relation-option : >= arith-expr
34. if-stmt                     34.   relation-option : > arith-expr
35. loop-stmt                   35.   relation-option : <> arith-expr
36. break-stmt                  36.   relation-option :
37. assign-stmt                 37.   arith-expr : term term-tail
38. read-stmt                   38.   term-tail : + term term-tail
39. print-stmt                  39.   term-tail : - term term-tail
40. expr                        40.   term-tail :
41. else-part                   41.   term : factor factor-tail
42. id-option                   42.   factor-tail : * factor factor-tail
43. id-list-tail                43.   factor-tail : / factor factor-tail
44. bool-term                   44.   factor-tail :
45. bool-term-tail              45.   factor : - factor
46. bool-factor                 46.   factor : atom
47. bool-factor-tail            47.   factor : ( expr )
48. arith-expr                  48.   atom : ID
49. relation-option             49.   atom : CONST
50. term
51. term-tail
52. factor 
53. factor-tail
54. atom

The following, very-useful table, will be explained in class.

prog                assign-stmt            bool-factor             term-tail

PERIOD      0       ID      18          ID          29          PERIOD      40
SEMICOLON   0                           -           29          SEMICOLON   40
IF          0      read-stmt            (           29          THEN        40
LOOP        0                           CONST       29          ELSE        40
ID          0       READ    20                                  FI          40
BREAK       0                           bool-factor-tail        REPEAT      40
PRINT       0      print-stmt                                   OR          40
READ        0                           PERIOD      28          AND         40
                    PRINT   19          SEMICOLON   28          <           40
stmt-list                               THEN        28          <=          40  
                   expr                 ELSE        28          =           40
PERIOD      1                           FI          28          >=          40
SEMICOLON   1       ID      23          REPEAT      28          >           40
IF          1       -       23          OR          28          <>          40
ELSE        1       (       23          AND         27          +           38
FI          1       CONST   23          )           28          -           39
LOOP        1                                                   )           40
ID          1      else-part            arith-expr
REPEAT      1                                                   factor
BREAK       1       ELSE    12          ID          37
PRINT       1       FI      13          -           37          ID          46
READ        1                           (           37          -           45
                   id-option            CONST       37          (           47
stmt                                                            CONST       46
                    PERIOD      17      relation-option
PERIOD      10      SEMICOLON   17                               factor-tail
SEMICOLON   10      ELSE        17      PERIOD      36
IF          4       FI          17      SEMICOLON   36          PERIOD      44
ELSE        10      ID          16      THEN        36          SEMICOLON   44
FI          10      REPEAT      17      ELSE        36          THEN        44
LOOP        5                           FI          36          ELSE        44
ID          7      id-list-tail         REPEAT      36          FI          44
REPEAT      10                          OR          36          REPEAT      44
BREAK       6       PERIOD      22      AND         36          OR          44
PRINT       9       SEMICOLON   22      <           30          AND         44
READ        8       ELSE        22      <=          31          <           44  
                    FI          22      =           32          <=          44
stmt-tail           REPEAT      22      >=          33          =           44  
                    ,           21      >           34          >=          44
PERIOD      3                           <>          35          >           44
SEMICOLON   2      bool-term            )           36          <>          44
ELSE        3                                                   +           44
FI          3       ID          26        term                  -           44
REPEAT      3       -           26                              *           42
                    (           26      ID          41          /           43
if-stmt             CONST       26      -           41          )           44
                                        (           41
IF          11    bool-term-tail        CONST       41          atom

loop-stmt           PERIOD      25                              ID          48  
                    SEMICOLON   25                              CONST       49
LOOP        14      THEN        25
                    ELSE        25
break-stmt          FI          25 
                    REPEAT      25
BREAK       15      OR          24  
                    )           25








