# OnIt

## Project Description

With OnIt, a user can create a highly customizable, visually pleasing to-do list. They can view a graph showing how many they've completed over the past <number specified by user> days, as well as download their task list to a file! All of these features are accessible after registering for an account and signing in.

## Technologies Used
* Angular
* HTML
* CSS
* Angular Material
* Bootstrap
* Google Maps
* CanvasJS
* Typescript
* Java
* Spring (Core, MVC).
* Hibernate.
* PostgreSQL.
* Apache Tomcat.
* JUnit.
* Mockito.
* Log4j.
  
## Features
With OnIt, a user can:

* Register for an account.
* Sign in to a user home page displaying a filterable (by category, by whether it has been completed) grid list of their tasks.
* Create and edit a task with a name, description, due date, category label, (optional) location via Google Maps and can (optionally) be marked as repeatable. 
  Each task also has an option to send an email reminder to the user one day before its due date.
* View their "Task Stats", where a curve graph shows them how many tasks they've completed over the past n days, where they specify n via a dropdown box.
* Download their task list to a file.
* Update their account information, or delete their account.

To-do's:
* Allow users to specify their own category label for a task instead of (only) offering a set list of choices,
* Show additional data related to user productivity on the "Task Stats" page.
* Send email to a user when they register.

## Getting Started

OnIt can be accessed through the following link:
  
     http://onitp2.s3-website.us-east-2.amazonaws.com


## Usage


When you use the above link, you will be greeted with the sign-in page:
  
  
![image](https://user-images.githubusercontent.com/47725693/119705691-ab780d00-be1e-11eb-87f8-41f8438d613e.png)




### Register
  
Click 'Please register here!' to be taken to the Registration page:

  
![image](https://user-images.githubusercontent.com/47725693/119705748-bdf24680-be1e-11eb-8229-a2d1755e4d6f.png)


  
  

Enter your first and last name, and a desired email and password:

  
![image](https://user-images.githubusercontent.com/47725693/119705864-e24e2300-be1e-11eb-9c77-0d41581bcdb8.png)

  
  
Click 'Register' to complete registration.

### Sign in and Task Creation
Back on the OnIt Home page, login with your username and password.

After signing in with this fresh account, you will see your home page, which will display a grid layout of all your to-do list items:
  
  
![image](https://user-images.githubusercontent.com/47725693/119706042-16294880-be1f-11eb-9a0f-236f474e5af7.png)


 Now you can start adding tasks. Click "Create Task" to create your first task and you'll see this:
  
  
 ![image](https://user-images.githubusercontent.com/47725693/119707076-5dfc9f80-be20-11eb-9a54-92abf6ebf80d.png)

  
  
Fill in "Task name", "Task description", "Due Date", "Label", and optionally, add a location via Google Maps, mark it as repeatable, and send an email reminder a day before it's due:
  
![image](https://user-images.githubusercontent.com/47725693/119707306-a1efa480-be20-11eb-882d-b870b234530a.png)

  
![image](https://user-images.githubusercontent.com/47725693/119707348-ac11a300-be20-11eb-98d5-3211b0ea481d.png)
  

Going back to your task home page, now you'll see the newly created task:
  
![image](https://user-images.githubusercontent.com/47725693/119707518-db281480-be20-11eb-9294-142eab88fbfb.png)

You can hit done when that task is complete and the list will update:
  
![image](https://user-images.githubusercontent.com/47725693/119707615-f72bb600-be20-11eb-8f2d-742a2bbb8f82.png)

  
  
#### Viewing your Task Stats
 
Click "Task Stats" on the top bar and you will be brought to a page displaying a graph of the number of tasks completed over the past n days, where you specify the value of n via a dropdown menu. Here is a sample for a user different from the one above:
  
![image](https://user-images.githubusercontent.com/47725693/119707885-483baa00-be21-11eb-88f4-56d078c85f67.png)

  
On this same page, you can also download all your to-do list items to a file.
  
  
### Viewing your account information

Finally, you can view your account information (currently not editable), and delete your account by clicking "Account Management":
  
![image](https://user-images.githubusercontent.com/47725693/119708070-80db8380-be21-11eb-8838-cbe5cf299c5f.png)




## Contributors
Benjamin Markos (Team lead), Hassah Shallal (Scrum master), James Sasser, Kent Berry, Jacob Erdman
