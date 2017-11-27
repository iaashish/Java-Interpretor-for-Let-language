/**
 * Author : ippil
 *	Nov 24, 2017
 */
package com.ppl.project;

/**
 * @author ippil
 *
 */
public class LetRecExp extends LetLangExp {
	VarExp v1;
	VarExp v2;
	LetLangExp e1;
	LetLangExp e2;

	public LetRecExp() {
		this.name = "LetRecExp";
	}

	public VarExp getV1() {
		return v1;
	}

	public void setV1(VarExp v1) {
		this.v1 = v1;
	}

	public VarExp getV2() {
		return v2;
	}

	public void setV2(VarExp v2) {
		this.v2 = v2;
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

}
