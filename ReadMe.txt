This backend targets users, tasks tables on the rds using raw json body in the case of post requests

The json based backend is currently deployed on the server for receiving post requests on the following endpoints:
 /register 
 /login
 /logout
 /deleteAccount
 /downloadMyData
 /updateEmailReminders
 /updateDailyGoals
 /addTask

All the above endpoints persist data on rds in cases of record CRUD.

How to test using postman?

1) Test /register endpoint?
Post url: http://142.93.205.142:8090/OnItJson/register
POST
Body-raw-json: Please change the values for firstname, lastname, email, password
                  {
		    "firstname":"new",
 		    "lastname": "register1",
		    "email": "trial10@trial.com",
		    "password": "12345"
		  }

Different scensiors: 1) new user: should receive 200 ok along with a serializable token
                     2) registered user: attempting to register with a registered email returns 200 ok along with 1, not a serializable token
			the email is used to decide whether a user is registered or not


2) Test /login endpoint?
Post url: http://142.93.205.142:8090/OnItJson/login
POST
Body-raw-json:
		{
		    "email": "a@b.com",
		    "password": "c"
		}
Different scenarios:
 		1) Logging in a registered user (email/password are in the db) using correct email and password: returns 200 ok and a user object
		2) Loggin in a non registered user returns 200 ok and 1, not a user object


3) Test /logout endpoint?
GET url: http://142.93.205.142:8090/OnItJson/logout
GET
                Successful logging out should redirect to homepage after it invalidates the httpsession


4) Test /deleteAccount endpoint? When a logged in user decides to delete his/her account, they are prompted to provide their
password as a means of confirming their account, they provide the pwssword account and hit a delete my account button, this
leads to a post request on the /deleteAccount endpoint.

Post url: http://142.93.205.142:8090/OnItJson/deleteAccount
POST
Body-raw-json:
		{
		    "password": "c"
		}
Different scenarios:
		1) Deleting a loggedin user after he/she successfully provide the correct password: return 200 ok, true
		2) Any other issue such as providing the wrong password: returns 200 ok, false


5) Test /downloadMyData endpoint? 
GET url: http://142.93.205.142:8090/OnItJson/downloadMyData 
GET
                Successful download my data request by a logged in user returns 200 ok user data except id and password (stored in hashed format)
		

6) Test /updateEmailReminders endpoint? 
Post url: http://142.93.205.142:8090/OnItJson/updateEmailReminders 
POST
Body-raw-json:
		{
		    formInteger: "2"
		}
	
		Successful update by a logged in user returns 200 ok and true

7) Test /updateDailyGoals endpoint? 
Post url: http://142.93.205.142:8090/OnItJson/updateDailyGoals
POST
Body-raw-json:
		{
		    formInteger: "2"
		}
	
		Successful update by a logged in user returns 200 ok and true


8) Test /addTask endpoint?
Post url: http://142.93.205.142:8090/OnItJson/addTask 
POST
Body-raw-json: Please change the values for taskName, notes, reminder, repeatable
                  {
		    "taskName":"visit friends in CA",
		    "notes": "",
		    "reminder": "2",
		    "repeatable": "false"
		  }

		A successful task insertion returns 200 ok and a serializable, of course adding a task requires
                an authenticated user (loggedin user) and uses the user id as a foreign key on the database tasks table





