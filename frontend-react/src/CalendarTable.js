import React, { useState, useEffect } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import PieChart from './PieChart';
import DataAdaptor from './DataAdaptor';
import moment from 'moment';
import GetStartDate from './GetStartDate';

const columns = [
  'Sunday',
  'Monday',
  'Tuesday',
  'Wednesday',
  'Thursday',
  'Friday',
  'Saturday'
];

const rows = [0, 1, 2, 3];

const useStyles = makeStyles({
  root: {
    width: '100%',
  },
});

const END_DATE = moment().subtract(1, 'days').toDate();
const START_DATE = GetStartDate();

export default function CalendarTable(props) {
  
  const [datas, setDatas] = useState({});
  const classes = useStyles();
  useEffect(() => {
      getData();
  }, []);

  const getData = async () => {
    let res = await DataAdaptor(props.type, START_DATE, END_DATE);
    setDatas(res);
  }

  return (
    <Paper className={classes.root}>
      <TableContainer>
        <Table>
          <TableHead>
            <TableRow>
              {columns.map((column, i) => (
                <TableCell
                  key={column}
                  style={{ minWidth: 170 }}
                >
                  {column}
                </TableCell>
              ))}
            </TableRow>
          </TableHead>
          <TableBody>
            {rows.map((row) => {
              return (
                <TableRow hover role="checkbox" tabIndex={-1} key={row}>
                  {columns.map((column, i) => {
                    const day = row * 7 + i;
                    const date = moment(START_DATE).add(day, 'days');
                    const string = moment(date).format('YYYY-MM-DD');
                    if (!(string in datas)) {
                      return (<TableCell key={i} />);
                    }
                    if (Object.keys(datas[string]).length === 0 && datas[string].constructor === Object) {
                      return (
                          <TableCell key={i}>
                            <div>
                              <p>{string}</p>
                              <p>No Data</p>
                            </div>
                          </TableCell>);
                    }
                    for (const label in datas[string]) {
                      if (datas[string][label] === '-1') {
                        return (
                          <TableCell key={i}>
                            <div>
                              <p>{string}</p>
                              <p>No Data</p>
                            </div>
                          </TableCell>);
                      }
                    }
                    return (
                      <TableCell key={i}>
                        <div>
                          <p>{string}</p>
                          <PieChart data={datas[string]}/>
                        </div>
                        
                      </TableCell>
                    );
                  })}
                </TableRow>
              );
            })}
          </TableBody>
        </Table>
      </TableContainer>
    </Paper>
  );
}

