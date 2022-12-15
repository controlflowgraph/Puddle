package puddle.ast.segment;

import java.util.List;
import puddle.eval.*;

public record Tuple(List<Segment> parts) implements Segment
{
	@Override
	public Object eval(Context c)
	{
		Object[] values = new Object[this.parts.size()];
		for(int i = 0; i < this.parts.size(); i++)
		{
			values[i] = this.parts.get(i).eval(c);
		}
		return values;
	}
}
