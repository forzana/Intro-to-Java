package partII;

import java.util.ArrayList;

public class CSVExporter implements Exporter {

	@Override
	public String formatName() {
		return "CSV";
	}

	@Override
	public String fileExtension() {
		return "csv";
	}

	// Returns records in a CSV formatted string
	@Override
	public String export(ArrayList<Record> records) {
		String str = "id,name,score\n";

        for (int i = 0; i < records.size(); i++) {
        	Record r = records.get(i);
            str += r.id + "," + r.name + "," + r.score + "\n";
        }

        return str;
	}

}
