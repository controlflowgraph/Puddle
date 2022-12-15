import java.util.*;

public class TokenProvider extends Provider<String>
{
	public TokenProvider(List<String> tokens)
	{
		super(tokens);
	}

	public boolean nextIs(String text)
	{
		return has() && peek().equals(text);
	}

	public boolean nextMatches(String pattern)
	{
		return has() && peek().matches(pattern);
	}
}
