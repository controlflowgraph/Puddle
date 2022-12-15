package puddle.eval;

import java.util.*;

public record Scope(Scope parent, Map<String, Object> variables)
{
	public Scope()
	{
		this(null);
	}

	public Scope(Scope parent)
	{
		this(parent, new HashMap<>());
	}

	public void set(String key, Object value)
	{
		if(!setInternal(key, value))
			this.variables.put(key, value);
	}

	private boolean setInternal(String key, Object value)
	{
		if(this.variables.containsKey(key))
		{
			this.variables.put(key, value);
			return true;
		}
		if(this.parent != null)
			return this.parent.setInternal(key, value);
		return false;
	}

	public Object get(String key)
	{
		if(this.variables.containsKey(key))
			return this.variables.get(key);
		if(this.parent != null)
			return this.parent.get(key);
		return null;
	}
}
