
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Parsing {

	public static String COMMA_DELIMITER = ",";
	public static int androidUsers = 0;
	static String firstName;
	static String secondName;

	public static void main(String[] args) {

		List<List<String>> entireFile = new ArrayList<>();

		try (Scanner scanner = new Scanner(new File("sample.csv"));) { // sample.csv is imported
			if (scanner.hasNext() == true) { // skips the entire first line if there is a 
				scanner.nextLine();          // value afterwards and starts at the second line
			}
			while (scanner.hasNextLine()) {
				entireFile.add(getRecordFromLine(scanner.nextLine())); // reads both columns and lines/row

			}

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		List<String> personsWithA = new ArrayList<String>(); // create new ArrayList to get the size of people with 'a'
																// in their name.

		for (int i = 0; i < entireFile.size(); i++) { // loop once through the file

			List<String> columnsPersonsA = entireFile.get(i); // records.get(i) to check every single row
			if (columnsPersonsA.get(1).toLowerCase().contains("a")) { // specifies the column to be scanned for the
																		// condition
				personsWithA.add(columnsPersonsA.get(1)); // if 'a' is found, add to the APersons ArrayList size

			}
			if (columnsPersonsA.get(2).toLowerCase().contains("a")) {
				personsWithA.add(columnsPersonsA.get(2)); // if 'a' is found, add to the APersons ArrayList size

			}

		}
		System.out.println("Total amount of people with 'a' in their name is " + personsWithA.size() + ".");

		List<String> Android = new ArrayList<String>();

		for (int i = 0; i < entireFile.size(); i++) { // loops 1 time through the file
			List<String> ColumnAndroid = entireFile.get(i); // records.get(i) to check every single row
			if (ColumnAndroid.get(6).contains("Android")) { // specifies the column to be scanned for the condition
				Android.add(ColumnAndroid.get(6)); // if Android is found, adds to the size of Android.
			}
			androidUsers = Android.size() + Android.size(); // only get half of the size if we don't addition them.
		}
		System.out.println("The amount of people working with android are " + androidUsers + ".");

		List<String> dates = new ArrayList<String>(); // the timestamps column

		for (int i = 0; i < entireFile.size(); i++) { // simply getting the timestamps column into an ArrayList
			List<String> time = entireFile.get(i);
			dates.add(time.get(0));
		}

		List<String> duplicateTimes = new ArrayList<String>(); // stores the duplicated values in an ArrayList
		HashSet<String> unique = new HashSet<String>();

		for (String s : dates) {
			if (!unique.add(s)) { // removes everything that isn't duplicated
				duplicateTimes.add(s); // the 2 timestamps which are duplicates
			}
		}

		List<String> names1 = new ArrayList<String>(); // Necessary ArrayList to grab name group 1 after the upcoming for loop
		List<String> names2 = new ArrayList<String>(); // Necessary ArrayList to grab name group 2 after the upcoming for loop

		for (int i = 0; i < entireFile.size(); i++) {
			List<String> duplicateTimeStamps = entireFile.get(i); // grabs the information from entireFile
			if (duplicateTimes.contains(duplicateTimeStamps.get(0))) { // duplicateTimes checks through
																		// duplicateTimeStamps after it's own values
				firstName = duplicateTimeStamps.get(1); // grabs the names that fits into the duplicateTimes condition
				names1.add(firstName); // the names are taken into an ArrayList
				secondName = duplicateTimeStamps.get(2); // grabs the names that fits into the duplicateTimes condition
				names2.add(secondName); // the names are taken into an ArrayList
			}

		}
		System.out.println("The people sharing timestamps are ");
		List<String> namesgroup2 = names2;
		String output2 = String.join(" ", namesgroup2); // removing those pesky commas, looks much better now.
		System.out.println(output2);
		List<String> namesgroup1 = names1;
		String output1 = String.join(" ", namesgroup1); // removing those pesky commas, looks much better now.
		System.out.println(output1);
	}

	private static List<String> getRecordFromLine(String line) { // Columns

		List<String> columns = new ArrayList<String>();
		try (Scanner rowScanner = new Scanner(line)) {
			rowScanner.useDelimiter(COMMA_DELIMITER); // delimiter to make the file easier to read for users.
			while (rowScanner.hasNext()) { // checks if there's a line after the current one.
				columns.add(rowScanner.next()); // reads through line by line, all columns.
			}

		} catch (NumberFormatException e) {

		}
		return columns;
	}
}