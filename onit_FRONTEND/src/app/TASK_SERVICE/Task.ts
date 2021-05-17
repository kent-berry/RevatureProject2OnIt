import { LocalDate } from "../DATES/LocalDate";

export class Task {

    private _id : string;
    private _userId : string;
    private _taskName : string;
    private _notes : string;

    private _dateCreated : LocalDate;
    private _dueDate : LocalDate;
    private _dateCompleted : LocalDate;

    private _reminder : number;
    private _repeatable : boolean;

    private _taskLabel_fk : string;

    private _latitude: number;
    private _longitude: number;
    
    
    

    constructor(id : string,  userId : string,  taskName : string,  Notes : string,  dueDate : LocalDate,  label : string,  dateCreated : LocalDate,  dateCompleted : LocalDate,
                reminder : number, repeatable : boolean, latitude : number, longitude: number) {
                    this._id = id;
                    this._userId = userId;
                    this._taskName = taskName;
                    this._notes = Notes;
                    this._dueDate = dueDate;
                    this._taskLabel_fk = label;
                    this._dateCreated = dateCreated;
                    this._dateCompleted = dateCompleted;
                    this._reminder = reminder;
                    this._repeatable = repeatable;        
                    this._latitude = latitude;
                    this._longitude = longitude;
    }

    get id() {
        return this._id;
    }
    get userId() {
        return this._userId;
    }
    get taskName() {
        return this._taskName;
    }
    get notes() {
        return this._notes;
    }
    get dueDate() {
        return this._dueDate;
    }
    get taskLabel_fk() {
        return this._taskLabel_fk;
    }
    get dateCreated() {
        return this._dateCreated;
    }
    get dateCompleted() {
        return this._dateCompleted;
    }
    get reminder() {
        return this._reminder;
    }
    get repeatable() {
        return this._repeatable;
    }

    set id(id : string) {
        this._id = id;;
    }
    set userId(userId : string) {
        this._userId = userId;
    }
    set taskName(taskName : string) {
        this._taskName = taskName;
    }
    set notes(Notes : string) {
        this._notes = Notes;
    }
    set dueDate(dueDate : LocalDate) {
        this._dueDate = dueDate;
    }
    set taskLabel_fk(taskLabel_fk : string) {
        this._taskLabel_fk = taskLabel_fk;
    }
    set dateCreated(dateCreated : LocalDate) {
        this._dateCreated = dateCreated;
    }
    set dateCompleted(dateCompleted : LocalDate) {
        this._dateCompleted = dateCompleted;
    }
    set reminder(reminder : number) {
        this._reminder = reminder;
    }
    set repeatable(repeatable : boolean) {
        this._repeatable = repeatable;
    }

    get latitude() {
        return this._latitude;
    }
    get longitude() {
        return this._longitude;
    }

    set latitude(latitude : number) {
        this._latitude = latitude;
    }
    set longitude(longitude : number) {
        this._longitude = longitude;
    }

}