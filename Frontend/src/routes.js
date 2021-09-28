import React from 'react';
import { Switch, BrowserRouter} from 'react-router-dom';

import Equipments from './Pages/Equipments';
import Home from './Pages/Home';
import PublicRoute from './routes/PublicRoute';
import { ROUTES } from './constants/Routes'
import Alarms from './Pages/Alarms';
import TriggeredAlarms from './Pages/TriggeredAlarms';

const Routes = () => {
    return (
        <BrowserRouter>
          <Switch>
            <PublicRoute
              component={Home}
              path={ROUTES.HOME}
              exact
            />
            
            <PublicRoute
              component={Equipments}
              path={ROUTES.EQUIPMENTS}
              exact
            />

            <PublicRoute
              component={TriggeredAlarms}
              path={ROUTES.ALARMS_TRIGGEREDS}
              exact
            />

             <PublicRoute
              component={Alarms}
              path={ROUTES.ALARMS}
              exact
            />
            

          </Switch>
        </BrowserRouter>
    )
}

export default Routes;