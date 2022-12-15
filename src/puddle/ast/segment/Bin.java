package puddle.ast.segment;

public record Bin(String op, Segment left, Segment right) implements Segment { }
