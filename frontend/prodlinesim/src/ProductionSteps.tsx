import { useEffect, useState } from "react"

const ProductionStep: React.FC = () => {

    interface Employee {
        id: number,
        firstName: string,
        lastName: string
    }

    const [employees, setEmployees] = useState<Employee[]>([]);
    const [newProductionStep, setNewProductionStep] = useState(false);

    useEffect(() => {
        fetch("http://localhost:8080/employee/show-all")
            .then(response => response.json())
            .then(data => {
                setEmployees(data);
            }).catch(error => console.log("Error fetching employees: ", error))
    }, [])

    const handleProductionStepClick = () => {
        setNewProductionStep(state => !state);
    }

    return (
        <div id="productionsteps">
            <h2>All Employees</h2>
            <button onClick={handleProductionStepClick} 
            className={newProductionStep ? 'clicked' : ''}>
            {newProductionStep ? 'Cancel' : 'New Employee'}
            </button>
            {/* {newProductionStep && <NewEmployee />}
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
            </table>*/}
        </div> 
    )
}

export default ProductionStep