clear
clc
close all

diceAmount = 5;
rounds = 1000;

[distribution,diceResult,mostCommon] = throwDice(diceAmount,rounds);


histogram(diceResult)
ax = gca;
ax.XTick = unique( rounds(ax.XTick) );
ax.YTick = unique( rounds(ax.YTick) );

disp("Count of each die: ")
disp(distribution)

disp("Most common die: ")
disp(mostCommon)



