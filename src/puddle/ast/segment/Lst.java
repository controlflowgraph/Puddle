package puddle.ast.segment;

import java.util.*;
import puddle.eval.*;

public record Lst(List<Segment> elements) implements Segment
{
	@Override
	public Object eval(Context c)
	{
		var l = new ArrayList<>(elements.stream().map(e -> e.eval(c)).toList());
		System.out.println(l);
		return l;
	}
}
