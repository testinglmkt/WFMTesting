package com.wfmtesting.testng;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;

public class TestngTestSuite {

	public static void main(String[] args) {
        TestListenerAdapter tla = new TestListenerAdapter();
        TestNG testng = new TestNG();
        testng.setTestClasses(new Class[] 
        		{ Admin_CreateUsFoTa.class,Admin_ExportCSVSingleForm.class,
        		Admin_DesignationTests.class, Admin_LoginEnableTest.class, 
        		Admin_UnitTest.class, SAdmin_CreateOrg.class, emailReport.class
        		});
        testng.addListener(tla);
        testng.run();
    }
	
}
