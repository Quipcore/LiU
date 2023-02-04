function averageSwedeWithStyle(years,traffic)
%Plots datatraffic scaled to the swedish population, and then styles the
%result
swedes = 9.8e6;
gigabytesInPetabytes = 1e15/1e9; % 15-9 = 6, -> 1e6, this does not take into consideration 1024
trafficInGigabytes = traffic*gigabytesInPetabytes;
scaledTraffic = trafficInGigabytes/swedes;

totalTraffic = 0;
for i = 1:3
    totalTraffic = totalTraffic + scaledTraffic(:, i);
end

width = 1.5;
hold on
plot(years,totalTraffic,'LineStyle','--', 'Color', 'm', LineWidth=width) %g -> bad colours
plot(years,scaledTraffic(:, 1),'LineStyle','-.', 'Color', 'k', LineWidth=width)
plot(years,scaledTraffic(:, 2),'LineStyle',':', 'Color', 'b', LineWidth=width)
plot(years,scaledTraffic(:, 3),'LineStyle','-', 'Color', 'r', LineWidth=width)
hold off

title("Mobile data usage forcast")
xlabel("Years")
ylabel("Traffic used by averge swede(Gigabytes)")
legend('Total','Video','File transfer','Web and others') %Not 100% sure everything is in the right oreder
%Makes sure the x-axis only shows integer numbers
ax = gca;
ax.XTick = unique( round(ax.XTick) );

end

