package com.ppl.project;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Queue;

import com.ppl.project.Let_lang_scanner.Token;

public class Let_lang_parser {

	public static LinkedList<Token> tokens = new LinkedList<>();
	public static LinkedList<Token> tokens1 = new LinkedList<>();
	public static Token s;
	public static int index = 0;
	public static int tabSpace = 0;

	public static Queue<LetLangExp> dataQueue = new LinkedList<LetLangExp>();
	public static Queue<LetLangExp> dataQueue1 = new LinkedList<LetLangExp>();

	@SuppressWarnings("unchecked")
	public void getTokens() {

		// String input = "let x = 7\n" + "in let y = 2\n" + "in let y =\n" + "let x =
		// -(x, 1) in -(x, y)\n"
		// + "in -(-(x, 8), y)";

		// String input = "if iszero(-(x, 11))\n" + "then -(y, 2)\n" + "else -(y, 4)";

		// String input = "let x = 200 in let f = proc (z) -(z, x) in let x = 100 in let
		// g = proc (z) -(z, x) in -((f 1), (g 1))";

		String input = "letrec double(x) = if iszero(x) then 0 else +((double -(x," + "1)), 2) in (double 6)";

		// String input = "let x = 7 in -(x, 8)";

		Let_lang_scanner llc = new Let_lang_scanner();
		tokens = llc.lex(input);
		tokens1 = (LinkedList<Token>) this.tokens.clone();
	}

	public LetLangExp parseToken() {
		LetLangExp lle = new LetLangExp();
		if (s.type.toString().equals("Integeri32")) {
			lle = number();
		} else if (s.type.toString().equals("Minus")) {
			lle = minus();
		} else if (s.type.toString().equals("Plus")) {
			lle = plus();
		} else if (s.type.toString().equals("IsZero")) {
			lle = isZero();
		} else if (s.type.toString().equals("Ifexp")) {
			lle = ifExp();
		} else if (s.type.toString().equals("Letexp")) {
			lle = letExp();
		} else if (s.type.toString().equals("Proc")) {
			lle = ProcExp();
		} else if (s.type.toString().equals("Letrec")) {
			lle = letrec();
		} else if (s.type.toString().equals("Identifier")) {
			lle = identifier();
		} else if (s.type.toString().equals("LPARN")) {
			lle = callExp();
		} else {
			getToken();
			parseToken();
		}

		return lle;

	}

	public IsZeroExp isZero() {

		IsZeroExp ize = new IsZeroExp();
		tabprinter(tabSpace);
		System.out.println("isZero(");
		tabSpace = tabSpace + 1;
		lParen();
		getToken();
		ize.setExp1(parseToken());
		tabSpace = tabSpace - 1;
		System.out.println("");
		tabprinter(tabSpace);
		System.out.print(")");
		rParen();
		return ize;
	}

	public LetRecExp letrec() {
		LetRecExp lr = new LetRecExp();
		System.out.println("LetRecExp(");
		tabSpace = tabSpace + 1;
		getToken();
		lr.setV1(identifier());
		System.out.println(",");
		lParen();
		lr.setV2(identifier());
		rParen();
		System.out.println(",");
		assign();
		lr.setE1(parseToken());
		System.out.println(",");
		in();
		lr.setE2(parseToken());
		System.out.println("");
		tabSpace = tabSpace - 1;
		tabprinter(tabSpace);
		System.out.print(")");
		dataQueue.add(lr);
		return lr;
	}

	public IfExp ifExp() {
		IfExp ie = new IfExp();
		tabprinter(tabSpace);
		System.out.println("IfExp(");
		tabSpace = tabSpace + 1;
		getToken();
		ie.setExp1(parseToken());
		getToken();
		System.out.println(",");
		ie.setExp2(parseToken());
		getToken();
		System.out.println(",");
		ie.setExp3(parseToken());
		tabSpace = tabSpace - 1;
		System.out.println("");
		tabprinter(tabSpace);
		System.out.print(")");
		dataQueue.add(ie);

		return ie;
	}

	public ConstExp number() {
		tabprinter(tabSpace);
		System.out.println("ConstExp(");
		tabSpace = tabSpace + 1;
		tabprinter(tabSpace);
		ConstExp e1 = new ConstExp();
		IntBoolProc ibp1 = new IntBoolProc();
		ibp1.setValue(Integer.parseInt(s.data.toString()));
		e1.setValue(ibp1);
		System.out.println(s.data);
		tabSpace = tabSpace - 1;
		tabprinter(tabSpace);
		System.out.print(")");
		getToken();
		return e1;

	}

	public void lParen() {
		getToken();
	}

	public void comma() {
		getToken();
	}

	public void rParen() {
		getToken();
	}

	public DiffExp minus() {
		DiffExp de = new DiffExp();
		tabprinter(tabSpace);
		System.out.println("DiffExp(");
		tabSpace = tabSpace + 1;
		getToken();
		lParen();
		de.setE1(parseToken());
		comma();// ,
		System.out.println(",");
		de.setE2(parseToken());
		System.out.println("");
		tabSpace = tabSpace - 1;
		tabprinter(tabSpace);
		System.out.print(")");
		rParen();
		return de;
	}

	public PlusExp plus() {
		PlusExp de = new PlusExp();
		tabprinter(tabSpace);
		System.out.println("PlusExp(");
		tabSpace = tabSpace + 1;
		getToken();
		lParen();
		de.setE1(parseToken());
		comma();
		System.out.println(",");
		de.setE2(parseToken());
		System.out.println("");
		tabSpace = tabSpace - 1;
		tabprinter(tabSpace);
		System.out.print(")");
		rParen();
		return de;
	}

	public VarExp identifier() {
		tabprinter(tabSpace);
		System.out.println("VarExp(");
		tabSpace = tabSpace + 1;
		tabprinter(tabSpace);
		System.out.println("\"" + s.data + "\"");
		tabSpace = tabSpace - 1;
		tabprinter(tabSpace);
		System.out.print(")");
		VarExp v = new VarExp(s.data.toString());
		getToken();
		return v;
	}

	public void assign() {
		getToken();
	}

	public void in() {
		getToken();
	}

	public LetExp letExp() {
		tabprinter(tabSpace);
		System.out.println("LetExp(");
		LetExp exp = new LetExp();
		tabSpace = tabSpace + 1;
		getToken();
		exp.setVariable(identifier());
		System.out.println(",");
		assign();
		exp.setE1(parseToken());
		in();
		System.out.println(",");
		exp.setE2(parseToken());
		System.out.println("");
		tabSpace = tabSpace - 1;
		tabprinter(tabSpace);
		System.out.print(")");
		dataQueue.add(exp);
		return exp;
	}

	public ProcExp ProcExp() {
		tabprinter(tabSpace);
		System.out.println("ProcExp(");
		ProcExp pe = new ProcExp();
		tabSpace = tabSpace + 1;
		getToken();
		lParen();
		pe.setV(identifier());
		System.out.println(",");
		rParen();
		pe.setE1(parseToken());
		System.out.println("");
		tabSpace = tabSpace - 1;
		tabprinter(tabSpace);
		System.out.print(")");
		return pe;
	}

	public CallExp callExp() {
		tabprinter(tabSpace);
		System.out.println("CallExp(");
		CallExp ce = new CallExp();
		tabSpace = tabSpace + 1;
		lParen();
		ce.setE1(parseToken());
		System.out.println(",");
		ce.setE2(parseToken());
		System.out.println("");
		tabSpace = tabSpace - 1;
		tabprinter(tabSpace);
		System.out.print(")");
		rParen();
		return ce;

	}

	public void getToken() {

		tokens.pop();
		if (!tokens.isEmpty()) {
			s = tokens.getFirst();
		}
	}

	public static void main(String args[]) throws FileNotFoundException {

		// PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
		// System.setOut(out);
		Let_lang_parser letParser = new Let_lang_parser();
		letParser.getTokens();
		s = tokens.getFirst();
		letParser.parseToken();
		Evaluate ev = new Evaluate();
		dataQueue1.add((LetLangExp) dataQueue.toArray()[dataQueue.size() - 1]);
		let_lang_env lle = new let_lang_env();
		IntBoolProc value = ev.evaluateParse(dataQueue1, lle);
		System.out.println("Expression result : " + value.getValue());

	}

	public void tabprinter(Integer tab_counter) {
		int count = 0;
		for (count = 0; count < tab_counter; count++) {
			System.out.print("\t");
		}
	}
}