-- @author: Julien Caillon

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
	photo                varchar(50)  ,
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
	photo                varchar(50)  ,
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
-- INSERT INTO (premiere partie)
-- ///////////////////////////////////////////////////////////////////////////////////////////////////

-- producteurs
INSERT INTO producteurs (raison_sociale, siren, ligne_adresse1, ligne_adresse2, code_postal, ville, description, delai_livraison, photo, gpslat, gpslong) VALUES 
('Les terres d''auvergne', 111111111, 'Croix de la Combe', '', '63610', 'Bresse-et-saint-anastaise', 
	'Notre passion pour l’affinage est toujours présente depuis 3 générations maintenant, et elle se traduit par un savoir-faire dans la façon de traiter le fromage. <br><br>Nous récupérons le fromage frais directement chez nos producteurs de la région, qui nourrissent leurs bêtes avec des matières premières au maximim issues de leur propre exploitation. <br><br>L’esprit d’équipe que nous, les maîtres affineurs avons avec nos producteurs, se traduit par de nombreuses récompenses lors de concours.<br><br>Notre société a su adopter une technologie innovante sans renoncer aux spécificités des produits de terroir, nos caves sont d’ailleurs enterrées et voutées pour un ensemencement naturel, donnant ainsi au fromage l’unicité à son goût, sa couleur et sa texture.',
	5, 'Les-Terres-dAuvergne.jpg', '45.512924', '2.942802'),
('Earl Goyard', 111111111, 'Logeat', '', '18370', 'Saint-Priest-la-Marche', 
	'Nous sommes dans le fromage depuis 1993, ce qui nous permet d’utiliser notre savoir-faire dans tout le processus de fabrication de nos produits.',
	2, 'EARL-Goyard.jpg', '46.457189', '2.163977'),
('La bergerie de la prade', 111111111, 'La Vaud', '', '23500', 'La Nouaille', 
	'Cela fait maintenant plus de 20 ans que je fabrique Le Nouaille, un fromage de Brebis au saveur exceptionnelle dont le savoir-faire est primordial',
	3, 'La-Bergerie-de-la-Prade.jpg', '45.840173', '2.079941'),
('La fromagerie Deret', 111111111, 'Le Bourg', '', '03350', 'Theneuille', 
	'C’est au début des années 50, que mon père coquetier Max DERET lance l’aventure de notre fromagerie, dont une partie du succès provient du beurre de baratte',
	1, 'Fromagerie-Deret.jpg', '46.533833', '2.838274'),
('L''atelier de viand''art', 111111111, '9 Rue Alfred Grand', '', '23000', 'Guéret', 
	'C’est au début des années 50, que mon père coquetier Max DERET lance l’aventure de notre fromagerie, dont une partie du succès provient du beurre de baratte',
	2, 'Boucherie-Dubois.jpg', '46.533833', '2.838274'),
('L''usseloise', 111111111, 'Rue du Moulin du Peuch', '', '19200', 'Guéret', 
	'Notre salaison née en 1930 a été créée sous le nom de « La Sociale » car une partie de nos employés étaient des combattants mutilés',
	3, 'Usseloise.jpg', '46.533833', '2.838274'),
('Les collines', 111111111, 'Rue du jaiplusenviedecopiercoller', '', '66699', 'HiThere', 
	'Ceci est une description de producteur complètement bidon. Pourquoi continuez-vous à lire? Non mais vraiment, ça n''a aucun sens! Stop! Enfin bref... Bonne journée!''',
	8, 'Collines.jpg', '46.533833', '2.838274'),
('Gaec fontloup', 111111111, 'Rue du jaiplusenviedecopiercoller', '', '66699', 'HiThere', 
	'Ceci est une description de producteur complètement bidon. Pourquoi continuez-vous à lire? Non mais vraiment, ça n''a aucun sens! Stop! Enfin bref... Bonne journée!''',
	10, 'GAEC-fontloup_1.jpg', '46.533833', '2.838274'),
('Moulin de saint désiré', 111111111, 'Rue du jaiplusenviedecopiercoller', '', '66699', 'HiThere', 
	'Ceci est une description de producteur complètement bidon. Pourquoi continuez-vous à lire? Non mais vraiment, ça n''a aucun sens! Stop! Enfin bref... Bonne journée!''',
	100, 'Moulin-de-Saint-Desire.jpg', '46.533833', '2.838274'),
('Comtes de la marche', 111111111, 'Rue du jaiplusenviedecopiercoller', '', '66699', 'HiThere', 
	'Ceci est une description de producteur complètement bidon. Pourquoi continuez-vous à lire? Non mais vraiment, ça n''a aucun sens! Stop! Enfin bref... Bonne journée!''',
	8, 'Comtes-de-la-Marche.jpg', '46.533833', '2.838274');
	
-- type1
INSERT INTO types1 (libelle1) VALUES ('Fromage');
INSERT INTO types1 (libelle1) VALUES ('Charcuterie');
INSERT INTO types1 (libelle1) VALUES ('Epicerie');
INSERT INTO types1 (libelle1) VALUES ('Caviste');

-- type2
INSERT INTO types2 (id_type1, libelle2) VALUES (1, 'Vache'), (1, 'Chèvre'), (1, 'Brebis');
INSERT INTO types2 (id_type1, libelle2) VALUES (2, 'Foie gras'), (2, 'Jambon sec'), (2, 'Saucisson');
INSERT INTO types2 (id_type1, libelle2) VALUES (3, 'Confiture'), (3, 'Condiment'), (3, 'Gourmandise');
INSERT INTO types2 (id_type1, libelle2) VALUES (4, 'Vin rouge'), (4, 'Vin blanc'), (4, 'Vin rosé'), (4, 'Alcool divers');

-- utilisateurs
INSERT INTO utilisateurs (nom, prenom, ligne_adresse1, ligne_adresse2, code_postal, ville, email, mdp, tel, photo, droits) VALUES 
('Admin', 'Sopra', '72 Allée des Noisetiers', 'Parc du Puy d''Or', '69760', 'Limonest', 'admin@mail.fr', '', '0659624137', '', '10'),
('Ghost', 'ForTests', '72 Allée des Noisetiers', 'Parc du Puy d''Or', '69760', 'Limonest', 'ghost@mail.fr', '', '0659624137', '', '10'),
('dupont', 'pierre', 'rue de la poste', '', '69001', 'Lyon', 'pdupont@mail.fr', 'dupont69', '0659624137', '', '0'),
('martin', 'simon', 'impasse du jardin', '', '33002', 'Bordeaux', 'smartin@mail.fr', 'martin33', '0644832015', '', '0'),
('durant', 'marie', 'avenue de la gare', '', '44003', 'Nantes', 'mdurant@mail.fr', 'durant44', '0613256948', '', '0'),
('andre', 'céline', 'avenue de la republique', '', '59005', 'Lille', 'candre@mail.fr', 'andre59', '0656947122', '', '0');

-- produits
INSERT INTO produits (id_producteur, id_type2, description, prix, designation, photo, disponible) VALUES 
(4, 1, 'Fromage à pâte pressée cuite.', 8, 'Abondance', 'Abondance.jpg', true),
(4, 1, 'Fromage à la croûte brossée', 7.99, 'Beaufort', 'Beaufort.jpg', true),
(4, 1, 'Fromage à pâte non cuite', 15, 'Beaumont', 'Beaumont.jpg', false),
(4, 1, 'Fromage à pâte persillée', 22, 'Bleu de Dévoluy', 'Bleu_de_Devoluy.jpg', true),
(4, 1, 'Fromage à pâte persillée', 12, 'Bleu de Gex', 'bleu_de_gex.jpg', true),
(4, 1, 'Fromage à de lait de vâche & lait de chèvre, à Pâte persillée', 22, 'Bleu de Sainte-Foy', 'bleu-vercors.png', true),
(4, 1, 'Fromage à croûte cendré', 22, 'Brisegoût', 'Brisegout.jpg', true),
(4, 1, 'Fromage à pâte molle', 14, 'Chambarand', 'Chambarand.jpg', true),
(4, 1, 'Fromage à pâte persillée', 13.99, 'Rigotte de Condrieu', 'Rigotte_de_Condrieu.jpg', true),
(4, 1, 'Fromage en forme de coeur', 25, 'Neufchâtel', 'Neufchatel.jpg', true),
(4, 2, 'Fromage à croûte noire comme le charbon', 25, 'St_Felicien', 'St_felicien.jpg', true),
(6, 10, 'Le côtes-du-rhône villages est un vin d''appellation d''origine contrôlée produit sur une large partie du vignoble de la vallée du Rhône méridionale, sur quatre départements : l''Ardèche, la Drôme, le Gard et le Vaucluse.', 18.00, 'Côtes-du-rhône villages 2001', 'cdr.jpg', true),
(5, 10, 'Le côte-rôtie est un vin rouge d''appellation d''origine contrôlée produit sur les communes d''Ampuis, de Saint-Cyr-sur-le-Rhône et de Tupin-et-Semons, sur la rive droite du Rhône, en face de la ville de Vienne, au sud de Lyon.', 17.8, 'AOC Côte-Roti 2005', 'cote-rotie.jpg', true),
(3, 13, 'La chartreuse est une liqueur d''origine végétale fabriquée par les moines chartreux dans les caves de Voiron en Isère, en bordure du Massif de la Chartreuse. Liqueur au très haut degré d''alcool sa fabrication se fait sous la supervision des moines de la Grande-Chartreuse.', 25.00, 'La Chartreuse', 'chartreuse.jpg', true),
(1, 13, 'La recette de la liqueur de génépi historiquement et universellement (mais pas la meilleure) connue est "40 brins, 40 sucres, 40 jours". En français : faire macérer 40 brins de génépi fleuri dans 1 litre d''alcool à 40° pendant 40 jours et rajouter 40 sucres.', 8.72, 'Liqueur de Genepi Dolin', 'genepi.jpg', true),
(6, 11, 'Le condrieu est un vin blanc d''appellation d''origine contrôlée produit sur la rive droite du Rhône, près de Condrieu, au sud de la ville de Vienne. Il s''agit d''une appellation du vignoble de la vallée du Rhône septentrionale, entre les aires de production du côte-rôtie au nord et du saint-joseph au sud.', 19.60, 'AOC le Condrieu 2011', 'condrieu.jpg', true),
(9, 11, 'Le coteaux-de-die est un vin blanc d''appellation d''origine contrôlée produit dans la haute vallée de la Drôme. Cette AOC désigne des vins blancs secs produits sur la même aire d’appellation que la Clairette de Die.', 18.00, 'AOC le Coteau de Die 2012', 'coteau-de-die.jpg', true),
(6, 12, 'Parfaitement adapté à nos terroirs le Merlot Noir reste le cépage dominant de notre vignoble, dans ce vin, il représente 40%. Le Cabernet Franc apporte ici de la structure et surtout un bouquet délicat, il est présent à 35% et complété par 25% de Cabernet Sauvignon.', 13.50, 'Vin rosé Bergerac 2013', 'vin-rose.jpg', true),
(1, 12, 'Les vins du Vivarais dans le sud-est du Massif central ont une origine assez ancienne qui remonte à l''époque romaine mais son implantation s''est réellement développée au Moyen Âge. Les chemins muletiers, dès le Moyen Âge et jusqu''à la fin du XIXe siècle, ont été utilisés pour monter le vin du bas-Vivarais vers les contrées du Gévaudan, de la Lozère et même vers les plateaux auvergnats (Le Puy).', 18.00, 'AOC Côtes-du-vivarais 2013', 'Côtes_du_Vivarais_rose.jpg', true),
(8, 13, 'La liqueur de gentiane est une boisson apéritive amère et alcoolisée fabriquée par macération et distillation de racines de gentiane jaune d''Auvergne (gentiana lutea) qui lui confèrent son amertume bien spécifique. On retrouve cette plante en importantes quantités dans les monts du Cantal, entre le Puy Mary et le Plomb du Cantal.', 10.00, 'Liqueur de Gentiane', 'gentiane.jpg', true),
(2, 7, 'Poids : 350 g, Composition : 62 g de sucre / 100 g et 60 g de fruits / 100 g.', 4.19, 'Confiture d''Abricots aux Amandons', 'confiture-01.jpg', true),
(6, 7, 'Poids : 350 g, Composition : 62 g de sucre / 100 g et 64 g de fruits / 100 g.', 4.19, 'Confiture de Cassis', 'confiture-02.jpg', true),
(3, 7, 'Poids : 350 g, Composition : 62 g de sucre / 100 g et 63 g de fruits / 100 g.', 4.19, 'Confiture de Châtaignes', 'confiture-03.jpg', true),
(5, 7, 'Poids : 350 g, Composition : 62 g de sucre / 100 g et 69 g de fruits / 100 g.', 4.19, 'Confiture de Figues aux Noix', 'confiture-04.jpg', true),
(4, 8, 'Conditionnement : bouteille de 1 L. Produit fermier FR-BIO-01 pour friture.', 5.02, 'Huile de Tournesol AB', 'condiment-01.jpg', true),
(8, 8, 'Poids : 200 g. Composition : 500 mg / kg de Grande Absinthe.', 4.46, 'Moutarde à l''Absinthe ', 'condiment-02.jpg', true),
(1, 8, 'Conditionnement : bouteille de 1 L.', 3.58, 'Vinaigre de Cidre Fermier', 'condiment-03.jpg', true),
(9, 8, 'Poids : 100 g. Composition : 66 % d''oignons.', 2.70, 'Confit d''Oignons', 'condiment-04.jpg', true),
(4, 9, 'Poids : 320 g. Composition : 10 % de noisettes broyées. S''accompagne avec : Gewurtztraminer', 7.44, 'Le Creusois', 'gourmandise-01.jpg', true),
(10, 9, 'Poids : 100 g. Composition : essentiellement du Miel de Lavande.', 2.79, 'Nougat de Montélimar', 'gourmandise-02.jpg', true),
(7, 9, 'Poids : 350 g. Composition : Amandes grillées concassées.', 6.46, 'Pralines concassées d''Auvergne pour Brioches', 'gourmandise-03.jpg', true),
(5, 9, 'Poids : 210 g. Composition : 14 % d''amande.', 3.44, 'Mini Financiers aux Amandes', 'gourmandise-04.jpg', true),
(1, 3, 'Le banon est un petit fromage français issu d''anciennes recettes des fermes des Alpes-de-Haute-Provence. Son nom vient d''un petit village adossé au Plateau d''Albion entre la montagne de Lure et le Mont Ventoux, dans les collines chères à Jean Giono. C''est un fromage de 6 à 7 cm de diamètre, au lait cru d''une centaine de grammes.', 1.29, 'Le Banon', 'Banon_02.jpg', true),
(2, 3, 'Sa forme caratéristique haute et légèrement bombée comme un tonnelet, sa pâte moelleuse, sa saveur douce, font de ce fromage de chèvre une véritable gourmandise.
Très apprécié sur le plateau d''un repas de fête...', 1.56, 'Charolais', 'Charolais.jpg', true),
(3, 3, 'Le Picodon est un fromage au lait de chèvre à pâte molle et à croûte naturelle produit dans un vaste territoire constitué par les Cévennes ardéchoises, une partie de la vallée du Rhône et les Préalpes drômoises en France. Il pèse entre 45 à 90 grammes et est en forme de disque plat aux bords arrondis1. Il bénéficie d''une appellation d''origine contrôlée depuis 1983 et d''une appellation d''origine protégée.', 1.72, 'Picodon', 'picodon.jpg', true),
(4, 3, 'Saveur à la fois salée et acidulée, avec des arômes subtils de fruits, de lait caillé, et un goût caprin au départ très doux qui se renforce avec le temps. Lorsque les bouchons sèchent leur arômes deviennent typés, poivrés, et plus persistants en bouche.', 2.65, 'Bouchon de Chèvre', 'bouchon_de_chevre.jpg', false),
(5, 3, 'Il porte le nom de la commune homonyme. Il est protégé par une appellation d''origine contrôlée2, depuis février 1972 et par une appellation d''origine protégée, depuis le 29 octobre 20093.', 1.82, 'Pouligny-Saint-Pierre', 'Pouligny-saint-pierre.jpg', true),
(1, 3, 'C''est un petit fromage à pâte molle à croûte fleurie et de couleur blanche, jaune ou bleue selon sa maturité2. Il contient entre 40 et 45 % de matière grasse.', 1.50, 'Bouton de culotte', 'bouton_de_culotte.jpg', false);

INSERT INTO produits (id_producteur, id_type2, description, prix, designation, photo, disponible) VALUES 
(1, 4, 'Terrine de foie gras de canard. Se déguste avec un vin moelleux ou du beaujolais. Conditionné ainsi il se conserve 2 ans.', 19.50, 'Terrine de foie gras, 190gr', 'foiegras.jpg', true),
(1, 4, 'Ce magret de canard de race Mulard est farci au foie gras. Il est idéal en salade, avec un vin moelleux. Il se conserve 40 jours.', 14.80, 'Magret de canard au foie gras (35%),150gr', 'magret.jpg', false),
(2, 4, 'Nos rillettes de canard se dégustent avec un vin rouge léger. Elles contiennent 20% de foie gras. La conservation est de 30 jours.', 8.70, 'Rillettes de canard', 'rillettes.jpg', true),
(1, 4, 'Foie gras de canard entier aux pépites de figues séchées. Les Canards de Limagne sont issus de souche Mulard, et sont nourris avec du maïs de la plaine de Limagne en Auvergne. Cette terre particulière de notre terroir donne un goût spécifique et unique à nos foies gras. Conditionné en bloc de 400gr, il se conserve 1 ans', 28.20, 'Bloc de foie gras de canard entier', 'blocfoie.jpg', true),
(3, 5, 'Demi-Jambon Sec origine Auvergne, sans os et sans jarret, de 2.75 kg. Il se conserve 20 jours. A accompagner de vin rosé.', 43.15, 'Demi-Jambon Sec Auvergne', 'jambonsec.jpg', true),
(3, 5, 'Jambon Sec des Combrailles, conditionné en paquet de 150 gr. Un vin rosé est le bienvenue pour déguster de jambon sec. Se conserve 75 jours.', 5.60, 'Jambon Sec', 'jambon.jpg', true),
(3, 6, 'Saucisson sec aux figues, une des spécialités de notre exploitation. Dans la fabrication, outre la qualité des matières premières, le séchage donne au saucisson ses qualités gustatives.', 3.10, 'Saucisson aux figues', 'saucissonfigues.jpg', false),
(3, 6, 'Saucisson aux herbes, spécialité de notre terroir. Le séchage est important mais la qualité des herbes de provence lui confère son parfum unique.', 3.25, 'Saucisson aux herbes', 'saucissonherbes.jpg', true),
(5, 6, 'Saucisson aux noisettes a subit un séchage particulier en cave ce qui lui confère un gout incomparable. Les petits éclats de noisettes donne du croquant au saucisson', 4.85, 'Saucisson aux noisettes', 'saucissonnoisettes.jpg', true),
(4, 6, 'Saucisse sèche primée au salon agricole 2012 de la région Rhône-Alpes pour son gout unique. En boyaux naturels, elle se conserve plus de 50 jours. Accompagner absolument de Velançay', 8.20, 'Saucisse seche', 'saucisseseche.jpg', true);


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


-- ///////////////////////////////////////////////////////////////////////////////////////////////////
-- INSERT INTO (seconde partie) + UPDATE
-- ///////////////////////////////////////////////////////////////////////////////////////////////////

-- commentaires
INSERT INTO commentaires (id_utilisateur, avis, note, etat) VALUES 
(1, 'Mon avis sur le produit 1 c''est qu''il est bof, note de 2', 2, 1),
(1, 'j''ai oublie de dire un truc alors je rajouter un second comm avec note 3', 3, 1),
(2, 'Commentaire de dupont a moderer', 3, 0),
(2, 'Commentaire de dupont rejete', 3, -1);

-- commentaires_produits
INSERT INTO commentaires_produits (id_commentaire, id_produit) VALUES 
(1, 1),
(2, 1),
(3, 1),
(4, 1);

-- commandes
-- INSERT INTO commandes (id_utilisateur, etat, ts_validation, ts_archivage, moyen_paiement) VALUES (?, ?, ?, ?, ?)
INSERT INTO commandes (id_utilisateur, etat, moyen_paiement) VALUES 
(1, -1, 0),
(1, -1, 0);
UPDATE commandes SET (etat, moyen_paiement) = (0, 0) WHERE id_commande = 2;

-- items_commandes
INSERT INTO items_commandes (id_produit, id_commande, quantite) VALUES 
(1, 1, 2),
(2, 1, 1),
(3, 1, 5),
(4, 2, 4),
(5, 2, 5);