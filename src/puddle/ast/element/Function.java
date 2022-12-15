package puddle.ast.element;

import java.util.*;
import puddle.eval.*;

public interface Function extends Element
{
	void eval(Context c);
	Object call(Context c, List<Object> arguments);
}
