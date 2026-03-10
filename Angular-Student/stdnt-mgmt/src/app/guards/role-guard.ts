import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { Role } from '../services/role';

export const roleGuard: CanActivateFn = (route, state) => {
  const rs = inject(Role)
  const router = inject(Router)
  if(rs.getRole()=="Guest")
  {
        router.navigate(['failure'])
        return false;   
  }
  else if(route.data[0]==rs.getRole() || route.data[1]==rs.getRole())
      return true;
  else
  {
        router.navigate(['failure'])
        return false;   
  }
};
