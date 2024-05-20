import { useEffect, useState } from "react"
import '../styling/Employees.css'
import NewEmployee from "./NewEmployee"

const Employees: React.FC = () => {

    interface Employee {
        id: number,
        firstName: string,
        lastName: string
    }

    const [employees, setEmployees] = useState<Employee[]>([]);
    const [newEmployeeField, setNewEmployeeField] = useState(false);

    const handleNewEmployeeClick = () => {
        setNewEmployeeField(state => !state);
    }

    const handleRemoveEmployee = (id: number) => {
        fetch(`http://localhost:8080/employee/delete-employee?id=${id}`, {
            method: 'DELETE',
            headers: { 'Content-Type': 'application/json' },
        })
        .then(response => {
            if (!response.ok) {
                throw new Error("Error removing user");
            }
            setEmployees(prevEmployees => prevEmployees.filter(employee => employee.id !== id));
        })
        .catch(error => console.log("Error removing user: ", error));
    }

    useEffect(() => {
        fetch("http://localhost:8080/employee/show-all")
            .then(response => response.json())
            .then(data => {
                setEmployees(data);
            }).catch(error => console.log("Error fetching employees: ", error))
    }, [handleNewEmployeeClick])

    return (
        <div id="employees">
            <h2>All Employees</h2>
            <button onClick={handleNewEmployeeClick}
                className={newEmployeeField ? 'new-employee-clicked' : ''}>
                {newEmployeeField ? 'Cancel' : 'New Employee'}
            </button>
            {newEmployeeField && <NewEmployee />}
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Station</th>
                        <th>Manage Employee</th>
                    </tr>
                </thead>
                <tbody>
                    {employees.map(employee => (<tr key={employee.id}>
                        <td>{employee.id}</td>
                        <td>{employee.firstName}</td>
                        <td>{employee.lastName}</td>
                        <td><button onClick={() => 
                            { handleRemoveEmployee(employee.id) }}>Remove
                            </button></td>
                    </tr>
                    ))}
                </tbody>
            </table>
        </div>
    )
}

export default Employees