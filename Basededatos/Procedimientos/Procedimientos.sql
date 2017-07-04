--- PROCEDIMIENTOS PARA EL MODULO 1:

CREATE OR REPLACE FUNCTION Registrar(usuario VARCHAR(50),nombre VARCHAR(50),apellido VARCHAR(50),correo VARCHAR(100),pregunta VARCHAR(1000),respuesta VARCHAR(1000),contrasena VARCHAR(1000)) 
    RETURNS void AS $$
    BEGIN
      INSERT INTO usuario ( u_usuario , u_nombre , u_apellido , u_correo , u_pregunta , u_respuesta , u_password ) VALUES
      (usuario,nombre,apellido,correo,pregunta,respuesta,contrasena);
    END;
    $$ LANGUAGE plpgsql;
    

CREATE OR REPLACE FUNCTION recuperarclave(usuariom VARCHAR(50)) 
    RETURNS table(id numeric(10),
        usuario varchar(50),
        password varchar(1000),
        nombre varchar(50),
        apellido varchar(50),
        correo varchar(100),
        pregunta varchar(1000),
        respuesta varchar(1000)) AS $$
        
       DECLARE 
        var record;
        begin
     
        FOR var in (SELECT u_id,u_usuario,u_password,u_nombre,u_apellido,u_correo,u_pregunta,u_respuesta 
        FROM Usuario 
        where u_usuario = usuariom)
        LOOP 
        id:= var.u_id;
        usuario := var.u_usuario;
        password := var.u_password;
        nombre := var.u_nombre;
        apellido := var.u_apellido;
        correo := var.u_correo;
        pregunta := var.u_pregunta;
        respuesta := var.u_respuesta;

        RETURN NEXT;
        END LOOP;
        end;$$
    
     LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION verificarUsuario(usuariom VARCHAR(50)) 
    RETURNS Varchar(50) AS $$
    declare
    	cursorUsuario CURSOR FOR SELECT u_usuario FROM Usuario where u_usuario = usuariom;
        nombreUsuario varchar(50);
    BEGIN
    open cursorUsuario;
         loop
    	 FETCH cursorUsuario INTO nombreUsuario;
         return nombreUsuario;
         exit when not found;
         end loop;
    close cursorUsuario;
    
    END;
    $$ LANGUAGE plpgsql;
    

   CREATE OR REPLACE FUNCTION ActualizarClave(usuariom varchar(50),contrasena varchar(1000)) 
   RETURNS varchar(50) AS $$
   BEGIN
	IF not exists (SELECT u_usuario FROM Usuario where u_usuario = usuariom) THEN
	  BEGIN
		return '0';
	  END;
	ELSE 
	  BEGIN
      		update usuario set u_password=contrasena
      		where u_usuario=usuariom;
		return '1';
	  END;
      END IF;
    END;
    $$ LANGUAGE plpgsql;
  

CREATE OR REPLACE FUNCTION iniciarSesion(usuariom VARCHAR(50),clave varchar(1000)) 
    RETURNS table(id numeric(10),
        usuario varchar(50),
        password varchar(1000),
        nombre varchar(50),
        apellido varchar(50),
        correo varchar(100),
        pregunta varchar(1000),
        respuesta varchar(1000)) AS $$
        
       DECLARE 
        var record;
        begin
     
        FOR var in (SELECT u_id,u_usuario,u_password,u_nombre,u_apellido,u_correo,u_pregunta,u_respuesta 
        FROM Usuario 
        where u_usuario = usuariom and u_password = clave)
        LOOP 
        id:= var.u_id;
        usuario := var.u_usuario;
        password := var.u_password;
        nombre := var.u_nombre;
        apellido := var.u_apellido;
        correo := var.u_correo;
        pregunta := var.u_pregunta;
        respuesta := var.u_respuesta;

        RETURN NEXT;
        END LOOP;
        end;$$
    
     LANGUAGE plpgsql;
    

--- PROCEDIMIENTOS PARA EL MODULO 2:

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
   	 OUT pg_fecha text, 
     OUT pg_descripcion varchar(255)
   ) RETURNS setof record AS
$BODY$
BEGIN

   return query SELECT to_char(pa.pg_fecha,'DD-MM-YYYY') pg_fecha, pa.pg_descripcion FROM pago pa, Categoria c
where (pa.categoriaca_id = c.ca_id and c.usuariou_id =idusuario) order by pa.pg_fecha desc LIMIT 3;

return;
END;
$BODY$
LANGUAGE 'plpgsql';


CREATE OR REPLACE FUNCTION obtenerUltimosPlanificaciones
   ( IN idusuario integer,
   	 OUT pa_fecha text, 
     OUT pa_nombre varchar(255)
   ) RETURNS setof record AS
$BODY$
BEGIN

   return query SELECT to_char(pl.pa_fechainicio,'DD-MM-YYYY') pa_fecha, pl.pa_nombre FROM planificacion pl where pl.usuariou_id = idusuario order by pl.pa_fechainicio desc LIMIT 3;

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
	(SELECT SUM(pg_monto) FROM Pago as Ingresos,Categoria c where (Ingresos.categoriaca_id = c.ca_id and c.usuariou_id =u_id and Ingresos.pg_tipotransaccion = 'ingreso')) as ingreso,
        (SELECT SUM(pg_monto) FROM Pago as Egresos, Categoria c where (Egresos.categoriaca_id = c.ca_id and c.usuariou_id =u_id and  Egresos.pg_tipotransaccion = 'egreso')) as egreso 
                FROM Usuario WHERE u_id = idusuario;

return;
END;
$BODY$
LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION obtenerUltimosPresupuestos
   ( IN idusuario integer,
   	 OUT pr_fecha text, 
     OUT pr_nombre varchar(255)
   ) RETURNS setof record AS
$BODY$
BEGIN

   return query SELECT to_char(pr.pr_fecha,'DD-MM-YYYY') pr_fecha, pr.pr_nombre FROM presupuesto pr where pr.usuariou_id = idusuario order by pr.pr_fecha desc LIMIT 3;

return;
END;
$BODY$
LANGUAGE 'plpgsql';

--- PROCEDIMIENTOS PARA EL MODULO 3:

CREATE OR REPLACE FUNCTION agregarpresupuesto(
	nombrepresupuesto character varying,
	monto real,
	clasificacion character varying,
	duracion integer,
	usuario integer,
	categoria integer)
    RETURNS integer
    LANGUAGE 'plpgsql'
    
AS $function$

DECLARE
 result integer;
 foo integer;

BEGIN
  insert into presupuesto(pr_nombre, pr_monto, pr_fecha, pr_clasificacion, pr_duracion, usuariou_id, categoriaca_id)
    values (nombrePresupuesto, monto, CURRENT_TIMESTAMP, clasificacion, duracion, usuario, categoria) returning pr_id into result;

    if found then
  foo := 1;
  else result := 0;
  end if;
  RETURN result;
END;

$function$;


CREATE OR REPLACE FUNCTION verificarnombre(
	"nombrePresupuesto" character varying)
    RETURNS record
    LANGUAGE 'sql'
AS $function$

select pr_nombre from presupuesto where pr_nombre = "nombrePresupuesto";

$function$;

CREATE OR REPLACE FUNCTION obtenerlistapresupuesto(
	idusuario integer,
	OUT pr_id integer,
	OUT pr_nombre character varying,
	OUT ca_nombre character varying,
	OUT pr_monto real,
	OUT pr_duracion integer,
	OUT pr_clasificacion character varying,
	OUT ca_esingreso boolean)
    RETURNS SETOF record 
    LANGUAGE 'sql'
    
AS $function$

SELECT p.pr_id, p.pr_nombre, c.ca_nombre,p.pr_monto,p.pr_duracion,p.pr_clasificacion,c.ca_esIngreso
FROM Presupuesto p, Categoria c
WHERE p.categoriaca_id = c.ca_id AND  p.usuariou_id = idUsuario

$function$;

CREATE OR REPLACE FUNCTION obtenerlistapresupuestodesc(
	idusuario integer,
	OUT pr_id integer,
	OUT pr_nombre character varying,
	OUT ca_nombre character varying,
	OUT pr_monto real,
	OUT pr_duracion integer,
	OUT pr_clasificacion character varying,
	OUT ca_esingreso boolean)
    RETURNS SETOF record 
    LANGUAGE 'sql'
    
AS $function$

SELECT p.pr_id, p.pr_nombre,c.ca_nombre,p.pr_monto,p.pr_duracion,p.pr_clasificacion,c.ca_esIngreso
FROM Presupuesto p, Categoria c
WHERE p.categoriaca_id = c.ca_id AND  p.usuariou_id = idUsuario
ORDER BY p.pr_clasificacion DESC

$function$;


CREATE OR REPLACE FUNCTION obtenerpresupuesto(
	idpresupuesto integer,
	OUT pr_id integer,
	OUT pr_nombre character varying,
	OUT ca_id integer,
	OUT pr_monto real,
	OUT pr_duracion integer,
	OUT pr_clasificacion character varying,
	OUT ca_esingreso boolean)
    RETURNS record
    LANGUAGE 'sql'
    
AS $function$

select p.pr_id, p.pr_nombre, c.ca_id, p.pr_monto, p.pr_duracion, p.pr_clasificacion, c.ca_esingreso from Presupuesto p, Categoria c where p.categoriaca_id = c.ca_id 
and p.pr_id = idPresupuesto

$function$;

CREATE OR REPLACE FUNCTION eliminarpresupuesto(
	idpresupuesto integer)
    RETURNS integer
    LANGUAGE 'plpgsql'
   
AS $function$

DECLARE
 result integer;

BEGIN
	DELETE FROM Presupuesto 
    WHERE pr_id = idpresupuesto;

    if found then
	result := 1;
	else result := 0;
	end if;
 	RETURN result;
END;

$function$;

CREATE OR REPLACE FUNCTION modificarPresupuesto(nombrepresupuesto character varying,
	monto real,
	clasificacion character varying,
	duracion integer,
	usuario integer,
	categoria integer,
	idPr integer)
    RETURNS integer LANGUAGE 'plpgsql'
    AS $$
DECLARE
result integer;
    
BEGIN 

UPDATE presupuesto SET 
					pr_nombre = nombrepresupuesto,
					pr_monto = monto,
                    pr_clasificacion = clasificacion,
                    pr_duracion = duracion,
                    categoriaca_id = categoria 
                    WHERE usuariou_id= usuario and pr_id = idPr;
    if found then
	result := 1;
	else result := 0;
	end if;
 	RETURN result;
END;
$$ 

--- PROCEDIMIENTOS PARA EL MODULO 4:

CREATE OR REPLACE FUNCTION AgregarCategoria(
	usuariou_i integer,
	ca_nombr character varying,
	c_descripcio character varying,
	ca_esingres boolean,
	ca_eshabilitad boolean)
    RETURNS integer
    LANGUAGE 'plpgsql'
    
AS $function$

DECLARE
 result integer;

BEGIN
  INSERT INTO categoria ( usuariou_id, ca_nombre , c_descripcion , ca_esingreso , ca_eshabilitado  ) VALUES
      (usuariou_i, ca_nombr , c_descripcio , ca_esingres , ca_eshabilitad);

    if found then
  result := (Select ca_id from Categoria where ca_nombre = ca_nombr and c_descripcion = c_descripcio and usuariou_id = usuariou_i);
  else result := 0;
  end if;
  RETURN result;
END;

$function$;


CREATE OR REPLACE FUNCTION ModificarCategoria(
	ca_nombr character varying,
	c_descripcio character varying,
	ca_esingres boolean,
	ca_eshabilitad boolean,
	ca_i integer	)
    RETURNS integer
    LANGUAGE 'plpgsql'
    AS $$
DECLARE
result integer;
    
BEGIN 

UPDATE categoria SET 
					ca_nombre = ca_nombr ,
					c_descripcion = c_descripcio ,
					ca_esingreso = ca_esingres ,
					ca_eshabilitado = ca_eshabilitad 
				    where ca_id=ca_i;
    if found then
	result := 1;
	else result := 0;
	end if;
 	RETURN result;
END;
$$ 


CREATE OR REPLACE FUNCTION ConsultarCategoria(
	idcategoria integer,
    OUT ca_id integer,
	OUT ca_nombre character varying,
	OUT c_descripcion character varying,
    OUT ca_eshabilitado boolean,
	OUT ca_esingreso boolean,
	OUT usuariou_id integer)
    RETURNS setof record
    LANGUAGE 'sql'
    
AS $function$


select c.ca_id, c.ca_nombre, c.c_descripcion, c.ca_eshabilitado , c.ca_esingreso , c.usuariou_id from Categoria c
where (c.ca_id = idcategoria)
$function$;

CREATE OR REPLACE FUNCTION ConsultarTodos(
	idusuario integer,
    OUT ca_id integer,
	OUT ca_nombre character varying,
	OUT c_descripcion character varying,
    OUT ca_eshabilitado boolean,
	OUT ca_esingreso boolean,
	OUT usuariou_id integer)
    RETURNS record
    LANGUAGE 'sql'
    
AS $function$

select c.ca_id, c.ca_nombre, c.c_descripcion, c.ca_eshabilitado , c.ca_esingreso , c.usuariou_id from Categoria c
where (c.usuariou_id = idusuario and c.ca_id <> -1)
$function$;

CREATE OR REPLACE FUNCTION EliminarCategoria(
    idCategoria integer)
RETURNS integer AS $$
DECLARE
 result integer;

BEGIN
	DELETE FROM Categoria c 
    WHERE  c.ca_id = idCategoria;

    if found then
	result := 1;
	else result := 0;
	end if;
 	RETURN result;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION EliminarCategoria2(
    idcat integer,
    tabla character varying)
    RETURNS integer
    LANGUAGE 'plpgsql'
    AS $$
DECLARE
result bool;
    
BEGIN 

UPDATE tabla SET 
					categoriaca_id = -1 
				    where categoriaca_id=idcat;
    if found then
	result := true;
	else result := false;
	end if;
 	RETURN result;
END;
$$ 

--- PROCEDIMIENTOS PARA EL MODULO 5:
CREATE OR REPLACE FUNCTION AgregarPago(
  monto float,
  descripcion character varying,
  transaccion character varying,
  categoria integer)
    RETURNS integer
    LANGUAGE 'plpgsql'
    
AS $function$

DECLARE
 result integer;
 foo integer;
 
BEGIN
  INSERT INTO Pago (pg_monto , pg_fecha , pg_descripcion , pg_tipotransaccion , categoriaca_id) VALUES
      (monto,CURRENT_TIMESTAMP,descripcion,transaccion,categoria) returning pg_id into result;

    if found then
  foo := 1;
  else result := 0;
  end if;
  RETURN result;
END;

$function$;


CREATE OR REPLACE FUNCTION ModificarPago(pag integer, monto float,
	descripcion character varying,
	transaccion character varying,
	categoria integer)
    RETURNS integer LANGUAGE 'plpgsql'
    AS $$
DECLARE
result integer;
    
BEGIN 

UPDATE PAGO SET 
					pg_monto=monto , 
					pg_descripcion=descripcion , 
					pg_tipotransaccion=transaccion , 
					categoriaca_id= categoria
				    where pg_id = pag;
    if found then
	result := 1;
	else result := 0;
	end if;
 	RETURN result;
END;
$$ 


CREATE OR REPLACE FUNCTION ConsultarPago(
	idpago integer,
    
	OUT pg_id integer,
	OUT pg_monto real,
	OUT pg_descripcion character varying,
	OUT pg_tipoTransaccion character varying,
	OUT categoriaca_id integer,
	OUT categoriaca_nombre character varying)
    RETURNS record
    LANGUAGE 'sql'
    
AS $function$

select p.pg_id, p.pg_monto, p.pg_descripcion, p.pg_tipotransaccion , p.categoriaca_id , c.ca_nombre   from Pago p , Categoria c
where ( p.pg_id = idpago and p.categoriaca_id = c.ca_id )
$function$;

CREATE OR REPLACE FUNCTION ListaPagos(
	idusuario integer,
	OUT pg_id integer,
	OUT pg_monto real,
	OUT pg_descripcion character varying,
	OUT pg_tipotransaccion character varying,
	OUT categoriaca_id integer,
	OUT categoriaca_nombre character varying)
    RETURNS SETOF record 
    LANGUAGE 'sql'
    
AS $function$

select p.pg_id, p.pg_monto, p.pg_descripcion, p.pg_tipotransaccion , p.categoriaca_id, c.ca_nombre  from Pago p , Categoria c
where (p.categoriaca_id = c.ca_id and c.usuariou_id =idusuario )

$function$;

--- PROCEDIMIENTOS PARA EL MODULO 6:


--- [Coloquen aqui sus procedimientos]
