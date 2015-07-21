package main.autosys.job

/**
 * 
 * @author saar
 * Builds jobs based on JSON string input
 */
class JobBuilder {

	Job _currentJob;
	
	/**
	 * 
	 * @param JSON
	 * @return top level job.  Build is used recursively to build all sub-components 
	 */
	Job build(String JSON) {
		_currentJob = new Job()
		slurp(JSON)
		return _currentJob
	}
	

	
	/**
	 * 
	 * @param JSON
	 * @return
	 */
	JobBuilder slurp(String JSON) {
		// get the next token and set corresponding property in current job.
		// can get all the text until the next job key-word as a JSON string and 
		// then slurp.
	}
	
	JobBuilder addPred(Job a, Job b) { 
		a.setPredecessor(b)
		b.setSuccessor(a)
	}
	
	JobBuilder setBox(Job a) {
		// sets currentJob to be in box a.  
		// Note: a may have not been a box before.  It needs to potentially 
		// turn into a box
	}
}
