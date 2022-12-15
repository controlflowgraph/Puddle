package puddle;

import java.util.*;

public class LineProvider extends Provider<Line>
{
	public LineProvider(List<Line> lines)
	{
		super(lines);
	}

	public boolean nextStartsWith(String word)
	{
		return has() && !peek().tokens().isEmpty() && peek().tokens().get(0).equals(word);
	}

	public boolean nextOffsetIs(int n)
	{
		return has() && peek().offset() == n;
	}
}
