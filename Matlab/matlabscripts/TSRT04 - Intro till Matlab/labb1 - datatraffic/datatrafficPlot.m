function datatrafficPlot(years,traffic)
%%Plots trafficdata over time (years)
plot(years,traffic)
title("Mobile data usage forcast")
xlabel("Years")
ylabel("Traffic (Petabytes)")
legend('video','file transfer','web and others')

%Makes sure the x-axis only shows integer numbers
ax = gca;
ax.XTick = unique( round(ax.XTick) );
end

