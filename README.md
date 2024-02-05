# Movlist

An Android app to list movies from [The Movie Database](https://www.themoviedb.org) (TMDB).

Home&nbsp;&nbsp; | Search&nbsp; | Details
:-------------------------:|:-------------------------:|:-------------------------:
![](https://github.com/paulohc/movlist/assets/18506267/4feeba34-e347-4fb4-9bba-2adad71df619) | ![](https://github.com/paulohc/movlist/assets/18506267/146531e0-1381-4c98-b7b2-baa03f02835f) | ![](https://github.com/paulohc/movlist/assets/18506267/7d81055c-008b-411d-8eaa-67005e653e8c)

## Setup

You need to setup a [TMDB API secret token](https://developer.themoviedb.org/docs/authentication-application) to fetch movies data.


Copy `.env.example` file and rename it to `.env`. So place your secret token at `TMDB_API_TOKEN`:
```
TMDB_API_TOKEN="Your token"
```

After that you'll be able to fetch movies data.
