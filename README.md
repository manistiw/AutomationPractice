# AutomationPractice
This is general practice for automation

In order to run the project please run cammand from project folder location
--for running parallel execution
mvn clean test -DsuiteXmlFile=testngParallel.xml
-- for running sequential execution
mvn clean test -DsuiteXmlFile=testngSequencial.xml
--for running API tests
mvn clean test -DsuiteXmlFile=testngApi.xml


Generating Report:

install allure for better viewing of reports.
For Windows, Allure is available from the Scoop commandline-installer.
To install Allure, download and install Scoop and then execute in the Powershell:
scoop install allure

for documentation please visit :https://docs.qameta.io/allure/#_installing_a_commandline


Viewing Reports:

After completion of execution you can view report following steps:
1.open powershell/cmd and navigate to /target folder.
2.run allure serve
3.browser will get open with the latest reports of the execution.
