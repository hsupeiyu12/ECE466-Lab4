clc;clear all;close all;
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%Reading data from a file
%Note that time is in micro seconds and packetsize is in Bytes
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
[seqno, send, receive] = textread('sink.out', '%f %f %f');

figure(1);
plot(seqno, send / 1000);
hold on;
plot(seqno, receive / 1000);
hold off;
title('Packet Train N=100, L=400, r=1000');
xlabel('Sequence Number');
ylabel('Timestamp (ms)');
legend('Send', 'Receive');