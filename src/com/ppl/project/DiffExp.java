package com.ppl.project;

import com.ppl.project.Let_lang_scanner.Token;

public class DiffExp extends LetLangExp {

	LetLangExp e1;
	LetLangExp e2;
	private IntBoolProc value;

	public IntBoolProc getValue() {
		return value;
	}

	public void setValue(IntBoolProc value) {
		this.value = value;
	}

	public DiffExp() {
		this.name = "DiffExp";
	}

	public LetLangExp getE1() {
		return e1;
	}

	public LetLangExp getE2() {
		return e2;
	}

	public void setE1(LetLangExp e1) {
		this.e1 = e1;
	}

	public void setE2(LetLangExp e2) {
		this.e2 = e2;
	}

	public IntBoolProc valueOf(let_lang_env lle) {
		IntBoolProc a = new IntBoolProc();
		this.val = this.e1.valueOf(lle).value - this.e2.valueOf(lle).value;
		a.setValue(this.val);
		return a;
	}

	public void CalculateExp1(LetLangExp exp1) {
		System.out.println("Diff Calculate Exp1");
	}

	public void CalculateExp2(LetLangExp exp2) {
		System.out.println("Diff Calculate Exp2");
	}

}
