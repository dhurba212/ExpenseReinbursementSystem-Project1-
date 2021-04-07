#### ExpenseReinbursementSystem-Project1-
This is the backend for the project 1 of the Revature 2102 GCP curriculum.

  Employees and Managers can login through a login page using the input fields username and password. Once logged in, the login page will redirect the user to the appropriate page depending on their role. Employee will be redirected to employee page while Managers will be redirected to the Managers page.

  Employees can submit new expenses,the expense amount as well as the note about the expense. Employees will be able to view all their expenses and see the status of each one whether it is pending, approved or denied.

  The Manager page will display the list of all the expenses submitted by all the employees and their status. Manager can approve, deny and also update the amount of the expense submitted by the employee. Manager can sort the expense table by the status of the expense, id of the expense and by date submitted. They will be able to see the total amount and percentage of the expense approved, denied and pending.
*** 
### TECHNOLOGIES USED
* Java
* PostgreSQL
* GCP Virtual Machine
* Hibernate
* Gradle build tool
* HTML
* CSS
* JS
*** 

### FEATURES
* login
* Create and submit expenses
* Update and delete expense 
* Sort expenses
* logout
* logging
* Authenticating users using JWT
*** 
### TESTING
* The project follows TDD.
* All the DAOs and Services are tested using Junit and Mockito where applicable.
* All the endpoints are tested using postman.

*** 
### TO-DO LIST
* Signup page to register new employees and managers
* Pop-up message confirming if managers would like to goahead with updates
*** 
### LICENSE
* This project uses the following license: MIT license
