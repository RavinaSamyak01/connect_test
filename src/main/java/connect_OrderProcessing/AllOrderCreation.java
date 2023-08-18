package connect_OrderProcessing;

import java.time.Duration;import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import connect_BasePackage.BaseInit;
import connect_BasePackage.Email;
import connect_BasePackage.EmailOld;
import connect_OrderProcessNonSPL.AIR;
import connect_OrderProcessNonSPL.DRV;
import connect_OrderProcessNonSPL.FRA;
import connect_OrderProcessNonSPL.FRG;
import connect_OrderProcessNonSPL.LOC;
import connect_OrderProcessNonSPL.P3P;
import connect_OrderProcessNonSPL.PA;
import connect_OrderProcessNonSPL.SD;
import connect_OrderProcessNonSPL.SDC;
import connect_OrderProcessSPL.CPU;
import connect_OrderProcessSPL.D3P;
import connect_OrderProcessSPL.H3P;
import connect_OrderProcessSPL.T3PLAST;

public class AllOrderCreation extends BaseInit {
	String File = null;

	@Test
	public void allOrderCreation() throws Exception {

		msg.append("\n\n" + "===== Order Creation and Processing Test : Start ====" + "\n");
		String Env = storage.getProperty("Env");
		try {

			// -- Activate Account

			ActivateAccount();

			try {
				LOC LocJob = new LOC();
				LocJob.locLocal();

				setResultData("Result", 1, 4, "PASS");

			} catch (Exception LOC) {
				logs.info(LOC);
				getScreenshot(driver, "LOCIssue");
				String Error = LOC.getMessage();
				setResultData("Result", 1, 4, "FAIL");
				setResultData("Result", 1, 5, Error);
				login();
			}

			try {
				SD SDJob = new SD();
				SDJob.sdSameDay();
				setResultData("Result", 2, 4, "PASS");

			} catch (Exception SD) {
				logs.info(SD);
				getScreenshot(driver, "SDIssue");
				String Error = SD.getMessage();
				setResultData("Result", 2, 4, "FAIL");
				setResultData("Result", 2, 5, Error);

				login();

			}

			try {
				P3P P3PJob = new P3P();
				P3PJob.p3pservice();
				setResultData("Result", 3, 4, "PASS");

			} catch (Exception P3P) {
				logs.info(P3P);
				getScreenshot(driver, "P3PIssue");
				String Error = P3P.getMessage();
				setResultData("Result", 3, 4, "FAIL");
				setResultData("Result", 3, 5, Error);
				login();

			}

			try {
				PA PAJob = new PA();
				PAJob.paPriorityAir();
				setResultData("Result", 4, 4, "PASS");

			} catch (Exception PA) {
				logs.info(PA);
				getScreenshot(driver, "PAIssue");
				String Error = PA.getMessage();
				setResultData("Result", 4, 4, "FAIL");
				setResultData("Result", 4, 5, Error);
				login();

			}

			try {
				DRV DRVJob = new DRV();
				DRVJob.drvDriver();
				setResultData("Result", 5, 4, "PASS");

			} catch (Exception DRV) {
				logs.info(DRV);
				getScreenshot(driver, "DRVIssue");
				String Error = DRV.getMessage();
				setResultData("Result", 5, 4, "FAIL");
				setResultData("Result", 5, 5, Error);
				login();

			}

			try {
				AIR AIRJob = new AIR();
				AIRJob.airService();
				setResultData("Result", 6, 4, "PASS");

			} catch (Exception AIR) {
				logs.info(AIR);
				getScreenshot(driver, "AIRIssue");
				String Error = AIR.getMessage();
				setResultData("Result", 6, 4, "FAIL");
				setResultData("Result", 6, 5, Error);
				login();

			}

			try {
				SDC SDCJob = new SDC();
				SDCJob.sdcSameDayCity();
				setResultData("Result", 7, 4, "PASS");

			} catch (Exception SDC) {
				logs.info(SDC);
				String Error = SDC.getMessage();
				setResultData("Result", 7, 4, "FAIL");
				setResultData("Result", 7, 5, Error);
				getScreenshot(driver, "SDCIssue");
				login();

			}

			try {
				FRA FRAJob = new FRA();
				FRAJob.fraFreight();
				setResultData("Result", 8, 4, "PASS");

			} catch (Exception FRA) {
				logs.info(FRA);
				getScreenshot(driver, "FRAIssue");
				String Error = FRA.getMessage();
				setResultData("Result", 8, 4, "FAIL");
				setResultData("Result", 8, 5, Error);
				login();

			}

			try {
				FRG FRGJob = new FRG();
				FRGJob.frgFreight();
				setResultData("Result", 9, 4, "PASS");

			} catch (Exception FRG) {
				logs.info(FRG);
				getScreenshot(driver, "FRGIssue");
				String Error = FRG.getMessage();
				setResultData("Result", 9, 4, "FAIL");
				setResultData("Result", 9, 5, Error);
				login();

			}

			try {
				H3P H3PJob = new H3P();
				H3PJob.h3P();
				setResultData("Result", 10, 4, "PASS");

			} catch (Exception H3P) {
				logs.info(H3P);
				getScreenshot(driver, "LOCIssue");
				String Error = H3P.getMessage();
				setResultData("Result", 10, 4, "FAIL");
				setResultData("Result", 10, 5, Error);
				login();

			}

			try {
				D3P D3PJob = new D3P();
				D3PJob.d3P();
				setResultData("Result", 11, 4, "PASS");

			} catch (Exception D3P) {
				logs.info(D3P);
				getScreenshot(driver, "D3PIssue");
				String Error = D3P.getMessage();
				setResultData("Result", 11, 4, "FAIL");
				setResultData("Result", 11, 5, Error);
				login();

			}

			try {
				T3PLAST T3PLASTJob = new T3PLAST();
				T3PLASTJob.t3PLAST();
				setResultData("Result", 12, 4, "PASS");

			} catch (Exception T3PLAST) {
				logs.info(T3PLAST);
				getScreenshot(driver, "LOCIssue");
				String Error = T3PLAST.getMessage();
				setResultData("Result", 12, 4, "FAIL");
				setResultData("Result", 12, 5, Error);
				login();

			}

			try {
				CPU CPUJob = new CPU();
				CPUJob.FedExCpu();
				setResultData("Result", 13, 4, "PASS");

			} catch (Exception CPU) {
				logs.info(CPU);
				getScreenshot(driver, "CPUIssue");
				String Error = CPU.getMessage();
				setResultData("Result", 13, 4, "FAIL");
				setResultData("Result", 13, 5, Error);
				login();

			}

			Env = storage.getProperty("Env");
			if (Env.equalsIgnoreCase("Test")) {
				File = ".\\src\\main\\resources\\Connect OCP Result_Test.xlsx";
			} else if (Env.equalsIgnoreCase("STG")) {
				File = ".\\src\\main\\resources\\Connect OCP Result_STG.xlsx";

			} else if (Env.equalsIgnoreCase("Pre-Prod")) {
				File = ".\\src\\main\\resources\\Connect OCP Result_PreProd.xlsx";

			} else if (Env.equalsIgnoreCase("PROD")) {
				File = ".\\src\\main\\resources\\Connect OCP Result_Prod.xlsx";

			}
		} catch (Exception ee) {
			logs.error(ee);
			logs.info("Line number is: " + ee.getStackTrace()[0].getLineNumber() + ee.getClass());
			getScreenshot(driver, "OrderCreation&ProcessConnectIssue");
			System.out.println("Issue in Order Creation and processing from connect");
			logs.info("Issue in Order Creation and processing from connect");
			// --Connect LogOut
			Env = storage.getProperty("Env");
			if (Env.equalsIgnoreCase("Test")) {
				File = ".\\src\\main\\resources\\Connect OCP Result_Test.xlsx,.\\Report\\NA_Screenshot\\OrderCreation&ProcessConnectIssue.png";
			} else if (Env.equalsIgnoreCase("STG")) {
				File = ".\\src\\main\\resources\\Connect OCP Result_STG.xlsx,.\\Report\\NA_Screenshot\\OrderCreation&ProcessConnectIssue.png";

			} else if (Env.equalsIgnoreCase("Pre-Prod")) {
				File = ".\\src\\main\\resources\\Connect OCP Result_PreProd.xlsx,.\\Report\\NA_Screenshot\\OrderCreation&ProcessConnectIssue.png";

			} else if (Env.equalsIgnoreCase("PROD")) {
				File = ".\\src\\main\\resources\\Connect OCP Result_Prod.xlsx,.\\Report\\NA_Screenshot\\OrderCreation&ProcessConnectIssue.png";

			}
			//

		}

		msg.append("Environment==" + Env + "\n\n");
		String subject = "Selenium Automation Script: " + Env + " Connect Order Processing";
		try {
//			/kunjan.modi@samyak.com, pgandhi@samyak.com,parth.doshi@samyak.com

			EmailOld.sendMail(EmailID, subject, msg.toString(), File);

			/*
			 * SendEmail.sendMail(
			 * "ravina.prajapati@samyak.com, asharma@samyak.com, parth.doshi@samyak.com,saurabh.jain@samyak.com"
			 * , subject, msg.toString(), File);
			 */
			// SendEmail.sendMail("ravina.prajapati@samyak.com, asharma@samyak.com
			// ,parth.doshi@samyak.com", subject, msg.toString(), File);

		} catch (Exception ex) {
			logs.error(ex);
			logs.info("Line number is: " + ex.getStackTrace()[0].getLineNumber());

		}

		msg.append("\n" + "===== Order Creation and Processing Test : End ====" + "\n");

	}

	public void loadURL() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));// wait time
		String Env = storage.getProperty("Env");
		System.out.println("Env " + Env);

		String baseUrl = null;
		if (Env.equalsIgnoreCase("Pre-Prod")) {
			baseUrl = storage.getProperty("PREPRODURL");
			driver.get(baseUrl);

		} else if (Env.equalsIgnoreCase("STG")) {
			baseUrl = storage.getProperty("STGURL");
			driver.get(baseUrl);
		}
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("login")));

	}

}
