/**
 * Author : ippil
 *	Nov 19, 2017
 */
package com.ppl.project;

/**
 * @author ippil
 *
 */
public class ProcExp extends LetLangExp {
	LetLangExp e1;
	public VarExp v;
	public let_lang_env copy_env;

	public let_lang_env getCopy_env() {
		return copy_env;
	}

	public void setCopy_env(let_lang_env copy_env) {
		this.copy_env = copy_env;
	}

	public ProcExp() {
		this.name = "ProcExp";
	}

	public LetLangExp getE1() {
		return e1;
	}

	public void setE1(LetLangExp e1) {
		this.e1 = e1;
	}

	public VarExp getV() {
		return v;
	}

	public void setV(VarExp v) {
		this.v = v;
	}

	public IntBoolProc valueOf(let_lang_env lle) {
		IntBoolProc ib = new IntBoolProc();
		Procedure p = new Procedure();
		let_lang_env v1 = new let_lang_env();
		this.copy_env = v1.deepcopy(lle);
		p.env = this.getCopy_env();
		p.e1 = this.getE1();
		p.v = this.getV().v;
		ib.proc = p;
		return ib;
	}

}
