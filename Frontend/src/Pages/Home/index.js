import React, { useEffect, useState } from 'react';
import { Grid, Paper } from '@material-ui/core';
import { getMostAlarms } from '../../Services/clients/Alarms';
import { withRouter } from 'react-router-dom';
import TableMostUsedAlarms from '../../Components/TableMostUsedAlarm';


function Home() {
	const [alarms, setAlarms] = useState([]);

	useEffect(() => {
		const setMostAlarmsTriggered = async () => {
			await getMostAlarms().then(({ data }) => {
				setAlarms(data)
			})
		}

		setMostAlarmsTriggered();
	}, []);

	return (
		<Grid item xs={12}>
			<Paper sx={{ p: 2, display: 'flex', flexDirection: 'column' }}>
				<TableMostUsedAlarms tableData={alarms} />
			</Paper>
		</Grid>
	);

}

export default withRouter(Home);