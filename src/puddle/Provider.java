import java.util.*;

public class Provider<T>
{
	private final List<T> elements;
	private int index;

	public Provider(List<T> elements)
	{
		this.elements = elements;
	}

	public boolean has()
	{
		return this.index < this.elements.size();
	}

	public T next()
	{
		return this.elements.get(this.index++);
	}

	public T peek()
	{
		return this.elements.get(this.index);
	}
}
