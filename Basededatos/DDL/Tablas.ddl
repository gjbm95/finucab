CREATE TABLE Categoria (Ca_id SERIAL NOT NULL, Ca_nombre varchar(255) NOT NULL, C_descripcion varchar(1000) NOT NULL, Ca_eshabilitado bool NOT NULL, Ca_esingreso bool NOT NULL, UsuarioU_id int4, PRIMARY KEY (Ca_id));
CREATE TABLE Cuenta_Bancaria (Ct_id SERIAL NOT NULL, Ct_tipoCuenta varchar(255) NOT NULL, Ct_numCuenta varchar(255) NOT NULL UNIQUE, Ct_nombreBanco varchar(255) NOT NULL, Ct_saldoActual float4 NOT NULL, UsuarioU_id int4 NOT NULL, PRIMARY KEY (Ct_id));
CREATE TABLE Pago (Pg_id SERIAL NOT NULL,Pg_monto float4 NOT NULL,Pg_descripcion varchar(1000) NOT NULL, Pg_tipoTransaccion varchar(255) NOT NULL, CategoriaCa_id int4 NOT NULL, UsuarioU_id int4, PRIMARY KEY (Pg_id));
CREATE TABLE Planificacion (Pa_id SERIAL NOT NULL, Pa_nombre varchar(1000) NOT NULL, Pa_descripcion varchar(1000) NOT NULL, Pa_monto real NOT NULL, Pa_fechaInicio date NOT NULL, Pa_fechaFin date NOT NULL, Pa_recurrente boolean NOT NULL, Pa_recurrencia varchar(1000) NOT NULL, UsuarioU_id int4, categoriaca_id int4 NOT NULL, Pa_activo boolean NOT NULL, PRIMARY KEY (Pa_id));
CREATE TABLE Presupuesto (Pr_id SERIAL NOT NULL, Pr_nombre varchar(255) NOT NULL, Pr_monto float4 NOT NULL, Pr_clasificacion varchar(255) NOT NULL, Pr_duracion int4 NOT NULL, UsuarioU_id int4, CategoriaCa_id int4 NOT NULL, PRIMARY KEY (Pr_id));
CREATE TABLE Tarjeta_Credito (Tc_id SERIAL NOT NULL, Tc_tipo varchar(255) NOT NULL, Tc_fechaVencimiento date NOT NULL, Tc_numero varchar(255) NOT NULL, Tc_saldo float4 NOT NULL, UsuarioU_id int4 NOT NULL, PRIMARY KEY (Tc_id));
CREATE TABLE Usuario (U_id SERIAL NOT NULL, U_usuario varchar(50) NOT NULL UNIQUE, U_password varchar(1000) NOT NULL, U_nombre varchar(50) NOT NULL, U_apellido varchar(50) NOT NULL, U_correo varchar(100) NOT NULL, U_pregunta varchar(1000) NOT NULL, U_respuesta varchar(1000) NOT NULL, PRIMARY KEY (U_id));
ALTER TABLE Categoria ADD CONSTRAINT FKCategoria441513 FOREIGN KEY (UsuarioU_id) REFERENCES Usuario (U_id);
ALTER TABLE Presupuesto ADD CONSTRAINT crea FOREIGN KEY (CategoriaCa_id) REFERENCES Categoria (Ca_id);
ALTER TABLE Pago ADD CONSTRAINT genera FOREIGN KEY (CategoriaCa_id) REFERENCES Categoria (Ca_id);
ALTER TABLE Pago ADD CONSTRAINT usuarioPoseePosee FOREIGN KEY (UsuarioU_id) REFERENCES Usuario (U_id);
ALTER TABLE Tarjeta_Credito ADD CONSTRAINT usuarioPoseeTDC FOREIGN KEY (UsuarioU_id) REFERENCES Usuario (U_id);
ALTER TABLE Cuenta_Bancaria ADD CONSTRAINT posee FOREIGN KEY (UsuarioU_id) REFERENCES Usuario (U_id);
ALTER TABLE Presupuesto ADD CONSTRAINT realiza FOREIGN KEY (UsuarioU_id) REFERENCES Usuario (U_id);
ALTER TABLE Planificacion ADD CONSTRAINT tiene FOREIGN KEY (UsuarioU_id) REFERENCES Usuario (U_id);
ALTER TABLE Planificacion ADD CONSTRAINT de FOREIGN KEY (categoriaca_id) REFERENCES Categoria (Ca_id);

