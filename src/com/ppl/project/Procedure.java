/**
 * Author : ippil
 *	Nov 19, 2017
 */
package com.ppl.project;

/**
 * @author ippil
 *
 */
public class Procedure{
	
	String v;
	LetLangExp e1;
	let_lang_env env;

	public Procedure() {
		// TODO Auto-generated constructor stub
	}

	public String getV() {
		return v;
	}

	public void setV(String v) {
		this.v = v;
	}

	public LetLangExp getE1() {
		return e1;
	}

	public void setE1(LetLangExp e1) {
		this.e1 = e1;
	}

	public let_lang_env getEnv() {
		return env;
	}

	public void setEnv(let_lang_env env) {
		this.env = env;
	}
	
	public IntBoolProc valueOf(IntBoolProc operand) {
		IntBoolProc ibp = new IntBoolProc();
		this.env.extendEnv(v, operand, this.env);
		ibp = e1.valueOf(this.env);
		return ibp;
	}
}
