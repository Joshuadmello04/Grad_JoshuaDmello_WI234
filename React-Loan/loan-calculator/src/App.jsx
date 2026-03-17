import './App.css'
import {Route,Router,Routes} from 'react-router-dom'
import Header from './shared/Header'
import Footer from './shared/Footer'
import Menu from './shared/Menu'
import Home from './screens/Home'
import Loan from './screens/Loan'
function App() {

  return (
    <>
    <Menu/>
     <Routes>
          <Route path="/" element={<Home />} />
          <Route path='/vite' element={<h1>This is created using</h1>} />
          <Route path="/loan" element={<Loan />} />
      </Routes>
    <hr/>
    <Footer/>
    </>
  )
}

export default App
