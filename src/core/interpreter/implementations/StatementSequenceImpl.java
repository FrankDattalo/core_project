package core.interpreter.implementations;

import java.io.PrintStream;

import core.interpreter.interfaces.Statement;
import core.interpreter.interfaces.StatementSequence;

public class StatementSequenceImpl implements StatementSequence {

	private Statement stmt;
	private StatementSequence stmtSeq;

	public StatementSequenceImpl(Statement stmt) {
		this.stmt = stmt;
		this.stmtSeq = null;
	}

	public StatementSequenceImpl(Statement stmt, StatementSequence stmtSeq) {
		this.stmt = stmt;
		this.stmtSeq = stmtSeq;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
	}

	@Override
	public void print(int indent, PrintStream printer) {
		this.stmt.print(indent, printer);

		if (this.stmtSeq != null) {
			this.stmtSeq.print(indent, printer);
		}
	}

}
