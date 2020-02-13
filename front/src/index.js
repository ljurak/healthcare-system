import React from 'react';
import ReactDOM from 'react-dom';
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
import './css/scheduler.css';

import { clearAlert, clearVisits } from './actions';
import { history } from './helpers/history';
import configureStore from './helpers/configureStore';
import Root from './components/Root';

const store = configureStore();

history.listen(location => {
	store.dispatch(clearAlert());
	store.dispatch(clearVisits());
});

ReactDOM.render(
	<Root store={store} history={history} />, 
	document.getElementById('root')
);
