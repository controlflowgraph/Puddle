package puddle;

import java.util.*;
import puddle.ast.element.Element;
import puddle.ast.element.*;
import puddle.ast.segment.*;

public class ElementParser
{
	public static List<Element> parse(List<Line> lines)
	{
		LineProvider p = new LineProvider(lines);
		List<Element> elements = parseBlock(p, 0).elements();
		if(p.has())
			throw new RuntimeException("Unexpected offset '" + p.peek().offset() + "'!");
		return elements;
	}

	private static Block parseBlock(LineProvider p, int offset)
	{
		List<Element> lines = new ArrayList<>();
		while(p.nextOffsetIs(offset))
		{
			lines.add(parseNext(p));
		}
		return new Block(lines);
	}

	private static Element parseNext(LineProvider p)
	{
		if(p.nextStartsWith("fn")) return parseFunction(p);
		if(p.nextStartsWith("ret")) return parseReturn(p);
		if(p.nextStartsWith("if")) return parseIf(p);
		if(p.nextStartsWith("while")) return parseWhile(p);
		if(p.nextStartsWith("for")) return parseFor(p);
		return parseExpression(p);
	}

	private static Element parseFor(LineProvider p)
	{
		Line l = p.next();
		TokenProvider t = nextProvider(l);
		t.next();
		String variable = t.next();
		t.next();
		Segment source = parseSegment(t);
		Block body = parseBlock(p, l.offset() + 4);
		return new For(variable, source, body);
	}

	private static Element parseWhile(LineProvider p)
	{
		Line l = p.next();
		TokenProvider t = nextProvider(l);
		t.next();
		Segment condition = parseSegment(t);
		Block body = parseBlock(p, l.offset() + 4);
		return new While(condition, body);
	}

	private static Element parseIf(LineProvider p)
	{
		Line l = p.next();
		TokenProvider t = nextProvider(l);
		t.next();
		Segment condition = parseSegment(t);
		Block body = parseBlock(p, l.offset() + 4);
		Block successor = null;
		if(p.nextStartsWith("else"))
		{
			p.next();
			successor = parseBlock(p, l.offset() + 4);
		}
		return new If(condition, body, successor);
	}

	private static Element parseReturn(LineProvider p)
	{
		Line l = p.next();
		TokenProvider t = nextProvider(l);
		t.next();
		Segment segment = parseSegment(t);
		return new Return(segment);
	}

	private static Element parseFunction(LineProvider p)
	{
		Line l = p.next();
		TokenProvider t = nextProvider(l);
		t.next();
		String name = t.next();
		t.next();
		List<String> arguments = new ArrayList<>();
		while(t.has() && !t.nextIs(")"))
		{
			arguments.add(t.next());
		}
		Block body = parseBlock(p, l.offset() + 4);
		return new UserFunction(name, arguments, body);
	}

	private static TokenProvider nextProvider(Line l)
	{
		return new TokenProvider(l.tokens());
	}

	private static Expression parseExpression(LineProvider p)
	{
		return new Expression(parseSegment(p.next().tokens()));
	}

	private static Segment parseSegment(TokenProvider t)
	{
		List<String> str = new ArrayList<>();
		while(t.has())
		{
			str.add(t.next());
		}
		return parseSegment(str);
	}

	private static Segment parseSegment(List<String> tokens)
	{
		return SegmentParser.parse(tokens);
	}
}
