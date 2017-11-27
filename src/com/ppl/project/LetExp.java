package com.ppl.project;

import com.ppl.project.Let_lang_scanner.Token;

public class LetExp extends LetLangExp {

	public VarExp variable;
	private LetLangExp e1; // expression 1
	private LetLangExp e2; // expression 2
	private LetLangExp e3;

	public LetExp() {
		// TODO Auto-generated constructor stub
		this.name = "LetExp";
	}

	public VarExp getVariable() {
		return variable;
	}

	public void setE3(LetLangExp e3) {
		this.e3 = e3;
	}

	public LetLangExp getE1() {
		return e1;
	}

	public LetLangExp getE2() {
		return e2;
	}

	public void setVariable(VarExp variable) {
		this.variable = variable;
	}

	public void setE1(LetLangExp e1) {
		this.e1 = e1;
	}

	public void setE2(LetLangExp e2) {
		this.e2 = e2;
	}
	
	public int valueOf() {
		return val;
	}

	public LetLangExp getE3() {
		return e3;
	}

	public void CalculateExp1(LetLangExp exp1) {
		System.out.println("LetExp Calculate Exp1");
	}

	public void CalculateExp2(LetLangExp exp2) {
		System.out.println("LetExp Calculate Exp1");
	}
}
