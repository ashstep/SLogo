# Refactoring

We chose to refactor the History class. It had some public methods but it was not
part of any interface, although it definitely interacted with other parts of the project.
I cobbled it together in one day before basic implementation was due, so it was pretty 
gross. 

We added better documentation to all the methods in History and removed 2 extraneous
methods that were not actually called anywhere else in the program. We then created
an IHistory interface that the History class could implement in order to add it to the
API. This allows other people to use our History in other ways or types of program.