package httpstest;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ZopeeConnectionExample {

	private List<String> cookies;
	private HttpsURLConnection conn;

	private static final String URLELICZNIK = "https://elicznik.tauron-dystrybucja.pl";
	private static final String URLLOGOWANIE = "https://logowanie.tauron-dystrybucja.pl/login";
	private String date;
	private String resultState;
	private String taryfa;
	private JLabel jLabel2 = new JLabel("zuzycie: ");
	private JLabel jLabel3 = new JLabel("taryfa dystrybucyjna: ");
	private JLabel jLabel1 = new JLabel("stan licznika na dzien: ");

	public ZopeeConnectionExample() throws Exception {
		initFrame();
	}

	public static void main(String[] args) throws Exception {
		new ZopeeConnectionExample();
	}

	private void initFrame() {
		JFrame jFrame = new JFrame("ZOPEE");
		jFrame.setSize(300, 200);
		JPanel jPanel = new JPanel();
		jFrame.setContentPane(jPanel);
		jFrame.setLayout(new GridLayout(10, 1));
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setLocationRelativeTo(null);
		
		jPanel.add(new JLabel("uzytkownik"));
		jPanel.add(new JTextField("U685745623"));
		jPanel.add(new JLabel("haslo"));
		jPanel.add(new JTextField("********"));

		JButton jButton = new JButton("czytaj dane licznika");
		jPanel.add(jButton);
		jButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CookieHandler.setDefault(new CookieManager()); // necessary (java.net.ProtocolException: Server redirected too
				try {
					sendPost(URLLOGOWANIE, "username=demo&password=demo123&service=");
					String result = retrieveContent(URLELICZNIK);
					date = parseDate(result);
					resultState = parseResult(result);
					taryfa = parseTaryfa(result);
					
					jLabel2.setText("zuzycie: " + resultState);
					jLabel3.setText("taryfa dystrybucyjna: " + taryfa);
					jLabel1.setText("stan licznika na dzien: " + date);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});

		jPanel.add(jLabel1);
		jPanel.add(jLabel2);
		jPanel.add(jLabel3);
		
		
		jFrame.setVisible(true);
	}

	private String parseTaryfa(String result) {
		String tag = "Taryfa dystrybucyjna"; // <a href="odczyty">016282</a>
		int indexOf = result.indexOf(tag);
		return result.substring(indexOf + 21, indexOf + 25);
	}
	private String parseResult(String result) {
		String tag = "<a href=\"odczyty\">"; // <a href="odczyty">016282</a>
		int indexOf = result.indexOf(tag);
		return result.substring(indexOf + 18, indexOf + 24);
	}
	private String parseDate(String result) {
		String tag = "Stan licznika na dzie"; // <a href="odczyty">016282</a>
		int indexOf = result.indexOf(tag);
		return result.substring(indexOf + 41, indexOf + 62);
	}
	
	private String parseKWh(String html) throws UnsupportedEncodingException {
		Document doc = Jsoup.parse(html);
		Element chartDayInput = doc.getElementById("chartDay");
		Element chartValueInput = doc.getElementById("chartValue");
		
		String date = chartDayInput.val();
		String value = chartValueInput.val();
		
		return html;
	}

	private void sendPost(String url, String postParams) throws Exception {
		URL obj = new URL(url + "?" + postParams);
		conn = (HttpsURLConnection) obj.openConnection();
		// Acts like a browser
		conn.setUseCaches(false);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Host", "logowanie.tauron-dystrybucja.pl");
		conn.setRequestProperty("User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:64.0) Gecko/20100101 Firefox/64.0");
		conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		conn.setRequestProperty("Accept-Language", "pl,en-US;q=0.7,en;q=0.3");
		conn.addRequestProperty("Cookie",
				"GA1.2.974758340.1548242257; _gid=GA1.2.1016691733.1548242257; PHPSESSID=9n8a17tg8atkvhgo7489ro8k93; BIGipServerWPL_PROD_HTTPS=!1XNn3Ndfh8tZRReHp7dpMVRssTMLtiOr/z6/HbOn7ZZZUM4bEBVgjbZQRrQiF3JX9l8upfT6fRu49kJJjFgTgNW3KyznXm5DZAlRcjeeaQ==; tauron-load-balancer-cookie-w=!oFJ457jHeO82ft//bV21mG3uy4VhhpVB/EuvQe1cpxFSnqteZOoxAIoLUk1E/NuJIXBCEGNljekFOHAG1bUgXJYOMnBZjcYJQBtcknAahcLg; rxVisitor=1548265849087EQNB9JP3P6H5VU19UTIPCSEHM1C5I3KT; dtPC=1$265849079_45h-vDDKNOEBIHNDOBFHKBHMPMNLOTIHPEJFA; rxvt=1548267656207|1548265849094; dtSa=true%7CC%7C-1%7CZaloguj%20si%C4%99%7C-%7C1548265864461%7C265849079_45%7Chttps%3A%2F%2Flogowanie.tauron-dystrybucja.pl%2F%7CPlatforma%20do%20rejestracji%20i%20logowania%7C1548265852196%7C; dtLatC=7; dtCookie=1$26F5D2403437042F95BB4A2FB96B0176|moj.tauron.pl|0");
		conn.setRequestProperty("Connection", "keep-alive");
		conn.setRequestProperty("Referer", "https://logowanie.tauron-dystrybucja.pl/");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setRequestProperty("Content-Length", "39");
		conn.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
		conn.setRequestProperty("Upgrade-Insecure-Requests", "1");
		conn.setDoOutput(true);
		conn.setDoInput(true);

		// Send post request
		// DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
		// wr.writeBytes(postParams);
		// wr.flush();
		// wr.close();

		int responseCode = conn.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + postParams);
		System.out.println("Response Code : " + responseCode);

		// BufferedReader in = new BufferedReader(new
		// InputStreamReader(conn.getInputStream()));
		// String inputLine;
		// StringBuffer response = new StringBuffer();
		//
		// while ((inputLine = in.readLine()) != null) {
		// response.append(inputLine + "\n");
		// }
		// in.close();
	}

	private String retrieveContent(String url) throws Exception {
		URL obj = new URL(url);
		conn = (HttpsURLConnection) obj.openConnection();
		// default is GET
		conn.setRequestMethod("GET");
		conn.setUseCaches(false);
		// act like a browser
		conn.setRequestProperty("User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:64.0) Gecko/20100101 Firefox/64.0");
		conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		if (cookies != null) {
			for (String cookie : this.cookies) {
				conn.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
			}
		}
		int responseCode = conn.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		// Get the response cookies
//		setCookies(conn.getHeaderFields().get("Set-Cookie"));
		return response.toString();
	}
}