package sgreben.regex_builder;

import java.util.List;
import sgreben.regex_builder.expression.*;
import sgreben.regex_builder.tokens.*;

public class CaptureGroup extends Unary implements Expression {
	private java.util.regex.Matcher rawMatcher;
	
	public CaptureGroup(Expression expression) { super(expression); }
	
	public void compile(java.util.List<TOKEN> output) {
		output.add(new START_GROUP());
		for(Expression child : children()) {
			child.compile(output);
		}
		output.add(new END_GROUP());
	}
}