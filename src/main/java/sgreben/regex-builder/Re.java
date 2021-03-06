package sgreben.regex_builder;

import sgreben.regex_builder.expression.*;
import sgreben.regex_builder.compiler.Compiler;

public class Re {
	public static Expression string(String s) {
		return new Literal(s);
	}
	public static Expression character(char c) {
		return string(""+c);
	}
	public static Expression beginInput() {
		return new Raw("\\A");
	}
	public static Expression endInput() {
		return new Raw("\\z");
	}
	public static Expression endInputBeforeFinalTerminator() {
		return new Raw("\\Z");
	}
	public static Expression beginLine() {
		return new Raw("^");
	}
	public static Expression endLine() {
		return new Raw("$");
	}
	public static Expression anyCharacter() {
		return new AnyCharacter();
	}
	public static Expression charClass(String charClass) {
		return new Raw(charClass);
	}
	public static Expression wordCharacter() {
		return new Raw("\\w");
	}
	public static Expression nonWordCharacter() {
		return new Raw("\\W");
	}
	public static Expression wordBoundary() {
		return new Raw("\\b");
	}
	public static Expression nonWordBoundary() {
		return new Raw("\\B");
	}
	public static Expression digit() {
		return new Raw("\\d");
	}
	public static Expression nonDigit() {
		return new Raw("\\D");
	}
	public static Expression whitespaceCharacter() {
		return new Raw("\\s");
	}
	public static Expression nonWhitespaceCharacter() {
		return new Raw("\\S");
	}
	public static Expression many(Expression e) {
		return new Many(e);
	}
	public static Expression repeat(Expression e, int times) {
		return new Repeat(e, times);
	}
	public static Expression repeat(Expression e, int timesMin, int timesMax) {
		return new Repeat(e, timesMin, timesMax);
	}
	public static Expression many1(Expression e) {
		return new Many1(e);
	}
	public static Expression sequence(Expression... es) {
		return new Sequence(es);
	}
	public static Expression choice(Expression... es) {
		return new Choice(es);
	}
	public static Expression optional(Expression e) {
		return new Optional(e);
	}
	public static Expression separatedBy(Expression separator, Expression e) {
		return optional(separatedBy1(separator, e));
	}
	public static Expression separatedBy1(Expression separator, Expression e) {
		return sequence(e,many(sequence(separator, e)));
	}
	public static Expression word() {
		return many1(wordCharacter());
	}
	public static Expression number() {
		return many1(digit());
	}
	public static Expression whitespace() {
		return many(whitespaceCharacter());
	}
	public static Expression whitespace1() {
		return many1(whitespaceCharacter());
	}
	public static CaptureGroup capture(Expression expression) {
		return new CaptureGroup(expression);
	}
	public static Pattern compile(Expression expression) {
		return Compiler.compile(expression);
	}
}