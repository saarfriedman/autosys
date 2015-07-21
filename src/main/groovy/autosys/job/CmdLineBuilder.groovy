
package autosys.job


class CmdLineBuilder {

	CmdLine _cmdLine;
	
	CmdLineBuilder() {
		_cmdLine = new CmdLine()
	}
	
	CmdLine build() {
		return _cmdLine
	}
	
	CmdLine setCommand(String cmd) {
		_cmdLine._cmd = cmd;
	}
	
	CmdLine setHost(String host) {
		_cmdLine._host = host;
	}
	
	CmdLine setProfile(String profile) {
		_cmdLine._profile = profile;
	}
	

}
