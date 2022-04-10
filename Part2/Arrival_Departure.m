clc;clear all;

%sink output
[packet_no_p3, arrival_time, departure_time] = textread('sinkOutputBB3.txt', '%f %f %f');
k = 1000;

num = 100
arrival_time = arrival_time/k;
time3 = zeros(1,num);
arrival3 = zeros(1,num);
packetsize_p3 = packet_no_p3 * 10;
time3(1) = arrival_time(1);
arrival3(1) = packetsize_p3(1);
i=2
while i<= num
    time3(i) = time3(i-1) + arrival_time(i);
    arrival3(i) = arrival3(i-1) + packetsize_p3(i);
    i=i+1;
end

figure(1);

plot(time3,arrival3, 'r' );
hold on;

clear time3;
clear arrival3;
%clear packetsize_p3;

departure_time = departure_time/k;
time3 = zeros(1,num);
arrival3 = zeros(1,num);
%packetsize_p3 = packet_no_p3 * 10;
time3(1) = departure_time(1);
arrival3(1) = packetsize_p3(1);
i=2;
while i<= num
    time3(i) = time3(i-1) + departure_time(i);
    arrival3(i) = arrival3(i-1) + packetsize_p3(i);
    i=i+1;
end
plot(time3,arrival3, 'b' );

legend('Arrival', 'Departure');
title('Arrival and Departure');
xlabel('time (in us)');
ylabel('packets arrival (in bytes)');







