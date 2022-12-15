package puddle.ast.element;

import puddle.ast.segment.*;

public record If(Segment condition, Block body, Block successor) implements Element { }
