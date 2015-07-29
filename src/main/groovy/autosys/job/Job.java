package autosys.job;

import java.util.*;

// explore DSL support for defining jobs.

/**
 * 
 * @author saar
 * A Job can be executed.
 * 
 * A job has an environment (bindings) which impact itself and child jobs.
 * This make sense if the execution of the job (through command line) actually
 * takes code.  Code can come from Json, but more appropriately should come from extending the framework.
 * 
 * CmdLine is suitable for simple job only.  A more comprehensive solution should come from a 
 * groovy source code file.  One way is to create a GroovyScript object which can run instead of simple
 * command line.
 * 
 * command line script - runs a local script (on the remote machine).
 * script object - loads the script when reading the definition so script object is kept on server,
 *   and is serialized to run on remote machine.  It may, in turn, call a local script on the remote
 *   machine.
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
	private JobEnvironment _env;
	private boolean _envReady;

	
	public Job(String name) { 
		_name=name;
		_preds = new ArrayList<Job>();
		_succs = new ArrayList<Job>();
		_parent = null;
		_cmdLine = null;
		_graph = null;
		_children = new ArrayList<Job>();
		_env = null;
		_envReady = false;
				
		List<Job> _children;
		System.out.println("added job " + name);
	}
	
	/**
	 * Box containing this job
	 */
	public void setParent(Job p) {
		_parent = p;
	}
	
	public Job getParent() {
		return _parent;
	}
	
	public void addChild(Job c) {
		_children.add(c);
		c.setParent(this);
	}
	
	public List<Job> getChildren() {
		return _children;
	}
	
	
	public void setPredecessor(Job pr) {
		_preds.add(pr);
	}
	
	public void setSuccessor(Job s) {
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

	/**
	 * Method should be called only after all relationships are set.  The reason is that the environment 
	 * is derived from parent.
	 * 
	 * @return environment which includes ancestors' definitions.
	 */
	public JobEnvironment getEnv() {
		
		if (!_envReady) {
			JobEnvironment pEnv = getParent().getEnv();
			
			// merge environment with the current _env variable
			_env.addParentEnv(pEnv);
		}
		
		return _env;
	}

	public void setEnv(JobEnvironment _env) {
		this._env = _env;
	}


	public String toString()
	{
		String str =  "{ name : " + _name + "} "; 
		return str;
	}
}
