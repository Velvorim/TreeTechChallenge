
import * as React from 'react';
import Title from '../Title';
import { DataGrid } from '@material-ui/data-grid';



export default function TableMostUsedAlarms(props) {

  const { tableData } = props;
  const [data, setData] = React.useState(tableData);

  React.useEffect(() => {
    const getData =  () => {
      setData(tableData)
    } 

    getData();

  }, [tableData])

  const columns = [
    { field: 'alarmDescription', headerName: 'Descrição do Alarme', width: 250 },
    { field: 'classification', headerName: 'Classificação', width: 200 },
    { field: 'registrationDate', headerName: 'Data De Registro', width: 250 },
    // { field: 'quantity', headerName: 'Quantidade', width: 250 }
  ];


  return (
    <React.Fragment>
      <Title>Alarmes mais disparados</Title>
      <div style={{ height: 400, width: '100%' }}>
      <DataGrid
        rows={tableData}
        columns={columns}
        pageSize={5}
        rowsPerPageOptions={[3]}
  
      />
      </div>
    </React.Fragment>
  );
}