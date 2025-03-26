# A Simple Game About Scheduling Trains

# ``` ABOUT  ``` 



### This program is a simulator for managing arrival and departure for different train traffic for different station  , here the player acts just like a station master . Trains are created at different intervals of time with some metadata , the player is supposed to modify the signals and the switches to stop trains at the station or let them pass without any interruption. The goal will be to prevent delays , achieve maximum throughput and most importantly not cause collisions .

![](https://raw.githubusercontent.com/SID-JCP/A-Simple-Game-About-Scheduling-Trains/refs/heads/main/docs/images/Screenshot%202025-03-26%20194626.png)

---

# ``` Components the Simulation ```


- ##   Tracks 

![Up and Down line](https://raw.githubusercontent.com/SID-JCP/A-Simple-Game-About-Scheduling-Trains/refs/heads/main/docs/images/Screenshot%202025-03-26%20195021.png)
	

###  There are 2 types of tracks 
   #### - UP line -  This is created as light grey colour . Trains on this track are supposed to move from left side of the screen to right side . The location is above centre line of the screen .
---
  #### - DOWN Line - This is created as dark grey colour . Train a are supposed to move from right to left on such tracks . The location is below the centre line of the screen.
---
 ###  A station is denoted as a map , each map may have 1 or more UP lines or Down lines . The track section which are the full length of the screen are called **MAIN** Lines .
	 
### Main Lines are the busy tracks on which the traffic flows . They pass through or close to different stations   , loop lines emerge from main lines which are beside platforms and then converge back into it . Trains are usually stopped on the loop lines for boarding and deboarding of passengers.

   ### The track section which connect Loop lines to Main lines are denoted by 3 colours which are - red  , light grey and dark grey  , these colours denote the different states of this section .   These track sections are tilted towards the left or the right depending on the type of line (UP/DOWN) they connect. 

### The 3 States are - 
	
   -  Disabled - This denoted in red colour , it means the train moving on the main line will keep moving on the main line and would not enter into the loop line .
   
	   ![Disabled](https://raw.githubusercontent.com/SID-JCP/A-Simple-Game-About-Scheduling-Trains/refs/heads/main/docs/images/Screenshot%202025-03-26%20194800.png)
---
 - Down To Up - This is denoted in light grey colour , same as the colour of up line track section . Train moves from down line to up line in this config or up line to the connected loop line which is above.
 ![Down to Up](https://raw.githubusercontent.com/SID-JCP/A-Simple-Game-About-Scheduling-Trains/refs/heads/main/docs/images/Screenshot%202025-03-26%20195334.png)

---

- Up To Down - This is denoted in dark grey colour , same as down line track section . Train moves from up line to down line in this config or down line to the connected loop line below the main line .   
![Up to Down](https://raw.githubusercontent.com/SID-JCP/A-Simple-Game-About-Scheduling-Trains/refs/heads/main/docs/images/Screenshot%202025-03-26%20195123.png)

---
### The states can be toggled by clicking the white circle which appears when hovering near the centre of the track section . 

- ##   Signals -

	### The signals are used to control the speed of the train or slow them down  . In railways the signals have 4 states , which are green , double yellow , yellow and red . The signals toggled by clicking them to change. 

   ### When a train crosses a signal , the signal turns red. 


	### There are 2 type of Signals  
	
	- Home Signal -  They are present on switches , on 4 sides . These signals can be controlled during the simulation if the state of the track section is disabled . Changing of signals by toggling of the switch section will be discussed later .   
	--- 
	- Block Signal - They are mainly present on main lines before and after stations . They cannot by controlled by the user until its red during the simulation . As the train keeps crossing more block signals , the previous ones get to a lower state . The 4th signal after red will always be green .  
	---

- ##   Trains -
	 

 










