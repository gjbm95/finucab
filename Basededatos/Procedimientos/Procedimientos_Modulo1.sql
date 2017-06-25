DROP FUNCTION actualizarclave(character varying,character varying);
DROP FUNCTION recuperarclave(character varying);
DROP FUNCTION iniciarsesion(character varying,character varying);
DROP FUNCTION registrar(character varying,character varying,character varying,character varying,character varying,character varying,character varying);
DROP FUNCTION verificarusuario(character varying);



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
    
















