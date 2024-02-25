import { Card, Title, Badge } from '@tremor/react';
import styled from 'styled-components';

export const StyledCard = styled(Card)`
  display: flex;
  align-items: center;
`;

export const StyledTitle = styled(Title)`
  margin-right: 5px;
`;

export const StyledBadge = styled(Badge)`
    background-color: #f2f2f2; /* Fondo claro */
    border-radius: 20%; /* Hace que el Badge sea redondo */
`;
