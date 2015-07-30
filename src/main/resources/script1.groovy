
import groovy.time.TimeCategory;
import autosys.app.JobDefinitionReader;
import autosys.job.*;

boolValue = true
notEmptyStr = new String("Hello");
numberList = ["1", "2", "3"]

if (boolValue && notEmptyStr && numberList) {
	println "Condition is true"
}else{
	println "Condition is false"
}

use(TimeCategory) {
	println 1.minute.from.now
}

def addJob(name) { 
	j = new Job(name)
	[box: { boxName -> 
		[command: { cmd -> 
			System.out.println(boxName)
			j.setCmdLine(new CmdLine(cmd))
			}]
	}]
}

def addJob2(String[] args) {
	System.out.println(args[0])
	System.out.println(args[1])
	
}

def addJob3(String json) { 
	StringReader srd = new StringReader(json)
	JobDefinitionReader reader = new JobDefinitionReader();
	reader.parseScript(srd);
}

// define job name: "test"
addJob "test1" box "root" command "ls"

addJob2 "name=test2", "box=root", "command=ls"

addJob3 """
{	
"jobs" : 
[
	
	{
		"name" : "root",
		"env" : { "x" : "4", "y" : "hi", "comment" : "env variables accessible to all children" }
	},
	{
		"name" : "box2",
		"box" : "root"
	},
	{
		"name" : "jobX",
		"box" : "box2",
		"command" : "cd \$cwd"
	},
	{
		"name" : "run_test1",
		"box" : "root",
		"command" : "ls \$x",
		"host" : "localhost",
		"profile" : "~/scheduler/profile1.txt"				
	},
	{
		"name" : "run_test2",
		"box" : "root",
		"command" : "ls",
		"host" : "localhost",
		"profile" : "~/scheduler/profile1.txt",		
		"pred" : "run_test1"		
	},
	{
		"name" : "script_test",
		"box" : "root",
		"command" : "ls",
		"host" : "localhost",
		"script" : "src/main/resources/script1.groovy",
		"pred" : "run_test2"		
	}	
]


"comment" : "source profile, define env variables, execute command" 
	
}
"""


