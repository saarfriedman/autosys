package main.autosys.job;

import java.util.*;

public class JobBox extends Job {
	List<Job> _children;
	
	JobBox(String name) {
		super(name);
		_children = new ArrayList<Job>();
	}
	void add(Job c) {
		_children.add(c);
		c.setParent(this);
	}
	
}
