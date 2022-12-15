package puddle.ast.segment;

import java.util.*;
import puddle.eval.*;

public record Index(Segment source, Segment index) implements Segment
{
	@Override
	public Object eval(Context c)
	{
		Object s = this.source.eval(c);
		if(s instanceof Object[] a)
		{
			int value = (int)(double)(Double) this.index.eval(c);
			return a[value];
		}
		if(s instanceof List l)
		{
			int value = (int)(double)(Double) this.index.eval(c);
			System.out.println(l);
			return l.get(value);
		}
		if(s instanceof Map m)
		{
			Object k = this.index.eval(c);
			return m.get(k);
		}
		throw new RuntimeException("Unable to index " + s.getClass());
	}
}
