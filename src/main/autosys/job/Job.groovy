package main.autosys.job

import java.util.*;

// explore DSL support for defining jobs.

/**
 * 
 * @author saar
 * A Job can be executed.
 *
 */
class Job {
	public String _name
	List<Job> preds;
	Job _parent;
	public CmdLine _cmdLine;
	JobStatus _status;
	public JobGraph _graph;
	
	Job(String name) { 
		_name=name
		_preds = new ArrayList<Job>()
		_succs = new ArrayList<Job>()
		_parent = null
		_cmdLine = null
		_status = JobStatus.NotSet
		_graph = null;
		System.out.println("added job " + name)
	}
	
	/**
	 * Box containing this job
	 */
	void setParent(Job p) {
		_parent = p;
	}
	
	void setCmdLine(CmdLine cl) {
		_cmdLine = cl;
	}
	
	void setPredecessor(Job pr) {
		_preds.add(pr)
	}
	
	void setSuccessor(Job s) {
		_succs.add(s)
	}

	public String getName() {
		return _name;
	}

	public void setName(String _name) {
		this._name = _name;
	}

	public CmdLine getCmdLine() {
		return _cmdLine;
	}

	public void setCmdLine(CmdLine _cmdLine) {
		this._cmdLine = _cmdLine;
	}

	public JobGraph getGraph() {
		return _graph;
	}

	public void setGraph(JobGraph _graph) {
		this._graph = _graph;
	}
	
}
