import { BrowserRouter, Route, Routes } from "react-router-dom";
import Header from "./Header";
import Employees from "./employees/Employees";
import ProductionSteps from "./ProductionSteps";

const App: React.FC = () => {

  return (<BrowserRouter>
    <div>
      <Header />
      <Routes>
        <Route path="/productionsteps" element={<ProductionSteps />} />
        <Route path="/employees" element={<Employees />} />
      </Routes>
    </div>
  </BrowserRouter>)}

export default App;