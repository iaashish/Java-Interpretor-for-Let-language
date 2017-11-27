package com.ppl.project;

public class IfExp extends LetLangExp {

	private LetLangExp exp1; // expression 1
	private LetLangExp exp2; // expression 2
	private LetLangExp exp3;

	public LetLangExp getExp1() {
		return exp1;
	}

	public void setExp1(LetLangExp exp1) {
		this.exp1 = exp1;
	}

	public LetLangExp getExp2() {
		return exp2;
	}

	public void setExp2(LetLangExp exp2) {
		this.exp2 = exp2;
	}

	public LetLangExp getExp3() {
		return exp3;
	}

	public void setExp3(LetLangExp exp3) {
		this.exp3 = exp3;
	}

	public IntBoolProc evaluateValue(let_lang_env lle) {
		IntBoolProc ib = new IntBoolProc();

		if (this.exp1.valueOf(lle).getValue() == 0) {

			ib = exp2.valueOf(lle);
		} else {
			ib = exp3.valueOf(lle);
		}

		return ib;
	}

	public IntBoolProc valueOf(let_lang_env lle) {
		if (exp1.valueOf(lle).boolexp) {
			return this.exp2.valueOf(lle);
		} else {
			return this.exp3.valueOf(lle);
		}

	}

	public IfExp() {
		this.name = "ifexp";
	}

}
