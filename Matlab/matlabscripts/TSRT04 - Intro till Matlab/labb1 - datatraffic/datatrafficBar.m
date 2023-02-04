function datatrafficBar(years,traffic)
%%Plots a bargraph of the traffic data over time

bar(years,traffic)
title("Mobile data usage forcast")
xlabel("Years")
ylabel("Traffic (Petabytes)")
legend('Video','File transfer','Web and others')

%Makes sure the x-axis only shows integer numbers
ax = gca;
ax.XTick = unique( round(ax.XTick) );
end

