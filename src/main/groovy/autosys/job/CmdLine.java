package autosys.job;

import groovy.lang.*;
import java.io.*;

public class CmdLine {
	String _cmd;
	String _host; // IP or host
	String _profile;
	String _script;

	public CmdLine() {
		_cmd = null; // default
		_host = "localhost";
		_profile = null;
		_script = null;
	}

	public CmdLine(String command) {
		_cmd = command;
		_host = "localhost";
		_profile = null;
	}

	public String getProfile() {
		return _profile;
	}

	public void setProfile(String _profile) {
		this._profile = _profile;
	}

	public String getHost() {
		return _host;
	}

	public void setHost(String _host) {
		this._host = _host;
	}
	
	public void setScript(String script) {
		_script = script;
	}

	/**
	 * executes command on remote host, after sourcing profile
	 */
	public void execute() throws Exception {
		if (_cmd == null) {
			throw new Exception("Command is null.  Cannot execute.");
		}
		System.out.println("executing command: " + _cmd);
		
		if (_script != null) executeScript(_script);
	}

	public void executeScript(String scriptName) {
		Binding binding = new Binding();

		// add all job environment to bindings.

		try {
			BufferedReader br = new BufferedReader(new FileReader(scriptName));

			GroovyShell shell = new GroovyShell(binding);
			shell.evaluate(br);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
