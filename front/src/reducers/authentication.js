import * as actions from '../actions/actionTypes';
import { getToken } from '../helpers/token';

const isLoggedIn = getToken() !== null;
const initialState = { isLogging: false, isLoggedIn };

const authentication = (state = initialState, action) => {
	switch (action.type) {
		case actions.LOGIN_REQUEST:
			return {
				isLogging: true,
				isLoggedIn: false
			};
		case actions.LOGIN_SUCCESS:
			return {
				isLogging: false,
				isLoggedIn: true
			};
		case actions.LOGIN_FAILURE:
			return {
				isLogging: false,
				isLoggedIn: false
			};
		case actions.LOGOUT:
			return {
				isLogging: false,
				isLoggedIn: false
			};
		default:
			return state;
	}
};

export default authentication;