package com.ppl.project;

public class IsZeroExp extends LetLangExp {

	LetLangExp exp1;

	public IsZeroExp() {
		this.name = "iszeroexp";
	}

	public LetLangExp getExp1() {
		return exp1;
	}

	public void setExp1(LetLangExp exp1) {
		this.exp1 = exp1;
	}

	public IntBoolProc evaluateValue(let_lang_env lle) {

		return null;
	}

	public IntBoolProc valueOf(let_lang_env lle) {
		IntBoolProc a = new IntBoolProc();
		a.boolexp = exp1.valueOf(lle).value==0;
//		if(a.getValue() == 0) {
//			a.setBoolexp(true);
//		}
		//a = exp1.valueOf(lle);

		return a;
	}

}
