-- DELETE EXISTING public
DROP SCHEMA public CASCADE;
CREATE SCHEMA public;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO public;
COMMENT ON SCHEMA public IS 'standard public schema';

CREATE TABLE producteurs ( 
	id_producteur        serial  NOT NULL,
	raison_sociale       varchar(50)  NOT NULL,
	siren                integer  NOT NULL,
	ligne_adresse1       varchar(50)  NOT NULL,
	ligne_adresse2       varchar(50)  ,
	code_postal          char(5)  NOT NULL,
	ville                varchar(50)  NOT NULL,
	gps                  varchar(25)  NOT NULL,
	description          text  NOT NULL,
	delai_livraison      integer  NOT NULL,
	photo                varchar(50)  NOT NULL,
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

CREATE TABLE commentaires ( 
	id_commentaire       serial  NOT NULL,
	id_utilisateur       serial  NOT NULL,
	avis                 text  ,
	note                 integer  NOT NULL,
	etat                 integer  NOT NULL,
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
	id_utilisateur       bigint  NOT NULL,
	id_produit           bigint  NOT NULL,
	etat                 integer  NOT NULL,
	quantite             integer  NOT NULL,
	moyen_paiement       integer  NOT NULL,
	ts_creation          timestamp  ,
	ts_validation        timestamp  ,
	ts_archivage         timestamp  ,
	CONSTRAINT pk_commandes PRIMARY KEY ( id_item_commande )
 );

CREATE INDEX idx_commandes_0 ON items_commandes ( id_produit );

CREATE INDEX idx_commandes ON items_commandes ( id_utilisateur );

COMMENT ON COLUMN items_commandes.etat IS '-1 = commande dans le panier, non validee
0 = commande validee en cours de livraison
1 = commande livree, archivee';

ALTER TABLE commentaires ADD CONSTRAINT fk_commentaires_utilisateurs FOREIGN KEY ( id_utilisateur ) REFERENCES utilisateurs( id_utilisateur ) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE commentaires_produits ADD CONSTRAINT fk_commentaires_produits_commentaires FOREIGN KEY ( id_commentaire ) REFERENCES commentaires( id_commentaire ) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE commentaires_produits ADD CONSTRAINT fk_commentaires_produits_produits FOREIGN KEY ( id_produit ) REFERENCES produits( id_produit ) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE items_commandes ADD CONSTRAINT fk_commandes_produits FOREIGN KEY ( id_produit ) REFERENCES produits( id_produit ) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE items_commandes ADD CONSTRAINT fk_commandes_utilisateurs FOREIGN KEY ( id_utilisateur ) REFERENCES utilisateurs( id_utilisateur ) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE produits ADD CONSTRAINT fk_produits_producteurs FOREIGN KEY ( id_producteur ) REFERENCES producteurs( id_producteur ) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE produits ADD CONSTRAINT fk_produits_types2 FOREIGN KEY ( id_type2 ) REFERENCES types2( id_type2 ) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE types2 ADD CONSTRAINT fk_types2_types1 FOREIGN KEY ( id_type1 ) REFERENCES types1( id_type1 ) ON DELETE CASCADE ON UPDATE CASCADE;

