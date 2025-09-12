class Student {
	fullName: string;
	constructor(public firstName: string, public middle: string, public lastName: string) {
		this.fullName = firstName + " " + middle + " " + lastName;
	}
}

interface Person {
	firstName: string;
	lastName : string;
}

function greeter(person: Person) {
    return "Hello, " + person.firstName + " " + person.lastName;
}

let user = new Student("Vishal", "V.", "Patel");

document.body.textContent = greeter(user);