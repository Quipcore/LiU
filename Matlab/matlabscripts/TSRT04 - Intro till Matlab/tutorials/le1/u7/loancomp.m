function newDebt = loancomp(debt,salary)
%NEW_DEBT Summary of this function goes here
%   Detailed explanation goes here
interest = 0.01;
paymentsize = 0.1;

newDebt = debt + debt*interest - paymentsize*salary;
end

