package autosys.job;

import java.util.*;

public class JobEnvironment extends HashMap<String, Object> {

	public void addParentEnv(JobEnvironment env)
	{
		// Iterator<String> keys = env.keySet().iterator();
		
		for(Map.Entry<String, Object> entry : env.entrySet())
		{
			if (!containsKey(entry.getKey())) {
				put (entry.getKey(), entry.getValue());		
			}
		}
		
	}
}
