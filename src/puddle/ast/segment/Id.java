package puddle.ast.segment;

import puddle.eval.*;

public record Id(String name) implements Segment
{
	@Override
	public Object eval(Context c)
	{
		return c.current().get(this.name);
	}

	@Override
	public void assign(Context c, Object value)
	{
		c.current().set(this.name, value);
	}
}
