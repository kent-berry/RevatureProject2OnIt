# OnIt



1) The following interfaces and classes are laid out:

//Models: field are added according to the google doc project plan
User
Task
Label

//Controllers: almost all methods are implemented except: viewProgress, viewPastProgressGraph
IUserController
UserController

//Services: almost all methods are implemented except: viewProgress, viewPastProgressGraph
IUserService
UserService

//dao: no implemeneted methods, passed to Jacob & Kent
IUserDao
UserDao
ITaskDao
TaskDao

Notes about this commit:
1) int receiveEmailReminders is added to the User POJO
2) interfaces start with capital I, please keep this naming convention
3) please use the above classes/interfaces only, other configuration files are not completed.
