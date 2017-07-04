DROP FUNCTION actualizarclave(character varying,character varying);
DROP FUNCTION recuperarclave(character varying);
DROP FUNCTION iniciarsesion(character varying,character varying);
DROP FUNCTION registrar(character varying,character varying,character varying,character varying,character varying,character varying,character varying);
DROP FUNCTION verificarusuario(character varying);

--Area de Cuentas Bancarias:
DROP FUNCTION obtenercuentasbancarias(character varying,integer);
DROP FUNCTION agregarCuentaBancaria(character varying,character varying,character varying,float4,integer);
DROP FUNCTION modificarCuentaBancaria(character varying,character varying,character varying,float4,integer);
DROP FUNCTION eliminarCuentaBancaria(integer);

--Area de Tarjetas de Credito:
DROP FUNCTION obtenerTarjetasCredito(character varying,integer);
DROP FUNCTION agregarTarjetaCredito(character varying,date,character varying,float4,integer);
DROP FUNCTION modificarTarjetaCredito(character varying,date,character varying,float4,integer);
DROP FUNCTION eliminarTarjetasCredito(integer);

--Area de Cuentas de Usuario:
DROP FUNCTION modificarCuentaUsuario(character varying, character varying, character varying, character varying,character varying, character varying, character varying,integer);

--Area de Estadisticas:
DROP FUNCTION obtenerBalanceEconomico(integer);
DROP FUNCTION obtenertoppagos(integer);
DROP FUNCTION obtenerTOPProximosPagos(integer);
DROP FUNCTION obtenerTOPPresupuestos(integer);
DROP FUNCTION obtenerUltimosPresupuestos(integer);
DROP FUNCTION obtenerBalance(integer);
DROP FUNCTION obtenerUltimosPlanificaciones(integer);
DROP FUNCTION getSaldoTarjetas(integer);
DROP FUNCTION getSaldoCuentas(integer);
DROP FUNCTION obtenerUltimosPagos(integer);

DROP FUNCTION eliminarpresupuesto(integer);
DROP FUNCTION obtenerpresupuesto(integer);
DROP FUNCTION obtenerlistapresupuesto(integer);
DROP FUNCTION agregarpresupuesto(character varying, real, character varying, integer, integer, integer);
DROP FUNCTION verificarnombre(character varying);
DROP FUNCTION modificarpresupuesto(character varying, real, character varying, integer, integer, integer, integer);
DROP FUNCTION obtenerlistapresupuestodesc(integer);

DROP FUNCTION AgregarCategoria(integer, character varying, character varying, boolean, boolean);
DROP FUNCTION ModificarCategoria(character varying, character varying, boolean, boolean, integer);	
DROP FUNCTION ConsultarCategoria(integer);
DROP FUNCTION ConsultarTodos(integer);
DROP FUNCTION EliminarCategoria(integer);
DROP FUNCTION EliminarCategoria2(integer,character varying);

DROP FUNCTION ConsultarPago(integer);
DROP FUNCTION ListaPagos(integer);
DROP FUNCTION ModificarPago(integer, float, character varying, character varying, integer );
DROP FUNCTION AgregarPago(float, character varying, character varying, integer);
