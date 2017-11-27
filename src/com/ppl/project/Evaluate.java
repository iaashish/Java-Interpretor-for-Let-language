/**
 * Author : ippili
 *	Oct 23, 2017
 */

package com.ppl.project;

import java.util.LinkedList;
import java.util.Queue;

public class Evaluate {

	let_lang_env env = new let_lang_env();
	let_lang_env env2 = new let_lang_env();
	// LetLangExp ev = new LetLangExp();
	String valuetobeprinted;
	// int finalvalue = 0;
	IntBoolProc finalvalue = new IntBoolProc();

	public Evaluate() {
		// TODO Auto-generated constructor stub
	}

	public IntBoolProc evaluateParse(Queue<LetLangExp> tokens, let_lang_env lle) {

		LetLangExp ev = tokens.poll();
		Queue<LetLangExp> q1 = new LinkedList<LetLangExp>();
		if (ev.name.equalsIgnoreCase("LetExp")) {
			LetExp le = (LetExp) ev;
			VarExp var = le.getVariable();
			LetLangExp exp1 = le.getE1();
			LetLangExp exp2 = le.getE2();
			LetLangExp exp3;

			le.setE3(exp1);
			exp3 = le.getE3();
			tokens.add(exp3);
			IntBoolProc ibp = new IntBoolProc();
			ibp = evaluateParse(tokens, lle);
			lle.extendEnv(var.v, ibp, lle);

			le.setE3(exp2);// 2
			exp3 = le.getE3();
			tokens.add(exp3);
			let_lang_env temp = lle;
			IntBoolProc ibp1 = new IntBoolProc();
			ibp1 = evaluateParse(tokens, temp);
			lle.list.remove(lle.list.size() - 1);
			finalvalue = ibp1;
			return ibp1;

		} else if (ev.name.equalsIgnoreCase("ConstExp")) {
			ConstExp ce = (ConstExp) ev;
			IntBoolProc ibp = new IntBoolProc();
			ibp = ce.valueOf(lle);
			// ce.val = ce.getValue();
			return ibp;
		} else if (ev.name.equalsIgnoreCase("DiffExp")) {
			DiffExp de = (DiffExp) ev;

			tokens.add(de.getE1());
			IntBoolProc ibp1 = new IntBoolProc();
			ibp1 = evaluateParse(tokens, lle);
			de.e1.val = ibp1.getValue();

			tokens.add(de.getE2());
			IntBoolProc ibp2 = new IntBoolProc();
			ibp2 = evaluateParse(tokens, lle);
			de.e2.val = ibp2.getValue();

			IntBoolProc ibp3 = new IntBoolProc();
			ibp3 = de.valueOf(lle);

			finalvalue = ibp3;
			return ibp3;
		} else if (ev.name.equalsIgnoreCase("PlusExp")) {
			PlusExp pe = (PlusExp) ev;

			tokens.add(pe.getE1());
			IntBoolProc ibp1 = new IntBoolProc();
			ibp1 = evaluateParse(tokens, lle);
			pe.e1.val = ibp1.getValue();

			tokens.add(pe.getE2());
			IntBoolProc ibp2 = new IntBoolProc();
			ibp2 = evaluateParse(tokens, lle);
			pe.e2.val = ibp2.getValue();

			IntBoolProc ibp3 = new IntBoolProc();
			ibp3 = pe.valueOf(lle);

			finalvalue = ibp3;
			return ibp3;

		} else if (ev.name.equalsIgnoreCase("VarExp")) {
			VarExp ve = (VarExp) ev;
			IntBoolProc ibp1 = new IntBoolProc();
			ibp1 = ve.valueOf(lle);
			finalvalue = ibp1;
			return ibp1;
		} else if (ev.name.equalsIgnoreCase("ifexp")) {
			IfExp ie = (IfExp) ev;
			LetLangExp exp1 = ie.getExp1();
			if (exp1.valueOf(lle).boolexp) {
				LetLangExp exp2 = ie.getExp2();
				tokens.add(exp2);
				finalvalue = evaluateParse(tokens, lle);
				return finalvalue;
			} else {
				LetLangExp exp3 = ie.getExp3();
				tokens.add(exp3);
				finalvalue = evaluateParse(tokens, lle);
				return finalvalue;
			}
		} else if (ev.name.equalsIgnoreCase("CallExp")) {
			CallExp ce = (CallExp) ev;
			IntBoolProc ibp = new IntBoolProc();
			ibp = ce.valueOf(lle);
			return ibp;
		} else if (ev.name.equalsIgnoreCase("ProcExp")) {
			ProcExp pe = (ProcExp) ev;
			IntBoolProc ibp = new IntBoolProc();
			ibp = pe.valueOf(lle);
			finalvalue = ibp;
			return ibp;
		} else if (ev.name.equalsIgnoreCase("LetRecExp")) {
			LetRecExp lr = (LetRecExp) ev;
			VarExp v1 = lr.getV1();
			VarExp bvar = lr.getV2();
			LetLangExp exp1 = lr.getE1();
			lle.extendEnvRec(v1.v, bvar.v, exp1, lle);
			LetLangExp exp2 = lr.getE2();
			tokens.add(exp2);
			finalvalue = evaluateParse(tokens, lle);
			lle.list.remove(lle.list.size() - 1);
			return finalvalue;
		}
		return finalvalue;
	}

}
