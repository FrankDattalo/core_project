package core.interpreter.implementations;

import java.io.PrintStream;

import core.interpreter.interfaces.Condition;
import core.interpreter.interfaces.If;
import core.interpreter.interfaces.StatementSequence;
import core.parser.interfaces.Printable;

public class IfImpl implements If {

	private Condition cond;
	private StatementSequence trueSeq;
	private StatementSequence falseSeq;

	public IfImpl(Condition cond, StatementSequence stmsSeq) {
		this.cond = cond;
		this.trueSeq = stmsSeq;
	}

	public IfImpl(Condition cond, StatementSequence stmsSeqTrue, StatementSequence stmsSeqFalse) {
		this.cond = cond;
		this.trueSeq = stmsSeqTrue;
		this.falseSeq = stmsSeqFalse;
	}

	@Override
	public void execute() {
		boolean val = this.cond.evaluate();

		if (val) {
			this.trueSeq.execute();
		} else if (this.falseSeq != null) {
			this.falseSeq.execute();
		}

	}

	@Override
	public void print(int indent, PrintStream printer) {

		printer.print("if ");
		this.cond.print(indent, printer);
		printer.println(" then");

		int next = Printable.nextIndent(indent);

		this.trueSeq.print(next, printer);

		if (this.falseSeq != null) {
			Printable.printNSpaces(indent, printer);
			printer.println("else");
			this.falseSeq.print(next, printer);
		}

		Printable.printNSpaces(indent, printer);
		printer.println("end;");
	}

}
