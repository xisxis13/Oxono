# Oxono

![Java](https://img.shields.io/badge/Java-ED8B00?logo=openjdk&logoColor=white)
![JavaFX](https://img.shields.io/badge/JavaFX-2E6DA4)
![Maven](https://img.shields.io/badge/Maven-C71A36?logo=apachemaven&logoColor=white)

Oxono est un jeu de stratégie qui s'inspire du morpion, avec des mécaniques supplémentaires qui lui donnent plus de profondeur. Le but reste d'aligner des symboles sur le plateau, mais il faut aussi anticiper les coups de l'adversaire pour ne pas se faire devancer.

Au-delà du jeu, le projet met l'accent sur la logique de jeu, une conception modulaire et un code propre.

## Architecture

Le projet suit le modèle **MVC** (modèle - vue - contrôleur), ce qui sépare les règles du jeu de l'affichage et de la gestion des actions du joueur.

## Design patterns

Deux patterns ont été mis en place pour garder une structure claire et facile à faire évoluer :

- **Observer** : la vue se met à jour automatiquement dès que le modèle change.
- **Command** : chaque action du joueur est encapsulée dans une commande, ce qui permet d'annuler et de rétablir les coups (undo / redo).

## Installation

Il faut avoir [Java](https://www.java.com/) et [Maven](https://maven.apache.org/) installés.

**1. Cloner le dépôt**

```bash
git clone https://github.com/xisxis13/Oxono.git
```

**2. Se placer dans le dossier du projet**

```bash
cd cd Oxono/project/oxono-g62664
```

**3. Lancer le jeu**

```bash
mvn javafx:run
```

## Auteur

Alexis Ravasco, étudiant en dernière année de bachelier en développement d'applications, HELHa Mons.