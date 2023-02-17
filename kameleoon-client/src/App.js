import './App.css';

import { Header } from './components/header/Header';

function App( { children } ) {
  return (
    <div className="wrapper">
      <Header />
      <main>
        { children }
      </main>
    </div>
  );
}

export default App;
