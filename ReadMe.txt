The json based backend is currently deployed on the server for receiving post requests on /register or /login endpoints.

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
