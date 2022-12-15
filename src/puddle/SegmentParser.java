package puddle;

import java.util.*;
import puddle.ast.segment.*;

public class SegmentParser
{
	public static Segment parse(List<String> tokens)
	{
		List<Segment> segments = process(tokens);
		List<Segment> unary = resolveUnary(segments);
		List<Segment> binary = resolveBinary(segments);
		return binary.get(0);
	}

	private static List<Segment> process(List<String> tokens)
	{
		List<Segment> segments = new ArrayList<>();
		TokenProvider p = new TokenProvider(tokens);
		while(p.has())
		{
			if(p.nextMatches("\\d+(\\.\\d+)?"))
			{
				segments.add(new Num(p.next()));
			}
			else if(p.nextMatches("[_a-zA-Z][_a-zA-Z0-9]*"))
			{
				String name = p.next();
				if(name.equals("true")) segments.add(new Bool("true"));
				else if (name.equals("false")) segments.add(new Bool("false"));
				else segments.add(new Id(name));
			}
			else if(p.nextMatches("[+\\-*/%&|<>=]+"))
			{
				segments.add(new Operator(p.next()));
			}
			else if(p.nextIs("["))
			{
				p.next();
				int indentation = 1;
				List<Segment> keys = new ArrayList<>();
				while(p.has() && indentation > 0 && !p.nextIs("]"))
				{
					List<String> keySub = new ArrayList<>();
					while(p.has() && indentation > 0 && (indentation > 1 || !p.nextIs("]")))
					{
						if(p.nextIs("[")) indentation++;
						if(p.nextIs("]")) indentation--;
						if(indentation > 0) keySub.add(p.next());
					}
					keys.add(parse(keySub));
				}
				p.next();
				if(segments.isEmpty() || segments.get(segments.size() - 1) instanceof Operator)
				{
					segments.add(new Lst(keys));
				}
				else
				{
					if(segments.size() != 1)
						throw new RuntimeException("Expected a single key");
					segments.set(segments.size() - 1, new Index(segments.get(segments.size() - 1), keys.get(0)));
				}
			}
			else if(p.nextIs("{"))
			{
				p.next();
				List<Segment> keys = new ArrayList<>();
				List<Segment> values = new ArrayList<>();
				int indentation = 1;
				while(p.has() && indentation > 0 && !p.nextIs("}"))
				{
					List<String> keySub = new ArrayList<>();
					while(p.has() && indentation > 0 && (indentation > 1 || !p.nextIs(":")))
					{
						keySub.add(p.next());
					}
					p.next();
					List<String> valueSub = new ArrayList<>();
					while(p.has() && indentation > 0 && (indentation > 1 || (!p.nextIs("}") && !p.nextIs(","))))
					{
						if(p.nextIs("{")) indentation++;
						if(p.nextIs("}")) indentation--;
						if(indentation > 0) valueSub.add(p.next());
					}
					keys.add(parse(keySub));
					values.add(parse(valueSub));
					p.next();
				}
				segments.add(new Mapping(keys, values));
			}
			else if(p.nextIs("("))
			{
				p.next();
				List<Segment> subs = new ArrayList<>();
				int indentation = 1;
				while(p.has() && indentation > 0)
				{
					List<String> sub = new ArrayList<>();
					while(p.has() && indentation > 0 && (indentation > 1 || !p.nextIs(",")))
					{
						if(p.nextIs(")")) indentation--;
						if(p.nextIs("(")) indentation++;
						if(indentation > 0) sub.add(p.next());
					}
					subs.add(parse(sub));
					p.next();
				}
				if(segments.isEmpty() || segments.get(segments.size() - 1) instanceof Operator)
				{
					if(subs.size() == 1) segments.add(subs.get(0));
					else segments.add(new Tuple(subs));
				}
				else
				{
					Segment source = segments.get(segments.size() - 1);
					segments.set(segments.size() - 1, new Call(source, subs));
				}
			}
			else
			{
				segments.add(new Token(p.next()));
			}
		}
		return segments;
	}

	private static List<Segment> resolveUnary(List<Segment> segments)
	{
		boolean change;
		do
		{
			Deque<Operator> operators = new ArrayDeque<>();
			boolean unary = true;
			List<Segment> changed = new ArrayList<>();
			for(Segment s : segments)
			{
				if(s instanceof Operator o)
				{
					if(unary) operators.add(o);
					else
					{
						unary = true;
						changed.add(s);
					}
				}
				else
				{
					unary = false;
					while(!operators.isEmpty())
					{
						s = new Un(operators.pop().op(), s);
					}
					changed.add(s);
				}
			}
			change = segments.size() != changed.size();
			segments = changed;
		}
		while(change);
		return segments;
	}

	private static final List<Set<String>> OPERATORS = List.of(
		Set.of("*", "/", "%"),
		Set.of("+", "-"),
		Set.of("<", ">", "<=", ">=", "==", "!="),
		Set.of("=")
	);

	private static List<Segment> resolveBinary(List<Segment> segments)
	{
		for(Set<String> level : OPERATORS)
		{
			List<Segment> changed = new ArrayList<>();
			for(int i = 0; i < segments.size(); i++)
			{
				if(segments.get(i) instanceof Operator o && level.contains(o.op()))
				{
					Segment left = changed.get(changed.size() - 1);
					Segment right = segments.get(i + 1);
					changed.set(changed.size() - 1, new Bin(o.op(), left, right));
					i++;
				}
				else
				{
					changed.add(segments.get(i));
				}
			}
			segments = changed;
		}
		return segments;
	}
}
