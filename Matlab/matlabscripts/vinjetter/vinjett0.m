t = tiledlayout(4,3);
title(t, "Vinjett funktioner")
range = 8;

resolution = 10.^3;
x = linspace(-range,range,resolution);

nexttile
A = sqrt(x.^2 + 2*x +1) +sqrt(x.^2 - 2*x +1);
plot(x,A)
title("sqrt(x^2 + 2x +1) + sqrt(x^2 - 2x + 1)")

nexttile
B = atan(x) + atan(1./x);
plot(x,B)
title("arctan(x) + arctan(1/x)")

nexttile
C = sin(1./sin(1./x));
plot(x,C)
title("sin(1/sin(1/x))")

nexttile
D = x.^2 .* sin(1./x);
plot(x,D)
title("x^2 * sin(1/x)")

nexttile
E = x .* sin(1./x);
plot(x,E)
title("x * sin(1/x)")

nexttile
F = sin(1./x);
plot(x,F)
title("sin(1/x)")

nexttile
G = sin(x);
plot(x,G)
title("sin(x)")

nexttile
H = cos(x);
plot(x,H)
title("cos(x)")

nexttile
I = tan(x);
plot(x,I)
title("tan(x)")

r = linspace(-1,1);
nexttile
J = asin(r);
plot(r,J)
title("arcsin(x)")

nexttile
K = acos(r);
plot(r,K)
title("arccos(x)")

nexttile
L = atan(x);
plot(x,L)
title("arctan(x)")