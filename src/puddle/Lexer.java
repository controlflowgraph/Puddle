import java.util.*;

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
		return Arrays.asList(
			text
				.trim()
				.replace("(", " ( ")
				.replace(")", " ) ")
				.replace("[", " [ ")
				.replace("]", " ] ")
				.replace("([+\\-*/%&|<>=])", " $1 ")
				.split(" +")
		);
	}
}
