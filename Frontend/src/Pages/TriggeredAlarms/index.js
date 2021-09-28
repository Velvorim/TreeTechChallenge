import { Grid, Paper } from '@material-ui/core';
import Box from '@material-ui/core/Box';
import Button from '@material-ui/core/Button';
import Stack from '@material-ui/core/Stack';
import TextField from '@material-ui/core/TextField';
import React, { useEffect, useState } from 'react';
import { withRouter } from 'react-router-dom';
import TableTriggerdAlarms from '../../Components/TableTriggeredAlarms';
import { getAllTriggeredAlarm, saveTriggeredAlarm } from '../../Services/clients/TriggeredAlarm';
import MenuItem from '@material-ui/core/MenuItem';

const status = [
  {
    value: false,
    label: 'Desativado',
  },
  {
    value: true,
    label: 'Ativado',
  },
];

function TriggeredAlarms() {

  const [alarmStatus, setAlarmStatus] = useState(false);


  const [entryDate, setEntryDate] = useState('');
  const [exitDate, setExitDate] = useState('');
  const [alarmTriggeredIds, setalarmTriggeredIds] = useState('');
  const [equipmentTriggeredIds, setEquipmentTriggeredIds] = useState([]);
  const [triggeredAlarms, setTriggeredAlarms] = useState([]);
  const [action, setAction] = useState('');

  const handleChange = (event) => {
    setAlarmStatus(event.target.value);
  };


  useEffect(() => {
    const setTriggersAlarm = async () => {
      await getAllTriggeredAlarm().then(({ data }) => {
        setTriggeredAlarms(data)
      })
    }

    setTriggersAlarm();
  }, []);



  const save = (async () => {

    const dataObj = {
      entryDate,
      exitDate,
      alarmStatus,
      alarmTriggeredId: {id: alarmTriggeredIds},
      equipmentTriggered: {id: equipmentTriggeredIds}
    }

    await saveTriggeredAlarm(dataObj)
    .then((response) => {alert("Alarme disparado salvo com sucesso")})
    .catch(e => console.log("e", e))

  })

  const update = (() => {
    setAction('update')
  })

  const deleteData = (() => {
    setAction('delete')
  })

  return (
    <>
      <Box
        component="form"
        sx={{
          '& > :not(style)': { m: 1, width: '24ch' },
        }}
        noValidate
        autoComplete="off"
      >

        <TextField id="entryDate" type="datetime-local" variant="outlined" value={entryDate} onChange={(event) => setEntryDate(event.target.value)} />
        <TextField id="exitDate" type="datetime-local" variant="outlined" value={exitDate} onChange={(event) => setExitDate(event.target.value)} />
        <TextField
          id="alarmStatus"
          select
          label="Estado do alarme"                      
          value={alarmStatus}
          onChange={(event)=>handleChange(event)}
          helperText="Por favor selecione o tipo"
        >
          {status.map((option) => (
            <MenuItem key={option.value} value={option.value}>
              {option.label}
            </MenuItem>
          ))}
        </TextField>
        <TextField id="alarmTriggeredIds" label="Id do alarme" variant="outlined" value={alarmTriggeredIds} onChange={(event) => setalarmTriggeredIds(event.target.value)} />
        <TextField id="equipmentTriggeredIds" label="Id do equipamento" variant="outlined" value={equipmentTriggeredIds} onChange={(event) => setEquipmentTriggeredIds(event.target.value)} />
        
      </Box>

      <Grid item xs={12} style={{padding: '15px'}}>
        <Stack direction="row" spacing={2}>
          <Button type="submit" variant="contained" color="success" onClick={() => save()} >
            Enviar
          </Button>
          <Button type="submit" variant="contained" color="error" onClick={() => deleteData()} >
            Deletar
          </Button>
        </Stack>
      </Grid>

      <Grid item xs={12}>
        <Paper sx={{ p: 2, display: 'flex', flexDirection: 'column' }}>
          <TableTriggerdAlarms tableData={triggeredAlarms} action={action} setAction={() => setAction()} />
        </Paper>
      </Grid>
    </>
  );
}



export default withRouter(TriggeredAlarms);