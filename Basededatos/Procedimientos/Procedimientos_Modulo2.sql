-- Modulo 2  - Procedimientos Almacenados: 


-------------------------------------------DROPS DE FUNCIONES-------------------------------------------
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


-------------------------------------GESTION DE CUENTAS BANCARIAS --------------------------------------
CREATE OR REPLACE FUNCTION agregarCuentaBancaria
 ( IN nombrebanco VARCHAR(255), tipocuenta VARCHAR(255), numerocuenta VARCHAR(255), saldo float4, usuarioid int)
 RETURNS integer AS
$BODY$
begin

 INSERT INTO Cuenta_Bancaria (ct_nombrebanco,ct_numcuenta,ct_tipocuenta,ct_saldoactual,usuariou_id) 
  VALUES (nombrebanco,numerocuenta,tipocuenta,saldo,usuarioid);
  
return 1;
end;
$BODY$
LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION modificarCuentaBancaria(varchar(255), varchar(255), varchar(255),float4,int) RETURNS integer AS $$
DECLARE
 result integer;

BEGIN
	UPDATE Cuenta_Bancaria SET ct_nombrebanco = $1, ct_numcuenta = $2, ct_tipocuenta = $3,ct_saldoactual = $4
    WHERE  ct_id = $5;


	result := 1;
 	RETURN result;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION eliminarCuentaBancaria(int) RETURNS integer AS $$
DECLARE
 result integer;

BEGIN
	DELETE FROM Cuenta_Bancaria 
    WHERE  ct_id = $1;

	result := 1;
 	RETURN result;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION obtenerCuentasBancarias
   ( IN PV_OPCION VARCHAR(50), filtrousuario integer,
   	 OUT ct_id integer, 
     OUT ct_tipocuenta varchar(255), 
     OUT ct_numcuenta varchar(255), 
     OUT ct_nombrebanco varchar(255), 
     OUT ct_saldo float4,
     OUT uusuariou_id integer
   ) RETURNS setof record AS
$BODY$
BEGIN
 IF PV_OPCION = 'OBTENER' THEN
   return query SELECT * FROM Cuenta_Bancaria;
 END IF;  
 IF PV_OPCION = 'OBTENERCUENTASUSUARIO' THEN
   return query SELECT * FROM Cuenta_Bancaria WHERE usuariou_id = filtrousuario;
 END IF;  
return;
END;
$BODY$
LANGUAGE 'plpgsql';


-------------------------------------GESTION DE TARJETAS DE CREDITO --------------------------------------
CREATE OR REPLACE FUNCTION agregarTarjetaCredito
 ( IN tipotarjeta VARCHAR(255), fechavencimiento date, numero VARCHAR(255), saldo float4, usuarioid int)
 RETURNS integer AS
$BODY$
begin

 INSERT INTO Tarjeta_Credito (tc_tipo,tc_fechavencimiento,tc_numero,tc_saldo,usuariou_id) 
  VALUES (tipotarjeta,fechavencimiento,numero,saldo,usuarioid);
  
return 1;
end;
$BODY$
LANGUAGE 'plpgsql';


CREATE OR REPLACE FUNCTION modificarTarjetaCredito(varchar(255), date, varchar(255),float4,int) RETURNS integer AS $$
DECLARE
 result integer;

BEGIN
	UPDATE Tarjeta_Credito SET tc_tipo = $1, tc_fechavencimiento = $2, tc_numero = $3, tc_saldo = $4
    WHERE  tc_id = $5;


	result := 1;
 	RETURN result;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION eliminarTarjetasCredito(int) RETURNS integer AS $$
DECLARE
 result integer;

BEGIN
	DELETE FROM Tarjeta_Credito 
    WHERE  tc_id = $1;

	result := 1;
 	RETURN result;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION obtenerTarjetasCredito
   ( IN PV_OPCION VARCHAR(10) default 'OBTENER', filtrousuario integer default 0,
   	 OUT tc_id integer, 
     OUT tc_tipotarjeta varchar(255), 
     OUT tc_fechavencimiento date, 
     OUT tc_numero varchar(255), 
     OUT tc_saldo float4,
     OUT uusuariou_id integer
   ) RETURNS setof record AS
$BODY$
BEGIN
 IF PV_OPCION = 'OBTENER' THEN
   return query SELECT * FROM Tarjeta_Credito;
 END IF;  
 IF PV_OPCION = 'OBTENERTARJETASSUSUARIO' THEN
   return query SELECT * FROM Tarjeta_Credito WHERE usuariou_id = filtrousuario;
 END IF;  
return;
END;
$BODY$
LANGUAGE 'plpgsql';

------------------------------------- GESTION DE USUARIOS  --------------------------------------
CREATE OR REPLACE FUNCTION update_usuario(id INT,usuario VARCHAR(50),nombre VARCHAR(50),apellido VARCHAR(50),correo VARCHAR(100),pregunta VARCHAR(1000),respuesta VARCHAR(1000),contrasena VARCHAR(1000)) 
    RETURNS void AS $$
    BEGIN
      UPDATE usuario SET
	u_usuario = $2,
	u_nombre = nombre,
	u_apellido = apellido,
	u_correo = correo,
	u_pregunta = pregunta,
	u_respuesta = respuesta
      WHERE u_id = $1;	
      
    END;
    $$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION modificarCuentaUsuario(varchar(255), varchar(255), varchar(255), varchar(255),varchar(255), varchar(255), varchar(255),int) RETURNS integer AS $$
DECLARE
 result integer;

BEGIN
	UPDATE Usuario SET u_usuario = $1, u_password = $2, u_nombre = $3, u_apellido = $4, u_correo = $5, u_pregunta = $6, u_respuesta = $7
    WHERE  ct_id = $8;


	result := 1;
 	RETURN result;
END;
$$ LANGUAGE plpgsql;

------------------------------------- GESTION DE ESTADISTICAS  --------------------------------------

CREATE OR REPLACE FUNCTION obtenerBalanceEconomico
   ( IN filtrousuario integer,
   	 OUT ingresos float4, 
     OUT egresos float4
   ) RETURNS setof record AS
$BODY$
BEGIN
 
   return query SELECT (SELECT SUM(pg_monto) FROM Pago as Ingresos WHERE Ingresos.usuariou_id =u_id AND Ingresos.pg_tipotransaccion = 'Ingreso') as ingresos,
                       (SELECT SUM(pg_monto) FROM Pago as Egresos WHERE Egresos.usuariou_id =u_id AND Egresos.pg_tipotransaccion = 'Gasto') as egresos 
                FROM Usuario WHERE u_id = filtrousuario;
 
return;
END;
$BODY$
LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION obtenerTOPPagos
   ( IN filtrousuario integer,
   	 OUT fecha date, 
     OUT descripcion VARCHAR(1000)
   ) RETURNS setof record AS
$BODY$
BEGIN
 
   return query SELECT MAX(Pg_fecha), Pg_descripcion FROM Pago 
                WHERE usuariou_id = filtrousuario 
                GROUP BY Pg_descripcion LIMIT 3;
 
return;
END;
$BODY$
LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION obtenerTOPProximosPagos
   ( IN filtrousuario integer,
   	 OUT fecha date, 
     OUT nombre VARCHAR(1000)
   ) RETURNS setof record AS
$BODY$
BEGIN
 
   return query SELECT MAX(Pa_fechainicio), Pa_nombre FROM Planificacion 
                WHERE usuariou_id = filtrousuario 
                GROUP BY Pa_nombre LIMIT 3;
 
return;
END;
$BODY$
LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION obtenerTOPPresupuestos
   ( IN filtrousuario integer,
   	 OUT fecha date, 
     OUT nombre VARCHAR(1000)
   ) RETURNS setof record AS
$BODY$
BEGIN
 
   return query SELECT MAX(Pr_fecha), Pr_nombre FROM Presupuesto 
                WHERE usuariou_id = filtrousuario 
                GROUP BY Pr_nombre  LIMIT 3;
 
return;
END;
$BODY$
LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION getSaldoCuentas( IN idusuario int)
 RETURNS int AS $$
 DECLARE  total int ;
begin

  SELECT sum(ct_saldoactual) INTO total FROM cuenta_bancaria WHERE usuariou_id = idusuario;
 return total;
end;
$$ LANGUAGE 'plpgsql';




CREATE OR REPLACE FUNCTION getSaldoTarjetas( IN idusuario int)
 RETURNS int AS $$
 DECLARE  total int ;
begin

  SELECT sum(tc_saldo) INTO total FROM tarjeta_credito WHERE usuariou_id = idusuario;
 return total;
end;
$$ LANGUAGE 'plpgsql';



CREATE OR REPLACE FUNCTION obtenerUltimosPagos
   ( IN idusuario integer,
   	 OUT pg_fecha varchar(255), 
     OUT pg_descripcion varchar(255)
   ) RETURNS setof record AS
$BODY$
BEGIN

   return query SELECT to_char(pg_fecha,'DD-MM-YYYY') pg_fecha, pg_descripcion FROM pago where usuariou_ui = idusuario order by pg_fecha desc LIMIT 3;

return;
END;
$BODY$
LANGUAGE 'plpgsql';


CREATE OR REPLACE FUNCTION obtenerUltimosPlanificaciones
   ( IN idusuario integer,
   	 OUT pa_fecha varchar(255), 
     OUT pa_nombre varchar(255)
   ) RETURNS setof record AS
$BODY$
BEGIN

   return query SELECT to_char(pa_fechainicio,'DD-MM-YYYY') pa_fecha, pa_nombre FROM planificacion where usuariou_ui = idusuario order by pa_fechainicio desc LIMIT 3;

return;
END;
$BODY$
LANGUAGE 'plpgsql';



CREATE OR REPLACE FUNCTION obtenerBalance
   ( IN idusuario integer,
   	 OUT ingreso float4, 
     OUT egreso float4
   ) RETURNS setof record AS
$BODY$
BEGIN

   return query SELECT 
	(SELECT SUM(pg_monto) FROM Pago as Ingresos WHERE Ingresos.usuariou_id =u_id AND Ingresos.pg_tipotransaccion = 'ingreso') as ingreso,
        (SELECT SUM(pg_monto) FROM Pago as Egresos WHERE Egresos.usuariou_id =u_id AND Egresos.pg_tipotransaccion = 'egreso') as egreso 
                FROM Usuario WHERE u_id = idusuario;

return;
END;
$BODY$
LANGUAGE 'plpgsql';





CREATE OR REPLACE FUNCTION obtenerUltimosPresupuestos
   ( IN idusuario integer,
   	 OUT pr_fecha varchar(255), 
     OUT pr_nombre varchar(255)
   ) RETURNS setof record AS
$BODY$
BEGIN

   return query SELECT to_char(pr_fechainicio,'DD-MM-YYYY') pr_fecha, pr_nombre FROM presupuesto where usuariou_ui = idusuario order by pr_fecha desc LIMIT 3;

return;
END;
$BODY$
LANGUAGE 'plpgsql';

