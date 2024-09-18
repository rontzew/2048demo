# JavaFX 2048 game

 A small game to practice use of JavaFX. Why? Just out of curiosity to see how it works.

   At this point the game is a skeleton, as it is able to do the math and the update to the board. 
  

 Fixed issues with last update:

   -fixed some game logic where more than one addition operation is done (ex, on a row or column you have the following tiles: 4 2 2. The game adds the twos, then sees that there are now two 4s, and adds them as well with the initial user input);


Updates to make to this game in the future:

  -fix some game logic where it will add tiles even though the previous command produced no change (normally new tiles should appear only when tiles are added to each other and/or tiles are moved);

  -implement the game over condition when all tiles are occupied and and there is no addition possible on the board;

  -implement the game winning condition when you obtain the 2048 tile;

  -implement a score system to track current / high score;

  -add animations to the game for better following of the tiles (and visual aspect as well);

  -add new game, restart, start buttons;

  -for sure I will come up with many other ideas, let me see these ones through to the end though.
