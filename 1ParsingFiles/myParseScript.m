clear;
format short;

myExtension = '*.mp3';
myDir = dir(myExtension);
for i=1:length(myDir)
    myFile = myDir(i).name;
    myParse(myFile,10);
% Uncomment if want to plot amplitude and frequency
%     [a,b,c] = myParse(myFile,10);
%     figure(i); clf;
%     subplot(2,1,1);
%     plot(c,a);
%     
%     subplot(2,1,2);
%     plot(c,b);    
end
