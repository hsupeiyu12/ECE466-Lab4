figure(1);
x = 1:1:1000;
k = 1000;

y0 = k*1000*x - 9990000;

y1 = k*1000* - 748000;
y2 = k*1100*x - 483200;
y3 = k*900*x - 435200;

y4 = k*1000*x - 503200;
y5 = k*1100*x - 474400;
y6 = k*900*x - 976000;


plot(x,y0, x,y1, x,y2, x,y3, x,y4, x,y5, x,y6);


title('Estimated Service Curves');
xlabel('Time (milliseconds)');
ylabel('Transmitted Data (bits)');
legend('Ideal Service Curve', 'experiment 1', 'experiment 2', 'experiment 3','experiment 4','experiment 5','experiment 6');
