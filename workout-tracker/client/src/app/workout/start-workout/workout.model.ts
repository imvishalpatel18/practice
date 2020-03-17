import { Category } from '../category/category.model';

export class Workout {
    constructor(
        public id?: string,
        public title?: string,
        public note?: string,
        public caloriesBurnPerMin?: number,
        public category?: Category
    ) { }
}
