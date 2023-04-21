import React from "react";
import { Outlet } from "react-router-dom";

function RouteComponent() {
  return (
    <>
      <Outlet />
    </>
  );
}
export default RouteComponent;
