package core.parser.implementations;

import core.interpreter.interfaces.Expression;
import core.interpreter.interfaces.Term;
import core.parser.interfaces.Parser;
import core.parser.interfaces.ParserContainer;
import core.tokenizer.interfaces.Tokenizer;

public class ExpressionParserImpl implements Parser<Expression> {

	@Override
	public Expression parse(ParserContainer parserContainer, Tokenizer tokenizer) {
		Parser<Term> termp = parserContainer.getTermParser();

		Term term = termp.parse(parserContainer, tokenizer);

		String currentToken = tokenizer.getCurrentToken();
		Tokenizer.Type currentTokenType = tokenizer.toType(currentToken);

		Expression ret = null;

		if (currentTokenType == Tokenizer.Type.PLUS) {
			tokenizer.nextToken();
			Expression expr = this.parse(parserContainer, tokenizer);
			ret = Expression.createInstance(term, Expression.Type.ADD, expr);

		} else if (currentTokenType == Tokenizer.Type.MINUS) {
			tokenizer.nextToken();
			Expression expr = this.parse(parserContainer, tokenizer);
			ret = Expression.createInstance(term, Expression.Type.SUB, expr);

		} else {
			ret = Expression.createInstance(term);
		}

		return ret;
	}
}
