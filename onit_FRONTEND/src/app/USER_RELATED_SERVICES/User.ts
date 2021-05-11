import { Task } from "../TASK_SERVICE/Task";

export class User {

    private _id: number;
    private _firstName: string;
    private _lastName: string;
    private _email: string;
    private _password: string;
    private _goal: number;
    private _reminder: number;
    private _tasks : Task[];

    constructor(id: number, firstName: string, lastName: string, email: string, password: string,
        goal: number, reminder: number, tasks: Task[]) {
        
        this._id = id;
        this._firstName = firstName;
        this._lastName = lastName;
        this._email = email;
        this._password = password;
        this._goal = goal;
        this._tasks = tasks;

    }
    
    get username() {
        return this._email;
    }
    set username(username: string) {
        this._email = username;
    }
    
    get password() {
        return this._password;
    }
    set password(password: string) {
        this._password = password;
    }

    get tasks() {
        return this._tasks;
    }
    set tasks(tasks : Task[]) {
        this._tasks = tasks;
    }
}