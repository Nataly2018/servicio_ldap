--------------------
--  PARAMETRICAS  --
--------------------

CREATE TABLE backend.par_dominio (
     id int8 NOT NULL,
     tipo_dominio CHARACTER VARYING(50)  NOT NULL,
     codigo  CHARACTER VARYING(100) NOT NULL,
     valor   CHARACTER VARYING(100) NOT NULL,
     orden integer,
     eliminado bool NOT NULL DEFAULT false,
     fecha_registro timestamp NOT NULL DEFAULT now(),
     usuario_aplicacion varchar(100) NULL,
     CONSTRAINT pk_par_dominio PRIMARY KEY (id)
);
CREATE SEQUENCE "backend"."seq_dominio" START WITH 1 INCREMENT BY 1 NO MAXVALUE NO MINVALUE CACHE 1 NO CYCLE;
