x = -10:0.001:10;

y = (exp(1i*x)-exp(-1i*x))/(2*1i)

plot(x,y,x,sin(x))
