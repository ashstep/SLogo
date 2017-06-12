SLogo
===================
Ashka Stephen, Vishnu Gottiparthy, Christian Martindale, Harry Liu


Start date: 2/14/2017
End date: 3/10/2017
Estimated time spent per person: 25-30 hr

Front-end developers: Christian Martindale and Harry Liu
Back-end - Command Parsing: Ashka Stephen
Back-end - Controller/Turtle/Commands: Vishnu Gottiparthy



### Project Overview
Our goal for this project was to provide an integrated development environment, or IDE, that supports users to write SLogo programs.
The Main class is located in the Visuals package. Resource bundles for languages (English, Spanish, and Chinese are currently supported) are located in the resources.languages package. Extra features include the ability to create multiple movable turtle UIs, the ability to select any image to serve as the turtle, full Spanish and Chinese support, and clickable History buttons to instantly rerun commands. THe list of commands that have been implemented can be found at: http://www.cs.duke.edu/courses/compsci308/spring17/assign/03_slogo/commands.php. 

Java concepts used include: Reflections, Enumerations, Generics, Inheritance Hierarchies, Abstraction, Lambda Functions, etc.

## High Level Design: Overview
On a broad scale, our design choices for the project follow a Model-View-Controller design pattern. We chose this because it allows for a lot of encapsulation and thus less duplicated code and interclass dependencies through the program. The project uses a read-evaluate-display loop to control the turtle and change its properties based on what the user inputs as a command.Our design goal for this project was to properly use interfaces, abstract classes, and encapsulated methods to keep each component of the project independent of the others. Additionally, we made use of new concepts we learned in class such as reflection, enums, generics and other concepts (such as those in functional programming) covered in lecture.

## High Level Design: Front-end and Back-end
The front-end and back-end components only interact with the Controller.java class (the middleman). The backend controls the turtle, parses commands and updates the turtle state (in other words, the internal backend API). On the other hand, the external backend API deals with the retrieval of the commands from the front-end and returning the executed command (in terms of a changed turtle state) to the front-end. On the other side, the internal front-end API controlled visuals of the program, the command history, the help page, creation of new windows, etc. The external front-end API works with connecting the backend by passing commands to the back-end, listening for turtle state updates and command errors.



----------
