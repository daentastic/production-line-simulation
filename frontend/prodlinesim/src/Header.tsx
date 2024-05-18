// Header.tsx
import React from 'react';
import { Link } from 'react-router-dom';
import "../src/styling/Header.css"

const Header: React.FC = () => {
  return (
    <div className='nav-list'>
      <Link to="/employees"><button>Employees</button></Link>
      <Link to ="/productionsteps"><button>Production Steps</button></Link>
    </div>
  );
};

export default Header;
