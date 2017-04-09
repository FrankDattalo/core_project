package core.parser.interfaces;

import core.interpreter.interfaces.Assign;
import core.interpreter.interfaces.Composite;
import core.interpreter.interfaces.Condition;
import core.interpreter.interfaces.Declaration;
import core.interpreter.interfaces.DeclarationSequence;
import core.interpreter.interfaces.Expression;
import core.interpreter.interfaces.Factor;
import core.interpreter.interfaces.Id;
import core.interpreter.interfaces.IdList;
import core.interpreter.interfaces.If;
import core.interpreter.interfaces.In;
import core.interpreter.interfaces.Loop;
import core.interpreter.interfaces.Out;
import core.interpreter.interfaces.Program;
import core.interpreter.interfaces.Statement;
import core.interpreter.interfaces.StatementSequence;
import core.interpreter.interfaces.Term;
import core.parser.implementations.ParserContainerImpl;

/**
 * ParserContainer interface defines methods to retrieve parsers for AST nodes.
 */
public interface ParserContainer {

	/**
	 * @return A program node parser.
	 */
	Parser<Program> getProgramParser();

	/**
	 * @return An assign node parser.
	 */
	Parser<Assign> getAssignParser();

	/**
	 * @return A composite node parser.
	 */
	Parser<Composite> getCompositeParser();

	/**
	 * @return A condition node parser.
	 */
	Parser<Condition> getConditonParser();

	/**
	 * @return A declaration node parser.
	 */
	Parser<Declaration> getDeclarationParser();

	/**
	 * @return A declaration sequence node parser.
	 */
	Parser<DeclarationSequence> getDeclarationSequenceParser();

	/**
	 * @return An expression node parser.
	 */
	Parser<Expression> getExpressionParser();

	/**
	 * @return A factor node parser.
	 */
	Parser<Factor> getFactorParser();

	/**
	 * @return An id list node parser.
	 */
	Parser<IdList> getIdListParser();

	/**
	 * @return An if node parser.
	 */
	Parser<If> getIfParser();

	/**
	 * @return An in node parser.
	 */
	Parser<In> getInParser();

	/**
	 * @return A loop parser.
	 */
	Parser<Loop> getLoopParser();

	/**
	 * @return An out parser.
	 */
	Parser<Out> getOutParser();

	/**
	 * @return A statement parser.
	 */
	Parser<Statement> getStatementParser();

	/**
	 * @return A statement sequence parser.
	 */
	Parser<StatementSequence> getStatementSequenceParser();

	/**
	 * @return A term parser.
	 */
	Parser<Term> getTermParser();

	/**
	 * @return An id parser.
	 */
	Parser<Id> getIdParser();

	/**
	 * @return A new ParserContainer instance.
	 */
	static ParserContainer newInstance() {
		return new ParserContainerImpl();
	}
}
