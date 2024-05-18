import { useEffect, useState } from "react"
import "../src/styling/Employees.css"

const Employees: React.FC = () => {

    interface Employee {
        id: number,
        firstName: string,
        lastName: string
    }

    const [employees, setEmployees] = useState<Employee[]>([]);

    useEffect(() => {
        fetch("http://localhost:8080/employee/show-all")
            .then(response => response.json())
            .then(data => {
                setEmployees(data);
            }).catch(error => console.log("Error fetching employees: ", error))
    }, [])

    return (
        <div id="employees">
            <h2>All Employees</h2>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                    </tr>
                </thead>
                <tbody>
                {employees.map(employee => (<tr key={employee.id}>
                    <td>{employee.id}</td>
                    <td>{employee.firstName}</td>
                    <td>{employee.lastName}</td>
                </tr>
                ))}
                </tbody>
            </table>
        </div>
    )
}

export default Employees