package main.autosys.job

class CmdLine {
	String _cmd;
	String _host;	// IP or host
	String _profile;
		
	CmdLine() {
		_cmd = null  // default 
		_host = "localhost"
		_profile = null
	}
	
	/**
	 * executes command on remote host, after sourcing profile
	 */
	void execute() throws Exception {
		if (_cmd == null) {
			throw Exception("Command is null.  Cannot execute.")
		}
	}
	
}
