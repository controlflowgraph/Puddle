package puddle.ast.element;

import puddle.ast.segment.*;

public record For(String variable, Segment source, Block body) implements Element { }
