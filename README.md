Nom de l'application: MyWeather

Fonctionnalités implémentées:
  - Affichage de la météo d'un ville:
        - Affichage d'une icone indiquant le temps qu'il fait (nuageux, pluvieux,..).
        - Affichage du nom de la ville et de sa température.
        - Affichage d'autres informations (température ressentie,minimale,maximale,pression et l'humidité)
        
  - Affichage de la météo sur 5 jours:
        - Affichage dans une liste d'items (en utilisant un recyclerView) des informations suivantes: date,le temps qu'il fait,la température et une icône.
        
  - Recherche d'une ville:
        - capacité de rechercher une ville dans la barre de recherche.
        
  - Ajouter le nom d'une ville dans les favoris: 
        - La fonctionnalité des favrois a été implémenté en utilisant le mécanisme des "Shared Preferences"
        - capacité d'ajouter le nom d'une ville dans les favoris en cliquant sur l'icone "ajouter au favoris" présente dans la barre de recherche.
        - Une fois une ville ajoutée aux favoris, on peut voir la météo de cette dernière en cliquant sur le nom de la ville présent dans l'onglet "Favoris".
        
  - Splashscreen:
        - Un splashcreen contenant l'icone de l'application est affiché au lancement de cette dernière.
  
  - Géolocalisation:
        - Capacité à afficher la météo de la ville en utilisant la géolocalisation.
        - Demande d'accès à la localisation lors du lancement de l'application pour la première fois.
        - Remarque: si l'application est lancée sur un émulateur, il faut d'abord ouvrir Maps sinon la géolocalisation retourne un objet null.
        
  - Persistance des données:
        - En utilisant le mécanisme des "Shared Preferences", les favoris ne sont pas perdus en quittant l'application.
    
  - Gestion des erreurs:
        - L'application ne crash pas en étant déconnectée d'Internet.
        - En cas d'insertion d'un nom d'une ville inexistante, l'application affiche un message d'erreur demandant à l'utilisateur de ré-inserer un autre nom d'une ville.
        - Lors de l'ajout d'une ville dans les favoris, l'application verifie bien qu'on a introduit un nom dans la barre de recherche et que la ville n'existe pas déjà dans les favoris.
  
  - Architecture de l'application:
        - Une activité qui contient une barre de recherche, un bouton valider (afin d'effectuer la recherche d'une ville) et une navigation barre (afin de naviguer dans les différents fragments).
        - La navigation barre contient:
                - Météo -> naviguer dans le fragment qui affiche la météo d'une journée.
                - Liste -> naviguer dans le fragment qui affiche la météo pour 5 jours.
                - Favoris -> naviguer dans le fragment qui affiche les favoris.
                - Géolocalisation -> naviguer dans le fragment qui affiche la météo d'une journée en utilisant la géolocalisaiton.
