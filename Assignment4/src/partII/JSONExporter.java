package partII;

import java.util.ArrayList;

public class JSONExporter implements Exporter {

	@Override
	public String formatName() {
		return "JSON";
	}

	@Override
	public String fileExtension() {
		return "json";
	}
	
	// Returns records in a JSON formatted string
	@Override
	public String export(ArrayList<Record> records) {
        String str ="[\n";

        for (int i = 0; i < records.size(); i++) {
        	Record r = records.get(i);
        	str += "\t{\n\t\t\"id\": " + r.id + ",\n\t\t\"name\": \"" + r.name + "\",\n\t\t\"score\": " + r.score;

            if (i < records.size() - 1) {
                str += "\n\t},\n";
            } else {
            	str += "\n\t}\n]";
            }
        }

        return str;
	}

}
