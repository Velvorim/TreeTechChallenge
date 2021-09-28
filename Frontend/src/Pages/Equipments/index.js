import React, { useEffect, useState } from 'react';
import Box from '@material-ui/core/Box';
import TextField from '@material-ui/core/TextField';
import MenuItem from '@material-ui/core/MenuItem';
import Stack from '@material-ui/core/Stack';
import Button from '@material-ui/core/Button';
import { withRouter } from 'react-router-dom';
import TableEquipments from '../../Components/TableEquipments';
import { Grid, Paper } from '@material-ui/core';
import { getAllEquips, saveEquips } from '../../Services/clients/Equipments';

const types = [
  {
    value: 'Tensão',
    label: 'Tensão',
  },
  {
    value: 'Corrente',
    label: 'Corrente',
  },
  {
    value: 'Óleo',
    label: 'Óleo',
  },
];

function Equipments() {

  const [type, setType] = useState('Tensão'); 
  const [serialNumber, setSerialNumber] = useState('');
  const [equipmentName, setEquipmentName] = useState('');
  const [registrationDate, setRegistrationDate] = useState('');
  const [equips, setEquips] = useState([]);
  const [action, setAction] = useState('');

  const handleChange = (event) => {
    setType(event.target.value);
  };

  useEffect(() => {
    const setEquipments = async () => {
      await getAllEquips().then(({ data }) => {
        setEquips(data)
      })
    }

    setEquipments();
  }, []);

  

  const save = (async () => {

    const dataObj = {
      serialNumber,
      equipName: equipmentName,
      type,
      registrationDate
    }

    await saveEquips(dataObj).then((response) => {
      alert("Equipamento salvo com sucesso!")
    })
   
  })

  const update = (() => {
    setAction('update')
  })

  const deleteData = (() =>{
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
        <TextField id="serial-number" label="Número de série" variant="outlined"  value={serialNumber} onChange={(event)=>setSerialNumber(event.target.value)}/>
        <TextField id="equipment-name" label="Nome do equipmamento" variant="outlined" value={equipmentName} onChange={(event)=>setEquipmentName(event.target.value)}/>
        <TextField
          id="Type"
          select
          label="Type"                      
          value={type}
          onChange={(event)=>handleChange(event)}
          helperText="Por favor selecione o tipo"
        >
          {types.map((option) => (
            <MenuItem key={option.value} value={option.value}>
              {option.label}
            </MenuItem>
          ))}
        </TextField>

        <TextField type="datetime-local" id="registration-date"  variant="outlined"  value={registrationDate} onChange={(event)=>setRegistrationDate(event.target.value)}/>

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
          <TableEquipments tableData={equips} action={action} setAction={()=>setAction()} />
        </Paper>
      </Grid>
    </>
  );
}



export default withRouter(Equipments);
