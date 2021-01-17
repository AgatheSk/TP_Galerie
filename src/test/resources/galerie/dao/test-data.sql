-- Initialisation spécifiques pour un jeu de test
INSERT INTO Galerie(id, nom, adresse) VALUES (1, 'Saatchi', 'King\''s Road, Londres');
 insert into personne (id_personne, dtype, nom) values (1, 'Personne', 'paul');
 insert into personne(id_personne, dtype, nom) values (2, 'Artiste', 'Picassou');
 insert into tableau(id_tablo, titre, hauteur, largeur, auteur_id_personne) values (1, 'bonjour', 10, 10, 2);
 insert into tableau(id_tablo, titre, hauteur, largeur, auteur_id_personne) values (2, 'bonsoir', 12, 10, 2);
 insert into exposition(id_expo, debut, duree, organisateur_id) values (1, to_date('12/03/2020','DD/MM/YYYY'), 2, 1);
 insert into expo_tableau VALUES (1,1);
 insert into expo_tableau VALUES(1,2);
 insert into transaction(id_transaction, prix_vente, vendu_le, lieu_de_vente_id_expo, client_id_personne, oeuvre_id_tablo) values(1, 200, to_date('12/03/2020','DD/MM/YYYY'), 1,1,2);
 insert into transaction(id_transaction, prix_vente, vendu_le, lieu_de_vente_id_expo, client_id_personne, oeuvre_id_tablo) values(2, 100, to_date('12/03/2020','DD/MM/YYYY'), 1,1,1);
