### Fonctionnalités implémentés :

1. **getAllMenus()** :
    - Description : Récupère tous les menus disponibles.
    - État de l'implémentation : Totalement fonctionnelle.
    - Entrées : Aucune.
    - Sorties : Liste JSON de tous les menus.

2. **getMenu(int id)** :
    - Description : Récupère un menu spécifique par son identifiant.
    - État de l'implémentation : Totalement fonctionnelle.
    - Entrées : ID du menu à récupérer.
    - Sorties : JSON représentant le menu demandé.

3. **getAllPlats()** :
    - Description : Récupère tous les plats disponibles.
    - État de l'implémentation : Totalement fonctionnelle.
    - Entrées : Aucune.
    - Sorties : Liste JSON de tous les plats.

4. **getPlatsFromMenu(int id)** :
    - Description : Récupère les plats associés à un menu spécifique.
    - État de l'implémentation : Totalement fonctionnelle.
    - Entrées : ID du menu. @Path("plats/{id}")
    - Sorties : JSON représentant les plats associés au menu.

5. **getPlatsByMenu(int id)** :
    - Description : Récupère les plats associés à un menu spécifique.
    - État de l'implémentation : Totalement fonctionnelle.
    - Entrées : ID du menu. @Path("plats/menu={id}")
    - Sorties : JSON représentant les plats associés au menu.

6. **getDateJSON(int id)** :
    - Description : Récupère la date d'un menu spécifique.
    - État de l'implémentation : Partiellement Fonctionnelle ( Error 500 ).
    - Entrées : ID du menu. @Path("{id}/date")
    - Sorties : Date de livraison au format JSON.

7. **addMenu(Menu menu)** :
    - Description : Ajoute un nouveau menu.
    - État de l'implémentation : Partiellement Fonctionnelle
    - Entrées : Objet Menu à ajouter.
    - Sorties : Aucune (ou éventuellement un statut HTTP pour indiquer le succès ou l'échec de l'opération).

8. **addPlat(Plat plat, int id)** :
    - Description : Ajoute un nouveau plat à un menu spécifique.
    - État de l'implémentation : Partiellement fonctionnelle
    - Entrées : Objet Plat à ajouter, ID du menu auquel le plat doit être ajouté.
      @Path("plats/{id}")
    - Sorties : Aucune (ou éventuellement un statut HTTP pour indiquer le succès ou l'échec de l'opération).

9. **updateMenu(int id, Menu menu)** :
    - Description : Met à jour un menu existant.
    - État de l'implémentation : Non fonctionnelle  
    - Entrées : ID du menu à mettre à jour, nouvel objet Menu.
    @Path("{id}")
      - Sorties : Aucune (ou éventuellement un statut HTTP pour indiquer le succès ou l'échec de l'opération).

10. **updateMenu(Menu menu, int id, String creationDate)** :
    - Description : Met à jour un menu existant avec une nouvelle date de création.
    - État de l'implémentation : Partiellement fonctionnelle
    - Entrées : ID du menu à mettre à jour, nouvel objet Menu, nouvelle date de création. @Path("{id}/date={creationDate}")
    - Sorties : Aucune (ou éventuellement un statut HTTP pour indiquer le succès ou l'échec de l'opération).

11. **deleteMenu(int id)** :
    - Description : Supprime un menu spécifique.
    - État de l'implémentation : Fonctionnelle (affiche deleted ou no deleted)
    - Entrées : ID du menu à supprimer. @Path("{menuId}")
    - Sorties : Réponse HTTP indiquant si la suppression a réussi ou échoué.

12. **deletePlat(int id)** :
    - Description : Supprime un plat spécifique.
    - État de l'implémentation : Totalement fonctionnelle.
    - Entrées : ID du plat à supprimer. @Path("plats/menu={id}")
    - Sorties : Chaîne de caractères indiquant si la suppression a réussi ou échoué.

