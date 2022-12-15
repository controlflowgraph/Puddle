package puddle.ast.segment;

import puddle.eval.*;

public record Bool(String value) implements Segment
{
	@Override
	public Object eval(Context c)
	{
		return value.equals("true");
	}
}
