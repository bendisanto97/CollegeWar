package xcel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * @author Paul Courbois, Benjamin Di Santo, Julien Leopardo, Jules Plateau,
 *         Velan Senguttuvan and Simon Weber
 * @since 19/02/2019
 * @version 1.0
 * @see Reads the Excel table and transforms it into list of string's arrays
 */
public class Excel {

	private static ArrayList<String[]> tab = new ArrayList<String[]>();
	private static int line = 0;
	private static int column = 0;

	private final static String SAMPLE_XLSX_FILE_PATH = "./Cards.xls";
	private final static int NB_COLUMNS = 20;
	private final static int NB_LINES = 66;

	static {
		try {
			// Creating a Workbook from an Excel file (.xls or .xlsx)
			Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));

			// Getting the Sheet at index zero
			Sheet sheet = workbook.getSheetAt(0);

			// Create a DataFormatter to format and get each cell's value as String
			DataFormatter dataFormatter = new DataFormatter();

			// Use a for-each loop to iterate over the rows and columns
			for (Row row : sheet) {
				tab.add(new String[NB_COLUMNS]);
				for (Cell cell : row)
					tab.get(line)[column++] = dataFormatter.formatCellValue(cell);
				column = 0;
				line++;
			}

			// Closing the workbook
			workbook.close();
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return tab
	 */
	public static ArrayList<String[]> getTab() {
		return tab;
	}

	/**
	 * @return nbLines
	 */
	public static int getNbLines() {
		return NB_LINES;
	}
}
