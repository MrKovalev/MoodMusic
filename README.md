# Mood Music

# App description:
The application allows you to view popular groups of music performers, tracks, create your own track albums.
It is also possible to search for both the artist and individual tracks, followed by viewing detailed information, listening (if available)

# Technical description and implementation:
1. The application is split into packages component, shared, feature within one gradle module. This breakdown allows you to split your application into modules, if desired, without any problems. As part of the Clean Arhitecture modules with MVP (Moxy) for the presentation layer. The overall architecture makes it easy to make changes and expand functionality as needed.
2. Requests to the Last.fm server API are implemented using Retrofit2 + Rxjava2
3. To implement DI, the Dagger2 dependency injection library is used
4. Used ORM Room + Rxjava2 to save and load selected albums with tracks
5. Added Butteknife library to simplify work with view elements
6. Implemented error handling and interception
7. The main logic is covered by unit tests
8. Implemented correct processing of saving state when changing orientation
9. The screen of tracks and artists can be tested without the Internet using stubs

[![Screenshot-20190307-225452.png](https://i.postimg.cc/mDvQkYG7/Screenshot-20190307-225452.png)](https://postimg.cc/hz0J37vG)

[![Screenshot-20190307-225607.png](https://i.postimg.cc/W1zhRdBV/Screenshot-20190307-225607.png)](https://postimg.cc/Jsw1Zz7Y)

[![Screenshot-20190307-225655.png](https://i.postimg.cc/q7nB0yQP/Screenshot-20190307-225655.png)](https://postimg.cc/1gm1rgkB)

[![Screenshot-20190307-225525.png](https://i.postimg.cc/xTTdHkTm/Screenshot-20190307-225525.png)](https://postimg.cc/mhJRWg9Z)

