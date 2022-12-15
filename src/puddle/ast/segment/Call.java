package puddle.ast.segment;

import java.util.*;

public record Call(Segment source, List<Segment> arguments) implements Segment { }
