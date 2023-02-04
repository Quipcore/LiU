
xmin = 0;
xmax = 30;
hold on
for t = 0.1:0.2:0.9
    curveplot(xmin,xmax,t)
    %curveplot(xmin,xmax,10)
end
hold off