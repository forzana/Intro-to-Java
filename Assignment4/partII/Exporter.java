package partII;

import java.util.ArrayList;

public interface Exporter {
	String formatName(); // e.g. "CSV", "JSON"
	String fileExtension(); // e.g. "csv", "json"
	String export(ArrayList<Record> records);
}
