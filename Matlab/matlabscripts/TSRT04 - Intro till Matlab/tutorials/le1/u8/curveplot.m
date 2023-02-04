function [fmin,fmax] = curveplot(xmin,xmax)

resolution = 1/100;

x = xmin:resolution:xmax;

y = (x.^3)./((x.^2)-2.*abs(x-2));

fmin = min(y)
fmax = max(y)

plot(x,y)
grid
axis
title("f(x) = x³/x²-2|x-2|")
xlabel("x")
ylabel("y")
end

