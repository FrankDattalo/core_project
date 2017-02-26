package core.parser.implementations;

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
import core.parser.interfaces.Parser;
import core.parser.interfaces.ParserContainer;

public class ParserContainerImpl implements ParserContainer {

	private final Parser<Program> progp = new ProgramParserImpl();
	private final Parser<DeclarationSequence> decsp = new DeclarationSequenceParserImpl();
	private final Parser<Declaration> decp = new DeclarationParserImpl();
	private final Parser<StatementSequence> stmtsp = new StatementSequenceParserImpl();
	private final Parser<Statement> stmtp = new StatementParserImpl();
	private final Parser<IdList> idlp = new IdListParserImpl();
	private final Parser<Assign> assngp = new AssignParserImpl();
	private final Parser<If> ifp = new IfParserImpl();
	private final Parser<Loop> loopp = new LoopParserImpl();
	private final Parser<In> inp = new InParserImpl();
	private final Parser<Out> outp = new OutParserImpl();
	private final Parser<Condition> condp = new ConditionParserImpl();
	private final Parser<Composite> compp = new CompositeParserImpl();
	private final Parser<Expression> exprp = new ExpressionParserImpl();
	private final Parser<Term> termp = new TermParserImpl();
	private final Parser<Factor> factp = new FactorParserImpl();
	private final Parser<Id> idp = new IdParserImpl();

	public ParserContainerImpl() {
	}

	@Override
	public Parser<Program> getProgramParser() {
		return this.progp;
	}

	@Override
	public Parser<Assign> getAssignParser() {
		return this.assngp;
	}

	@Override
	public Parser<Composite> getCompositeParser() {
		return this.compp;
	}

	@Override
	public Parser<Condition> getConditonParser() {
		return this.condp;
	}

	@Override
	public Parser<Declaration> getDeclarationParser() {
		return this.decp;
	}

	@Override
	public Parser<Expression> getExpressionParser() {
		return this.exprp;
	}

	@Override
	public Parser<Factor> getFactorParser() {
		return this.factp;
	}

	@Override
	public Parser<IdList> getIdListParser() {
		return this.idlp;
	}

	@Override
	public Parser<If> getIfParser() {
		return this.ifp;
	}

	@Override
	public Parser<In> getInParser() {
		return this.inp;
	}

	@Override
	public Parser<Loop> getLoopParser() {
		return this.loopp;
	}

	@Override
	public Parser<Out> getOutParser() {
		return this.outp;
	}

	@Override
	public Parser<Statement> getStatementParser() {
		return this.stmtp;
	}

	@Override
	public Parser<StatementSequence> getStatementSequenceParser() {
		return this.stmtsp;
	}

	@Override
	public Parser<Term> getTermParser() {
		return this.termp;
	}

	@Override
	public Parser<Id> getIdParser() {
		return this.idp;
	}

	@Override
	public Parser<DeclarationSequence> getDeclarationSequenceParser() {
		return this.decsp;
	}
}
