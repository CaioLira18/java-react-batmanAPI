import { BrowserRouter, Routes, Route } from "react-router-dom";
import Items from "./pages/items";
import Home from "./components/Home";
import Header from "./components/Header";
import Footer from "./components/Footer";

const App = () => {
  return (
    <div>
    <Header />
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/items/:id" element={<Items />} />
    </Routes>
    <Footer />
    </div>
  );
};

const Main = () => (
  <BrowserRouter>
    <App />
  </BrowserRouter>
);

export default Main;