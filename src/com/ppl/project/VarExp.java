package com.ppl.project;

public class VarExp extends LetLangExp {

	public String v;
	

	public VarExp(String v) {
		this.v = v;
		this.name = "VarExp";
	}

	public String getV() {
		return v;
	}

	public void setV(String v) {
		this.v = v;
	}
	
//	public int valueOf() {
//		return 0;
//	}
	
	public int varGetVaue(let_lang_env llv) {
		int value = 0;
		IntBoolProc b = new IntBoolProc();
		b = llv.Applyenv(llv, this.v);
		if (b.isBoolexp()) {
			value = b.getValue();
		} 
		
		return value;
	}
	
	public IntBoolProc valueOf(let_lang_env llv) {
		
		int value = 0;
		IntBoolProc b = new IntBoolProc();
		b = llv.Applyenv(llv, this.v);
//		if (b.isBoolexp()) {
//			value = b.getValue();
//		} 
//		b.setValue(value);
		return b;
		
	}
}
