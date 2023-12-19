# Utopia - Doctrina engine

# Introduction

Utopia est un jeu de type monde ouvert. Le but est de découvrir la map, récupérer des ressources et tuer les monstres qui s'y trouvent.

# Modifications dans Doctrina

### Animation

* Animation: classe qui possède une séquence d'images
* AnimationHandler: classe abstraite qui gère toutes les animations pour une entité

### AttackZone

* Classe qui gère les zones d'attaque des entitées

### Blockade

* Classe qui crée des collisions dans la map, peut aussi s'updater pour s'activer ou non

### Bounds

* Classe qui est en fait un Rectangle, je n'ai pas eu le temps de l'implémenter partout...

### Camera

* Classe qui se gère toute seul puisque c'est une Thread.
* Elle s'update par elle-même et elle recueillit les coordonnées désirées en suivant sa cible

### Canvas

* Pas de changement important, seulement l'ajout de quelques fonctions en plus

### Collision

* Changement de leur emplacement par rapport aux entitées pour en faire de plus réaliste

### FontLoader

* Classe qui permet de créer une nouvelle font pour une String

### GameContext

* Énumération qui est un Singleton permettant de recueillir le state du jeu en cours et de la changer

### GameState

* Énumération des différentes states de la Game

### GameTime

* Changement de quelques fonctions selon le modèle de Samuel pour obtenir un 60fps fixe

### XmlReader

* XmlReader: interface permettant de redéfinir des fonctions pour lire un fichier XML
* XmlFileReaderTest: interface qui fait aussi la même chose de proche, mais que je n'ai pas enlevé, car mes collisions 
* sont basées sur cela et je n'ai pas pu le modifier pour le changer
* Manager: classe abstraite implémentant le XmlReader et qui définit les comportements pour les objets issus d'un fichier XML

### MouseController

* Classe qui implémente différentes interfaces de lecture de souris pour pouvoir définir leurs comportements 
et ainsi recueillir les bonnes données

### MovableEntity

* Changement des hitboxs pour les rendre plus réalistes

### Sound

* Classe qui possède toutes les fonctions pour faire jouer de la musique, en quelque sorte un wrapper de la classe Clip de Java
* La fonction pour changer le son est basé sur le modèle de Samuel

### SpriteSheetSlicer

* Classe qui découpe les images en plusieurs images

### State

* Énumération des différentes state d'une entité

### Ui

* Classe qui gère toute l'ATH pour le jeu

# Utopia

### Audio

* Music: Énumération de toutes les musiques du jeu
* SoundEffect: Énumération de tous les sound effects du jeu

### Entities

* Contient toutes les classes qui extendent la classe Manager pour définir les comportements pour chaque entitées

### Environment

* Raindrop: classe qui définit le comportement d'une goutte
* RainEffect: classe qui gère le comportement de toutes les raindrops
* Ils ont peu de coûts, car je ne crée pas de gouttes à chaque fois, mais je les réutilise

### Event

* Classes qui gèrent les différents event qui se passe dans le Ui pour le jeu

### Menu

* Classes qui gèrent les actions du menu
* Je n'ai pas ajouté la fonctionnalité pour les Options du jeu encore

### Player

* Classe qui contient le nécessaire pour faire fonctionner le joueur
* Définit aussi les sorts qu'il peut utiliser

### Spell

* SpellLoader: Classe qui gère le comportement des spells
* Spell: classe qui définit ce qu'est un spell et contient aussi un animationHandler

### GameMouse

* Classe en Singleton qui définit la souris du jeu

### GamePad

* Classe en Singleton qui définit les touches du jeu

### UtopiaGame

* Classe qui gère le cycle du jeu selon ses states

### World

* Classe qui gère le cycle de tout ec que le monde contient