x = 0:0.1:20;

t = 0;
roof = 2;
hold on
while roof > 1.2

div = exp(-t.*x)/sqrt(1-t.*t);
y = 1-div.*sin(x.*sqrt(1-t.*t) + acos(t));

roof = max(y)
t = t+0.01;
plot(x,y)
end 
hold off

t