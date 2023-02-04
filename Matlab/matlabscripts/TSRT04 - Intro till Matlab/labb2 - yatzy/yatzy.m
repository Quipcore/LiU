function [expectedValue,varience] = yatzy(amountOfExperiments)
%MONTECARLO Summary of this function goes here
%   Detailed explanation goes here

    %Tryvector -> index: which round, value: amount of tries for that round
    tryArray = zeros(1,amountOfExperiments);
    for i = 1:length(tryArray)
        tryArray(i) = fiveOfKind();
    end
    
    expectedValue = sum(tryArray)/length(tryArray); %Mean value
    
    %------------------------------------------------------------
    varienceSum = 0;
    for i = 1:length(tryArray)
        varienceSum = varienceSum + (tryArray(i)-expectedValue)^2;
    end
    varience = varienceSum/(length(tryArray)-1); % Migth need sqrt
    
    %------------------------------------------------------------ 
    distribution = zeros(1,max(tryArray));
    for i = 1:length(tryArray)
        distribution(tryArray(i)) = distribution(tryArray(i)) +1;
    end
    
    %------------------------------------------------------------
    e1 = [1,0,0,0,0];
    e5 = transpose([0,0,0,0,1]);
    A = [
        [0,1/6,1/36,1/256,1/1296]
        [0,5/6,10/36,15/216,25/1296]
        [0,0,25/36,80/216,250/1296]
        [0,0,0,120/216,900/1296]
        [0,0,0,0,120/1296]];
    
    probabilityArr = ones(1,length(distribution));
    for i = 1:length(probabilityArr)
        probabilityArr(i) = e1*A^(i)*e5;
    end
    
    %-----------------------------------------------------------
    hold on
    yyaxis left
    %histogram(tryArray)
    bar(distribution,1)
    xlabel('Tries the get five of a kind using monte carlo method')
    ylabel('Amount of rounds')
    
    yyaxis right
    probabilityArr = probabilityArr*100;
    bar(probabilityArr,1,FaceAlpha=0.7)
    ylim([0 15])
    ylabel('Probality to get X amount of tries (procent)')
    
    title("Tries to get five of a kind using the monte carlo method " ...
            + amountOfExperiments + " times")
    legend('Tries to get num','Probality(Procent)')
    hold off
end

