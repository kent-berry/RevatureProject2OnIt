
export class Task {

    private _id : number;
    private _userId : number;
    private _taskName : string;
    private _Notes : string;
    private _dueDate : Date;
    private _label : string;
    private _dateCreated : Date;
    private _dateCompleted : Date;
    private _reminder : number;
    private _repeatable : boolean;

    constructor(id : number,  userId : number,  taskName : string,  Notes : string,  dueDate : Date,  label : string,  dateCreated : Date,  dateCompleted : Date,
                reminder : number, repeatable : boolean) {
                    this._id = id;
                    this._userId = userId;
                    this._taskName = taskName;
                    this._Notes = Notes;
                    this._dueDate = dueDate;
                    this._label = label;
                    this._dateCreated = dateCreated;
                    this._dateCompleted = dateCompleted;
                    this._reminder = reminder;
                    this._repeatable = repeatable;        
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
    get Notes() {
        return this._Notes;
    }
    get dueDate() {
        return this._dueDate;
    }
    get label() {
        return this._label;
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

    set id(id : number) {
        this._id = id;;
    }
    set userId(userId : number) {
        this._userId = userId;
    }
    set taskName(taskName : string) {
        this._taskName = taskName;
    }
    set Notes(Notes : string) {
        this._Notes = Notes;
    }
    set dueDate(dueDate : Date) {
        this._dueDate = dueDate;
    }
    set label(label : string) {
        this._label = label;
    }
    set dateCreated(dateCreated : Date) {
        this._dateCreated = dateCreated;
    }
    set dateCompleted(dateCompleted : Date) {
        this._dateCompleted = dateCompleted;
    }
    set reminder(reminder : number) {
        this._reminder = reminder;
    }
    set repeatable(repeatable : boolean) {
        this._repeatable = repeatable;
    }

}