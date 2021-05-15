
export class LocalDate {

    year: number;
    month: number;
    chronology: number;
    era: number;
    leapYear: number;
    monthValue: number;
     dayOfMonth: number;
     dayOfWeek: number;
     dayOfYear: number;

    constructor(year: number, month: number, chronology: number, era: number, leapYear: number, monthValue: number, dayOfMonth: number, dayOfWeek: number, dayOfYear: number) {
        this.year = year;
        this.month = month;
        this.chronology = chronology;
        this.era = era;
        this.leapYear = leapYear;
        this.monthValue = monthValue;
        this.dayOfMonth = dayOfMonth;
        this.dayOfWeek = dayOfWeek;
        this.dayOfYear = dayOfYear;
    }


}