# A Simple Game About Scheduling Trains
---
# ``` ABOUT  ``` 

### Just like how an ATC schedules and controls flights arrivals anf departures from an airport runway , here arrival and departure of trains is controlled by the player for different stations just like a station master . Trains are created at different intervals of time with some metadata , the player is supposed to modify the signals and the switches to stop trains at the station or let them pass without any interruption. The goal will be to prevent delays , achieve maximum throughput and most importantly not cause collisions .

---

# ``` SOME KEY POINTS ```

 - ###  It uses the java swing library for the graphics . Runs on a single threaded game loop with the **update()** and the **draw()** methods for display of the graphical elements. 

 - ###  Another threaded class is used for the in-game clock which is used for scheduling the departure of trains . 

 - ### The screen is divided into a grid which each veritical line being a place where an object can be drawn such as a signal or switch . Horizontal lines are regions where tracks are drawn there is a fixed gap between them . The grid is recalculated for each thread cycle by getting the current window **height** and **width**  thus is dynamically updated .

 - ### Track Section class is used to create track objects . The tracks are drawn either the full width of the screen which is the **Main Line** or by specifying the length and position in the constructor. Tracks are divided into 4 types ```UP``` , ```DOWN``` , ```UP_START``` , ```UP_END``` , ```DOWN_START``` and ```DOWN_END``` . These types depict is the track is a **Main Line** or **Loop Line** or part of a **Switch** . Additional data is provided for creation of switches which require the 2 routes it is connecting, discussed later .

 - ### Signal class is used to create signal objects . It is drawn on either the **switch** object or by specifying the vertical position count from the left in 2 different constructors . Both constructors take a track object argument. I decide to use 2 types of singals called ```HOME``` and ```BLOCK``` . Home     singal is used near stations and on switches and block signals are on the mainline . Signals have 4 states which are  ```RED``` , ```YELLOW``` , ```DOUBLE YELLOW``` and ```GREEN``` , the meanings of their states will be explained later . 


---

# ```NEXT MAIN ELEMENTS NEEDED NEXT```

-### Train : A train class needs to be created which would move on the provided track section and would change according to the switch . The train needs to get the data of the next signal on the section to change it's speed accordingly or stop.
 
-### Dispatch System : Similar to the map objects where different track objects are created and put in a list for the controller to manage and draw them , a similar system needs to be creared to construct trains and specify there attribues like departure time , has a stop or not and others . Which would also be updated and drawn by the controller .

-### Interaction : Train dispatch would depend on the game clock , movement by the controller . Similarly all other elements are managed by the controller , there needs to be an interction system to properly allow them to work such as train stop on a signal .





