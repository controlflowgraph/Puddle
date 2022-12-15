package puddle.ast.segment;

import java.util.*;
import puddle.eval.*;
import puddle.ast.element.*;

public record Call(Segment source, List<Segment> arguments) implements Segment
{
	@Override
	public Object eval(Context c)
	{
		Function f = (Function) this.source.eval(c);
		List<Object> args = this.arguments.stream().map(a -> a.eval(c)).toList();
		return f.call(c, args);
	}
}
