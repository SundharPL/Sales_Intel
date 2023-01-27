package org.runner;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.base.Global;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src\\test\\resources\\Features\\", glue = { "org.stepdefinition",
		"org.runner" }, dryRun = false, monochrome = true, tags = {
				"@Various_Region_India,@Various_Region_US,@Various_Region_UK,@Customer_Details" }, plugin = {
						"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", "pretty",
						"io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm", "html:src\\test\\resources\\Reports\\HTML",
						"json:src\\test\\resources\\Reports\\Json\\cucumber.json",
						"junit:src\\test\\resources\\Reports\\XML\\cucumber.xml" })
public class Runner extends AbstractTestNGCucumberTests {
	
	@BeforeSuite
	public void allureReportCleanup() throws Throwable {
		String fileDirectoryDetails = System.getProperty("user.dir") + "\\allure-results";
		String targetDirectory = System.getProperty("user.dir") + "\\target";
		File file = new File(fileDirectoryDetails);

		if (file.isDirectory()) {
			try {
				FileUtils.cleanDirectory(file);
				Thread.sleep(2000);
				FileUtils.cleanDirectory(new File(targetDirectory));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Previously generated Files Deleted Permanently");
		}

	}

	@AfterSuite
	public void cucumberReportGenerate() {
		Global.generateJVMReport(
				System.getProperty("user.dir") + "\\src\\test\\resources\\Reports\\Json\\cucumber.json");
	}

}
