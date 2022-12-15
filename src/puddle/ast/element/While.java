package puddle.ast.element;

import puddle.ast.segment.*;
import puddle.eval.*;

public record While(Segment condition, Block body) implements Element
{
	@Override
        public void eval(Context c)
        {
        	while((Boolean) this.condition.eval(c))
        	{
			this.body.eval(c);
        	}
        }
}
