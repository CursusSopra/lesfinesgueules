-- INSERT INTO types2 (id_type1, libelle2) VALUES (3, 'Confiture'), (3, 'Condiment'), (3, 'Gourmandise'); -- 7/8/9

INSERT INTO produits (id_producteur, id_type2, description, prix, designation, photo, disponible) VALUES 
(2, 7, 'Poids : 350 g, Composition : 62 g de sucre / 100 g et 60 g de fruits / 100 g.', 4.19, 'Confiture d\'Abricots aux Amandons', 'confiture-01.jpg', true),
(6, 7, 'Poids : 350 g, Composition : 62 g de sucre / 100 g et 64 g de fruits / 100 g.', 4.19, 'Confiture de Cassis', 'confiture-02.jpg', true),
(3, 7, 'Poids : 350 g, Composition : 62 g de sucre / 100 g et 63 g de fruits / 100 g.', 4.19, 'Confiture de Châtaignes', 'confiture-03.jpg', true),
(5, 7, 'Poids : 350 g, Composition : 62 g de sucre / 100 g et 69 g de fruits / 100 g.', 4.19, 'Confiture de Figues aux Noix', 'confiture-04.jpg', true);

INSERT INTO produits (id_producteur, id_type2, description, prix, designation, photo, disponible) VALUES 
(4, 8, 'Conditionnement : bouteille de 1 L. Produit fermier FR-BIO-01 pour friture.', 5.02, 'Huile de Tournesol AB', 'condiment-01.jpg', true),
(8, 8, 'Poids : 200 g. Composition : 500 mg / kg de Grande Absinthe.', 4.46, 'Moutarde à l\'Absinthe ', 'condiment-02.jpg', true),
(1, 8, 'Conditionnement : bouteille de 1 L.', 3.58, 'Vinaigre de Cidre Fermier', 'condiment-03.jpg', true),
(9, 8, 'Poids : 100 g. Composition : 66 % d\'oignons.', 2.70, 'Confit d\'Oignons', 'condiment-04.jpg', true);

INSERT INTO produits (id_producteur, id_type2, description, prix, designation, photo, disponible) VALUES 
(4, 9, 'Poids : 320 g. Composition : 10 % de noisettes broyées. S\'accompagne avec : Gewurtztraminer', 7.44, 'Le Creusois', 'gourmandise-01.jpg', true),
(10, 9, 'Poids : 100 g. Composition : essentiellement du Miel de Lavande.', 2.79, 'Nougat de Montélimar', 'gourmandise-02.jpg', true),
(7, 9, 'Poids : 350 g. Composition : Amandes grillées concassées.', 6.46, 'Pralines concassées d\'Auvergne pour Brioches', 'gourmandise-03.jpg', true),
(5, 9, 'Poids : 210 g. Composition : 14 % d\'amande.', 3.44, 'Mini Financiers aux Amandes', 'gourmandise-04.jpg', true);