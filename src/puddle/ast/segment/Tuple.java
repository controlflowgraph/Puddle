package puddle.ast.segment;

import java.util.List;

public record Tuple(List<Segment> parts) implements Segment { }
