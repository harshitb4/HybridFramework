package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testbase.BaseClass;

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	String repName;
	
	@Override
	public void onStart(ITestContext testContext)
	{
		System.out.println("OnStart started");
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName = "Test-Report-"+timeStamp+".html";
		System.out.println(repName);
		sparkReporter = new ExtentSparkReporter(".\\reports\\"+repName);
		
		sparkReporter.config().setDocumentTitle("OpenCart Automation Report"); //Title of the Report
		sparkReporter.config().setReportName("OpenCart Functional Testing"); //Name of the Report
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "OpenCart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("OperatingSystem", os);
		
		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty())
		{
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
	}
	
	@Override
	public void onTestSuccess(ITestResult result)
	{
		/*if (test == null) {  // Create class-level entry only once
	        test = extent.createTest(result.getTestClass().getName());
	    }
	    test.createNode(result.getName()).log(Status.PASS, result.getName() + " got successfully executed");*/
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName()+"got successfully executed");
	}
	
	@Override
	public void onTestFailure(ITestResult result)
	{	
		System.out.println("OnTestFailure started");
		//logger.info("On failure test method started");
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName()+"got failed");
		System.out.println("Failed status logged successfully");
		//logger.info("Failed status logged successfully");
		System.out.println(result.getName());
		BaseClass base = (BaseClass) result.getInstance();
		String imgPath = base.captureScreen(result.getName());
		System.out.println("ImagePath is: "+imgPath);
		//logger.info("Image path stored scuuessfully");
		test.addScreenCaptureFromPath(imgPath);
		System.out.println("Screenshot attached successfully");
		//logger.info("Attached screenshot successfully");
	}
	
	@Override
	public void onTestSkipped(ITestResult result)
	{
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+"got skipped");
	}
	
	@Override
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
		System.out.println("Flushed Successfully");
		String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
