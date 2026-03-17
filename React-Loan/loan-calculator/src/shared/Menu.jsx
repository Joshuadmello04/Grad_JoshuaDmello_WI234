import { Link } from "react-router-dom";

const Menu = () => {
  return (
    <div className="menu">
      <ul>
        <li><Link to="/">Home</Link></li>
        <li><Link to="/loan">Loan</Link></li>
      </ul>
    </div>
  )
}

export default Menu;