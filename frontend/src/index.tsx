import * as React from 'react';
import { createRoot } from 'react-dom/client';
import App from './imports/ui/App';

const root = createRoot(document.querySelector('#app'));
root.render(<App />);
