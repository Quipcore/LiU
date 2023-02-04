function [tries] = fiveOfKind()
%FIVEOFKIND A function that return how many tries it takes to optimaly get
%five of a kind
%   Detailed explanation goes here

    diceAmount = 5;
    [distribution,diceResult,mostOccuring] = throwDice(diceAmount,1);

    matchingArray = mostOccuring(1)*ones(1,diceAmount);
    tries = 1;
    %diceResult = zeros(1,diceAmount);
    while ~isequal(diceResult,matchingArray)
        for i = 1:diceAmount
            if diceResult(i) ~= mostOccuring(1)
                diceResult(i) = randi([1 6]);
            end
        end
        mostOccuring = find(distribution == max(distribution));
        matchingArray = mostOccuring(1)*ones(1,diceAmount);
        tries = tries + 1;
    end
end

