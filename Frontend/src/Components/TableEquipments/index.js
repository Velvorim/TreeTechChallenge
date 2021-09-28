/* eslint-disable array-callback-return */
/* eslint-disable react-hooks/exhaustive-deps */
import React, { Fragment, useEffect, useState } from 'react';
import Title from '../Title';
import { DataGrid } from '@material-ui/data-grid';
import { deleteEquips, updateEquips } from '../../Services/clients/Equipments';



export default function TableEquipments(props) {

  const { tableData, action, setAction } = props;
  const [data, setData] = React.useState(tableData);
  const [selectedId, setSelectedId] = useState(null)

  React.useEffect(() => {
    const getData =  () => {
      setData(tableData)
    } 

    getData();

  }, [tableData])
 

  useEffect(() => {

    if (action === 'delete') {
      deleteEquips(selectedId)
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
    { field: 'serialNumber', headerName: 'Serial number', width: 200, editable: true },
    { field: 'equipName', headerName: 'Equipment name', width: 200, editable: true },
    { field: 'type', headerName: 'Type', width: 120, editable: true },
    { field: 'registrationDate', headerName: 'Registration date', width: 250, editable: true },
  ];

  const handleCellEditCommit = React.useCallback(

    ({ id, field, value }) => {
      let serialNumber;
      let equipName;
      let type;
      let registrationDate;
      let updatedRows;
      
      if (field === 'serialNumber') {
        serialNumber = parseInt(value);
       // console.log(tableData)
        updatedRows = tableData.map((row) => {
          if (row.id === id) {
            return { ...row, serialNumber };
          }
          return row;
        });
      }

      if (field === 'equipName') {
        equipName = value;

        updatedRows = tableData.map((row) => {
          if (row.id === id) {
            return { ...row, equipName };
          }
          return row;
        });
      }

      if (field === 'type') {
        type = value;

        updatedRows = tableData.map((row) => {
          if (row.id === id) {
            return { ...row, type };
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
          updateEquips(updatedRows[index])
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
    <Fragment>
      <Title>Equipamentos</Title>
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
    </Fragment>
  );
}
