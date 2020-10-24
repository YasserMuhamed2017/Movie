# Movies App (CS50 Android)

This project I created for implementing some different concepts I
learned in android track which teach me a lot of important concepts in
android development and those which I implemented here in the project. I
implemented some important core concepts in android like RecyclerView
which loads JSON data from movies database (TMDB) Web Server from
[www.themoviedb.org](https://www.themoviedb.org/) API,  
and extract some attributes to load in different activities in your app
and when you click on each image, it makes an intent for another
activity to show them in another activity.

## Features
* Discover most popular movies
* Discover top rated movies
* Get detailed information for each movie you select
* Show reviews for each movie related to each movie
* Show and play movies trailers

## Screenshots

![photo_2020-10-24_13-55-38](https://user-images.githubusercontent.com/26174282/97081153-bfe1f600-1600-11eb-95fc-513b0b9ee3cd.jpg)


![photo_2020-10-24_13-55-43](https://user-images.githubusercontent.com/26174282/97081154-c2445000-1600-11eb-8b32-53eaf8367bf7.jpg)


![photo_2020-10-24_13-55-47](https://user-images.githubusercontent.com/26174282/97081156-c3757d00-1600-11eb-9479-580e8aee5603.jpg)


![photo_2020-10-24_13-55-51](https://user-images.githubusercontent.com/26174282/97081157-c4a6aa00-1600-11eb-9624-c09ecdaba287.jpg)


![photo_2020-10-24_13-54-21](https://user-images.githubusercontent.com/26174282/97081159-c6706d80-1600-11eb-8218-41c6ca1b71e2.jpg)


![photo_2020-10-24_13-55-27](https://user-images.githubusercontent.com/26174282/97081160-c7090400-1600-11eb-805f-42acedb2ba49.jpg)

## Libraries

* [RecyclerView](https://developer.android.com/jetpack/androidx/releases/recyclerview)
* [Glide](https://github.com/bumptech/glide)
* [Volley](https://developer.android.com/training/volley/index.html)
* [OptionMenu](https://developer.android.com/guide/topics/ui/menus)

## Dependencies
```
implementation "androidx.recyclerview:recyclerview:1.1.0"
implementation 'com.android.volley:volley:1.1.1' 
implementation 'com.github.bumptech.glide:glide:4.11.0'
```

## Future Work
* I want to add a recyclerview for loading movies trailers on a cardview
  to show a cardview with a movie show and the name of it but it's a
  horizontal recyclerview not vertical and more complicated than I did
  right now.
* Also, I want to add a recyclerview with list of cardviews.
* Also, I added a Room database but I didn't complete it, I implemented
  it to complete it in a favorite button for each movie you like by
  inserting and deleting it from the database.
* Then, I will query this data from a favorite in the options menu for
  accessing it.
