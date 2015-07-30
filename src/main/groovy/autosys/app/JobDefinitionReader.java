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

	public JobDefinitionReader() {
		_currentJob = null;
		_graph = new JobGraph();
	}

	/**
	 * 
	 * @param filePath
	 *            - points to a JSon file.
	 * @return a new Job
	 */
	public Job parseScript(Reader br) {
		// TODO Auto-generated method stub

		/*
		if (filePath == null) {
			filePath = "/home/saar/proj/workspace/eclipse/autosys/src/main/resources/sample1.json";
		}
*/
		try {
			
			//FileReader reader = new FileReader(filePath);
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(br);
			// get an array from the JSON object
			JSONArray jobs = (JSONArray) jsonObject.get("jobs");
			Iterator<JSONObject> jobsIterator = jobs.iterator();

			while (jobsIterator.hasNext()) {
				parseJob((JSONObject) jobsIterator.next());
			}

			// a second pass for adjusting references.

			Iterator<JSONObject> jobsIterator2 = jobs.iterator();

			while (jobsIterator2.hasNext()) {
				parseJobRefs((JSONObject) jobsIterator2.next());
			}

		} catch (FileNotFoundException ex) {
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

		Iterator jobIt =  _graph.keySet().iterator();
		if (jobIt.hasNext()) {
			String firstJob = (String) jobIt.next();
			return _graph.getJob(firstJob);
		} else {
			return null;
		}

	}

	public void parseJob(JSONObject jsonJob) throws Exception {
		
		String name = (String) jsonJob.get("name");

		Job job = new Job(name);

		// add all data that does not involve job references.

		String command = (String) jsonJob.get("command");
		if (command != null) {
			job.setCmdLine(new CmdLine(command));
		}
		
		String profile = (String) jsonJob.get("profile");
		if (profile != null) {
			job.getCmdLine().setProfile(profile);
		}
		
		String host = (String) jsonJob.get("host");
		if (host != null) {
			job.getCmdLine().setHost(profile);
		}
		
		String script = (String) jsonJob.get("script");
		if (script != null) {
			job.getCmdLine().setScript(script);
		}
		
		JSONObject env = (JSONObject)jsonJob.get("env");
		if (env != null) {
			job.setEnv(parseEnv(env));
		}
		
		_graph.addJob(job);
		job.setGraph(_graph);

	}

	public void parseJobRefs(JSONObject jsonJob) throws Exception {
		String name = (String) jsonJob.get("name");
		Job job = _graph.getJob(name);

		String box = (String) jsonJob.get("box");

		// If a box is declared, (i.e. not a root job), find parent.
		if (box != null) {
			Job parent = _graph.getJob(box);
			if (parent == null) {
				throw new Exception("Illegal box: " + box + "for job: " + name);
			}
			job.setParent(parent);
			parent.addChild(job);
		}

		String pred = (String) jsonJob.get("pred");

		if (pred != null) {
			Job predecessor = _graph.getJob(pred);
			if (predecessor == null) {
				throw new Exception("Illegal pred: " + pred + "for job: "
						+ name);
			}
			job.setPredecessor(predecessor);
			predecessor.setSuccessor(job);
		}

	}
	
	/**
	 * Create an environment where all the values are Strings, based on JSon definition.
	 * 
	 * @param env
	 * @return
	 * @throws Exception
	 */
	public JobEnvironment parseEnv(JSONObject env) throws Exception {
		Iterator<String> envIt = env.keySet().iterator();
		
		JobEnvironment environment = new JobEnvironment();
		
		while (envIt.hasNext()) {
			String key = envIt.next();
			String value = (String)env.get(key);
			System.out.println("inserting : " + key + "-> " + value);
			environment.put(key,  value);
		}
		
		return environment;
	}

}
