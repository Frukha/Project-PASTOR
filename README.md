# Projet Chuck Norris API

## Introduction

Dans ce "README", vous pourrez trouver des explications sur le contenu de ce projet. Ce dernier est destiné à l'unité "OUAP 4331 : Android Development For Smartphones With Kotlin", supervisée par Nicolas Duponchel.


# Part 1 - Create a UI List component

## 1. Create a static list of jokes

Il a tout d'abord fallu créer un Object capable de contenir 10 blagues que l'on peut trouver sur https://api.chucknorris.io/.

## 2. RecyclerView instantiation

Ici, nous allons ajouter notre première RecyclerView qui sera un outil qui nous permettra d'afficher les données que l'on voudra afficher sur notre téléphone/émulateur.  
Pour que ce dernier puisse fonctionner correctement, il nous faudra aussi un LayoutManager et un Adapter (qui sera l'objectif du prochain point).

## 3. Custom Adapter

Il est temps de créer notre Adapter qui stockera notre liste de blagues pour l'instant. Pour cela, il faut qu'il "extends" le ViewHolder de notre RecyclerView. Ensuite, il faut Override 3 fonctions qui sont : onCreateViewHolder (qui servira à créer une TextView que l'on affichera), onBondViewHolder (qui transmet la blague à afficher) et finalement getItemCount (qui nous permets de savoir le nombre de blagues).

## 4. Custom view for items

On s'occuper maintenant de l'esthétique de notre application afin que sa présentation soit à notre goût.  
J'ai choisi de mettre une marge entre les blagues afin de faciliter la lecture et, à l'aide de wrap_content, il est possible d'avoir des écarts réguliers entre les blagues.  
Nous avons aussi dû changer la TextView à l'aide de joke_layout et LayoutInflater.


# Part 2 - Fetch jokes

## 1. Create the data model matching the API

Il nous faut maintenant créer une data class nommée Joke et l'accompagner du tag @Serializable.  
Dans cette dernière, il a aussi fallu ajouter les differentes valeurs correspondant aux catégories.  
Le fichier JokeSerializationTest.kt sera là pour vérifier si notre code est correcte ou non.

## 2. Import Retrofit & Rx Java

Dans cette partie, nous avons seulement eu à faire des imports pour avoir Retrofit et RxJava2. Cependant, pour que ces deux puissent fonctionner ensemble, il a aussi fallu importer l'Adapter et le Converter de Jake Wharton.  
Nous avons aussi donné à notre application l'accès à internet.

## 3. Retrofit usage

Durant cette étape, notre but sera de créer l'interface JokeApiService qui nous servira à donner des Joke venant du site internet https://api.chucknorris.io/ à l'aide du lien qui nous donne une blague aléatoire: https://api.chucknorris.io/jokes/random

## 4. Call Api to get one Joke

Nous avons créé jokeService dans MainActivity.kt ainsi qu'une fonction appelé dans ce code retrieveJoke qui nous servira à ajouter des Joke dans l'Adapter afin de pouvoir les afficher.  
Pour l'instant, notre Joke s'affichera dans Logcat avant de que l'on ne décide de l'afficher sur l'application plus tard.

## 5. Leaks killer

La création d'un CompositeDisposable a été nécessaire afin de se débarasser de la Joke une fois que cette dernière a été transmise.  
Il faut donc override la fonction onDestroy afin que cette dernière clear notre compositeDisposable.


# Part 3 - Display jokes on screen

## 1. Display a single Joke

A partir d'ici, nous n'aurons plus besoin de notre liste "hardcoded".  
A la place, il y aura maintenant listOf<Joke> dans laquelle nous ajouterons une Joke pour chaque appuie du bouton à l'aide de addJoke.

## 2. Add a loader

Afin de savoir lorsque notre application cherche des informations ou non, un barre de progression a été ajouté au layout.  
Cependant, ces chargements sont trop rapides alors un delay a été implémenter afin de pouvoir apercevoir le chargement.

## 3. Make the call for multiple jokes with Observable

Vu qu'afficher une blague à la fois est un peu long, il est maintenant possible d'en afficher 10 à la fois à l'aide de repeat.  
Il faut donc changer on success avec onNext et rajouter onComplete.

## 4. Reload new jokes

A la place d'appuyer sur un bouton, on préfèrerait que de nouvelles blagues apparaissent lorsque l'on atteint le bas de la page.  
Il a fallu changer le constucteur de JokeAdapter et donc notre Adapter qui était présent dans le MainActivity.  
Afin que l'on détecte bien le moment ou il faut rajouter des blagues, le test a été placé dans onBindViewHolder et, à l'aide de son paramètre "position", il est donc possible de suivre quelle est la dernière Joke chargée à l'écran. En comparant cette valeur avec le dernier index de la liste, il est possible de savoir quand nous arrivons vers le bas de la page.  
Maintenant, les blagues se rafraichissent en atteignant le bas de la page.


# Part 4 - Make UI great again

## 1. Manage screen rotation

Jusqu'à maintenant, une rotation de l'écran forçait les blagues à être rechargées et donc changées.  
Pour remédier à cela, il va falloir override deux fonctions: onSaveInstanceState et onRestoreInstanceState.  
Cela nous permet de garder la liste que l'on avait avant la rotation, et pour qu'aucune blague ne se rajoute pas, il faut s'assurer que retrieveJoke ne s'active pas durant une rotation.

## 2. Custom Joke View (Commencé mais pas terminé)

## Je ne suis pas aller plus loin dans le sujet