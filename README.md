# AutomationPractice

[![N|Solid](https://cldup.com/dTxpPi9lDf.thumb.png)](https://nodesource.com/products/nsolid)

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

This project is about a small framework for running ui and api test in parallel and sequential with allure reporting.You can refer it for

  - parallel execution using WebDriver
  - api automation using RestAssured
  - Allure reporting.

# What's New!

  - Used WebDriverManager to support all kind of browser irrespective of driver exe available in project or not.
  - Used spring framework for dependency injection and environment creation from properties file.

### Report Generation

Before execution its a good idea to install allure report for viewing reports in browser.
```sh
$ scoop install allure
```

| Report Plugin | README |
| ------ | ------ |
| Allure | [https://github.com/allure-framework/allure2/blob/master/README.md][PlDb] |

installation documentation: https://docs.qameta.io/allure/#_installing_a_commandline

### Running Tests

Execution requires [Java 7 or above] to run.

> running UI tests in parallel

```sh
$ mvn clean test -DsuiteXmlFile=testngParallel.xml
```
> running UI tests in sequence

```sh
$ mvn clean test -DsuiteXmlFile=testngSequencial.xml
```
> running api tests

```sh
$ mvn clean test -DsuiteXmlFile=testngSequencial.xml
```
### Report Generation

Is execution successfull? Great!

Now follow following steps to see report in browser:
```sh
$ cd target
$ allure serve
```

Browser should open with details of execution. Generated reports will have steps details of tests and failed tests screenshots as well.

Thanks,
