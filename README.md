ビルド手順
=====

```
$ docker run -v `pwd`:/work -v m2:/root/.m2 -it --rm maven /bin/bash
# cd /work
# bash build.sh
# mvn clean install
# exit
```

```
$ docker run -v /var/run/docker.sock:/var/run/docker.sock -v `pwd`:/work -v m2:/root/.m2 -it --rm gradle /bin/bash
# cd /work/example
# gradle bootBuildImage
# exit
```

```
$ docker run -p 8080:8080 -it --rm example:0.1.0-SNAPSHOT
```

http://localhost:8080/test
