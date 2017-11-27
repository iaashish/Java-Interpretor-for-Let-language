package com.ppl.project;

import java.io.IOException;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Let_lang_scanner {

	public static enum TokenType {
		Integeri32("-?[0-9]+"), LPARN("[(]"), Rparen("[)]"), Comma("[,]"), Minus("[-]"), Plus("[+]"), Proc(
				"proc"), Assign("[=]"), Letrec("letrec"), IsZero("iszero"), Ifexp("if"), Thenexp("then"), Elseexp(
						"else"), Letexp("let"), Inexp("in"), Identifier("-?[a-z]+"), WHITESPACE("[ \t \f \r \n]+");

		public final String pattern;

		private TokenType(String pattern) {
			this.pattern = pattern;
		}
	}

	public static class Token {
		public TokenType type;
		public String data;

		public Token(TokenType type, String data) {
			this.type = type;
			this.data = data;
		}

		public String getData() {
			return data;
		}

		@Override
		public String toString() {
			return String.format("%s %s", type.name(), data);
		}
	}

	public static LinkedList<Token> lex(String input) {
		// The tokens to return
		LinkedList<Token> tokens = new LinkedList<Token>();

		// Lexer logic begins here
		StringBuffer tokenPatternsBuffer = new StringBuffer();
		for (TokenType tokenType : TokenType.values())
			tokenPatternsBuffer.append(String.format("|(?<%s>%s)", tokenType.name(), tokenType.pattern));
		Pattern tokenPatterns = Pattern.compile(new String(tokenPatternsBuffer.substring(1)));

		// Begin matching tokens
		Matcher matcher = tokenPatterns.matcher(input);
		while (matcher.find()) {

			if (matcher.group().matches(TokenType.Ifexp.pattern)) {
				tokens.add(new Token(TokenType.Ifexp, matcher.group()));
				continue;
			} else if (matcher.group().matches(TokenType.IsZero.pattern)) {
				tokens.add(new Token(TokenType.IsZero, matcher.group()));
				continue;
			} else if (matcher.group().matches(TokenType.Proc.pattern)) {
				tokens.add(new Token(TokenType.Proc, matcher.group()));
				continue;
			} else if (matcher.group().matches(TokenType.Letrec.pattern)) {
				tokens.add(new Token(TokenType.Letrec, matcher.group()));
				continue;
			} else if (matcher.group().matches(TokenType.Inexp.pattern)) {
				tokens.add(new Token(TokenType.Inexp, matcher.group()));
				continue;
			} else if (matcher.group().matches(TokenType.Integeri32.pattern)) {
				tokens.add(new Token(TokenType.Integeri32, matcher.group()));
				continue;
			} else if (matcher.group().matches(TokenType.Letexp.pattern)) {
				tokens.add(new Token(TokenType.Letexp, matcher.group()));
				continue;
			} else if (matcher.group().matches(TokenType.Identifier.pattern)) {
				tokens.add(new Token(TokenType.Identifier, matcher.group()));
				continue;
			} else if (matcher.group().matches(TokenType.Assign.pattern)) {
				tokens.add(new Token(TokenType.Assign, matcher.group()));
				continue;
			} else if (matcher.group().matches(TokenType.LPARN.pattern)) {
				tokens.add(new Token(TokenType.LPARN, matcher.group()));
				continue;
			} else if (matcher.group().matches(TokenType.Rparen.pattern)) {
				tokens.add(new Token(TokenType.Rparen, matcher.group()));
				continue;
			} else if (matcher.group().matches(TokenType.Comma.pattern)) {
				tokens.add(new Token(TokenType.Comma, matcher.group()));
				continue;
			} else if (matcher.group().matches(TokenType.Minus.pattern)) {
				tokens.add(new Token(TokenType.Minus, matcher.group()));
				continue;
			} else if (matcher.group().matches(TokenType.Plus.pattern)) {
				tokens.add(new Token(TokenType.Plus, matcher.group()));
				continue;

			} else if (matcher.group().matches(TokenType.Thenexp.pattern)) {
				tokens.add(new Token(TokenType.Thenexp, matcher.group()));
				continue;
			} else if (matcher.group().matches(TokenType.Elseexp.pattern)) {
				tokens.add(new Token(TokenType.Elseexp, matcher.group()));
				continue;
			} else if (matcher.group().matches(TokenType.WHITESPACE.pattern)) {
				continue;
			}
		}
		return tokens;
	}

	public static void main(String[] args) throws IOException {
		// String input = "let x = 7\n" + "in let y = 2\n" + "in let y =\n" + "let x =
		// -(x, 1) in -(x, y)\n"
		// + "in +(-(x, 8), y)";
		// String input = "let x = 200 in let f = proc(z) -(z,x) in let x = 100 in let g
		// = proc (z) -(z, x) in -((f 1), (g 1))";
		String input = "letrec double(x) = if iszero(x) then 0 else +((double -(x 1)), 2) in (double 6)";
		LinkedList<Token> tokens = lex(input);
		for (Token token : tokens)
			System.out.println(token);
	}
}