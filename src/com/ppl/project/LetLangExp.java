package com.ppl.project;

import com.ppl.project.Let_lang_scanner.Token;

public class LetLangExp {

	Token expression;
	String name;
	Integer val;
	Boolean bool;

	public void setVariable(String variable) {
		this.variable = variable;
	}

	String variable;

	public LetLangExp() {

	}

	public void setExpression(Token expression) {
		this.expression = expression;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setVal(Integer val) {
		this.val = val;
	}

	public IntBoolProc valueOf(let_lang_env lle) {
		
		return null;

	}

}
