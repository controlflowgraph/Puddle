package puddle.ast.element;

import puddle.ast.segment.Segment;
import puddle.eval.*;

public record Return(Segment segment) implements Element
{
	@Override
        public void eval(Context c)
        {
        	Object result = this.segment.eval(c);
        	throw new ReturnException(result);
        }
}
