figure(1);
x = 1:1:1000;

y0 = 1000*x - 1840;
y1 = 1000*x - 12800;
y2 = 1.1*1000*x - 13600;
y3 = 0.9*1000*x - 20000;

y4 = 1000*x - 13600;
y5 = 1.1*1000*x - 16000;
y6 = 0.9*1000*x - 182400;



%plot(x,y0,x,y1, x,y2, x, y3, x, y4, x, y5, x, y6);
plot(x,y0, x,y1, x,y2, x, y3, x,y4, x,y5, x,y6);
%plot(x,y1, x,y2, x, y3, x, y4, x, y5, x, y6, x, y7);

title('Estimated Service Curves');
xlabel('Time (milliseconds)');
ylabel('Transmitted Data (bits)');
legend('Ideal Service Curve', 'experiment 1', 'experiment 2', 'experiment 3','experiment 4','experiment 5','experiment 6');
%legend('experiment 1', 'experiment 2', 'experiment 3', 'experiment 4', 'experiment 5', 'experiment 6', 'experiment');
%legend('Ideal Service Curve', 'experiment 1', 'experiment 2', 'experiment 3', 'experiment 4', 'experiment 5', 'experiment 6');