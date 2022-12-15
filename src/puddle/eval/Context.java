package puddle.eval;

import java.util.*;

public class Context
{
	private final Deque<Scope> scopes = new ArrayDeque<>();

	public Context()
	{
		this.scopes.push(new Scope());
	}

	public Scope global()
	{
		return this.scopes.getLast();
	}

	public Scope current()
	{
		return this.scopes.peek();
	}

	public void push()
	{
		this.scopes.push(new Scope(current()));
	}

	public void pop()
	{
		this.scopes.pop();
	}
}
