package core.interpreter.implementations;

import java.io.PrintStream;

import core.interpreter.interfaces.Condition;
import core.interpreter.interfaces.Loop;
import core.interpreter.interfaces.StatementSequence;
import core.parser.interfaces.Printable;

public class LoopImpl implements Loop {

	private Condition cond;
	private StatementSequence stmtSeq;

	public LoopImpl(Condition cond, StatementSequence stmtSeq) {
		this.cond = cond;
		this.stmtSeq = stmtSeq;
	}

	@Override
	public void execute() {
		while (this.cond.evaluate()) {
			this.stmtSeq.execute();
		}

	}

	@Override
	public void print(int indent, PrintStream printer) {
		printer.print("while ");
		this.cond.print(indent, printer);
		printer.println(" loop");

		int next = Printable.nextIndent(indent);

		this.stmtSeq.print(next, printer);

		Printable.printNSpaces(indent, printer);
		printer.println("end;");

	}

}
