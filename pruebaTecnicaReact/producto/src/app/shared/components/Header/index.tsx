import { Tab, TabGroup, TabList } from '@tremor/react';
import { Link, useLocation } from 'react-router-dom';

export const NavBar = () => {
  const location = useLocation();
  let value;
  switch (location.pathname) {
    case '/':
      value = 0;
      break;
    case '/agregar':
      value = 1;
      break;
    default:
      value = 1;
  }

  return (
    <TabGroup className="flex justify-start mb-2" index={value}>
      <TabList variant="solid">
        <Link to="/">
          <Tab value="1">                                       
            Home
          </Tab>
        </Link>
        <Link to="/agregar">
          <Tab value="2">
            Agregar Producto
          </Tab>
        </Link>
      </TabList>
    </TabGroup>
  );
};
