figure(1);
x = 1:1:1000;

k = 1000

y1 = 0.1*x*k - 13320;
y2 = 0.5*x*k - 301920;
y3 = 0.25*x*k - 26640;

y4 = 0.375*x*k - 115440;
y5 = 0.3*x*k - 35520;
y6 = 0.335*x*k - 39960;


plot(x,y1, x,y2, x, y3, x,y4, x,y5, x,y6);


title('Estimated Service Curves');
xlabel('Time (milliseconds)');
ylabel('Transmitted Data (bits)');
legend('experiment 1', 'experiment 2', 'experiment 3','experiment 4','experiment 5','experiment 6');

