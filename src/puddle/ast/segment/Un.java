package puddle.ast.segment;

import puddle.eval.*;

public record Un(String op, Segment source) implements Segment
{
	@Override
	public Object eval(Context c)
	{
		Object val = this.source.eval(c);
		if(this.op.equals("-"))
		{
			return - (Double) val;
		}
		if(this.op.equals("!"))
		{
			return ! (Boolean) val;
		}
		throw new RuntimeException("Unknown unary operator " + this.op + "!");
	}
}
