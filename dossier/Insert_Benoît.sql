-- ///////////////////////////////////////////////////////////////////////////////////////////////////
-- INSERT INTO
-- ///////////////////////////////////////////////////////////////////////////////////////////////////

-- producteurs
INSERT INTO producteurs (raison_sociale, siren, ligne_adresse1, ligne_adresse2, code_postal, ville, description, delai_livraison, photo, gpslat, gpslong) VALUES 
('Les Jardins de lucie', 111111111, '69 Chemin du Tram', '', '69360', 'COMMUNAY', 'Notre coeur de métier est de favoriser l''insertion sociale et professionnelle de personnes éloignées de l''emploi en leur proposant des contrats de travail dans le secteur de l''agriculture. Nous produisons des légumes de saison dans le cadre du cahier des charges de l''agriculture biologique. Nos légumes sont donc certifiés AB.', 4, 'images/default.jpg', '45.595258', '4.832434'),
('Jardin d''Avenir', 111111111, 'Le Colombier', '', '69850', 'Saint Martin en Haut', 'Le mode de distribution est original car il s''adresse à un réseau d''adhérents consommateurs qui viennent chercher leur panier chaque semaine dans différents points de dépôts. L''accent est mis sur la qualité et la diversité des produits proposés. Ces fruits et légumes sont accessibles sous forme de paniers hebdomadaires directement dans nos jardins ou dans des points de dépôts.', 4, 'images/default.jpg', '45.672523', '4.575432'),
('Potager Mi-Plaine', 111111111, '27, route de Grenoble', '', '69800', 'Saint-Priest', 'Nous employons pour l''instant plus de 4000 personnes en insertion dans déjà 120 jardins dans tout la France et nous prévoyons de nous développer davantage. L''objectif de nos jardins solidaires est à la fois social et environnemental.', 4, 'images/default.jpg', '45.704664', '4.994573');

-- type1
INSERT INTO types1 (libelle1) VALUES ('Fromage');
INSERT INTO types1 (libelle1) VALUES ('Cave');
INSERT INTO types1 (libelle1) VALUES ('Viande');
INSERT INTO types1 (libelle1) VALUES ('Légumes');
INSERT INTO types1 (libelle1) VALUES ('Fruit');

-- type2
INSERT INTO types2 (id_type1, libelle2) VALUES (1, 'Vache'), (1, 'Brebis'), (1, 'Chèvre');
INSERT INTO types2 (id_type1, libelle2) VALUES (2, 'Vin'), (2, 'Mousseux'), (2, 'Champagne');
INSERT INTO types2 (id_type1, libelle2) VALUES (3, 'Boeuf'), (3, 'Veau'), (3, 'Volail');
INSERT INTO types2 (id_type1, libelle2) VALUES (4, 'Poireaux'), (4, 'Tomates'), (4, 'Pommes-de-terre');
INSERT INTO types2 (id_type1, libelle2) VALUES (5, 'Melon'), (5, 'Orange'), (5, 'Pomme');

-- utilisateurs
-- INSERT INTO utilisateurs (nom, prenom, ligne_adresse1, ligne_adresse2, code_postal, ville, email, mdp, tel, photo, droits) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);

-- produits
INSERT INTO produits (id_producteur, id_type2, description, prix, designation, photo, disponible) VALUES 
(1, 1, 'Le camembert est...', 1.29, 'Camembert', 'images/default.jpg', true),
(1, 2, 'Idéale pour faire...', 2.54, 'Tome de brebis', 'images/default.jpg', true),
(1, 3, 'A partager en famille...', 10.72, 'Bûche fraîche (1kg)', 'images/default.jpg', false),
(2, 7, 'Le boeuf, c''est bon !', 22.65, 'Côte de boeuf (1kg)', 'images/default.jpg', false),
(2, 8, 'Les escalope de veau sont...', 7, 'Escalopes de veau (x6)', 'images/default.jpg', false),
(3, 13, 'Même si ceux de charente sont...', 3.8, 'Melon commun', 'images/default.jpg', true);

-- commentaires
-- INSERT INTO commentaires (id_utilisateur, avis, note, etat) VALUES (?, ?, ?, ?);

-- commentaires_produits
-- INSERT INTO commentaires_produits (id_commentaire, id_produit) VALUES (?, ?);

-- commandes
-- INSERT INTO commandes (id_utilisateur, etat, ts_validation, ts_archivage, moyen_paiement) VALUES (?, ?, ?, ?, ?);

-- items_commandes
-- INSERT INTO items_commandes (id_produit, id_commande, quantite, ts_creation) VALUES (?, ?, ?, ?);