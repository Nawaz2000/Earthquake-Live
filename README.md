# Earthquake-Live
A real-time Command Line (CLI) earthquake reporter tool.

# Description
Earthquake Live is a tool that reports real-time earthquakes based on your selected filter. It currently displays global earthquake data of last 24 hours. The various commands in the program helps helps you filter data based on different parameters.

**Command Usages :-**
<pre>
$ help                          # Opens the usage menu  
$ magnitude-filter              # generates a list of earthquakes based on the minimum and maximum magnitudes you enter.  
$ depth-filter                  # generates a list of earthquakes based on the minimum and maximum depths you enter.  
$ location-filter               # generates a list of earthquakes close to your provided location bounded by the maximum distance that you provide.  
$ minMag-filter                 # generates a list of earthquakes lower bounded by the minimum magnitude you enter.  
$ help-docs                     # opens up the documentation of some commands  
$ exit                          # Exit!  
$ clear                         # clears the screen  
</pre>
**Some documentations on the above commands used in the program...**
<pre>
@ depth-filter          # In seismology, the depth of focus or focal depth refers to the distance
                          below the earth's surface at which an earthquake occurs. It is always
                          negative. For example, when asked by the program, setting

                          Lower blound depth = -15000 and Upper bound depth = -5000

                          will display earthquakes which occured at a depth between 15km and 5km below
                          the earth's surface. When asked, the input values must be integers.


@ location-filter       # Location- Refers to any location in latitudes and longitudes.
                          "location-filter" asks for your current or any other location's coordinates
                          i.e., latitude and longitude

                          (Ex- 22.572 and 88.363, which are the coordinates of Kolkata, India)

                          (The maximum distance in meters- refers to the radius from your location),

                          The program takes your given location and displays earthquakes within
                          your inputted radius. When asked for, the input values must be decimals.


@ magnitude-filter      # Earthquake size is a quantitative measure of the size of the earthquake at
                          its source. The Magnitude Scale measures the amount of seismic energy
                          released by an earthquake. Enter the minimum and maximum magnitudes to get
                          list of earthquakes within that range. When asked, the input values must
                          be decimals.
</pre>                          
# Version
v1.3

# Features
1. **Real-time:** All data displayed are realtime and from the last 24 hours.
2. **Filters:** Includes 4 different filters to personalize your result.
3. **Accuracy:** The data provided to you are accurate as per international standards.

# Installation
This is a simple command line program and you must have jdk installed to use it
...
1. Make sure you have java installed in your PC
2. Clone the entire project into your pc or copy only the Executable folder to your desired location
3. Head to the Executable folder
6. Double click on the run.bat file
7. Use the commands from the command usages...
8. Enjoy!

# Usage
Educational purpose....
Don't worry, just make it your own!

# Project status
In development stage
