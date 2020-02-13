import { getVisibleSchedulerVisits } from '../reducers';

export const getVisitsForScheduler = state => {
	const visitsArray = getVisibleSchedulerVisits(state);
	const visits = visitsArray.reduce((acc, visit) => {
		acc[`${visit.visitDate} ${visit.visitTime}`] = visit;
		return acc;
	}, {});
	return visits;
};