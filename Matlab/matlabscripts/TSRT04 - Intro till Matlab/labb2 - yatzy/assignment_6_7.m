clc
clear

close all

%Assingment 2.6,2.7
amountOfExperiments = 1e4;
[expectedValue,varience] = yatzy(amountOfExperiments);

disp('ExpectedValue: ')
disp(expectedValue)
disp('Varience:')
disp(varience)