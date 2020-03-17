export class Todo {
    constructor(
        public id: number,
        public title: string,
        public description: string,
        public eventDate: Date,
        public checked: boolean
    ) { }
}
