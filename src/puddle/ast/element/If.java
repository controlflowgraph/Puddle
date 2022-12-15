package puddle.ast.element;

import puddle.ast.segment.*;
import puddle.eval.*;

public record If(Segment condition, Block body, Block successor) implements Element
{
	@Override
        public void eval(Context c)
        {
        	boolean res = (Boolean) this.condition.eval(c);
        	if(res) this.body.eval(c);
        	else if(this.successor != null) this.successor.eval(c);
        }
}
