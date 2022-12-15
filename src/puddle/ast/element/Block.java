package puddle.ast.element;

import java.util.*;
import puddle.eval.*;

public record Block(List<Element> elements) implements Element
{
	@Override
	public void eval(Context c)
	{
		c.push();
		elements.forEach(e -> e.eval(c));
		c.pop();
	}
}
