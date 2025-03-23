# A Simple Game About Scheduling Trains

# ``` ABOUT  ``` 

### This program is a simulator for managing arrival and departure for different train traffic for different station  , here the player acts just like a station master . Trains are created at different intervals of time with some metadata , the player is supposed to modify the signals and the switches to stop trains at the station or let them pass without any interruption. The goal will be to prevent delays , achieve maximum throughput and most importantly not cause collisions .

---

# ``` Components the Simulation ```


- ##   Tracks -
	### There are 2 types of tracks 
	- UP line -  This is created as light grey colour . Trains on this track are supposed to move from left side of the screen to right side . The location is above centre line of the screen .
	---
	- DOWN Line - This is created as dark grey colour . Train a are supposed to move from right to left on such tracks . The location is below the centre line of the screen.
	---
	 ###  A station is denoted as a map , each map may have 1 or more UP lines or Down lines . The track section which are the full length of the screen are called **MAIN** Lines .
	 
	### Main Lines are the busy tracks on which the traffic flows . They pass through or close to different stations   , loop lines emerge from main lines which are beside platforms and then converge back into it . Trains are usually stopped on the loop lines for boarding and deboarding of passengers.

   ### The track section which connect Loop lines to Main lines are denoted by 3 colours which are - red  , light grey and dark grey  , these colours denote the different states of this section .   These track sections are tilted towards the left or the right depending on the type of line (UP/DOWN) they connect. 

	### The 3 States are - 
	
	- Disabled - This denoted in red colour , it means the train moving on the main line will keep moving on the main line and would not enter into the loop line .
	 ---
	 - Down To Up - This is denoted in light grey colour , same as the colour of up line track section . Train moves from down line to up line in this config or up line to the connected loop line which is above.
	---
	- Up To Down - This is denoted in dark grey colour , same as down line track section . Train moves from up line to down line in this config or down line to the connected loop line below the main line .   
	---
	### The states can be toggled by clicking the white circle which appears when hovering near the centre of the track section . 

- ##   Signals -

 




# ```NEXT MAIN ELEMENTS NEEDED NEXT```

- ### Train : A train class needs to be created which would move on the provided track section and would change according to the switch . The train needs to get the data of the next signal on the section to change it's speed accordingly or stop.
 
- ### Dispatch System : Similar to the map objects where different track objects are created and put in a list for the controller to manage and draw them , a similar system needs to be creared to construct trains and specify there attribues like departure time , has a stop or not and others . Which would also be updated and drawn by the controller .

- ### Interaction : Train dispatch would depend on the game clock , movement by the controller . Similarly all other elements are managed by the controller , there needs to be an interction system to properly allow them to work such as train stop on a signal .





