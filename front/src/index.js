import React from 'react';
import ReactDOM from 'react-dom';
import { createBrowserHistory } from 'history';
import './index.css';
import './css/form.css';
import './css/update-form.css';
import './css/visit-add-form.css';
import './css/visit-update-form.css';
import './css/visit-search-form.css';
import './css/people-list.css';
import './css/visits-list.css';
import './css/page-header.css';
import './css/headers.css';
import './css/react-datepicker.css';
import './css/sidebar.css';
import './css/page-footer.css';
import './css/alert-box.css';
import './css/loading.css';

import { clearAlert } from './actions';
import Root from './components/Root';
import configureStore from './store/configureStore';

const store = configureStore();

const history = createBrowserHistory();
history.listen(location => store.dispatch(clearAlert()));

ReactDOM.render(
	<Root store={store} history={history} />, 
	document.getElementById('root')
);
