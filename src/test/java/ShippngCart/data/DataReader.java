package ShippngCart.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	public List<HashMap<String, String>> getJasodToMap( String JsonFile) throws IOException {

		// read data from Jason to string
		String jsonContent = FileUtils.readFileToString(
				new File(JsonFile),
				StandardCharsets.UTF_8);
		
		 
		//String to hashMap - jackson Databind
		ObjectMapper mapper = new ObjectMapper();
		  List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
	      });
		  return data;
	}
}
