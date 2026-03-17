import React, { useState } from "react";

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
    <>
      <h2>Loan Calculator</h2>
      <form onSubmit={calculateEmi} style={{ maxWidth: 400, margin: "auto" }}>
        <div style={{ marginBottom: 10 }}>
          <label>
            Applicant:{" "}
            <input
              type="text"
              value={applicant}
              onChange={(e) => setApplicant(e.target.value)}
              required
            />
          </label>
        </div>

        <div style={{ marginBottom: 10 }}>
          <label>
            Type:{" "}
            <select value={type} onChange={(e) => setType(e.target.value)}>
              <option value="home">Home Loan</option>
              <option value="car">Car Loan</option>
              <option value="personal">Personal Loan</option>
            </select>
          </label>
        </div>

        <div style={{ marginBottom: 10 }}>
          Interest: <strong>{loanConfig[type].rate}%</strong>
        </div>

        <div style={{ marginBottom: 10 }}>
          <label>
            Amount (Min ₹{loanConfig[type].minAmt.toLocaleString()}):{" "}
            <input
              type="number"
              placeholder={`Min ₹${loanConfig[type].minAmt.toLocaleString()}`}
              value={amount}
              onChange={(e) => setAmount(e.target.value)}
              required
              min={loanConfig[type].minAmt}
            />
          </label>
        </div>

        <div style={{ marginBottom: 10 }}>
          <label>
            Duration (Years, Max {loanConfig[type].maxYears}):{" "}
            <input
              type="number"
              placeholder={`Max ${loanConfig[type].maxYears}`}
              value={years}
              onChange={(e) => setYears(e.target.value)}
              required
              max={loanConfig[type].maxYears}
            />
          </label>
        </div>

        <button type="submit" style={{ padding: "8px 20px", cursor: "pointer" }}>
          Calculate EMI
        </button>
      </form>

      {emi && (
        <div style={{ marginTop: 20, textAlign: "center" }}>
          <h3>Monthly EMI: ₹ {emi}</h3>
        </div>
      )}
    </>
  );
};

export default Calculator;