/* eslint-disable react-hooks/exhaustive-deps */
import * as React from 'react';
import Title from '../Title';
import { DataGrid } from '@material-ui/data-grid';
import { deleteAlarms, updateAlarms } from '../../Services/clients/Alarms';



export default function TableAlarms(props) {

  const { tableData, action, setAction } = props;
  const [data, setData] = React.useState(tableData);
  const [selectedId, setSelectedId] = React.useState(null)

  React.useEffect(() => {
    const getData =  () => {
      setData(tableData)
    } 

    getData();

  }, [tableData])


  React.useEffect(() => {
    if (action === 'delete') {
      deleteAlarms(selectedId)
      .then(response => {
        alert("DELETADO COM SUCESSO")
      })
      .catch(e => alert(e))
      setAction(null)

    }

  }, [action])


  const columns = [
    { field: 'id', headerName: 'id', width: 100},
    { field: 'alarmDescription', headerName: 'Descrição do Alarme', width: 250, editable: true },
    { field: 'classification', headerName: 'Classificação', width: 200, editable: true },
    { field: 'registrationDate', headerName: 'Data De Registro', width: 250, editable: true },
  ];

  const handleCellEditCommit = React.useCallback(

    ({ id, field, value }) => {
      let alarmDescription;
      let classification;
      let registrationDate;
      let updatedRows;

      if (field === 'alarmDescription') {
        alarmDescription = value;

        updatedRows = tableData.map((row) => {
          if (row.id === id) {
            return { ...row, alarmDescription };
          }
          return row;
        });
      }

      if (field === 'classification') {
        classification = value;

        updatedRows = tableData.map((row) => {
          if (row.id === id) {
            return { ...row, classification };
          }
          return row;
        });
      }

      if (field === 'registrationDate') {
        registrationDate = value;

        updatedRows = tableData.map((row) => {
          if (row.id === id) {
            return { ...row, registrationDate };
          }
          return row;
        });
      }

      console.log("updatedRows", updatedRows);

      tableData.map((row, index) => {
        if (row.id === id) {
          updateAlarms(updatedRows[index])
            .then(response => {
              console.log("response", response);
              alert("ALTERADO COM SUCESSO")
            })
            .catch(e => alert(e))
        }
      })


    },
    [tableData],
  );

  const handleOnCellClick = (event) => {
    setSelectedId(event.row.id);
    console.log(selectedId);
  }
  
  return (
    <React.Fragment>
      <Title>Alarmes</Title> 
      <div style={{ height: 400, width: '100%' }}>
      <DataGrid 
        rows={tableData}
        columns={columns}
        pageSize={5}
        rowsPerPageOptions={[5]}
        onCellEditCommit={event => handleCellEditCommit(event)}
        onCellClick={event => handleOnCellClick(event)} 
      />
      </div>
    </React.Fragment>
  );
}
