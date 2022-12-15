package puddle.ast.element;

import java.util.List;

public record Function(String name, List<String> parameter, Block block) implements Element { }
