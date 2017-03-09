# API Changes

## Frontend UI:
We removed the method passString from the External Frontend UI. We decided that 
a better way to pass the command String to the Controller is for the Controller to 
have a method to grab the String from its instance of View. 

We refactored the Frontend External API into separate interfaces for each part of the UI - 
IInputView and ITurtleView. This did not make any changes codewise, but allowed us to separate our
UI into separate objects and avoided having "unimplemented" methods in the menu and the turtleView.
As far as impacting other peoples' code, this change had almost no impact - it was just an 
organizational change.

We had to add a few methods to be initialized to our interface - pen width controller, colorPicker, and setBackground. We added these because we did not think through these methods needed to be public originally,
but their interactions with Controller and the View required them to be part of the API, instead of private
to the View. These changes were not major because we made the change relatively early on, so it was easy to extend the new interface into the Controller. These changes are for the better because they allow for easy communication between Model and Controller in our MVC system, and were easy to implement without disturbing the backend. 

We added a History interface. This was a pretty major change - we had planned for having a history, but did not realize that it would have to interact with other classes in our frontend and in Controller. This change is helpful for organization. 

## Backend API: