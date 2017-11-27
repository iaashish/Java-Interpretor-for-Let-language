/**
 * Author : ippil
 *	Nov 19, 2017
 */
package com.ppl.project;

import java.util.ArrayList;

import org.omg.IOP.ENCODING_CDR_ENCAPS;

/**
 * @author ippil
 *
 */
public class CallExp extends LetLangExp {

	LetLangExp e1;// operator
	LetLangExp e2;// operand

	public CallExp() {
		this.name = "CallExp";
	}

	public LetLangExp getE1() {
		return e1;
	}

	public void setE1(LetLangExp e1) {
		this.e1 = e1;
	}

	public LetLangExp getE2() {
		return e2;
	}

	public void setE2(LetLangExp e2) {
		this.e2 = e2;
	}

	public IntBoolProc valueOf(let_lang_env lle) {
		IntBoolProc iboperand = new IntBoolProc();
		iboperand = e2.valueOf(lle);
		IntBoolProc iboperator = new IntBoolProc();
		VarExp ve = (VarExp) e1;
		String operator = ve.getV();
		ve.valueOf(lle);
		iboperator = e1.valueOf(lle);
		ArrayList<Environment> a = lle.list;
		for (int i = 0; i < a.size(); i++) {
			if (a.get(i).name.equalsIgnoreCase(operator)) {
				Procedure p = (Procedure) a.get(i).value.getProc();
				iboperator = p.valueOf(iboperand);
			}
		}
		return iboperator;
	}

}
