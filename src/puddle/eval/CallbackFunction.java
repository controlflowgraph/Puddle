package puddle.eval;

import puddle.ast.element.*;
import java.util.*;
import java.util.function.BiFunction;

public class CallbackFunction implements Function
{
	private final String name;
	private final BiFunction<Context, List<Object>, Object> callback;

	public CallbackFunction(String name, BiFunction<Context, List<Object>, Object> callback)
	{
		this.name = name;
		this.callback = callback;
	}

	@Override
	public void eval(Context c)
	{
		c.current().set(this.name, this);
	}

	@Override
	public Object call(Context c, List<Object> args)
	{
		return this.callback.apply(c, args);
	}
}
