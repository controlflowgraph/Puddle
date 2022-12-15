package puddle.ast.segment;

import java.util.*;
import puddle.eval.*;

public record Mapping(List<Segment> keys, List<Segment> values) implements Segment
{
	@Override
	public Object eval(Context c)
	{
		Map<Object, Object> map = new HashMap<>();
		for(int i = 0; i < this.keys.size(); i++)
		{
			Object k = this.keys.get(i).eval(c);
			Object v = this.values.get(i).eval(c);
			map.put(k, v);
		}
		return map;
	}
}
