package autosys.app;


import autosys.job.*;
import java.io.*;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JobDefinitionReader {

	public Job _currentJob;
	public JobGraph _graph = null;
	
	public JobDefinitionReader()
	{
		_currentJob = null;
		_graph = new JobGraph();
	}
	
	/**
	 * 
	 * @param filePath - points to a JSon file.
	 * @return a new Job
	 */
	public Job parseScript(String filePath) {
		// TODO Auto-generated method stub
		Job j = new Job("test");

		if (filePath == null) {
			filePath = "/home/saar/proj/workspace/eclipse/autosys/src/main/resources/sample1.json";
		}

		try {

			FileReader reader = new FileReader(filePath);
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
			// get an array from the JSON object
			JSONArray jobs= (JSONArray) jsonObject.get("jobs");
			Iterator<JSONObject> jobsIterator = jobs.iterator();

			while (jobsIterator.hasNext())
			{
				parseJob((JSONObject)jobsIterator.next());
			}
			
			// a second pass for adjusting references.
			
			Iterator<JSONObject> jobsIterator2 = jobs.iterator();
			
			while (jobsIterator2.hasNext())
			{
				parseJobRefs((JSONObject)jobsIterator2.next());
			}

		}  catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ParseException ex) {
			ex.printStackTrace();
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		

		return j;

	}

	public void parseJob(JSONObject jsonJob) throws Exception
	{
		String name = (String)jsonJob.get("name");
		
		Job job = new Job(name);
		
		// add all data that does not involve job references.
		
		String command = (String) jsonJob.get("command");
		if (command != null) _currentJob.setCmdLine(new CmdLine(command));
		
		String profile = (String) jsonJob.get("profile");
		if (profile != null) _currentJob.getCmdLine().setProfile(profile);
		
		String host = (String) jsonJob.get("host");
		if (host != null) _currentJob.getCmdLine().setHost(profile);
				
		_graph.addJob(job);
		job.setGraph(_graph);
				
	}

	
	public void parseJobRefs(JSONObject jsonJob) throws Exception
	{
		String name = (String)jsonJob.get("name");
		Job job = _graph.getJob(name);
		
		
		String box = (String)jsonJob.get("box");
		
		Job parent = _graph.getJob(box);
		if (parent == null) {
			throw new Exception("Illegal box: " + box + "for job: " + name);
		}
		job.setParent(parent);
		
		String pred = (String)jsonJob.get("pred");
		
		Job predecessor = _graph.getJob(pred);
		if (predecessor == null) {
			throw new Exception("Illegal pred: " + pred + "for job: " + name);
		}
		job.setPredecessor(predecessor);
		predecessor.setSuccessor(job);
				
		
	}
}