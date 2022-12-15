package puddle.ast.element;

import puddle.ast.segment.*;
import puddle.eval.*;

public record Expression(Segment segment) implements Element
{
	@Override
        public void eval(Context c)
        {
        	this.segment.eval(c);
        }
}
