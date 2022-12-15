package puddle;

import java.util.*;
import java.nio.file.*;

public class Puddle
{
	public static void main(String[] args)
	{
		var text = read("./data/test.pdl");
		var lines = toLines(text);
		print(lines);
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
