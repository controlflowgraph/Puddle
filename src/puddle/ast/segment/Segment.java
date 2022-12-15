package puddle.ast.segment;

import puddle.eval.*;

public interface Segment
{
	default Object eval(Context c) { throw new RuntimeException("'eval' not implemented! " + getClass().getName()); }
	default void assign(Context c, Object o) { throw new RuntimeException("'assign' not implemented! " + getClass().getName()); }
}
