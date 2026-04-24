package partII;
import java.util.*;

public class Record {
    public final int id;
    public final String name;
    public final int score;

    public Record(int id, String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }
    
    public static void main(String[] args) {
    	ArrayList<Record> recordAr = new ArrayList<>();
    	recordAr.add(new Record(0, "John Smith", 89));
    	recordAr.add(new Record(1, "Jane Ma", 95));
    	recordAr.add(new Record(2, "Bob Jones", 76));
    	recordAr.add(new Record(3, "Lisa Li", 62));
    	
    	CSVExporter csvExporter = new CSVExporter();
    	JSONExporter jsonExporter = new JSONExporter();

    	System.out.println("CSV Export:");
    	System.out.println(csvExporter.export(recordAr));

    	System.out.println("JSON Export:");
    	System.out.println(jsonExporter.export(recordAr));
    }
}
