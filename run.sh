git pull && \
gradle distTar && \
rm ~/reinforcement-1.0-SNAPSHOT* -r && \
tar -xf ./build/distributions/reinforcement-1.0-SNAPSHOT.tar --directory ~ && \
nohup ~/reinforcement-1.0-SNAPSHOT/bin/reinforcement -i python3 &
tail nohup.out -f