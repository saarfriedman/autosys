package autosys.job

class CmdLine {
	String _cmd;
	String _host;	// IP or host
	String _profile;
		
	CmdLine() {
		_cmd = null  // default 
		_host = "localhost"
		_profile = null
	}
	
	CmdLine(String command) 
	{
		_cmd = command;
		_host = "localhost"
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
	
	/**
	 * executes command on remote host, after sourcing profile
	 */
	void execute() throws Exception {
		if (_cmd == null) {
			throw Exception("Command is null.  Cannot execute.")
		}
	}


}
