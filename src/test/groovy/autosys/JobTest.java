
package autosys;

import autosys.job.*;
import java.io.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JobTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Job j = new Job("test");

		final String filePath = "/home/saar/proj/workspace/eclipse/autosys/src/main/resources/sample1.json";

		try {

			FileReader reader = new FileReader(filePath);
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
			// get an array from the JSON object
			JSONArray jobs= (JSONArray) jsonObject.get("jobs");
			
			System.out.println(jobs.get(1));
			JSONObject job = (JSONObject) jobs.get(1);
			System.out.println(job.get("name"));
			
		}  catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ParseException ex) {
			ex.printStackTrace();
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}


	}

}
