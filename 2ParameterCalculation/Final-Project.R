library(data.table)
library(ggplot2)

#November 2015
nov15.1 <- fread("Data File for non-Top Songs/1 Adele - Hello.csv", header = FALSE)
setnames(nov15.1, names(nov15.1), c("Time", "Amp", "Freq"))
nov15.1[, Rank := "1"]
nov15.21 <- fread("Data File for non-Top Songs/21 X Ambassadors - Renegades (Official Video).csv", header = FALSE)
setnames(nov15.21, names(nov15.21), c("Time", "Amp", "Freq"))
nov15.21[, Rank := "21"]
nov15.41 <- fread("Data File for non-Top Songs/41 Fetty Wap - Again LYRICS.csv", header = FALSE)
setnames(nov15.41, names(nov15.41), c("Time", "Amp", "Freq"))
nov15.41[, Rank := "41"]
nov15.61 <- fread("Data File for non-Top Songs/61 DLOW - Bet You Can't Do It Like Me Challenge.csv", header = FALSE)
setnames(nov15.61, names(nov15.61), c("Time", "Amp", "Freq"))
nov15.61[, Rank := "61"]
nov15.81 <- fread("Data File for non-Top Songs/81 Demi Lovato - Cool for the Summer (Official Video).csv", header = FALSE)
setnames(nov15.81, names(nov15.81), c("Time", "Amp", "Freq"))
nov15.81[, Rank := "81"]

nov15 <- rbindlist(list(nov15.1, nov15.21, nov15.41, nov15.61, nov15.81))

#October 2015
oct15.1 <- fread("Data File for non-Top Songs/1 The Weeknd - The Hills.csv", header = FALSE)
setnames(oct15.1, names(oct15.1), c("Time", "Amp", "Freq"))
oct15.1[, Rank := "1"]
oct15.21 <- fread("Data File for non-Top Songs/21 Fetty Wap - Come My Way (feat. Drake) + download link.csv", header = FALSE)
setnames(oct15.21, names(oct15.21), c("Time", "Amp", "Freq"))
oct15.21[, Rank := "21"]
oct15.41 <- fread("Data File for non-Top Songs/41 Travis Scott - Antidote.csv", header = FALSE)
setnames(oct15.41, names(oct15.41), c("Time", "Amp", "Freq"))
oct15.41[, Rank := "41"]
oct15.61 <- fread("Data File for non-Top Songs/61 Carrie Underwood - Smoke Break (Lyric Video).csv", header = FALSE)
setnames(oct15.61, names(oct15.61), c("Time", "Amp", "Freq"))
oct15.61[, Rank := "61"]
oct15.81 <- fread("Data File for non-Top Songs/81 Gonna - Blake Shelton (OFFICIAL LYRICS).csv", header = FALSE)
setnames(oct15.81, names(oct15.81), c("Time", "Amp", "Freq"))
oct15.81[, Rank := "81"]

oct15 <- rbindlist(list(oct15.1, oct15.21, oct15.41, oct15.61, oct15.81))


#Amplitude
ggplot(nov15, aes(x = Time, y = Amp, group = Rank)) + geom_line(aes(color = Rank)) + labs(title = "November 2015")
ggplot(oct15, aes(x = Time, y = Amp, group = Rank)) + geom_line(aes(color = Rank)) + labs(title = "October 2015")

#Frequency
ggplot(nov15, aes(x = Time, y = Freq, group = Rank)) + geom_line(aes(color = Rank)) + labs(title = "November 2015")
ggplot(oct15, aes(x = Time, y = Freq, group = Rank)) + geom_line(aes(color = Rank)) + labs(title = "October 2015")

##########Calculating parameters##########
freq.sum <- function(freq) {
  n = length(freq)
  frames = NULL
  for(i in seq(1, n, 10)) {
    if((i+30) < n) {
      frames = c(frames, sum(freq[i:(i+30)])) 
    }
  }
  return(frames)
}
amp.sum <- function(amp) {
  n = length(amp)
  frames = NULL
  for(i in seq(1, n, 10)) {
    if((i+30) < n) {
      frames = c(frames, sum(abs(amp[i:(i+30)])))
    }
  }
  return(frames)
}
freq.diff.sum <- function(freq) {
  n = length(freq)
  frames = NULL
  for(i in seq(1, n, 10)) {
    if((i+30) < n) {
      sum = 0
      for(j in seq(i+1, i+30)) {
        sum = sum + abs(freq[j] - freq[j-1])
      }
      frames = c(frames, sum)
    }
  }
  return(frames)
}
amp.diff.sum1 <- function(amp) {
  n = length(amp)
  frames = NULL
  for(i in seq(1, n, 10)) {
    if((i+30) < n) {
      sum = 0
      for(j in seq(i+1, i+30)) {
        sum = sum + abs(amp[j] - amp[j-1])
      }
      frames = c(frames, sum)
    }
  }
  return(frames)
}
amp.diff.sum2 <- function(amp) {
  n = length(amp)
  frames = NULL
  for(i in seq(1, n, 10)) {
    if((i+30) < n) {
      sum = 0
      for(j in seq(i+1, i+30)) {
        sum = sum + abs(abs(amp[j]) - abs(amp[j-1]))
      }
      frames = c(frames, sum)
    }
  }
  return(frames)
}
freq.outliers <- function(freq) {
  n = length(freq)
  frames = NULL
  cutoff = mean(freq) + sqrt(var(freq))
  for(i in seq(1, n, 10)) {
    if((i+30) < n) {
      frames = c(frames, sum(freq[i:(i+30)] > cutoff)) 
    }
  }
  return(frames)
}
small.amp <- function(amp) {
  n = length(amp)
  frames = NULL
  for(i in seq(1, n, 10)) {
    if((i+30) < n) {
      frames = c(frames, sum(abs(amp[i:(i+30)]) < 0.5)/31)
    }
  }
  return(frames)
}

##########Make csv's##########
songs <- c("Data File for non-Top Songs/1 - Mark Ronson - Uptown Funk ft. Bruno Mars.csv",
           "Data File for non-Top Songs/1 - Taylor Swift - Blank Space.csv",
           "Data File for non-Top Songs/1 Adele - Hello.csv",
           "Data File for non-Top Songs/1 OMI - Cheerleader LYRICS (Felix Jaehn Edit).csv",
           "Data File for non-Top Songs/1 The Weeknd - Can't Feel My Face.csv",
           "Data File for non-Top Songs/1 The Weeknd - The Hills.csv",
           "Data File for non-Top Songs/21 - Calvin Harris - Blame feat. John Newman (Lyric Video).csv",
           "Data File for non-Top Songs/21 - ILOVEMAKONNEN (FEAT. DRAKE) - TUESDAY.csv",
           "Data File for non-Top Songs/21 Big Sean - I Don't Fuck With You (Lyric Video) (Explicit) ft. E-40.csv",
           "Data File for non-Top Songs/21 Chains - Nick Jonas (Lyrics Video).csv",
           "Data File for non-Top Songs/21 Fetty Wap - Come My Way (feat. Drake) + download link.csv",
           "Data File for non-Top Songs/21 Omarion ft. Chris Brown _ Jhene Aiko Post To Be Lyrics.csv",
           "Data File for non-Top Songs/21 Rihanna - Bitch Better Have My Money (Lyrics).csv",
           "Data File for non-Top Songs/21 Shut up and Dance - Walk The Moon (Lyrics).csv",
           "Data File for non-Top Songs/21 Thinking Out Loud by  Ed Sheeran (LYRICS) Album Version.csv",
           "Data File for non-Top Songs/21 Wiz Khalifa - See You Again ft. Charlie Puth [Official Video] Furious 7 Soundtrack.csv",
           "Data File for non-Top Songs/21 X Ambassadors - Renegades (Official Video).csv",
           "Data File for non-Top Songs/41 - Fergie - L.A.LOVE (la la) (Pseudo Video).csv",
           "Data File for non-Top Songs/41 - Something in The Water - Carrie Underwood (Lyrics).csv",
           "Data File for non-Top Songs/41 Ed Sheeran - Photograph (Lyric).csv",
           "Data File for non-Top Songs/41 Fetty Wap - Again LYRICS.csv",
           "Data File for non-Top Songs/41 Fetty Wap - Trap Queen (lyrics).csv",
           "Data File for non-Top Songs/41 House Party - Sam Hunt.csv",
           "Data File for non-Top Songs/41 Nasty lyrics x Bandit Gang Marco ft. Dro.csv",
           "Data File for non-Top Songs/41 Nicki Minaj - Only (Lyric) ft. Drake, Lil Wayne, Chris Brown.csv",
           "Data File for non-Top Songs/41 SUGAR - MAROON 5 ( LYRICS).csv",
           "Data File for non-Top Songs/41 Travis Scott - Antidote.csv",
           "Data File for non-Top Songs/61 - Alesso - Heroes _ Lyrics.csv",
           "Data File for non-Top Songs/61 - Luke Bryan - I See You (Lyric Video).csv",
           "Data File for non-Top Songs/61 - Nicki Minaj - Feeling Myself (Audio) ft. Beyonce虂.csv",
           "Data File for non-Top Songs/61 (Lyrics) Heartbeat Song - Kelly Clarkson.csv",
           "Data File for non-Top Songs/61 5 Seconds of Summer - She's Kinda Hot (Lyric Video).csv",
           "Data File for non-Top Songs/61 Carrie Underwood - Smoke Break (Lyric Video).csv",
           "Data File for non-Top Songs/61 Charlie Puth - Marvin Gaye ft. Meghan Trainor [Official Video].csv",
           "Data File for non-Top Songs/61 Dillon Francis, DJ Snake - Get Low.csv",
           "Data File for non-Top Songs/61 DLOW - Bet You Can't Do It Like Me Challenge.csv",
           "Data File for non-Top Songs/61 Lose My Mind Lyrics - Brett Eldredge.csv",
           "Data File for non-Top Songs/61 Rae Sremmurd ft Nicki Minaj Young Thug - Throw Sum Mo (lyrics by @koukibouziane ).csv",
           "Data File for non-Top Songs/61 The Weeknd - Often (NSFW).csv",
           "Data File for non-Top Songs/61 Thomas Rhett - Crash and Burn (Lyric Version).csv",
           "Data File for non-Top Songs/81 - J-Cole - Apparently ( Lyrics Video).csv",
           "Data File for non-Top Songs/81 - Luke Bryan Roller Coaster - Lyrics.csv",
           "Data File for non-Top Songs/81 - Matt McAndrew - Make It Rain - Studio Version - The Voice 7.csv",
           "Data File for non-Top Songs/81 Chris Young - I'm Comin' Over.csv",
           "Data File for non-Top Songs/81 Darius Rucker- Homegrown Honey (Lyrics).csv",
           "Data File for non-Top Songs/81 Demi Lovato - Cool for the Summer (Official Video).csv",
           "Data File for non-Top Songs/81 Dierks Bentley - Say You Do (with lyrics) [NEW SINGLE 2014].csv",
           "Data File for non-Top Songs/81 DJ Khaled - How many times Lyrics ft Chris Brown , Lil Wayne _ Big Sean.csv",
           "Data File for non-Top Songs/81 Gonna - Blake Shelton (OFFICIAL LYRICS).csv",
           "Data File for non-Top Songs/81 Jidenna-Classic Man (Lyrics).csv",
           "Data File for non-Top Songs/81 Michael Ray - Kiss You in the Morning (lyrics).csv",
           "Data File for non-Top Songs/81 Nothin' Like You Dan and Shay Lyrics.csv")
for(i in 1:length(songs)) {
  data.song = fread(songs[i], header = FALSE)
  setnames(data.song, names(data.song), c("Time", "Amp", "Freq"))
  frames.song = data.table(FreqSum = freq.sum(data.song$Freq),
                           AmpSum = amp.sum(data.song$Amp),
                           FreqDiffSum = freq.diff.sum(data.song$Freq),
                           AmpDiffSum1 = amp.diff.sum1(data.song$Amp),
                           AmpDiffSum2 = amp.diff.sum2(data.song$Amp),
                           FreqOutliers = freq.outliers(data.song$Freq),
                           SmallAmp = small.amp(data.song$Amp))
  write.table(frames.song, file = paste('Data Parameters/', substr(songs[i], 29, nchar(songs[i]))), row.names = FALSE, col.names = FALSE, sep = ",")
}
