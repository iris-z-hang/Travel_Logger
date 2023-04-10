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

### Instructions for Grader
- When you first run the program, you will have to maximize the resulting window to see the options. 
- To add multiple X's to a Y, or locations to the map, first choose either the visited or unvisited list. 
  Then choose Add Location. Type in the name (String), address (String), longitude (double), and latitude (double). 
- To look at the list of locations you have added for either the unvisited or visited list, click on View Location List.

- The visual component is a world map that is displayed on the top on the main page of the application. You will see it
  when the program is first run or if you hit Go Back.

- To save or load the file, click on Save or Load on the main page.

### Phase 4: Task 2
### Representative sample of events
Sun Apr 09 15:20:03 PDT 2023
Adding location to list.
Sun Apr 09 15:20:10 PDT 2023
Moving location to other list.
Sun Apr 09 15:20:16 PDT 2023
Adding location to list.
Sun Apr 09 15:20:21 PDT 2023
Removing location from list.
Sun Apr 09 15:20:26 PDT 2023
Adding location to list.
Sun Apr 09 15:20:32 PDT 2023
Calculating the distance between two locations.


### Phase 4: Task 3
### For the Future
In the future, I would like the refactor my program to utilize the composite pattern. With the composite pattern, I
could have the Composite as a list of locations and have my unvisited and visited lists extend that. I would then only 
have one list to maintain instead of two with the exact same items and the exact same methods. This would aid in 
the reduction of duplicate code which is present in my program. This would also be beneficial for further additions to
the functionality of my program. I would also like to figure out a way to reduce the duplication in my GUI class.


