package puddle.ast.segment;

public record Selection(Segment source, String attribute) implements Segment { }
