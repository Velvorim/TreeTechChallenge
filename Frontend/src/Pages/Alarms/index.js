import { Grid, Paper } from '@material-ui/core';
import Box from '@material-ui/core/Box';
import Button from '@material-ui/core/Button';
import Stack from '@material-ui/core/Stack';
import TextField from '@material-ui/core/TextField';
import React, { useEffect, useState } from 'react';
import { withRouter } from 'react-router-dom';
import TableAlarms from '../../Components/TableAlarms';
import { getAlarms, saveAlarms } from '../../Services/clients/Alarms';
import MenuItem from '@material-ui/core/MenuItem';

const classifications = [
  {
    value: 'alto',
    label: 'Alto',
  },
  {
    value: 'medio',
    label: 'Médio',
  },
  {
    value: 'baixo',
    label: 'Baixo',
  },
];


function Alarms() {

  const [classification, setClassifications] = useState('baixo');

  const [alarmDescription, setAlarmDescription] = useState('');
  const [registrationDate, setRegistrationDate] = useState('');
  const [associatedEquipmentId, setAssociatedEquipmentId] = useState('');
  const [alarms, setAlarms] = useState([]);
  const [action, setAction] = useState('');

  const handleChange = (event) => {
    setClassifications(event.target.value);
  };

  useEffect(() => {
    const setAlarm = async () => {
      await getAlarms().then(({ data }) => {
        setAlarms(data)

      })
    }

    setAlarm();

  }, []);

  const save = (async () => {

    const dataObj = {
      alarmDescription,
      classification,
      registrationDate,
      associatedEquipment: { id: associatedEquipmentId }
    }

    await saveAlarms(dataObj)
      .then((response) => { alert("Alarme salvo com sucesso.") })
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
          '& > :not(style)': { m: 1, width: '25ch' },
        }}
        noValidate
        autoComplete="off"
      >

        <TextField id="alarm-description" label="Descrição do alarme" variant="outlined" value={alarmDescription} onChange={(event) => setAlarmDescription(event.target.value)} />
        <TextField
          id="classification"
          select
          label="Classificação"
          value={classification}
          onChange={(event) => handleChange(event)}
          helperText="Por favor selecione o tipo"
        >
          {classifications.map((option) => (
            <MenuItem key={option.value} value={option.value}>
              {option.label}
            </MenuItem>
          ))}
        </TextField>
        <TextField type="datetime-local" id="registration-date" variant="outlined" value={registrationDate} onChange={(event) => setRegistrationDate(event.target.value)} />
        <TextField id="associatedEquipment" label=" id equipamento associado" variant="outlined" value={associatedEquipmentId} onChange={(event) => setAssociatedEquipmentId(event.target.value)} />

      </Box>

      <Grid item xs={12} style={{ padding: '15px' }}>
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
          <TableAlarms tableData={alarms} action={action} setAction={() => setAction()} />
        </Paper>
      </Grid>
    </>
  );
}



export default withRouter(Alarms);