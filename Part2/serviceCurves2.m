figure(1);
x = 1:1:1000;
k = 1000;

y0 = k*100*x - 9990000;

y1 = k*100* - 622400;
y2 = k*110*x - 409600;
y3 = k*90*x - 396000;

y4 = k*100*x - 1154400;
y5 = k*110*x - 1160000;
y6 = k*90*x - 1046400;


plot(x,y0, x,y1, x,y2, x,y3, x,y4, x,y5, x,y6);


title('Estimated Service Curves');
xlabel('Time (milliseconds)');
ylabel('Transmitted Data (bits)');
legend('Ideal Service Curve', 'experiment 1', 'experiment 2', 'experiment 3','experiment 4','experiment 5','experiment 6');
