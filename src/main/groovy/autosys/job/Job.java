package autosys.job;

import java.util.*;

// explore DSL support for defining jobs.

/**
 * 
 * @author saar
 * A Job can be executed.
 *
 */
public class Job {
	public String _name;
	public List<Job> _preds;
	public List<Job> _succs;
	public Job _parent;
	public CmdLine _cmdLine;
	public JobGraph _graph;
	public List<Job> _children;
	
	public Job(String name) { 
		_name=name;
		_preds = new ArrayList<Job>();
		_succs = new ArrayList<Job>();
		_parent = null;
		_cmdLine = null;
		_graph = null;
		_children = new ArrayList<Job>();
		List<Job> _children;
		System.out.println("added job " + name);
	}
	
	/**
	 * Box containing this job
	 */
	void setParent(Job p) {
		_parent = p;
	}
	
	Job getParent() {
		return _parent;
	}
	
	void addChild(Job c) {
		_children.add(c);
		c.setParent(this);
	}
	
	List<Job> getChildren() {
		return _children;
	}
	
	
	void setPredecessor(Job pr) {
		_preds.add(pr);
	}
	
	void setSuccessor(Job s) {
		_succs.add(s);
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
