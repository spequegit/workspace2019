import java.io.*;
import java.net.URL;
import java.util.stream.Stream;

public class ReportRetriever {

    private String address = "http://dehamsl1199.int.kn:8091/jenkins/job/SALOG/job/Reports/job/Jenkins%20Teststatus%20Report/{build}/artifact/output/testreport.txt";

    public BufferedReader retrieve(String build) throws IOException {
        System.out.println("reading... "+address.replace("{build}", build));
        URL url = new URL(address.replace("{build}", build));
        InputStream inputStream = url.openStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(new File(build)));
        BufferedReader result = new BufferedReader(inputStreamReader);

//        byte[] buffer = new byte[inputStream.available()];
//        inputStream.read(buffer);
//        File targetFile = new File(build);
//        OutputStream outStream = new FileOutputStream(targetFile);
//        outStream.write(buffer);

        return result;
    }
}
