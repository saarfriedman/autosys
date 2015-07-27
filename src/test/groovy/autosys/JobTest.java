
package autosys;

import autosys.job.*;
import autosys.app.*;


public class JobTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		final String filePath = System.getProperty("user.dir") + "/src/main/resources/sample1.json";

		
		JobDefinitionReader reader = new JobDefinitionReader();
		Job j = reader.parseScript(filePath);

	}

}
