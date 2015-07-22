package autosys.instance

import autosys.job.*;

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
	
	public JobStatus _status;
	
	JobInstance() {
		_status = JobStatus.NotSet
	}
	
	void setStatus(JobStatus status) {
		_status = status;
	}
	
	JobStatus getStatus() {
		return _status;
	}
}
