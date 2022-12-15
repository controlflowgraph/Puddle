package puddle.ast.element;

import puddle.ast.segment.*;

public record While(Segment condition, Block body) implements Element { }
