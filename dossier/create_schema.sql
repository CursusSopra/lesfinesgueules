-- ///////////////////////////////////////////////////////////////////////////////////////////////////
-- DELETE EXISTING public And reCREATE public
-- ///////////////////////////////////////////////////////////////////////////////////////////////////

DROP SCHEMA public CASCADE;

CREATE SCHEMA public;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO public;
COMMENT ON SCHEMA public IS 'standard public schema';


-- ///////////////////////////////////////////////////////////////////////////////////////////////////
-- CREATE TABLE
-- ///////////////////////////////////////////////////////////////////////////////////////////////////

CREATE TABLE producteurs ( 
	id_producteur        serial  NOT NULL,
	raison_sociale       varchar(50)  NOT NULL,
	siren                varchar(50)  NOT NULL,
	ligne_adresse1       varchar(50)  NOT NULL,
	ligne_adresse2       varchar(50)  ,
	code_postal          char(5)  NOT NULL,
	ville                varchar(50)  NOT NULL,
	description          text  NOT NULL,
	delai_livraison      integer  NOT NULL,
	photo                varchar(50)  NOT NULL,
	gpslat               varchar(13)  NOT NULL,
	gpslong              varchar(13)  NOT NULL,
	CONSTRAINT pk_producteurs PRIMARY KEY ( id_producteur )
 );

CREATE TABLE types1 ( 
	id_type1             serial  NOT NULL,
	libelle1             varchar(50)  NOT NULL,
	CONSTRAINT pk_types1 PRIMARY KEY ( id_type1 )
 );

CREATE TABLE types2 ( 
	id_type2             serial  NOT NULL,
	id_type1             bigint  NOT NULL,
	libelle2             varchar(50)  NOT NULL,
	CONSTRAINT pk_types1_0 PRIMARY KEY ( id_type2 )
 );

CREATE INDEX idx_types2 ON types2 ( id_type1 );

CREATE TABLE utilisateurs ( 
	id_utilisateur       serial  NOT NULL,
	nom                  varchar(50)  NOT NULL,
	prenom               varchar(50)  NOT NULL,
	ligne_adresse1       varchar(50)  NOT NULL,
	ligne_adresse2       varchar(50)  ,
	code_postal          char(5)  NOT NULL,
	ville                varchar(50)  NOT NULL,
	email                varchar(50)  NOT NULL,
	mdp                  varchar(50)  NOT NULL,
	tel                  char(10)  NOT NULL,
	photo                varchar(50)  NOT NULL,
	droits               integer DEFAULT 0 NOT NULL,
	CONSTRAINT pk_clients PRIMARY KEY ( id_utilisateur )
 );

CREATE TABLE commandes ( 
	id_commande          serial  NOT NULL,
	id_utilisateur       bigint  NOT NULL,
	etat                 integer  NOT NULL,
	ts_validation        timestamp  ,
	ts_archivage         timestamp  ,
	moyen_paiement       integer  NOT NULL,
	CONSTRAINT idx_commandes PRIMARY KEY ( id_commande ),
	CONSTRAINT idx_commandes_0 UNIQUE ( id_utilisateur, etat, ts_validation ) 
 );

CREATE INDEX idx_commandes_1 ON commandes ( id_utilisateur );

COMMENT ON COLUMN commandes.etat IS '-1 = commande dans le panier, non validee
0 = commande validee en cours de livraison
1 = commande livree, archivee';

CREATE TABLE commentaires ( 
	id_commentaire       serial  NOT NULL,
	id_utilisateur       bigint  NOT NULL,
	avis                 text  ,
	note                 integer  NOT NULL,
	etat                 integer  NOT NULL,
	ts_creation          timestamp  ,
	CONSTRAINT pk_commentaires PRIMARY KEY ( id_commentaire )
 );

CREATE INDEX idx_commentaires ON commentaires ( id_utilisateur );

COMMENT ON COLUMN commentaires.etat IS '-1 = comm rejete
 = en attente de validation
1 = valide';

CREATE TABLE produits ( 
	id_produit           serial  NOT NULL,
	id_producteur        bigint  NOT NULL,
	id_type2             bigint  NOT NULL,
	description          text  ,
	prix                 numeric(10,2)  NOT NULL,
	designation          varchar(50)  NOT NULL,
	photo                varchar(50)  ,
	disponible           bool  NOT NULL,
	CONSTRAINT pk_produits PRIMARY KEY ( id_produit )
 );

CREATE INDEX idx_produits ON produits ( id_producteur );

CREATE INDEX idx_produits_0 ON produits ( id_type2 );

COMMENT ON COLUMN produits.prix IS 'prix unitaire';

CREATE TABLE commentaires_produits ( 
	id_commentaire       bigint  NOT NULL,
	id_produit           bigint  NOT NULL,
	CONSTRAINT pk_commentaires_produits PRIMARY KEY ( id_commentaire, id_produit )
 );

CREATE INDEX idx_commentaires_produits ON commentaires_produits ( id_commentaire );

CREATE INDEX idx_commentaires_produits_0 ON commentaires_produits ( id_produit );

CREATE TABLE items_commandes ( 
	id_item_commande     serial  NOT NULL,
	id_produit           bigint  NOT NULL,
	id_commande          bigint  NOT NULL,
	quantite             integer  NOT NULL,
	ts_creation          timestamp  ,
	CONSTRAINT pk_items_commandes PRIMARY KEY ( id_item_commande )
 );

CREATE INDEX idx_items_commandes ON items_commandes ( id_produit );

CREATE INDEX idx_items_commandes_0 ON items_commandes ( id_commande );


-- ///////////////////////////////////////////////////////////////////////////////////////////////////
-- INSERT INTO
-- ///////////////////////////////////////////////////////////////////////////////////////////////////

-- producteurs
-- INSERT INTO producteurs (raison_sociale, siren, ligne_adresse1, ligne_adresse2, code_postal, ville, description, delai_livraison, photo, gpslat, gpslong)
-- VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);

-- type1
-- INSERT INTO types1 (libelle1) VALUES (?);

-- type2
-- INSERT INTO types2 (id_type1, libelle2) VALUES (?, ?);

-- utilisateurs
-- INSERT INTO utilisateurs (nom, prenom, ligne_adresse1, ligne_adresse2, code_postal, ville, email, mdp, tel, photo, droits) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);

-- produits
-- INSERT INTO produits (id_producteur, id_type2, description, prix, designation, photo, disponible) VALUES (?, ?, ?, ?, ?, ?, ?);

-- commentaires
-- INSERT INTO commentaires (id_utilisateur, avis, note, etat) VALUES (?, ?, ?, ?);

-- commentaires_produits
-- INSERT INTO commentaires_produits (id_commentaire, id_produit) VALUES (?, ?);

-- commandes
-- INSERT INTO commandes (id_utilisateur, etat, ts_validation, ts_archivage, moyen_paiement) VALUES (?, ?, ?, ?, ?);

-- items_commandes
-- INSERT INTO items_commandes (id_produit, id_commande, quantite, ts_creation) VALUES (?, ?, ?, ?);


-- ///////////////////////////////////////////////////////////////////////////////////////////////////
-- ADD CONSTRAINT FOREIGN KEY
-- ///////////////////////////////////////////////////////////////////////////////////////////////////

ALTER TABLE commandes ADD CONSTRAINT fk_commandes_utilisateurs FOREIGN KEY ( id_utilisateur ) REFERENCES utilisateurs( id_utilisateur ) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE commentaires ADD CONSTRAINT fk_commentaires_utilisateurs FOREIGN KEY ( id_utilisateur ) REFERENCES utilisateurs( id_utilisateur ) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE commentaires_produits ADD CONSTRAINT fk_commentaires_produits_commentaires FOREIGN KEY ( id_commentaire ) REFERENCES commentaires( id_commentaire ) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE commentaires_produits ADD CONSTRAINT fk_commentaires_produits_produits FOREIGN KEY ( id_produit ) REFERENCES produits( id_produit ) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE items_commandes ADD CONSTRAINT fk_items_commandes_produits FOREIGN KEY ( id_produit ) REFERENCES produits( id_produit ) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE items_commandes ADD CONSTRAINT fk_items_commandes_commandes FOREIGN KEY ( id_commande ) REFERENCES commandes( id_commande ) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE produits ADD CONSTRAINT fk_produits_producteurs FOREIGN KEY ( id_producteur ) REFERENCES producteurs( id_producteur ) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE produits ADD CONSTRAINT fk_produits_types2 FOREIGN KEY ( id_type2 ) REFERENCES types2( id_type2 ) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE types2 ADD CONSTRAINT fk_types2_types1 FOREIGN KEY ( id_type1 ) REFERENCES types1( id_type1 ) ON DELETE CASCADE ON UPDATE CASCADE;


-- ///////////////////////////////////////////////////////////////////////////////////////////////////
-- CREATE TRIGGERS
-- ///////////////////////////////////////////////////////////////////////////////////////////////////

-- %%%%%%%%
-- ts_creation for "commentaires" and "items_commandes"
-- %%%%%%%%

CREATE OR REPLACE FUNCTION on_insert_before()
RETURNS TRIGGER AS
$BODY$
BEGIN
	new.ts_creation = now();
	RETURN new;
END;
$BODY$
LANGUAGE plpgsql VOLATILE;

-- CREATE THE TRIGGER, ASSOCIATE IT TO THE function
-- DROP TRIGGER on_insert_before ON items_commandes CASCADE;
CREATE TRIGGER on_insert_before1 BEFORE INSERT ON items_commandes FOR EACH ROW EXECUTE PROCEDURE public.on_insert_before();
CREATE TRIGGER on_insert_before2 BEFORE INSERT ON commentaires FOR EACH ROW EXECUTE PROCEDURE public.on_insert_before();

/* NOTE, ACCESSIBLE STUFF :
	INSERT : new
	UPDATE : new / old
	DELETE : old */


-- %%%%%%%%
-- ts_validation, ts_archivage for "commandes"
-- %%%%%%%%

CREATE OR REPLACE FUNCTION commandes_on_update_before()
RETURNS TRIGGER AS
$BODY$
BEGIN
	IF old.etat <> new.etat THEN
		IF new.etat = 0 THEN
			new.ts_validation = now();
		ELSIF new.etat = 1 THEN
			new.ts_archivage = now();
		END IF;
	END IF;
	RETURN new;
END;
$BODY$
LANGUAGE plpgsql VOLATILE;

CREATE TRIGGER commandes_on_update_before BEFORE UPDATE ON commandes FOR EACH ROW EXECUTE PROCEDURE public.commandes_on_update_before();


-- ///////////////////////////////////////////////////////////////////////////////////////////////////
-- CREATE VIEWS
-- ///////////////////////////////////////////////////////////////////////////////////////////////////

-- USE : "SELECT * FROM v_ispublicholiday"
-- CREATE OR REPLACE VIEW v_ispublicholiday AS (
-- 	SELECT calendarday
-- 	FROM calendar
-- 	WHERE ispublicholiday = 1
-- 	);