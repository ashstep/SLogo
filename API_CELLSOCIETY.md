#APIs

Vishnu Gottiparthy (vtg4), Harry Liu (hkl6), Christian Martindale (cam119), and Ashka Stephen (aas74)

1. Internal simulation - Cell subclasses with specific fields for each simulation such as 
GOLCell, etc.
    Also includes State enum subclasses with specific updateneighbors overrides and States.
    Also includes Society subclasses with specific updating rules for each simulation.
    
2. External simulation - Superclasses Cell, Society, and State
3. External Configuration - XML parser methods
4. Internal Configuration - XML data formatting
5. External Display - UI (interacts with any generic Society)
6. Internal Display - Animator (only interacts with UI, specifics functionality not 
important to program's functionality)