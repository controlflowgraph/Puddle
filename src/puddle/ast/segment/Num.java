package puddle.ast.segment;

import puddle.eval.*;

public record Num(String value) implements Segment
{
	@Override
	public Object eval(Context c)
	{
		return Double.parseDouble(this.value);
	}
}
