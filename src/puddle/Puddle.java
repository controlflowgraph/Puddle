package puddle;

import java.util.*;
import java.nio.file.*;

import puddle.eval.*;
import puddle.ast.element.*;

public class Puddle
{
	public static void main(String[] args)
	{
		var text = read("./data/test.pdl");
		var lines = toLines(text);
		var elements = ElementParser.parse(lines);
		var all = new ArrayList<Element>();
		all.add(new CallbackFunction("print", (c, a) -> {
			System.out.println(String.join("    ", a.stream().map(Objects::toString).toList()));
			return null;
		}));
		all.addAll(elements);
		var context = eval(all);
	}

	private static Context eval(List<Element> elements)
	{
		var c = new Context();
		elements.forEach(e -> e.eval(c));
		return c;
	}

	private static List<Line> toLines(String text)
	{
		return Lexer.tokenize(text)
			.stream()
			.filter(l -> !l.tokens().isEmpty())
			.toList();
	}

	private static String read(String path)
	{
		try
		{
			return Files.readString(Path.of(path));
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	private static void print(List<?> lst)
	{
		lst.forEach(Puddle::print);
	}

	private static void print(Object o)
	{
		System.out.println(o);
	}
}
