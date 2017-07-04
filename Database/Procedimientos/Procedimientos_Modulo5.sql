DROP FUNCTION ConsultarPago(integer);
DROP FUNCTION ListaPagos(integer);
DROP FUNCTION ModificarPago(integer, float, character varying, character varying, integer );
DROP FUNCTION AgregarPago(float, character varying, character varying, integer);


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
 
BEGIN
  INSERT INTO Pago (pg_monto , pg_fecha , pg_descripcion , pg_tipotransaccion , categoriaca_id) VALUES
      (monto,CURRENT_TIMESTAMP,descripcion,transaccion,categoria);

    if found then
  result :=(Select pg_id from Pago where pg_descripcion = descripcion and pg_monto = monto and categoriaca_id = categoria  );
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