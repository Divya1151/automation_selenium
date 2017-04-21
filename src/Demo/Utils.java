package Demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

public class Utils {

	private static XSSFWorkbook excelWb;
	private static XSSFSheet excelSheet;

	public static Properties getPropertiesFromObject() throws IOException {
		Properties properties = new Properties();
		// Read object repository file

		InputStream stream = new FileInputStream(
				new File(System.getProperty("user.dir") + "/src/objects/divya.properties"));
		// load all objects
		properties.load(stream);
		return properties;
	}

	public static Object[][] readExcel(String excelName, String sheetName) {
		String testData[][] = null;
		try {

			FileInputStream fileInputStream = new FileInputStream(new File(excelName));
			excelWb = new XSSFWorkbook(fileInputStream);
			excelSheet = excelWb.getSheet(sheetName);

			int rowCount = excelSheet.getLastRowNum();
			int columnCount = 2;

			testData = new String[rowCount][columnCount];

			for (int i = 1; i < rowCount + 1; i++) {
				for (int j = 0; j < columnCount; j++) {
					testData[i - 1][j] = excelSheet.getRow(i).getCell(j).getStringCellValue();

				}

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error " + e.getMessage());
		}
		return testData;

	}

	public static void captureScreenshot(WebDriver driver) throws Exception {
		TakesScreenshot ts = (TakesScreenshot) driver;

		File srcFile = ts.getScreenshotAs(OutputType.FILE);

		File dstFile = new File("./Screenshots/capture.png");

		FileUtils.copyFile(srcFile, dstFile);
	}

}
