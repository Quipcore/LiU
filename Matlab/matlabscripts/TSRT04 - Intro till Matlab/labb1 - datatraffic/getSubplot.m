function getSubplot(years,traffic)
%%Returns a plot of two bargraphs, one with data side-by-side and one
%%stacked
subplot(2,1,1);
datatrafficBar(years,traffic)

subplot(2,1,2);
bar(years,traffic,"stacked")
title("Mobile data usage forcast")
xlabel("Years")
ylabel("Traffic (Petabytes)")
legend('Video','File transfer','Web and others')

%Makes sure the x-axis only shows integer numbers
ax = gca;
ax.XTick = unique( round(ax.XTick) );
end

