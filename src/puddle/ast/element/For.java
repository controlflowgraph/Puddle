package puddle.ast.element;

import java.util.*;
import puddle.ast.segment.*;
import puddle.eval.*;

public record For(String variable, Segment source, Block body) implements Element
{
	@Override
        public void eval(Context c)
        {
		var iter = this.source.eval(c);
		c.push();
		if(iter instanceof List l)
		{
			for(Object o : l)
			{
				c.current().set(this.variable, o);
				this.body.eval(c);
			}
		}
		else
		{
			throw new RuntimeException("IDK how to iterate " + iter);
		}
		c.pop();
        }
}
