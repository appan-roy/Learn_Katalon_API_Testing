import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable

response1 = WS.sendRequest(findTestObject('APIChaining - SOAP/ListCountries'))

String xml1 = response1.responseBodyContent

def dataValue = new XmlSlurper().parseText(xml1)

for (int i = 0; i <= 245; i++) {
	
	def countryName = dataValue.ListOfCountryNamesByNameResult.tCountryCodeAndName[i].sName.text()
	
	def countryCode = dataValue.ListOfCountryNamesByNameResult.tCountryCodeAndName[i].sISOCode.text()
    
	if (countryCode == 'US') {
		
		println('The country name is ' + countryName)
		
		println('The country code is ' + countryCode)
		
		GlobalVariable.countryISOCode = countryCode
        
		break
		
    } else {
        
		continue
    
	}
	
}


WS.sendRequestAndVerify(findTestObject('APIChaining - SOAP/GetCapitalCity'))

