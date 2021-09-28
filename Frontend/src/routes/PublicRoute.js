import React from 'react';
import { Route } from 'react-router-dom';
import Dashboard from '../Pages/Dashboard'

const PublicRoute = ({ component, ...rest }) => {
    return (
        <Dashboard>
            <Route {...rest} render={component} />
        </Dashboard>
    )

}

export default PublicRoute;