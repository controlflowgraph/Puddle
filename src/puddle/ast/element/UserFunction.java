package puddle.ast.element;

import java.util.List;
import puddle.eval.*;

public record UserFunction(String name, List<String> parameter, Block block) implements Function
{
	@Override
        public void eval(Context c)
        {
        	c.current().set(this.name, this);
	}

	public Object call(Context c, List<Object> arguments)
	{
		c.push();
		for(int i = 0; i < arguments.size(); i++)
			c.current().set(this.parameter.get(i), arguments.get(i));
		try
		{
			this.block.eval(c);
		}
		catch(ReturnException e)
		{
			c.pop();
			return e.getResult();
		}
		c.pop();
		return null;
	}
}
