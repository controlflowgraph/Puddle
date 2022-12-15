package puddle;

import java.util.*;
import static java.util.function.Predicate.not;

public class Lexer
{
	public static List<Line> tokenize(String text)
	{
		return Arrays.stream(text.replace("\t", "    ").split("\n"))
			.map(s -> {
				int offset = s.replaceFirst("[^ ].+", "").length();
				List<String> tokens = split(s);
				return new Line(offset, tokens);
			})
			.toList();
	}

	private static List<String> split(String text)
	{
		return Arrays.stream(
			text
				.trim()
				.replace("(", " ( ")
				.replace(")", " ) ")
				.replace("[", " [ ")
				.replace("]", " ] ")
				.replace("{", " { ")
				.replace("}", " } ")
				.replace(",", " , ")
				.replace(":", " : ")
				.replaceAll("([+\\-*/%&|<>=]+)", " $1 ")
				.split(" +")
		)
		.filter(not(String::isEmpty))
		.toList();
	}
}
