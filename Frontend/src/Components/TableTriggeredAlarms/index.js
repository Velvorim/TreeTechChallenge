/* eslint-disable react-hooks/exhaustive-deps */
import * as React from 'react';
import Title from '../Title';
import { DataGrid } from '@material-ui/data-grid';
import { deleteTriggeredAlarm, updateTriggeredAlarm } from '../../Services/clients/TriggeredAlarm';



export default function TableTriggerdAlarms(props) {

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
      deleteTriggeredAlarm(selectedId)
        .then(response => {
          alert("DELETADO COM SUCESSO")
        })
        .catch(e => alert(e))
        setAction(null)
        setSelectedId(null)
    }

  }, [action])


  const columns = [
    { field: 'id', headerName: 'id', width: 100 },
    { field: 'entryDate', headerName: 'Data de entrada', width: 200, editable: true},
    { field: 'exitDate', headerName: 'Data De saída', width: 250, editable: true},
    { field: 'alarmStatus', headerName: 'Estado do alarme', width: 250, editable: true}
  ];

  const handleCellEditCommit = React.useCallback(

    ({ id, field, value }) => {
      let entryDate;
      let exitDate;
      let alarmStatus;
      let updatedRows;
      
      if (field === 'entryDate') {
        entryDate = value;

        updatedRows = tableData.map((row) => {
          if (row.id === id) {
            return { ...row, entryDate };
          }
          return row;
        });
      }

      if (field === 'exitDate') {
        exitDate = value;

        updatedRows = tableData.map((row) => {
          if (row.id === id) {
            return { ...row, exitDate };
          }
          return row;
        });
      }

      if (field === 'alarmStatus') {
        alarmStatus = value;

        updatedRows = tableData.map((row) => {
          if (row.id === id) {
            return { ...row, alarmStatus };
          }
          return row;
        });
      }

      console.log("updatedRows", updatedRows);

      tableData.map((row, index) => {
        if (row.id === id) {
          updateTriggeredAlarm(updatedRows[index])
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

  const handleOnCellOut = (event) => {
    setSelectedId(event.row.id);
  }

  return (
    <React.Fragment>
      <Title>Alarmes Disparados</Title>
      <div style={{ height: 400, width: '100%' }}>
        <DataGrid
          rows={tableData}
          columns={columns}
          pageSize={5}
          rowsPerPageOptions={[5]}
          onCellEditCommit={event => handleCellEditCommit(event)}  
          onCellOut={event => handleOnCellOut(event)}     
        />
      </div>
    </React.Fragment>
  );
}
