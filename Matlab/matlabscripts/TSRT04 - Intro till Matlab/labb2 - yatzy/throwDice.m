function [distribution,diceResult,mostOccuring] = throwDice(diceAmount,rounds)
%THROWDICE a function that simulates throwing an amount of dice x rounds
%   Detailed explanation goes here
    
    %Create an array of size rounds*diceAmount with numbers between and
    %including 1 trough 6
    diceResult = randi([1 6],1,rounds*diceAmount); 
    
    %reduce the diceVector to show how many times a we landed on a specific dice number
    distribution = zeros(1,6);
    for i = 1:length(diceResult)
        distribution(diceResult(i)) = distribution(diceResult(i)) +1;
    end
    
    %Find the index of which the biggest number is located
    mostOccuring = find(distribution == max(distribution));

end