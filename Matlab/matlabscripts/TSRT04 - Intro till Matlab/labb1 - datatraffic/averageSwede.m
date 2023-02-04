function averageSwede(years,traffic)
%%Plots datatraffic over years scaled with the swedish population. no
%%return value

swedes = 9.8e6;
gigabytesInPetabytes = 1e15/1e9; % 15-9 = 6, -> 1e6, 
scaledTraffic = traffic*gigabytesInPetabytes/swedes;

plot(years,scaledTraffic)
title("Mobile data usage forcast")
xlabel("Years")
ylabel("Traffic used by averge swede(Gigabytes)")
legend('video','file transfer','web and others')

%Makes sure the x-axis only shows integer numbers
ax = gca;
ax.XTick = unique( round(ax.XTick) );
end


%Data seems relative small besides my own phone data usage
