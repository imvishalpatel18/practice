import { Category } from '../category/category.model';

export class WorkoutActive {
    constructor(
        public id?: string,
        public startDate?: string,
        public endData?: string,
        public startTime?: string,
        public endTime?: string,
        public comment?: string,
        public status?: boolean
    ) { }
}



export class Workout {
    constructor(
        public id?: string,
        public title?: string,
        public note?: string,
        public category?: Category
    ) { }
}

