import com.report.data.RecordKey;
import com.report.data.RecordType;
import com.report.data.ReportRecord;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class ParserController {

    public static final String EMPTY = "";
    public static final String XLSX = "JenkinsTestReport.xlsx";
    private ReportRetriever reportRetriever;

    public ParserController() throws IOException {
        process();
        System.out.println("opening excel...");
        Desktop.getDesktop().open(new File(XLSX));
    }

    private void process() {
        try {
            reportRetriever = new ReportRetriever();
            BufferedReader resultLast = reportRetriever.retrieve("1100");
            BufferedReader result = reportRetriever.retrieve("lastSuccessfulBuild");
            Map<RecordKey, ReportRecord> parsedLast = parse(resultLast, -1);
            Map<RecordKey, ReportRecord> parsed = parse(result, 0);
            for (RecordKey recordKey : parsed.keySet()) {
                parsed.get(recordKey).setReportRecordLast(parsedLast.get(recordKey));
            }
            ArrayList<ReportRecord> list = new ArrayList<>(parsed.values());
            ArrayList<ReportRecord> listLast = new ArrayList<>(parsedLast.values());

            list.addAll(listLast);

            new ApachePOIExcelWrite(list);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private Map<RecordKey, ReportRecord> parse(BufferedReader input, int week) throws IOException {
        Map<RecordKey, ReportRecord> result = new HashMap<>();
        boolean detailedView = false;
        String line;
        String team = "";
        while ((line = input.readLine()) != null) {
            if (line.contains("Detailed")) {
                detailedView = true;
            }
            if (detailedView) {
                line = StringUtils.deleteWhitespace(line);
                if (line.contains("Team:")) {
                    team = line.replace("Team:", EMPTY);
                }
                if (line.contains("Branch:4.3") || line.contains("Branch:trunk")) {
                    line = clearLine(line);
                    line = team + ":" + line;
                    String[] strings = StringUtils.splitPreserveAllTokens(line, "-:()");
                    List<Object> row = Arrays.asList(strings);
                    ReportRecord record = new ReportRecord(row);
                    record.setWeek(week);
                    if(Arrays.asList("Y","X","S","P").contains(strings[0])){
                        record.setType(RecordType.DEVELOPMENT);
                    }
                    result.put(new RecordKey(strings[0], strings[1]), record);
                }
            }
        }
        input.close();
        return result;
    }

    private String clearLine(String line) {
        line = line.replace("Branch:", EMPTY).replace("Build:", EMPTY).replace("UnitTests:", EMPTY);
        line = line.replace("STests:", EMPTY).replace("ITests:", EMPTY).replace("Ignored:", EMPTY);
        line = line.replace("Total:", EMPTY).replace("OldestAge:", EMPTY).replace(")", EMPTY);
        line = line.replace("(", "-").replace("OldestAge:", EMPTY).replace("null", EMPTY);
        return line;
    }

    public static void main(String[] args) throws IOException {
        new ParserController();
    }
}
