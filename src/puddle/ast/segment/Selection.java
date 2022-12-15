package puddle.ast.segment;

import puddle.eval.*;

public record Selection(Segment source, String attribute) implements Segment { }
