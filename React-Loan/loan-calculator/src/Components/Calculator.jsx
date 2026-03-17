import React, { useState } from "react";
import "./Calculator.css";

const Calculator = () => {
  const loanConfig = {
    home: { rate: 9, minAmt: 500000, maxYears: 30 },
    car: { rate: 12, minAmt: 100000, maxYears: 7 },
    personal: { rate: 15, minAmt: 10000, maxYears: 5 },
  };

  const [applicant, setApplicant] = useState("");
  const [type, setType] = useState("home");
  const [amount, setAmount] = useState("");
  const [years, setYears] = useState("");
  const [emi, setEmi] = useState(null);

  const calculateEmi = (e) => {
    e.preventDefault(); // Prevent form submission reload

    const P = parseFloat(amount);
    const loan = loanConfig[type];

    if (!applicant.trim()) {
      alert("Please enter applicant name");
      return;
    }
    if (!P || P < loan.minAmt) {
      alert(`Minimum amount for ${type} loan is ₹${loan.minAmt.toLocaleString()}`);
      return;
    }
    if (!years || years > loan.maxYears) {
      alert(`Maximum duration for ${type} loan is ${loan.maxYears} years`);
      return;
    }

    const r = loan.rate / 12 / 100;
    const n = years * 12;

    const emiValue = (P * r * Math.pow(1 + r, n)) / (Math.pow(1 + r, n) - 1);

    setEmi(emiValue.toFixed(2));
  };

return (
  <div className="container">
    <div className="card">
      <h2>💰 Loan Calculator</h2>

      <form onSubmit={calculateEmi}>
        <input
          type="text"
          placeholder="Applicant Name"
          value={applicant}
          onChange={(e) => setApplicant(e.target.value)}
        />

        <select value={type} onChange={(e) => setType(e.target.value)}>
          <option value="home">Home Loan</option>
          <option value="car">Car Loan</option>
          <option value="personal">Personal Loan</option>
        </select>

        <p className="interest">
          Interest: {loanConfig[type].rate}%
        </p>

        <input
          type="number"
          placeholder={`Amount (Min ₹${loanConfig[type].minAmt})`}
          value={amount}
          onChange={(e) => setAmount(e.target.value)}
        />

        <input
          type="number"
          placeholder={`Duration (Max ${loanConfig[type].maxYears} years)`}
          value={years}
          onChange={(e) => setYears(e.target.value)}
        />

        <button type="submit">Calculate EMI</button>
      </form>

      {emi && (
        <div className="result">
          <p>Monthly EMI</p>
          <h3>₹ {emi}</h3>
        </div>
      )}
    </div>
  </div>
);
}
export default Calculator;