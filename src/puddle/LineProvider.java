package puddle;

import java.util.*;

public class LineProvider extends Provider<Line>
{
	public LineProvider(List<Line> lines)
	{
		super(lines);
	}

	public boolean nextOffsetIs(int n)
	{
		return has() && peek().offset() == n;
	}
}
