function [fmin,fmax] = curveplot(xmin,xmax,t)

resolution = 1/100;

x = xmin:resolution:xmax;


div = exp(-t.*x)/sqrt(1-t*t);

y = 1-div.*sin(x.*sqrt(1-t.*t)+acos(t));

fmin = min(y)
fmax = max(y)

plot(x,y)
grid
axis
xlabel("x")
ylabel("y")
end