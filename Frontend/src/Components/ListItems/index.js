import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import AlarmIcon from '@material-ui/icons/Alarm';
import AlarmIconOn from '@material-ui/icons/AlarmOn';
import BusinessCenter from '@material-ui/icons/BusinessCenter';
import DashboardIcon from '@material-ui/icons/Dashboard';
import * as React from 'react';
import { ROUTES } from '../../constants/Routes';
import { Link } from "react-router-dom";


export const mainListItems = (
  <div>
    <ListItem button component={Link} to={`${ROUTES.HOME}`}>
      <ListItemIcon>
        <DashboardIcon />
      </ListItemIcon>
      <ListItemText primary="Dashboard"/>
    </ListItem>

    <ListItem button component={Link} to={`${ROUTES.EQUIPMENTS}`}>
      <ListItemIcon>
        <BusinessCenter />
      </ListItemIcon>
      <ListItemText primary="Equipments" />
    </ListItem>

    <ListItem button component={Link} to={`${ROUTES.ALARMS}`}>
      <ListItemIcon>
        <AlarmIcon />
      </ListItemIcon>
      <ListItemText primary="Alarms" />
    </ListItem>

    <ListItem button component={Link} to={`${ROUTES.ALARMS_TRIGGEREDS}`}>
      <ListItemIcon>
        <AlarmIconOn />
      </ListItemIcon>
      <ListItemText primary="Alarms Triggered" />
    </ListItem>
  </div>
);