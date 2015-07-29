package autosys.job;

import java.util.*;


/**
 * 
 * @author saar
 * Can access any job belonging to the current job graph.  A job is a hierarchical object which contains 
 * or references other jobs.  Both referring and referenced jobs in this hierarchy or set of 
 * relationships are considered to belong to the same graph.  Thus a parent, child, predecessor or 
 * successor all point to the same graph.  
 * 
 * A  set of scheduled jobs may be composed of several graphs, corresponding to different applications
 * or different environments. 
 *
 */

public class JobGraph extends HashMap<String, Job> {

	ArrayList<Job> _roots;
	
	public JobGraph() {
		//_jobGraph = new HashMap<String, Job>();
		_roots = null;
	}
	
	boolean contains(String key) {
		return false;
	}
	
	public Job getJob(String key) {
		return get(key);
	}
	
	public void addJob(Job job) {
		put(job.getName(), job);
	}
	
	public List<Job> getRoots()
	{
		if (_roots != null) return _roots;
		
		_roots = new ArrayList<Job>();
		
		Iterator jobIt = keySet().iterator();
		
		while (jobIt.hasNext()) {
			Job job = getJob((String)jobIt.next());
			Job root = findRoot(job);
			if (!_roots.contains(root)) _roots.add(root);
		}
		
		return _roots;
	}
	
	private Job findRoot(Job j) {
		if (j == null) return null;
		if (j.getParent() == null) return j;
		// otherwise, return parent (assume no cycles).
		return findRoot(j.getParent());
	}
	
}
