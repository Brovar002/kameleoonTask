import React from 'react';
import ReactDOM from 'react-dom/client';
import { Route, Routes } from 'react-router';
import { BrowserRouter } from 'react-router-dom';

import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { ProfilePage } from './containers/profile/ProfilePage';
import { QuoteList } from './containers/quote-list/QuoteList';

const root = ReactDOM.createRoot(document.getElementById('root'));

root.render(
  <React.StrictMode>
    <BrowserRouter>
      <App>
          <Routes>
              <Route path='/profile' element={<ProfilePage />} />
              <Route path='/top10' element={<QuoteList />} />
              <Route path='/flop10' element={<QuoteList />} />
              <Route path='/last' element={<QuoteList />} />
          </Routes>
      </App>
    </BrowserRouter>
  </React.StrictMode>
);

reportWebVitals();
