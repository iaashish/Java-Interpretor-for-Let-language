package com.ppl.project;

import java.util.ArrayList;

public class let_lang_env {

	public ArrayList<Environment> list = new ArrayList<>();

	public let_lang_env() {

	}

	public let_lang_env empty_env() {

		let_lang_env env = new let_lang_env();
		return env;
	}

	public let_lang_env extendEnv(String variable, IntBoolProc ib, let_lang_env lle) {

		Environment env = new Environment();
		env.name = variable;
		env.setValue(ib);
		lle.list.add(env);
		return lle;
	}

	public let_lang_env extendEnvRec(String v1, String v2, LetLangExp exp, let_lang_env lle) {
		Environment env = new Environment();
		env.name = v1;
		Procedure p = new Procedure();
		p.e1 = exp;
		p.env = lle;
		p.v = v2;
		IntBoolProc ibp = new IntBoolProc();
		ibp.proc = p;
		env.value = ibp;
		lle.list.add(env);
		return lle;
	}

	public IntBoolProc Applyenv(let_lang_env env, String x) {
		IntBoolProc b = new IntBoolProc();
		for (Environment e : env.list) {
			if (e.name.equalsIgnoreCase(x)) {
//				b.setBoolexp(true);
				b.setValue(e.value.getValue());
			}
		}
		return b;
	}

	public let_lang_env deepcopy(let_lang_env lle) {
		let_lang_env letenv = new let_lang_env();
		ArrayList<Environment> listenv = new ArrayList<>();
		Environment env1 = new Environment();
		for (Environment le : lle.list) {
			env1.name = le.name;
			env1.value = le.value;
			// env1.p = le.p;
			listenv.add(env1);
		}
		letenv.list = listenv;
		return letenv;
	}
}
