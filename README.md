# desktopAutomation_Robot

Eclipse project for automation of desktop applications using java.awt.Robot. 

Requires Java 1.8 or above.

#### The addresser.jar tool can run functional tests via JUnit5 framework for the resources/Addresser.exe application:

1. Test AddEditDeleteContactTest - runs three test cases - Add contact, Edit contact, Delete contact

2. Test AddresserAdd100ContactsTest - runs one test case - Add 100 distinct contacts


### Usage: 

```
git clone https://github.com/danrusu/desktopAutomation_Robot.git

cd desktopAutomation_Robot
```

#### Run all tests:

```
java -Dtest=all -jar addresser.jar
```

#### Run each test separate:

```
java -Dtest=addEditDelete -jar addresser.jar

java -Dtest=add100 -jar addresser.jar
```


