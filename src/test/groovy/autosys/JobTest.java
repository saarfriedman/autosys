
package autosys;

import autosys.job.*;
import autosys.app.*;
import java.util.*;


public class JobTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		final String filePath = System.getProperty("user.dir")
				+ "/src/main/resources/sample1.json";

		try {

			JobDefinitionReader reader = new JobDefinitionReader();
			Job j = reader.parseScript(filePath);

			System.out.println("Read job: " + j);
			j.getCmdLine().execute();
			
			System.out.println("Job definitions: ");
			System.out.println(j.getGraph());
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
