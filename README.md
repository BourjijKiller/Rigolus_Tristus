# JEU RIGOLUS & TRISTUS

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

----------------------------------------------------------

## UTILISATION

Ce projet est développé avec l'outil de développement [IntelliJ IDEA](https://www.jetbrains.com/idea/).
Les codes sources sont situés dans le dossier **src/** avec :
* Le package **main/** contenant les classes principale
* Le package **test/** contenant les classes de test
* Le package **exceptions/** contenant les exceptions Java utilisées dans les classes

----------------------------------------------------------

## AVANCEMENT

| **Date** | **Description de l'avancement** |
|:---:|:---:|
| _15/02/2018_ | Création du squelette du projet et des premières classes nécessaires. Les méthodes de tests sont déjà fournies, il faut donc écrire les classes et les énumérations nécessaires pour valider tous les tests définis dans les classes de test |
| _05/03/2018_ | Ajout des classes de Test **TestCreature** et **TestCreatureAbstraite**.																								Refonte de la classe Creature, en cohérence avec les classes de Test, et suppression des classes Java Rigolus et Tristus, qui sont inutiles puisque l'on instancie que des objets de type **Creature**.																																										Ajout des **encyclopédie des Rigolus et Tristus** sous forme de classe, ainsi que de la classe **Planete** |
| _18/03/2018_ | Suppression des classes associées aux différentes encyclopédies, on représente directement les encyclopédies par des ArrayLists. Avancement dans le code, en respectant les classes de tests. Création de la classe **CreatureEquanime**, héritant de la classe **Creature**, avec pour seul différence que les points d'humeurs sont constants pour les créatures équanime. Création d'une énumération afin de créer et récupérer le type de jeu de mot de la créature lorsqu'elle joue au jeu du _Pierre/Feuille/Ciseaux_. Création des différents packages :
* **exception** pour les **Exceptions** qui doivent être levées lors d'erreurs.
* **main** qui contient les classes des Créatures et de la Planete.
* **test** qui contient les classes de Test JUnit.
* **TypeJeuMot** qui contient **l'énumération** pour le type de jeu de mot.
Construction du squelette pour la classe **Planete** |

_Dernière modification le 05/03/2018, 09h36_