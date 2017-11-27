/**
 * Author : ippil
 *	Nov 19, 2017
 */
package com.ppl.project;

/**
 * @author ippil
 *
 */
public class PlusExp extends LetLangExp {

	/**
	 * 
	 */
	LetLangExp e1;
	LetLangExp e2;
	private IntBoolProc value;

	public IntBoolProc getValue() {
		return value;
	}

	public void setValue(IntBoolProc value) {
		this.value = value;
	}

	public PlusExp() {
		this.name = "PlusExp";
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
//		if (this.e1.val == null) {
//			let_lang_env le = new let_lang_env();
//			IntBoolProc ibp = e1.valueOf(lle);
//			// VarExp ve = (VarExp) e1;
//			IntBoolProc eb = le.Applyenv(lle, ibp.proc.v);
//			this.e1.val = eb.getValue();
//		}
//		if (this.e2.val == null) {
//			let_lang_env le = new let_lang_env();
//			// VarExp ve = (VarExp) e2;
//			IntBoolProc ibp = new IntBoolProc();
//			ibp = e2.valueOf(lle);
//			IntBoolProc eb = le.Applyenv(lle, ibp.proc.v);
//			this.e2.val = eb.getValue();
//		}
		this.val = this.e1.valueOf(lle).value + this.e2.valueOf(lle).value;
//		a.setBoolexp(false);
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
