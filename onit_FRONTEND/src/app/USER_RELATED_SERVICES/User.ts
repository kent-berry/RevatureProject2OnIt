import { Task } from "../TASK_SERVICE/Task";

export class User {

    private _username: string;
    private _password: string;
    private _tasks : Task[];

    constructor(username: string, password: string) {
        this._username = username;
        this._password = password;

    }
    /*
    constructor(username: string, password: string, tasks : Task[]) {
        this._username = username;
        this._password = password;
        this._tasks = tasks;
    }
*/
    get username() {
        return this._username;
    }
    set username(username: string) {
        this._username = username;
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