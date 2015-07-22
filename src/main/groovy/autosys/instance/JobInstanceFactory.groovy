package autosys.instance;

import autosys.job.Job;
import autosys.job.JobGraph;


public class JobInstanceFactory {

	JobGraph _graph;
	
	/**
	 * A factory should be used for one template.  The same factory 
	 */
	public JobInstanceFactory() {
					
	}
	
	public JobInstance createJobGraph(Job j, JobParameters p) {		
		_graph = new JobGraph();
		return createJobSubGraph(j,p)
	}
	
	/**
	 * This method creates a basic job, without setting children, pred, or success
	 * 
	 * @param j - job to be used as template
	 * @param p - customization parameters
	 * @return
	 */
	private JobInstance createJobInstance(Job j, JobParameters p) {
		// if job already exists, return existing job
		if (_graph.getJob(instanceName(j,p)) != null) return _graph.getJob(instanceName(j,p));
		
		JobInstance i = new JobInstance()
		i.setName(instanceName(j,p))
		i.setCmdLine(j.getCmdLine())  // may need to apply params here too.
		_graph.addJob(i)
		
	} 
	
	
	public String instanceName(Job j, JobParameters p) {
		return p.getPrefix() + j.getName();
	}
	
	/**
	 * This method performs a deep copy on job j, with parameter substitution.  It creates an
	 * instance of the workflow.
	 * 
	 * @param j - Job to be copied
	 * @param p - parameters for substitution.
	 * @return an instance of the workflow associated with the job.  
	 * 
	 * Note: invoking this method on a job, or its parent/child will yield the same result as it
	 * recurses through all relationships to build a copy of the graph.
	 */
	private JobInstance createJobSubGraph(Job j, JobParameters p) {
		
		// Since this is a recursive method, it will not copy jobs that are already in the graph
		
		JobInstance i = createJobInstance(j, p)
		
	
		j.getChildren().each {
			i.addChild(createJobSubGraph(it, p))			
		}
		
		j._preds.each {
			i._preds.add(createJobSubGraph(it, p))
		}

		j._succs.each {
			i._succs.add(createJobSubGraph(it, p))
		}
		
		i.setParent(createJobSubGraph(j.getParent(), p))

		return i;
		
	}
	
	
}
