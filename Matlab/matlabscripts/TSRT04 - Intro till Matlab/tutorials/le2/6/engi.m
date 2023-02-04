function [carsInLin, carsInNorr, carsInNy] = engi(lin,norr, ny, months)

carsInLin = [lin];
carsInNorr = [norr];
carsInNy = [ny];
total = lin + norr+ny;

linToLin = 0.7;
linToNorr = 0.1;
linToNy = 1- linToLin - linToNorr;

norrToLin = 0.7;
norrToNorr = 0.1;
norrToNy = 1 - norrToLin - norrToNorr;

nyToLin = 0.3;
nyToNorr = 0.1;
nyToNy = 1 - nyToLin-nyToNorr;

for month = 1:months
  
   cIL = carsInLin(end)*linToLin + norrToLin*carsInNorr(end) + carsInNy(end)*nyToLin;
   cIN = carsInLin(end)*linToNorr + norrToNorr*carsInNorr(end)+ carsInNy(end)*nyToNorr;
   cIY = carsInLin(end)*linToNy + norrToNy*carsInNorr(end)+ carsInNy(end)*nyToNy; 


   carsInLin(end+1) = cIL;
   carsInNorr(end+1) = cIN;
   carsInNy(end+1) = cIY;

   disp(carsInLin(end))
end

x = 0:months;
plot(x,carsInNorr, x,carsInLin, x,carsInNy);
xlabel('Months')
ylabel('cars')