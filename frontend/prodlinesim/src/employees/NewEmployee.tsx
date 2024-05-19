import { useState } from "react";

const NewEmployee: React.FC = () => {
    const [firstName, setFirstName] = useState<string>('');
    const [lastName, setLastName] = useState<string>('');

    const handleFirstNameChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setFirstName(event.target.value);
    }

    const handleLastNameChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setLastName(event.target.value)
    }

    const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();

        const employeeScheme = {
            firstName: firstName,
            lastName: lastName,
        };

        fetch("http://localhost:8080/employee/new-employee", {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(employeeScheme)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error("Error adding new user");
                }
                return response.json();
            })
            .then(data => {
                console.log("User created: ", data);
            })
            .catch(error => {
                console.log("Error creating user: ", error);
            })
    }

    return (
        <form className="new-employee-label" onSubmit={handleSubmit}>
            <label>
                First Name:
                <input type="text" value={firstName} onChange={handleFirstNameChange}></input>
            </label>
            <label>
                Last Name:
                <input type="text" value={lastName} onChange={handleLastNameChange}></input>
            </label>
            <button type="submit">Submit</button>
        </form>
    )
}

export default NewEmployee;
