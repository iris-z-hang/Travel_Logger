# **Travel Logger**
### Iris Zhang

The goal of my project is to develop an application that helps travellers when they arrive at a new city. The user
will be able to input the name, address, and coordinates of a destination they want to visit. The application will 
then keep track of this list for the user. There will also be an option to input places already visited. At the end
of their trip, the application will tell the user how many kilometers they travelled in that city. The target for this
application is people that like to keep track of where they have gone and where they have left to go.

This project is interesting to me because from my previous university degree, I have a certificate in *Geographic 
Information Systems* (GIS). In my GIS classes, we created maps using applications such as ArcGIS Pro and ENVI. I spent a lot
of time inputting coordinates and locations in geodatabases. I think it would be useful and fun to develop an
application that has some functionality of GIS applications, but with a more casual, user-oriented purpose.

### User Stories

- As a user, I want to be able to add a location to either my unvisited or visited list
- As a user, I want to view the list of unvisited and visited locations
- As a user, I want to be able to move locations from unvisited to visited or vice versa
- As a user, I want to check the distance between two locations on the unvisited/visited lists or not on the lists

- As a user, I want to be able to save my map to file (if I so choose)
- As a user, I want to be able to load my map list from file (if I so choose)

The MapFunctions UI class implementation was inspired/aided by the FitLifeGymChain program found at 
https://github.students.cs.ubc.ca/CPSC210/LongFormProblemSolutions/tree/main/FitLifeGymChainComplete

The data persistence package was based on the JsonSerializationDemo found at
https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo