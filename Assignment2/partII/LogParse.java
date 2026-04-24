package partII;

public class LogParse {

	public static void main(String[] args) {
		String[] lines = {
			    "1768467601000|INFO|Scheduler|Daily job started",
			    "1768467602000|DEBUG|Scheduler|Loading job configuration",
			    "1768467604000|INFO|Auth|User dean logged in",
			    "1768467605000|WARN|Auth|Password expires in 3 days for user dean",
			    "1768467607000|INFO|DB|Connection pool warmed: size=10",
			    "1768467610000|DEBUG|DB|Prepared statement cache hit",
			    "1768467612000|INFO|Disk|Checking free space on server03",
			    "1768467613000|ERROR|Disk|Quota exceeded on server03",
			    "1768467615000|INFO|Scheduler|Retrying failed step",
			    "1768467618000|WARN|Network|Packet loss detected: 2.1%",
			    "1768467620000|INFO|Network|Re-routing traffic to backup link",
			    "1768467621000|DEBUG|Network|Link metrics updated",
			    "1768467623000|INFO|Auth|User alice logged in",
			    "1768467625000|ERROR|DB|Deadlock detected on transaction 88421",
			    "1768467626000|INFO|DB|Retry succeeded for transaction 88421",
			    "1768467629000|INFO|Scheduler|Step 1 complete",
			    "1768467631000|INFO|Scheduler|Step 2 complete",
			    "1768467633000|WARN|Disk|Free space low on server03",
			    "1768467635000|INFO|Disk|Cleanup started on server03",
			    "1768467638000|INFO|Disk|Cleanup removed 120 temporary files",
			    "1768467640000|INFO|Scheduler|Job complete: status=SUCCESS",
			    "1768467641000|DEBUG|Auth|Session cache size=42",
			    "1768467643000|ERROR|Auth|Failed login for user bob (bad password)",
			    "1768467645000|WARN|Auth|Account locked for user bob",
			    "1768467647000|INFO|Auth|Password reset requested for user bob"
			};
		
		LogEntry[] logEntries = new LogEntry[lines.length];
				
		// Create a new LogEntry for each line
		for (int i = 0; i < lines.length; i++) {
			LogEntry thisLog = new LogEntry(lines[i]);
			logEntries[i] = thisLog;
		}
		
		// Iterate through the array and print out the LogEntrys
		for (int i = 0; i < logEntries.length; i++) {
			LogEntry log = logEntries[i];
			System.out.println("DateTime: " + log.getDatetime() 
				+ "\nSeverity: " + log.getSeverity() + "\nSource: " + log.getSource() 
				+ "\nMessage: " + log.getMessage() + "\n");
		}
	}

}
