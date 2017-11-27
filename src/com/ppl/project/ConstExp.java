package com.ppl.project;

public class ConstExp extends LetLangExp {

	private IntBoolProc value;

	public ConstExp() {
		this.name = "ConstExp";
	}

	public void setValue(IntBoolProc value) {
		this.value = value;
	}

	public IntBoolProc getValue() {
		return value;
	}

	// public void setValue(int value) {
	// this.val = value;
	// this.value = value;
	// }

	// public IntBoolProc valueOf(let_lang_env lle) {
	//
	// return null;
	// }

	/**
	 * @param lle
	 * @return
	 */
	public IntBoolProc valueOf(let_lang_env lle) {
		// TODO Auto-generated method stub
		IntBoolProc ibp = new IntBoolProc();
		ibp.setValue(this.value.value);
		return ibp;
	}

}
