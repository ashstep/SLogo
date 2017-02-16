When does parsing need to take place and what does it need to start properly?
Pass the input string to the backend
What is the result of parsing and who receives it?
MVC, split command into verb-object structures (what is it doing/how much, and what is being acted upon)
Objects representing command abstractions - determines if the given command is valid/supported by the program
When are errors detected and how are they reported?
Invalid string input, exception thrown. Pass string to backend, backend will try to match the command to a structure it recognizes, if it cannot do so it throws an exception
What do commands know, when do they know it, and how do they get it?
Strings passed to processor that will turn them into command object sequence, which is then passed to turtle, which calls methods on itself based on the information it receives
How is the GUI updated after a command has completed execution?
Check turtle position, check if draw is on or off, (line object with parameters?) , check stamp on/off, update frames and use a consistent animation.


The user types 'fd 50' in the command window, sees the turtle move in the display window leaving a trail, and has the command added to the environment's history.
    The string 'fd 50' gets passed to Controller, which passes it to the command parser, which matches fd with the forward movement command and sets its amount to 50 units
    . Controller tells Turtle to update its position field by 50 units in the current facing direction, and tells historyTracker to add fd 50 to its tracker. The Turtle then 
    passes its newly updated fields back to the Controller, which then tells the UI to display the turtle in its new position. 
The user types '50 fd' in the command window and sees an error message that the command was not formatted correctly.
    The string is passed to controller, which passes it to command parser. Command parser fails to match 50 to an operation, and returns an error to the console.
The user types 'pu fd 50 pd fd 50' in the command window and sees the turtle move twice (once without a trail and once with a trail).
    The same process is followed as in 'fd 50', but the boolean flag is set to true for pencilDown in the turtle itself when pd is processed, telling the turtle to draw a line.
The user changes the color of the environment's background.