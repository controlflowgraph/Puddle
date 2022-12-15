package puddle.ast.segment;

import java.util.List;

public record Mapping(List<Segment> keys, List<Segment> values) implements Segment { }
