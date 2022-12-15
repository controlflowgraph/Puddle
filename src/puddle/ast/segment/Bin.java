package puddle.ast.segment;

import java.util.*;
import puddle.eval.*;

public record Bin(String op, Segment left, Segment right) implements Segment
{
	@Override
	public Object eval(Context c)
	{
		if(op.equals("="))
		{
			Object v = this.right.eval(c);
			this.left.assign(c, v);
			return v;
		}
		else if(op.equals("!="))
		{
			Object l = this.left.eval(c);
			Object r = this.right.eval(c);
			return !Objects.equals(l, r);
		}
		else if(op.equals("=="))
		{
			Object l = this.left.eval(c);
			Object r = this.right.eval(c);
			return Objects.equals(l, r);
		}
		else
		{
			double l = (Double) this.left.eval(c);
			double r = (Double) this.right.eval(c);
			return switch(op)
			{
				case "+" -> l + r;
				case "-" -> l - r;
				case "*" -> l * r;
				case "/" -> l / r;
				case "%" -> l % r;
				case "<=" -> l <= r;
				case ">=" -> l >= r;
				case "<" -> l < r;
				case ">" -> l > r;
				default -> throw new RuntimeException("Unknown operator " + op + "!");
			};
		}
	}
}
