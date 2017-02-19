# Design
> Vishnu Gottiparthy , Harry Liu, Christian Martindale, and Ashka Stephen

## Introduction
This program will allow a user to control a turtle object through simple text commands. It will use a read-evaluate-display loop, like in the Logo language, to control the turtle and change its properties based on each user input. The Model-View-Controller design pattern will serve well for this program because it allows encapsulation of the functionality of the command parser and turtle properties, and only requires the user to deal with the text commands itself. 

## Design Overview
Our design goal for this project is to properly use interfaces, abstract classes, and encapsulated methods to keep each component of the project independent of each other. To do this, we will design the structure off of the Model-View-Controller design pattern. The front-end and back-end components will only interact with the controller. In other words, the controller package will be the “middleman”. It will be functionally similar to the main class (see diagram below).

![Design Diagram](slogo_design.jpg "Design Diagram")

The internal back-end API will control the turtle, parse commands, and update the turtle state. The external back-end API will be used to receive commands from the front-end, return command errors to the front-end, and communicate turtle state changes to the front-end. The internal front-end API will control all of the visual elements of the program, and will also provide access to the help page and command history. The external front-end API will communicate commands to the back-end, listen for turtle state updates, and listen for command errors.

## User Interface
One main function of the GUI is to get input from the reader. The input would be in the form of a string, which would be sent to the controller and the command parser. One such way is to have a text input field. Execution of commands will be displayed. If errors occur in the input by the user, error messages in the form of a pop-up will be displayed near the command line prompt.

![GUI Diagram](GUI.jpg "GUI Diagram")

The display itself will be a simple window containing the turtle and any objects it has created such as lines. There will also be a command history window next to the turtle display with the last few commands given to the turtle displayed in chronological order.

## API Details 

### Internal Backend API

- Instantiate turtle
	- Create and hold the active turtle
- Command parsing (identify turtle actions)
	- Break down raw text into commands and tell the turtle what to do
	- Command error checking: error is caught in the command parser, which short-circuits the animation loop
- Updating turtle state
	- Based on command parser output, tell turtle to update itself

### External Backend API

- Communicate turtle state to frontend
	- Store previous and next state of turtle in a data structure
	- Return structure to frontend
- Communicate errors to frontend
	- Popup - if error causes large issue in program (seldom)
	- Command window - all errors displayed (default)
- Accept command input from frontend
	- Parse, create object, pass object to turtle instance

### Internal Frontend API

- Display turtle’s movements
	- Unpackage collection object (new state) and render (render continuously updated frames in accordance to when loop is run)
- Draw lines
- Set background color
- Set line color/size
- Set/upload image for a turtle
- Track command history
	- Store a text list of all previous commands
- Access help page
	- Stowable HTML formatted command list
- Set language
	- ResourceBundles/properties files

### External Frontend API

- Send command input to backend
	- Accept text input and pass back as raw text
- Accept turtle state from backend
	- Parse data structure with turtle states into frontend actions
- Listen for errors from backend
	- If error is thrown, display popup or error text

## API Example Code

Use Case 1:
- The user types 'fd 50' in the command window, sees the turtle move in the display window leaving a trail, and has the command added to the environment's history.
- The string 'fd 50' gets passed to Controller, which passes it to the command parser, which matches fd with the built-in forward movement command and creates a forward movement object with parameter 50 units. Command parser passes this movement object to Controller, which passes the command object to Turtle.
- Turtle accepts the command object, reads it, and calls its internal methods that updates its position in the current direction by +50 units in the current facing direction, and tells history tracker to add fd 50 to its tracker. The Turtle then passes its newly updated fields back to the Controller, which then tells the UI to display the turtle in its new position.

Use Case 2:
- The user types '50 fd' in the command window and sees an error message that the command was not formatted correctly.
- The string is passed to controller, which passes it to command parser. Command parser fails to match 50 to an operation, and short-circuits the current iteration of the game loop, bypassing Turtle entirely, keeping the Scene’s state the same, and displays an error message in the UI.

Use Case 3:
- The user types 'pu fd 50 pd fd 50' in the command window and sees the turtle move twice (once without a trail and once with a trail).
- The same process is followed as in 'fd 50', but the boolean flag is set to true for penDown in the turtle itself when pd is processed, telling the turtle to draw a line as it moves.

Use Case 4:
- The user changes the color of the environment's background.
- The user inputs something like ‘color blue’ to the command line.
- The string is passed through Controller to Command Parser, which matches ‘color’ to its built-in color change method. It returns blue, passes the blue command to Turtle, which uses its setColor method to pass blue to Controller, which sets the UI background to blue.

Use Case 5:
- The user inputs “circle 50”
- The UI passes the string circle 50 string to the Controller, which passes it to the Command Parser
- The Command Parser matches ‘circle’ to its built-in “draw circle” command, and creates a new drawCircle command with radius 50. It passes this command back to controller, which passes it to Turtle, which sets its isPencilDown to True and then calls drawCircle. 
	
Use Case 6:
- The user inputs ‘showTurtle’.
- The command is passed through the system as described above, calling a private method in Turtle that sets its isVisible field to True. This information is passed through the Controller to the UI, which displays the Turtle.

Use Case 7:
- The user inputs ‘qj 50’, the program is able to recognize that the language is Chinese
- The program uses the Chinese property file to recognize that the command is equivalent to  'fd 50' and executes.

Use Case 8:
- The user inputs ‘random shape’
- The program generates a random number which is used in shape created (i.e. loop repetitions).
Visual execution.

Use Case 9:
- The user inputs ‘fiesta’
- The command is passed as before to the Command Parser, which matches fiesta to its built-in fiesta command.
- The Turtle receives the fiesta command object, and begins to update its orientation by a random degree amount between 0 and 180 every frame for the next 300 frames. The background color also changes to a random color every 20 frames.

## Design Considerations 

### Consideration 1: How much power to give the controller?
We discussed at length which classes will serve as the connectors between the front and backend. The Controller class, we decided, would take in the command line argument, create an object out of it by parsing (separate class) and the turtle registers that object. It will then add an instance to the collection object (we have designated above as the “structure” class). After a command is executed this will be passed to the Controller which renders it on the GUI. This makes our program’s structure very similar to that of the Model View Controller template. 

We initially thought of having a separate class which dealt with the collection of turtle instances and passed that on (making the program’s structure circular in nature), however decided against it simply due to following a more cohesive design. At this point in time, we did not see many significant tradeoffs of using one over the other and thus decided to go with the model that more closely resembled the MVC design in class readings. This resulted in giving the Controller package the ability to connect frontend and backend.

On a similar note, as can be seen by the diagram, the command line parser lies within the Controller as well. The reason we decided to do this was, again, to give the Controller power to deal with the front end. 

### Consideration 2:  Abstraction
Like in the last project, we will use abstraction to ensure that all the classes that work similarly do not duplicate code. For example, the commands for the turtle will have an abstract class which all commands are extensions of. We decided this after thinking about the commonalities between commands (such as the fact that they all operate on the instance of the turtle).

### Project Ambiguities:
The only ambiguity in this project is how it will be graded. Ouch.

### Dependencies:
Most of our dependencies are apparent in the visualization. The parser has a dependency on the controller, the command objects which the turtle instance takes in (which the parser creates) has a dependency on the controller (due to initialization by the parser), and the collection object (which in the diagram is named “structure”) is dependent on the turtle since each instance of the turtle is passed on and stored by the collection object.

### Assumptions
We are assuming that the input will be in one of the languages specified (but are also implementing error checking in case this is not the case). We are also assuming that there will be one turtle, but in case there are more turtles, we are ensuring that our code is extendable so that multiple collection objects are passable and thus many turtles will be able to move. 

## Team Responsibilities
Back-end developers: Ashka Stephen, Vishnu Gottiparthy
Front-end Developers: Harry Liu, Christian Martindale
Roles are not strictly defined and everyone will help each other out where needed. The back-end is a lot more work.
