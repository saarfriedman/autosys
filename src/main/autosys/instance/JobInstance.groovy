package main.autosys.instance

import main.autosys.job.*;

/**
 * 
 * @author saar
 * This class is produced from the Job.  Basically it has exactly the same definition as a job, 
 * with the additional status information, and parameters.  
 * 
 * Parameters should include environment,
 *
 */

class JobInstance extends Job {
	
	/**
	 * Makes a copy of Job and all child jobs (basically job graph).
	 * @param j - job to be copied from (instance is created from a Job template.
	 * @param p - parameters used to create a customized instance
	 */
	
	JobInstance(Job j, JobParameters p) {
		
		_name = p.getPrefix() + j.getName();
		_cmdLine = j.getCommandLine()
		
		
	}
	
	void setStatus(JobStatus status) {
		_status = status;
	}
	
	JobStatus getStatus() {
		return _status;
	}
}
