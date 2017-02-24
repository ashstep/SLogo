# Part I

## What about your API/design is intended to be flexible?

We are basing our API/design structure off of the Model-View-Controller design pattern. The front-end and back-end components will only interact with the controller. The API is modeled after this design concept so the internal API contains only the basic methods that pertain to the view. It does not have any methods that would pertain to the parsing. One such method for the internal front end is `displayTurtleMovements(List turtleStates)`. The flexibility of this method is that it only takes in a generic turtleState that we define and can manipulate.

## How is your API/design encapsulating your implementation decisions?

Our methods used in the API are all general methods, so you don't know what is happening behind the scenes. Each method has several tasks that will be defined later on when the interface is implemented. Overall, the idea is that the internal back-end API will control the turtle, parse commands, and update the turtle state. The external back-end API will be used to receive commands from the front-end, return command errors to the front-end, and communicate turtle state changes to the front-end. The internal front-end API will control all of the visual elements of the program, and will also provide access to the help page and command history. The external front-end API will communicate commands to the back-end, listen for turtle state updates, and listen for command errors. These tasks are broken down in the methods in the API.

## What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?

The main responsibilities for errors will be displaying messages and having the user understand that there 
are errors. This would be in the form of a pop up. For example, if the user inputs a wrongly formatted string, an exception would be thrown and the user would have to close out the error message and acknowledge that the message inputed was formatted incorrectly.

## Why do you think your API/design is good (also define what your measure of good is)?
After discussing the API with my partner for this lab, I realize that I could make some methods even more general. However, it is good because it contains the basic methods needed for all classes and provides the necessary methods for future extension. Good means that there shouldn't be too many changes made to the API to support future features.

# Part II

## How do you think Design Patterns are currently represented in the design or could be used to help improve the design?

One design pattern that could be used is generics. Currently there is a method `public void updateHistory(String history)`. One thing that could change is first the method is pretty specific to History. However, there might be several text objects or list items that need to be displayed. With generics, we would be able to declare a generic 'T' that would be updated. 

## How do you think the "advanced" Java features will help you implement your design?

Reflections will be key for decreasing duplicate code. The back-end has to implement many different commands and reflections could be used to simplify the back-end code. ENUMs could be used for defining different properties of the turtle. Finally, as mentioned before, I would use generics for updating information on the display (UI).

## What feature/design problem are you most excited to work on?

I'm most excited to work on the design of the overall structure. Before, I did not follow the Model-View-Controller design pattern. I hope to use more interfaces properly to decrease duplicate code and to make sure there is a well designed hierarchy in the code.

## What feature/design problem are you most worried about working on?

I'm most worried about using all of the new advanced Java features because I never had any practice with them and I'm not sure if I will use them properly. In particular, I'm nervous about using generics in the view.

## Come up with at least five use cases

Use Case 1:
- The user inputs “circle 50”
- The UI passes the string circle 50 string to the Controller, which passes it to the Command Parser
- The Command Parser matches ‘circle’ to its built-in “draw circle” command, and creates a new drawCircle command with radius 50. It passes this command back to controller, which passes it to Turtle, which sets its isPencilDown to True and then calls drawCircle. 
	
Use Case 2:
- The user inputs ‘showTurtle’.
- The command is passed through the system as described above, calling a private method in Turtle that sets its isVisible field to True. This information is passed through the Controller to the UI, which displays the Turtle.

Use Case 3:
- The user inputs ‘qj 50’, the program is able to recognize that the language is Chinese
- The program uses the Chinese property file to recognize that the command is equivalent to  'fd 50' and executes.

Use Case 4:
- The user inputs ‘random shape’
- The program generates a random number which is used in shape created (i.e. loop repetitions).
Visual execution.

Use Case 5:
- The user types 'pu fd 50 pd fd 50' in the command window and sees the turtle move twice (once without a trail and once with a trail).
- The same process is followed as in 'fd 50', but the boolean flag is set to true for penDown in the turtle itself when pd is processed, telling the turtle to draw a line as it moves.