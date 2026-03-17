import { Link, useLocation } from "react-router-dom";
import "./Menu.css";

const Menu = () => {
  const location = useLocation();

  const isActive = (path) => location.pathname === path ? "active" : "";

  return (
    <nav className="menu">
      <div className="menu-container">
        <h1 className="logo">💰 LoanApp</h1>

        <ul className="menu-list">
          <li>
            <Link to="/" className={isActive("/")}>Home</Link>
          </li>
          <li>
            <Link to="/loan" className={isActive("/loan")}>Loan</Link>
          </li>
        </ul>
      </div>
    </nav>
  );
};

export default Menu;