package puddle.ast.segment;

import puddle.eval.*;

public record Str(String value) implements Segment
{
	@Override
	public Object eval(Context c)
	{
		return this.value;
	}
}
