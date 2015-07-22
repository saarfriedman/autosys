package autosys.job;

import java.util.HashMap;


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

public class JobGraph {

	HashMap<String, Job> _jobGraph = null;
	
	JobGraph() {
		_jobGraph = new HashMap<String, Job>();
	}
	
	boolean contains(String key) {
		return false;
	}
	
	Job getJob(String key) {
		return _jobGraph.get(key);
	}
	
	void addJob(Job job) {
		_jobGraph.put(job.getName(), job);
	}
	
}
