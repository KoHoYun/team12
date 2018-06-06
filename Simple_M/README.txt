/***

CAU SE 2018 Spring Term Project: Development of SimpleMerge

TEAM 12
Hoyun Ko | Youngeun Kim | Seora Lee
Minjung Lim | Jihyo Han | Chaewon Hwang

***/


The main function of SimpleMerge is to compare and merge the files.




How to execute?

1. Press a "Load" button and choose a file in the file system. Then, the contents of the file will be displayed on chosen edit panel.

2-1. If user wants to edit the strings shown in the edit panel, press a "Edit" button.
2-2. Press a "Save" button, to save the edited content into the file.

3. If user press a "Compare" button on the center of the window, the program should display the differences between two files with a colored font/background.
  // Yellow background: corresponding line has differences.
  // Orange background with red font: specify the different part of the yellow colored line.
  // Gray background: Added line with blank
  
4. After the comparison, the user should be able to traverse the blocks indicating the differences and merge them.
4-1. Copy to right (>): copies the selected block in the left panel to the file shown in the right panel
4-2. Copy to left (<): copies the selected block in the right panel to the file shown in the left panel
