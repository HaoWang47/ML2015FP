function [AmpData, FreqData, TimeData, SpecData, SampRate] = myParse(myFile, SampPerSec)

%% Preprocessing
% Import data file, and output as variable 'AudioData'
AudioDataTemp = audioread(myFile);
AudioData = AudioDataTemp(:,1);

% Extract audio information
AudioInfo = audioinfo(myFile);
SampRate = AudioInfo.SampleRate;
AudioLength = AudioInfo.Duration;

%% Output 'TimeAmpData'
% Subset AudioData for Amplitude
x = linspace(1,length(AudioData), ceil(AudioLength));
x = ceil(x);

u = length(AudioData);
v = ceil(SampPerSec*AudioLength);
w = ceil(u/(SampPerSec*AudioLength));
AmpData = zeros(v,1);

i = 1;
j = 1;

while ((i <= u) && (j<=v));
    mySubset = AudioData(i:min(i+w-1, u));
    [myMax, myInd] = max(abs(mySubset));
    AmpData(j) = mySubset(myInd);
    i = i + w;
    j = j + 1;
end

%% Output 'SpecData'
% Run spectrogram on audio file
SpecData = spectrogram(AudioData, SampRate);

%% Output 'TimeFreqData'
% Transform 'SpecData' into frequencies
Freq = abs(SpecData(:));

% Subset Frequency, 1 sample per second
% Method:Take the maximum frequency in each second
n = length(Freq);
m = ceil(SampPerSec*AudioLength);
k = ceil(n/(SampPerSec*AudioLength));
FreqData = zeros(m,1);
i = 1;
j = 1;

while ((i <= n) && (j<=m));
    FreqData(j) = max(Freq(i:min(i+k-1, n)));
    i = i + k;
    j = j + 1;
end

%% Add time variable for amplitude and frequency
TimeData = linspace(0,AudioLength, length(FreqData))';


%% Output TimeAmpData and TimeFreqData to .csv
newFile = strrep(myFile, '.mp3', '.csv');
csvwrite(newFile, [TimeData, AmpData, FreqData]);
    
