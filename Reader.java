package ravenReader;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * The URL reader class
 * 
 * @author Federico
 *
 */
public class Reader {

	/**
	 * Method to read URL as string
	 * 
	 * @return A string of text read from a URL
	 */

	public static Connection getConnection() throws Exception {
		try {
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/wordoccurences";
			String username = "Federico";
			String password = "Password123!";
			Class.forName(driver);

			Connection conn = DriverManager.getConnection(url, username, password);
			System.out.println("connected");
			return conn;
		} catch (Exception e) {
			System.out.println(e);
		}

		return null;
	}

	public static void createTable() throws Exception {
		try {
			Connection conn = getConnection();
			PreparedStatement create = conn.prepareStatement("CREATE TABLE IF NOT EXISTS words (word varchar(15), occurence int);");
			create.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			System.out.println("Function complete");
		}
	}

	public static String raven() {
		String words = null;
		try {
			@SuppressWarnings("resource")
			String word = new Scanner(new URL("https://www.gutenberg.org/files/1065/1065-h/1065-h.htm").openStream(),
					"UTF-8").useDelimiter("\\A").next();
			Map<String, Integer> map = new HashMap<String, Integer>();
			String delStr;
			String delStr2;

			delStr = word.substring(0, 1924);
			delStr2 = word.substring(10899, 30163);
			word = word.replace(delStr, "");
			word = word.replace(delStr2, "");
			word = word.replaceAll("<[^>]*>", "");
			word = word.replaceAll("&mdash", "");
			word = word.replaceAll("\"", "");
			word = word.replaceAll("'", "");
			word = word.replaceAll(",", "");
			word = word.replaceAll("\\.", "");
			word = word.replaceAll("!", "");
			word = word.replaceAll(";", " ");
			word = word.replaceAll("\\s+", " ").trim();
			word = word.toLowerCase();

			String[] arr = word.split(" ");
			Connection conn = getConnection();
			for (int i = 0; i < arr.length; i++) {
				if (map.containsKey(arr[i]) == false)
					map.put(arr[i], 1);
				else {
					int count = (int) (map.get(arr[i]));
					map.remove(arr[i]);
					map.put(arr[i], count + 1);
				}

			}
			Set<Map.Entry<String, Integer>> set = map.entrySet();
			List<Map.Entry<String, Integer>> sortedList = new ArrayList<Map.Entry<String, Integer>>(set);
			Collections.sort(sortedList, new Comparator<Map.Entry<String, Integer>>() {
				public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
					return (b.getValue()).compareTo(a.getValue());
				}
			});
			for (Map.Entry<String, Integer> i : sortedList) {
				words = words + ("\n" + i.getKey() + " --> " + i.getValue());
				try {
					PreparedStatement insert = conn
							.prepareStatement("INSERT INTO words (word, occurence) VALUES ('" + i.getKey() + "', '"+i.getValue()+"')");
					insert.executeUpdate();
				} catch (Exception e) {
					System.out.println(e);
				}
			}
			System.out.println("Insert complete");

		} catch (Exception FileNotFound) {
			System.out.println("URL not found");
		}
		return words;
	}
}
