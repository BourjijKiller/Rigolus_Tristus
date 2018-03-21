# MONDE DES RIGOLUS & TRISTUS

----------------------------------------------------------

## DESCRIPTION

Programmation du monde des **Rigolus** et des **Tristus** en java.

### Les Rigolus et les Tristus

Les Rigolus et les Tristus sont des **créatures imaginaires** qui vivent sur la **planète Rigolus-Tristus** de la galaxie Lépiédechèze, située à des millions d'années-lumière de la Terre. Les Terriens ne pouvaient guère les rencontrer que dans les bandes dessinées de Jean Cézard dans le magazine Pig Gadget. La **planète Rigolus-Tristus** est un monde bicolore, rouge et vert, où vivent deux peuples très différents :
* **_Les Rigolus_**, au teint rose et habillés de rouge, qui rigolent tout le temps
* **_Les Tristus_**, au teint bleu et habillés de vert, déprimés et tristes à pleurer

Ces peuples sont continuellement en conflit et ils ont résolu le problème des pertes aux combats d'une manière tout à fait originale. Il leur suffit de faire rire un adversaire Tristus ou de faire pleurer un adversaire **Rigolus** pour que celui-ci se **métamorphose** : le **Tristus** contaminé par l'hilarité vire au rouge, ou le **Rigolus** frappé de morosité passe au vert.

Afin de modéliser cela, il faut créer les classes Java nécessaires.
On sait qu'une créature possède un **nom** et des **points d'humeur**.
Lorsque le nombre de points d'humeur de la créature est strictement positif, elle est dans le camp des Rigolus, sinon lorsqu'il est strictement négatif, elle est dans le camp des Tristus. Les créatures sont créées à partir de **l'encyclopédie des Rigolus et des Tristus**. Tous les Rigolus de l'encyclopédie ont initialement un nombre de points d'humeur strictement positif et tous les Tristus de l'encyclopédie un nombre de points d'humeur strictement négatif. Le premier Rigolus créé est nécessairement le chef des Rigolus, **_Jubilus_**, et le premier Tristus créé doit être le chef des Tristus, **_Taciturnus_**.

**[ATTENTION] Deux créatures ne peuvent pas porter le même nom.**

### Les créatures équanimes

Enfin, il existe dans chaque camp, un petit groupe d'irréductibles créatures qui restent des Rigolus ou des Tristus chroniques. Ces créatures irréductibles ont un nombre de points d'humeur constant. Il faut donc réécrire certaines méthodes de la classe _Creature_ dans la nouvelle classe _CreatureEquanime_, afin de ne pas modifier les points d'humeur.

### La planète Rigolus-Tristus

La vie de la planète **Rigolus-Tristus** est rythmée par les affrontements entre **Rigolus** et **Tristus**. Les chefs, qui sont très soucieux du moral de leur troupe, ont mis au point différents outils permettant de parcourir toutes les créatures de chaque camp, de mesurer l'indice de bonheur brut de la planète, d'obtenir la liste des créatures triée dans l'ordre croissant de leur **humeur**, d'obtenir la liste des créatures les plus fortes (celles qui ont le plus de victoires) ou d'obtenir la liste des Rigolus non chroniques dont l'humeur   est inférieure à un certain seuil.

Une **planète** est unique, et contient la population des **Rigolus** et des **Tristus**. Par conséquent, l'objet **Planete** ne pourra être instancié qu'une seul fois. Cette vérification est faite par une _variable static_, qui :

* Vaut **null** si la planète n'est pas encore créée
* Vaut l'instance de la planète si celle-ci est créé. Si la variable static ne vaut pas **null**, l'objet **Planete** ne sera pas instancié.

----------------------------------------------------------

## NOTIONS UTILISÉES

1. **Classe**
2. **Objet**
3. **Attribut**
4. **Méthode**
5. **Constructeur**
6. **Héritage**
7. **JUnit Test Case**
8. **Polymorphisme**
9. **Surcharge**
10. **JUnit Test Case**
11. **Interface Set, List et Map**
12. **Exception**
13. **Énumérations**
14. **Doclet**

----------------------------------------------------------

## UTILISATION

Ce projet est développé avec l'outil de développement [IntelliJ IDEA](https://www.jetbrains.com/idea/).
Les codes sources sont situés dans le dossier **src/** avec :
* Le package **main/** contenant les classes principale
* Le package **test/** contenant les classes de test
* Le package **exception/** contenant les exceptions Java utilisées dans les classes
* Le package **TypeJeuMot/** contenant l'énumération pour le type de jeu de mot
* Le package **outils/** contenant le filtre utilisé dans la classe **Creature**
* Le package **JavaDoc/** contenant la JavaDoc du projet

----------------------------------------------------------

## AVANCEMENT

| **Date** | **Description de l'avancement** |
|:---:|:---:|
| _15/02/2018_ | Création du squelette du projet et des premières classes nécessaires. Les méthodes de tests sont déjà fournies, il faut donc écrire les classes et les énumérations nécessaires pour valider tous les tests définis dans les classes de test |
| _05/03/2018_ | Ajout des classes de Test **TestCreature** et **TestCreatureAbstraite**.																								Refonte de la classe Creature, en cohérence avec les classes de Test, et suppression des classes Java Rigolus et Tristus, qui sont inutiles puisque l'on instancie que des objets de type **Creature**.																																										Ajout des **encyclopédie des Rigolus et Tristus** sous forme de classe, ainsi que de la classe **Planete** |
| _18/03/2018_ | Suppression des classes associées aux différentes encyclopédies, on représente directement les encyclopédies par des ArrayLists. Avancement dans le code, en respectant les classes de tests. Création de la classe **CreatureEquanime**, héritant de la classe **Creature**, avec pour seul différence que les points d'humeurs sont constants pour les créatures équanime. Création d'une énumération afin de créer et récupérer le type de jeu de mot de la créature lorsqu'elle joue au jeu du _Pierre/Feuille/Ciseaux_. Restrucuration du code avec l'ajout de packages. Construction du squelette pour la classe **Planete** |
| _19/03/2018_ | Avancement du code pour les classes **Creature**, **CreatureEquanime** et **Planete**. Finalisation de la classe **Creature** avec le codage des méthodes `tableauDeChasse`, _qui renvoie une table de hachage (Map) contenant les couples de la forme (vaincu, nombre de fois où le vaincu a été battu par la Creature courante)_, et `vaincus()` _qui renvoie un ensemble (Set) formé de toutes les Creature vaincues par la Creature courante_. Il reste donc à finir la classe Planete, et régler le problème de la méthode **ordinal()** et des itérateurs de chaque Encyclopédie, qui ne fonctionnent pas encore |
| _20/03/2018_ | Finalisation des classes **CreatureEquanime** et **Planete** du projet afin de passer les tests. Correction de l'énumération **TypeDeJeuDeMot** pour passer les tests **JUnit**. Il manque la programmation d'un **Doclet**, permettant de générer la JavaDoc du projet de façon personnalisée et la méthode `public Set<Creature> vaincusTelsQue(Filtre<Creature> toujoursVrai)` dans la classe **Creature** permettant de renvoyer les créatures vaincus qui respecte le **Filtre** passé en paramètre |
| _21/03/2018_ | Finalisation des classes. Complétion et génération de la JavaDoc dans le package JavaDoc |

##### [PROJET TERMINÉ]

_Dernière modification le 21/03/2018, 23h40_