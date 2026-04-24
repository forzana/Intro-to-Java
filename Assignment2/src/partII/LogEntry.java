package partII;

import java.util.Date;

public class LogEntry {
	private Date datetime;
	private String severity;
	private String source;
	private String message;

    public LogEntry(String logString) {
        String[] logs = logString.split("\\|");
        setDatetime(new Date(Long.parseLong(logs[0])));
        setSeverity(logs[1]);
        setSource(logs[2]);
        setMessage(logs[3]);
    }

    public Date getDatetime() {
    	return datetime;
    }
    
    public void setDatetime(Date datetime) {
    	this.datetime = datetime;
    }
    
    public String getSeverity() {
    	return severity;
    }
    
    public void setSeverity(String severity) {
    	this.severity = severity;
    }
    
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
