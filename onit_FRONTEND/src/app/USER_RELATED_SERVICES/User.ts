import { LocalDate } from "../DATES/LocalDate";
import { Task } from "../TASK_SERVICE/Task";

export class User {

    private _id: string;
    private _firstName: string;
    private _lastName: string;
    private _email: string;
    private _password: string;
    private _goal: number;
    private _receiveEmailReminders: number;
    private _accountCreated: LocalDate;
    private _sessionToken: string;

    constructor(id: string, firstName: string, lastName: string, email: string, password: string,
        goal: number, receiveEmailReminders: number, accountCreated: LocalDate, sessionToken: string) {
        
        
        this._firstName = firstName;
        this._lastName = lastName;
        this._email = email;
        this._password = password;
        this._id = id;
        
        this._goal = goal;
        this._accountCreated = accountCreated;

        this._receiveEmailReminders = receiveEmailReminders;

        this._sessionToken = sessionToken;
    }
    
    get email() {
        return this._email;
    }
    set email(email: string) {
        this._email = email;
    }
    
    get password() {
        return this._password;
    }
    set password(password: string) {
        this._password = password;
    }

    get firstName() {
        return this._firstName;
    }
    set firstName(firstName: string) {
        this._firstName = firstName;
    }

    get lastName() {
        return this._lastName;
    }
    set lastName(lastName: string) {
        this.lastName = lastName;
    }

    get accountCreated() {
        return this._accountCreated;
    }
    set accountCreated(accountCreated: LocalDate) {
        this._accountCreated = accountCreated;
    }

    get receiveEmailReminders() {
        return this._receiveEmailReminders;
    }
    set receiveEmailReminders(receiveEmailReminders: number) {
        this._receiveEmailReminders = receiveEmailReminders;
    }

    get id() {
        return this._id;
    }
    set id(id: string) {
        this._id = id;
    }

    get sessionToken() {
        return this._sessionToken;
    }
    set sessionToken(sessionToken: string) {
        this._sessionToken = sessionToken;
    }
    
    get goal() {
        return this._goal;
    }
}

