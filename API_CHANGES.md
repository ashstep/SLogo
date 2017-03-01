#API Changes

##External UI:
We removed the method passString from the External Frontend UI. We decided that 
a better way to pass the command String to the Controller is for the Controller to 
have a method to grab the String from its instance of View. 